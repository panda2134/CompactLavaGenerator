package panda2134.CLG.integration;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import panda2134.CLG.init.Blocks;
import panda2134.CLG.init.Items;
import panda2134.CLG.util.CLGReference;
import panda2134.CLG.util.Mods;
import cpw.mods.fml.common.registry.GameRegistry;

public class TEModule extends BaseModule {

	public void setTECompatRecipes() {
		ItemStack resonantCell = new ItemStack(GameRegistry.findBlock(
				Mods.IDs.TE, "Cell"), 1, 4);
		ItemStack redstoneCell = new ItemStack(GameRegistry.findBlock(
				Mods.IDs.TE, "Cell"), 1, 3);
		ItemStack resonantTank = new ItemStack(GameRegistry.findBlock(
				Mods.IDs.TE, "Tank"), 1, 4);
		ItemStack resonantFrame = new ItemStack(GameRegistry.findBlock(
				Mods.IDs.TE, "Frame"), 1, 3);
		ItemStack lavaGenerator = new ItemStack(GameRegistry.findBlock(
				Mods.IDs.TE, "Dynamo"), 1, 1);
		ItemStack redstoneCapacitor = new ItemStack(GameRegistry.findItem(
				Mods.IDs.TE, "capacitor"), 1, 4);
		ItemStack resonantCapacitor = new ItemStack(GameRegistry.findItem(
				Mods.IDs.TE, "capacitor"), 1, 5);
		// CLGController
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(
				Blocks.blockController),
				new Object[] { "ded", "grg", "scs", 'd', "gemDiamond", 'e',
						"ingotSignalum", 'g', "gearElectrum", 'r',
						resonantCell, 's', "gearSignalum", 'c',
						Blocks.blockCasing }));
		// CLGLavaGenerator
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(
				Blocks.blockLavaGenerator), new Object[] { "scs", "ere", "tlt",
				's', "ingotSignalum", 'c', Blocks.blockCasing, 'e',
				"gearElectrum", 'r', redstoneCell, 't', resonantTank, 'l',
				lavaGenerator }));
		// CLGCasing
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(
				Blocks.blockCasing), new Object[] { "dcd", "srs", "dcd", 'd',
				"gemDiamond", 'c', redstoneCapacitor, 's', "ingotSignalum",
				'r', resonantFrame }));
		// CLGLavaPump
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(
				Blocks.blockLavaPump), new Object[] { "ddd", "tct", "ttt", 'd',
				"gemDiamond", 'c', Blocks.blockCasing, 't', resonantTank }));
		// CLGEnergyHatch
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(
				Blocks.blockEnergyHatch), new Object[] { "gsg", "tct", "rer",
				'g', "gearSignalum", 's', "ingotSignalum", 't',
				redstoneCapacitor, 'c', Blocks.blockCasing, 'r',
				resonantCapacitor, 'e', redstoneCell }));
		// CLGHammer
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(
				Items.itemHammer), new Object[] { " i ", " di", "s  ", 'i',
				"ingotIron", 'd', "gemDiamond", 's', "stickWood" }));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see panda2134.CLG.integration.BaseModule#init()
	 */
	@Override
	public void postInit() {
		if (CLGReference.recipeState.get(Mods.IDs.TE))
			this.setTECompatRecipes();
	}

}
