package panda2134.CLG.init;

import cpw.mods.fml.common.registry.GameRegistry;
import panda2134.CLG.tileentity.TileEntityCLGController;
import net.minecraft.tileentity.TileEntity;

public class TileEntities {
	public static void init(){
		GameRegistry.registerTileEntity(TileEntityCLGController.class, "TileEntityCLGController");
	}

}
