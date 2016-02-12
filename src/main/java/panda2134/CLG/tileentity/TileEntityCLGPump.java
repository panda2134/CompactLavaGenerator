package panda2134.CLG.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityCLGPump extends TileEntity {

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getBoundingBox(xCoord - 128, yCoord - 128,
				zCoord - 128, xCoord + 128, yCoord + 128, zCoord + 128);
	}

}
