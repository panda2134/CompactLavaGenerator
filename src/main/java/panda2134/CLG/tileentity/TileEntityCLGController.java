package panda2134.CLG.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import panda2134.CLG.init.Blocks;
import panda2134.CLG.util.CLGReference;
import panda2134.CLG.util.ChatStyles;
import panda2134.CLG.util.GeneratorMultiblockHelper;

public class TileEntityCLGController extends TileEntityBase implements
		IUpdatePlayerListBox {
	public double generating, outputLimit, output, storage;
	public int timeToCheck;
	public boolean deconstruct = false;

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		generating = nbt.getDouble("generating");
		outputLimit = nbt.getDouble("outputLimit");
		output = nbt.getDouble("output");
		storage = nbt.getDouble("storage");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setDouble("generating", generating);
		nbt.setDouble("outputLimit", outputLimit);
		nbt.setDouble("output", output);
		nbt.setDouble("storage", storage);
	}

	public void onHitByHammer(EntityPlayer player) {
		if (!formed) {
			sendErrorMessage(player);
		} else {
			sendInfo(player);
		}
	}

	private void sendInfo(EntityPlayer player) {
		player.addChatComponentMessage(new ChatComponentText(
				"===============================================")
				.setChatStyle(ChatStyles.info));
		player.addChatMessage(new ChatComponentTranslation(
				"tile.CLGController.info", generating, storage, output)
				.setChatStyle(ChatStyles.info));
		player.addChatComponentMessage(new ChatComponentText(
				"===============================================")
				.setChatStyle(ChatStyles.info));
	}

	private void sendErrorMessage(EntityPlayer player) {
		player.addChatComponentMessage(new ChatComponentText(
				"=================================")
				.setChatStyle(ChatStyles.error));
		player.addChatComponentMessage(new ChatComponentTranslation(
				"tile.CLGController.notok.0").setChatStyle(ChatStyles.error));
		player.addChatComponentMessage(new ChatComponentTranslation(
				"tile.CLGController.notok.1").setChatStyle(ChatStyles.error));
		player.addChatComponentMessage(new ChatComponentTranslation(
				"tile.CLGController.notok.2").setChatStyle(ChatStyles.error));
		if (CLGReference.mustUseInNether)
			player.addChatComponentMessage(new ChatComponentTranslation(
					"tile.CLGController.notok.3")
					.setChatStyle(ChatStyles.error));
		player.addChatComponentMessage(new ChatComponentTranslation(
				"tile.CLGController.notok.4").setChatStyle(ChatStyles.error));
		player.addChatComponentMessage(new ChatComponentText(
				"=================================")
				.setChatStyle(ChatStyles.error));
	}

	public boolean isStructureComplete() {

		World world = this.getWorldObj();

		boolean state = GeneratorMultiblockHelper.checkPattern(
				CLGReference.CLGPattern, world, xCoord, yCoord, zCoord, 1, 1,
				0, Blocks.blockController.getUnlocalizedName());

		return state;
	}

	@Override
	public void update() {
		this.updateEntity();
	}

	public void updateEntity() {
		if (worldObj.isRemote) {
			worldObj.markBlockRangeForRenderUpdate(xCoord - 10, yCoord - 10,
					zCoord - 10, xCoord + 10, yCoord + 10, zCoord + 10);
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			return;
		} else {
			if (deconstruct)
				return;
			this.updateState();
			// set generators
			this.generating = GeneratorMultiblockHelper.countBlocksInRange(
					worldObj, CLGReference.CLGPattern, xCoord, yCoord, zCoord,
					1, 1, 0, Blocks.blockLavaGenerator.getUnlocalizedName(),
					this.formed)
					* CLGReference.unitPerGenerator;
			// set output limit
			int countOfHatch = GeneratorMultiblockHelper.countBlocksInRange(
					worldObj, CLGReference.CLGPattern, xCoord, yCoord, zCoord,
					1, 1, 0, Blocks.blockEnergyHatch.getUnlocalizedName(),
					this.formed);
			this.outputLimit = countOfHatch * CLGReference.unitPerHatch;
			// add generating to storage
			if (this.storage <= CLGReference.controllerStorage)
				this.storage += this.generating;
			// set this.output
			if (this.storage - this.outputLimit > 0)
				this.output = this.outputLimit;
			else
				this.output = this.storage;
			this.storage -= GeneratorMultiblockHelper.outputToHatch(worldObj,
					CLGReference.CLGPattern, output, countOfHatch, xCoord,
					yCoord, zCoord, 1, 1, 0, formed);
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}

	public void updateState() {
		if (worldObj.isRemote)
			return;
		if (timeToCheck++ == 20)
			timeToCheck = 0;
		else
			return;
		if (CLGReference.mustUseInNether) {
			formed = this.isStructureComplete()
					&& worldObj.provider.isHellWorld // must in nether
					&& !this.worldObj.isBlockIndirectlyGettingPowered(xCoord,
							yCoord, zCoord);
		} else {
			formed = this.isStructureComplete()
					&& !this.worldObj.isBlockIndirectlyGettingPowered(xCoord,
							yCoord, zCoord);
		}
		if (!formed)
			this.storage = 0.0D;
		this.sendChange();
	}

	public void sendChange() {
		int meta;
		if (this.formed)
			meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord) | 8;
		else
			meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord) & 7;
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, meta, 2);
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}
}
