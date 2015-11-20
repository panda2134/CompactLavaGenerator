package panda2134.CLG.integration;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import panda2134.CLG.init.Blocks;
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

		//CLGController
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(
				Blocks.blockController), new Object[] { "did", "clc", "asa",
				'd', diamondShard, 'i', logicMatrixProgrammer, 'c',
				darkIronBlock, 'l', controller, 'a', darkIronSprocket, 's',
				Blocks.blockCasing }));

		//
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see panda2134.CLG.integration.BaseModule#postInit()
	 */
	@Override
	public void postInit() {
		// TODO Auto-generated method stub
		setFZCompatRecipes();
	}

}
