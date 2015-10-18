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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCLGController extends TileEntityBase {
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
		boolean formed=this.isStructureComplete();
		if(!formed){
			player.addChatComponentMessage(new ChatComponentText("Structure Incorrect!"));
		}else{
			player.addChatMessage(new ChatComponentText(
					"Generating:"+this.generating+
					"     "
					+"Output:"+this.output));
		}
	}
	
	public boolean isStructureComplete() {
		String[][][] pattern=new String[][][]{
			{{"L","L","L"},{"L","L","L"},{"L","L","L"}},
			{{"B","B","B"},{"B","P","B"},{"B","B","B"}},
			{{"B","B","B"},{"B","A","B"},{"B","B","B"}},
			{{"B","B","B"},{"B","A","B"},{"B","B","B"}},
			{{"B","B","B"},{"B","B","B"},{"B","B","B"}}
	};
		World world=this.getWorldObj();
		boolean state=
				GeneratorMultiblockHelper.checkPattern(pattern, world, xCoord, yCoord, zCoord,
						1, 1, 0,
						Blocks.blockController.getUnlocalizedName());
		return state;
	}
	
	@Override
	public void updateEntity(){
		if(worldObj.isRemote){
			worldObj.markBlockRangeForRenderUpdate(
					xCoord-10, yCoord-10, zCoord-10, xCoord+10, yCoord+10, zCoord+10);
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}else{
			updateState();
			//enable generators if formed
			
			//disable generators if not formed
			
			//set output limit
			
			//add generating to storage
			
			//output and set this.output
		}
	}
	
	public void updateState(){
		if(worldObj.isRemote)
			return;
		formed=this.isStructureComplete();
		this.sendChange();

	}
	
	public void sendChange(){
		/*
		timeToCheck++;
		if(timeToCheck==10)
			timeToCheck=0;
		else
			return;
		*/
		
		//CLGPacketHandler.INSTANCE
		//.sendToAllAround(new CLGFormedMessage(formed,xCoord,yCoord,zCoord),
		//										new TargetPoint(worldObj.provider.dimensionId,
		//												xCoord, yCoord, zCoord, 64));
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		worldObj.notifyBlockChange(xCoord, yCoord, zCoord, this.blockType);
	}
	 @Override
	 public Packet getDescriptionPacket() 
	 {
	     NBTTagCompound tagCompound = new NBTTagCompound();
	     writeToNBT(tagCompound);
	     return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tagCompound);
	 }
	    
	    @Override
	    //Other Forge release use Packet132TileEntityData instead of S35PacketUpdateTileEntity
	 public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) 
	     {
	     //read the packet data from NBT 
	     readFromNBT(packet.func_148857_g());
	     //Update the block render in order to update the client texture
	     Minecraft.getMinecraft().renderGlobal.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
	 }
}
