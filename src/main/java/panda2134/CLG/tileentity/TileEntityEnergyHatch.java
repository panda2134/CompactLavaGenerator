package panda2134.CLG.tileentity;

import factorization.api.Charge;
import factorization.api.Coord;
import factorization.api.IChargeConductor;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import panda2134.CLG.integration.EnergySwitch;
import panda2134.CLG.integration.EnergyUnit;
import panda2134.CLG.integration.FZModule;
import panda2134.CLG.integration.IC2Module;
import panda2134.CLG.integration.RFEnergyModule;
import panda2134.CLG.util.CLGReference;
import panda2134.CLG.util.ChatStyles;
import panda2134.CLG.util.Mods;
import cofh.api.energy.IEnergyConnection;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyReceiver;
import cpw.mods.fml.common.Optional.Interface;
import cpw.mods.fml.common.Optional.InterfaceList;
import cpw.mods.fml.common.Optional.Method;

@InterfaceList({
		@Interface(iface = "ic2.api.energy.tile.IEnergySource", modid = Mods.IDs.ic2api),
		@Interface(iface = "cofh.api.energy.IEnergyConnection", modid = Mods.IDs.cofhApiEnergy),
		@Interface(iface = "cofh.api.energy.IEnergyHandler", modid = Mods.IDs.cofhApiEnergy),
		@Interface(iface = "factorization.api.IChargeConductor", modid = Mods.IDs.fz) })
public class TileEntityEnergyHatch extends TileEntity implements IEnergySource,
		IEnergyConnection, IEnergyHandler, IChargeConductor {

	private boolean init;
	public double energyToOutput;
	public EnergyUnit unit;
	public Object charge;

	public TileEntityEnergyHatch() {
		if (Mods.Factorization.available)
			initCharge();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.tileentity.TileEntity#readFromNBT(net.minecraft.nbt.
	 * NBTTagCompound)
	 */
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		String strUnit = nbt.getString("unit");
		if (strUnit == null)
			unit = EnergyUnit.EU;
		unit = EnergyUnit.valueOf(strUnit);
		if (Mods.Factorization.available && charge != null)
			((Charge) charge).readFromNBT(nbt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.tileentity.TileEntity#writeToNBT(net.minecraft.nbt.
	 * NBTTagCompound)
	 */
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setString("unit", unit.unit);

		if (Mods.Factorization.available && charge != null)
			((Charge) charge).writeToNBT(nbt);
	}

	@Override
	@Method(modid = Mods.IDs.ic2api)
	public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction) {
		if (this.getBlockMetadata() == direction.ordinal())
			if (this.unit != null && this.unit.equals(EnergyUnit.EU))
				return true;
		return false;
	}

	@Override
	@Method(modid = Mods.IDs.ic2api)
	public double getOfferedEnergy() {
		if (unit != null && unit.equals(EnergyUnit.EU))
			return IC2Module.getOutput(energyToOutput);
		return 0.0F;
	}

	@Override
	@Method(modid = Mods.IDs.ic2api)
	public int getSourceTier() {
		return 3;
	}

	@Override
	public void updateEntity() {
		if (!init) {
			init = true;
			this.doInit();
			return;
		}
		if (Mods.Factorization.available)
			refreshCG();
		if (unit != null && unit.equals(EnergyUnit.RF)
				&& Mods.cofhApiEnergy.available)
			outputRF();
	}

	private void doInit() {
		if (!CLGReference.isServer())
			return;
		if (Mods.IC2.available) {
			// ic2 compat
			loadTile();
		}

		if (unit == null)
			unit = EnergyUnit.EU;

		worldObj.notifyBlockChange(xCoord, yCoord, zCoord, getBlockType());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.tileentity.TileEntity#invalidate()
	 */
	@Override
	public void invalidate() {
		if (!CLGReference.isServer())
			return;
		if (Mods.IC2.available)
			unloadTile();
		super.invalidate();
	}

	@Method(modid = Mods.IDs.ic2api)
	public void loadTile() {
		MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
	}

	@Method(modid = Mods.IDs.ic2api)
	public void unloadTile() {
		MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
	}

	@Override
	@Method(modid = Mods.IDs.ic2api)
	public void drawEnergy(double amount) {
		if (unit != null && this.unit.equals(EnergyUnit.EU))
			this.energyToOutput = 0;
	}

	public void displayInfoOnChat(EntityPlayer player) {
		player.addChatComponentMessage(new ChatComponentTranslation(
				"tile.CLGEnergyHatch.info", unit.unit)
				.setChatStyle(ChatStyles.info));
	}

	public void onHitByHammer(EntityPlayer player) {
		if (worldObj.isRemote)
			return;
		if (player.isSneaking()) {
			unit = EnergySwitch.switchUnit(unit);
			worldObj.notifyBlockChange(xCoord, yCoord, zCoord, getBlockType());
		}
		displayInfoOnChat(player);
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return (from.ordinal() == this.getBlockMetadata())
				&& (this.unit == null || this.unit.equals(EnergyUnit.RF));
	}

	@Method(modid = Mods.IDs.cofhApiEnergy)
	public void outputRF() {
		if ((energyToOutput > 0) && unit.equals(EnergyUnit.RF)) {
			TileEntity tile = worldObj.getTileEntity(
					xCoord
							+ ForgeDirection.getOrientation(this
									.getBlockMetadata()).offsetX,
					yCoord
							+ ForgeDirection.getOrientation(this
									.getBlockMetadata()).offsetY,
					zCoord
							+ ForgeDirection.getOrientation(this
									.getBlockMetadata()).offsetZ);
			ForgeDirection side = ForgeDirection.getOrientation(this
					.getBlockMetadata());
			if (tile != null
					&& ((tile instanceof IEnergyHandler)
							|| tile instanceof IEnergyConnection || tile instanceof IEnergyReceiver)
					&& canReceiveRFPower(tile, side.getOpposite())) {
				((IEnergyReceiver) tile).receiveEnergy(side.getOpposite(),
						(int) RFEnergyModule.getOutput(this.energyToOutput),
						false);
			}
		}
	}

	@Method(modid = Mods.IDs.cofhApiEnergy)
	private boolean canReceiveRFPower(TileEntity tile, ForgeDirection side) {
		if (tile instanceof IEnergyReceiver) {
			IEnergyReceiver h = (IEnergyReceiver) tile;
			return h.canConnectEnergy(side);
		}
		return false;
	}

	@Method(modid = Mods.IDs.fz)
	public void refreshCG() {
		if (this.charge != null)
			((Charge) charge).update();
		if (unit != null && unit == EnergyUnit.Charge) {
			((Charge) this.charge).setValue((int) FZModule
					.getOutput(energyToOutput));
		} else if (this.charge != null && this.charge instanceof Charge)
			((Charge) this.charge).deplete();
		else if (this.charge == null)
			charge = new Charge(this);
	}

	@Override
	@Method(modid = Mods.IDs.cofhApiEnergy)
	public int getEnergyStored(ForgeDirection from) {
		return 0;
	}

	@Override
	@Method(modid = Mods.IDs.cofhApiEnergy)
	public int getMaxEnergyStored(ForgeDirection from) {
		return 0;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive,
			boolean simulate) {
		return 0;
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract,
			boolean simulate) {
		return 0;
	}

	@Method(modid = Mods.IDs.fz)
	@Override
	public Coord getCoord() {
		return new Coord(this);
	}

	@Method(modid = Mods.IDs.fz)
	@Override
	public String getInfo() {
		return null;
	}

	@Method(modid = Mods.IDs.fz)
	@Override
	public Charge getCharge() {
		return (Charge) charge;
	}

	@Method(modid = Mods.IDs.fz)
	public void initCharge() {
		charge = new Charge(this);
	}

}
