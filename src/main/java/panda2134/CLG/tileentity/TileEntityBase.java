package panda2134.CLG.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBase extends TileEntity {
	public boolean formed;
	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		nbt.getBoolean("formed");
	}
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		nbt.setBoolean("formed", formed);
	}
}
