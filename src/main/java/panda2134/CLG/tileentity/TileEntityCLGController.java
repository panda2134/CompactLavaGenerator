package panda2134.CLG.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import panda2134.CLG.init.Blocks;
import panda2134.CLG.network.CLGMultiblockMessage;
import panda2134.CLG.network.CLGPacketHandler;
import panda2134.CLG.util.ModValue;
import panda2134.CLG.util.MultiblockHelper;
import scala.collection.immutable.List;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCLGController extends TileEntity {
	public double generating,outputLimit,stroage;
	public boolean formed;
	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		generating=nbt.getDouble("generating");
		outputLimit=nbt.getDouble("outputLimit");
		stroage=nbt.getDouble("stroage");
		formed=nbt.getBoolean("formed");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setDouble("generating",generating);
		nbt.setDouble("outputLimit",outputLimit);
		nbt.setDouble("stroage",stroage);
		nbt.setBoolean("formed", formed);
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
		boolean state=MultiblockHelper.checkPattern(pattern, world, xCoord, yCoord, zCoord, 1, 1, 0, ModValue.clgBlockList,Blocks.blockController);
		return state;
	}
	
	@Override
	public void updateEntity(){
		if(this.getWorldObj().isRemote) return;
		formed=this.isStructureComplete();
		//send to client
		CLGPacketHandler.INSTANCE.
		sendToAll(new CLGMultiblockMessage(true,xCoord,yCoord,zCoord));
	}
}
