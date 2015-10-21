package panda2134.CLG.init;

import cpw.mods.fml.common.registry.GameRegistry;
import panda2134.CLG.block.CLGCasing;
import panda2134.CLG.block.CLGController;
import panda2134.CLG.block.CLGEnergyHatch;
import panda2134.CLG.block.CLGLavaGenerator;
import panda2134.CLG.block.CLGLavaPump;
import panda2134.CLG.util.CLGReference;
import net.minecraft.block.Block;

public class Blocks {
	public static Block blockController;
	public static Block blockCasing;
	public static Block blockLavaPump;
	public static Block blockLavaGenerator;
	public static Block blockEnergyHatch;
	public static void init(){
		blockController=new CLGController();
		blockCasing=new CLGCasing();
		blockLavaPump=new CLGLavaPump();
		blockLavaGenerator=new CLGLavaGenerator();
		blockEnergyHatch=new CLGEnergyHatch();
		GameRegistry.registerBlock(blockController,CLGReference.controllerName);
		GameRegistry.registerBlock(blockCasing, CLGReference.casingName);
		GameRegistry.registerBlock(blockLavaPump, CLGReference.pumpName);
		GameRegistry.registerBlock(blockLavaGenerator, CLGReference.generatorName);
		GameRegistry.registerBlock(blockEnergyHatch, CLGReference.hatchName);
	}
}
