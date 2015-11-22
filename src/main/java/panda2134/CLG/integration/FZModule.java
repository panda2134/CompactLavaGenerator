package panda2134.CLG.integration;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import panda2134.CLG.init.Blocks;
import panda2134.CLG.init.Items;
import panda2134.CLG.util.CLGReference;
import panda2134.CLG.util.Mods;
import cpw.mods.fml.common.registry.GameRegistry;

public class FZModule extends BaseModule {
	public static double getOutput(double lU) {
		return lU * .81;
	}

	public void setFZCompatRecipes() {
		ItemStack diamondShard = new ItemStack(GameRegistry.findItem(
				Mods.IDs.fz, "diamond_shard"));
		ItemStack darkIronBlock = new ItemStack(GameRegistry.findItem(
				Mods.IDs.fz, "ResourceBlock"), 1, 3);
		ItemStack darkIronIngot = new ItemStack(GameRegistry.findItem(
				Mods.IDs.fz, "dark_iron_ingot"));
		ItemStack shaft = new ItemStack(GameRegistry.findItem(Mods.IDs.fz,
				"FzBlock"), 1, 54);
		ItemStack pressurizer = new ItemStack(GameRegistry.findItem(
				Mods.IDs.fz, "fan"));
		ItemStack generator = new ItemStack(GameRegistry.findItem(Mods.IDs.fz,
				"FzBlock"), 1, 52);
		ItemStack controller = new ItemStack(GameRegistry.findItem(Mods.IDs.fz,
				"logic_matrix_controller"));
		ItemStack battery = new ItemStack(GameRegistry.findItem(Mods.IDs.fz,
				"charge_battery"), 1, OreDictionary.WILDCARD_VALUE);
		ItemStack darkIronSprocket = new ItemStack(GameRegistry.findItem(
				Mods.IDs.fz, "servo/sprocket"));
		Item programmer = GameRegistry.findItem(Mods.IDs.fz,
				"tool/matrix_programmer");
		ItemStack logicMatrixProgrammer = new ItemStack(
				programmer.setContainerItem(programmer));
		ItemStack leydenJar = new ItemStack(GameRegistry.findItem(Mods.IDs.fz,
				"FzBlock"), 1, 26);
		ItemStack leadWire = new ItemStack(GameRegistry.findItem(Mods.IDs.fz,
				"FzBlock"), 1, 14);
		// CLGController
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(
				Blocks.blockController), new Object[] { "did", "clc", "asa",
				'd', diamondShard, 'i', logicMatrixProgrammer, 'c',
				darkIronBlock, 'l', controller, 'a', darkIronSprocket, 's',
				Blocks.blockCasing }));

		// CLGLavaGenerator
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(
				Blocks.blockLavaGenerator, 8), "dcd", "gsg", "ABC", 'd',
				darkIronIngot, 'c', Blocks.blockCasing, 'g', darkIronSprocket,
				's', shaft, 'A', logicMatrixProgrammer, 'B', pressurizer, 'C',
				generator));

		// CLGCasing
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(
				Blocks.blockCasing, 4), "AAA", "ABA", "AAA", 'A',
				darkIronIngot, 'B', logicMatrixProgrammer));

		// CLGLavaPump
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(
				Blocks.blockLavaPump), "AAA", "BCB", "BDB", 'A', diamondShard,
				'B', darkIronBlock, 'C', Blocks.blockCasing, 'D',
				logicMatrixProgrammer));

		// CLGEnergyHatch
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(
				Blocks.blockEnergyHatch, 6), "ABA", "CDC", "CEC", 'A',
				darkIronIngot, 'B', darkIronSprocket, 'C', leydenJar, 'D',
				Blocks.blockCasing, 'E', leadWire));

		// CLGEnergyHatch
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(
				Items.itemHammer, 2), logicMatrixProgrammer, "ingotIron"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see panda2134.CLG.integration.BaseModule#postInit()
	 */
	@Override
	public void postInit() {
		if (CLGReference.recipeState.get(Mods.IDs.fz))
			setFZCompatRecipes();
	}

}
