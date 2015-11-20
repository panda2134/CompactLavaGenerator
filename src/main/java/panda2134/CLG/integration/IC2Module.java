package panda2134.CLG.integration;

import ic2.core.Ic2Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import panda2134.CLG.init.Blocks;
import panda2134.CLG.init.Items;
import cpw.mods.fml.common.registry.GameRegistry;

public class IC2Module extends BaseModule {
	public void setIC2CompatRecipes() {
		// CLGController
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(
				Blocks.blockController), new Object[] {
				"did",
				"clc",
				"asa",
				'd',
				"dustDiamond",
				'i',
				Ic2Items.iridiumPlate,
				'c',
				Ic2Items.carbonPlate,
				'l',
				new ItemStack(Ic2Items.lapotronCrystal.getItem(), 1,
						OreDictionary.WILDCARD_VALUE), 'a', "circuitAdvanced",
				's', Blocks.blockCasing }));
		// CLGLavaGenerator
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(
				Blocks.blockLavaGenerator), new Object[] {
				"ici",
				"pep",
				"mgm",
				'i',
				Ic2Items.iridiumPlate,
				'c',
				Blocks.blockCasing,
				'p',
				Ic2Items.carbonPlate,
				'e',
				new ItemStack(Ic2Items.energyCrystal.getItem(), 1,
						OreDictionary.WILDCARD_VALUE), 'm',
				Ic2Items.miningPipe, 'g', Ic2Items.geothermalGenerator }));
		// ClGCasing
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(
				Blocks.blockCasing), new Object[] { "ded", "iai", "ded", 'd',
				"dustDiamond", 'e', Ic2Items.energiumDust, 'i',
				Ic2Items.iridiumPlate, 'a', Ic2Items.advancedMachine, }));
		// CLGLavaPump
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(
				Blocks.blockLavaPump), new Object[] { "ddd", "mcm", "mpm", 'd',
				"dustDiamond", 'm', Ic2Items.miningPipe, 'c',
				Blocks.blockCasing, 'p', Ic2Items.pump }));
		// CLGEnergyHatch
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(
				Blocks.blockEnergyHatch), new Object[] {
				"aia",
				"ece",
				"gng",
				'a',
				"circuitAdvanced",
				'i',
				Ic2Items.iridiumPlate,
				'e',
				Ic2Items.energiumDust,
				'c',
				Blocks.blockCasing,
				'g',
				Ic2Items.glassFiberCableItem,
				'n',
				new ItemStack(Ic2Items.energyCrystal.getItem(), 1,
						OreDictionary.WILDCARD_VALUE) }));
		// CLGHammer
		GameRegistry.addRecipe(new ShapedOreRecipe(Items.itemHammer,
				new Object[] { " i ", " di", "s  ", 'i', "ingotIron", 'd',
						"dustDiamond", 's', "stickWood" }));
	}

	public static double getOutput(double lU) {
		return lU;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see panda2134.CLG.integration.BaseModule#init()
	 */
	@Override
	public void init() {
		this.setIC2CompatRecipes();
	}

}
