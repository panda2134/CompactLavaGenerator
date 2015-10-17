package panda2134.CLG.init;

import cpw.mods.fml.common.registry.GameRegistry;
import panda2134.CLG.block.CLGCasing;
import panda2134.CLG.block.CLGController;
import panda2134.CLG.block.CLGLavaPump;
import panda2134.CLG.util.CLGReference;
import net.minecraft.block.Block;

public class Blocks {
	public static Block blockController;
	public static Block blockCasing;
	public static Block blockLavaPump;
	public static void init(){
		blockController=new CLGController();
		blockCasing=new CLGCasing();
		blockLavaPump=new CLGLavaPump();
		GameRegistry.registerBlock(blockController,CLGReference.controllerName);
		GameRegistry.registerBlock(blockCasing, CLGReference.casingName);
		GameRegistry.registerBlock(blockLavaPump, CLGReference.pumpName);
	}
}
