package panda2134.CLG.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockFaceMetaHelper {
	public static void setBlockFaceMeta(World world,int x,int y,int z,
												EntityLivingBase entity){
		int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) 
																		+ 0.5D) & 3;

        if (l == 0)
        {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (l == 1)
        {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if (l == 2)
        {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (l == 3)
        {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }
	}
}
