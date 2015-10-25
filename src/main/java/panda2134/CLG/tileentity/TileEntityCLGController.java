package panda2134.CLG.tileentity;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import panda2134.CLG.init.Blocks;
import panda2134.CLG.network.CLGFormedMessage;
import panda2134.CLG.network.CLGPacketHandler;
import panda2134.CLG.util.CLGReference;
import panda2134.CLG.util.GeneratorMultiblockHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCLGController extends TileEntityBase implements IUpdatePlayerListBox{
	public double generating,outputLimit,output,storage;
	public int timeToCheck;
	public boolean init;
	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		generating=nbt.getDouble("generating");
		outputLimit=nbt.getDouble("outputLimit");
		output=nbt.getDouble("output");
		storage=nbt.getDouble("storage");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setDouble("generating",generating);
		nbt.setDouble("outputLimit",outputLimit);
		nbt.setDouble("output", output);
		nbt.setDouble("storage",storage);
	}
	
	public void onHitByHammer(EntityPlayer player){
		//this.update();
		if(!formed){
			player.addChatComponentMessage(new ChatComponentText("§cStructure Incorrect!"));
			player.addChatComponentMessage(new ChatComponentText("§cMust use in Nether!"));
		}else{
			player.addChatMessage(new ChatComponentText(
					"§6"+
					"Generating:"+this.generating+
					"     "
					+"Stroage:"+this.storage+
					"     "
					+"Output:"+this.output));
		}
	}
	
	public boolean isStructureComplete() {
		
		World world=this.getWorldObj();
		boolean state=
				GeneratorMultiblockHelper.checkPattern(CLGReference.CLGPattern,
						world, xCoord, yCoord, zCoord,
						1, 1, 0,
						Blocks.blockController.getUnlocalizedName());
		return state;
	}
	
	@Override
	public void update(){
		this.updateEntity();
	}
	
	public void updateEntity(){
		if(worldObj.isRemote){
			worldObj.markBlockRangeForRenderUpdate(
					xCoord-10, yCoord-10, zCoord-10, xCoord+10, yCoord+10, zCoord+10);
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			return;
		}else{
			this.updateState();
			//set generators
			this.generating=GeneratorMultiblockHelper.countBlocksInRange(worldObj,
							CLGReference.CLGPattern,
							xCoord, yCoord, zCoord, 1, 1, 0,
							Blocks.blockLavaGenerator.getUnlocalizedName(), 
							this.formed)*CLGReference.unitPerGenerator;
			//set output limit
			int countOfHatch=GeneratorMultiblockHelper.countBlocksInRange(worldObj,
					CLGReference.CLGPattern,
					xCoord, yCoord, zCoord, 1, 1, 0,
					Blocks.blockEnergyHatch.getUnlocalizedName(), 
					this.formed);
			this.outputLimit=countOfHatch*CLGReference.unitPerHatch;
			//add generating to storage
			if(this.storage<=CLGReference.controllerStorage)
				this.storage+=this.generating;
			//set this.output
			if(this.storage-this.outputLimit>0)
				this.output=this.outputLimit;
			else
				this.output=this.storage;
			//output
			//TODO

			this.storage-=GeneratorMultiblockHelper.outputToHatch
			(worldObj, CLGReference.CLGPattern, output,
					countOfHatch, xCoord, yCoord, zCoord,
					1, 1, 0, formed);
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}
	public void updateState(){
		if(worldObj.isRemote)
			return;
		formed=this.isStructureComplete() && worldObj.provider.isHellWorld; //must in nether
		if(!formed)
			this.storage=0.0D;
		this.sendChange();

	}
	
	public void sendChange(){
		int meta;
		if(this.formed)
			meta=worldObj.getBlockMetadata(xCoord, yCoord, zCoord) | 8;
		else
			meta=worldObj.getBlockMetadata(xCoord, yCoord, zCoord) & 7;
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, meta, 2);
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}
}
