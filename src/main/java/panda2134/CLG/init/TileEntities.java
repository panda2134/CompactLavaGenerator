package panda2134.CLG.init;

import panda2134.CLG.tileentity.TileEntityCLGController;
import panda2134.CLG.tileentity.TileEntityCLGPump;
import panda2134.CLG.tileentity.TileEntityEnergyHatch;
import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntities {
	public static void init() {
		GameRegistry.registerTileEntity(TileEntityCLGController.class,
				"TileEntityCLGController");
		GameRegistry.registerTileEntity(TileEntityEnergyHatch.class,
				"TileEntityEnergyHatch");
		GameRegistry.registerTileEntity(TileEntityCLGPump.class,
				"TileEntityCLGPump");
	}
}
