package panda2134.CLG.integration;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.oredict.ShapedOreRecipe;
import panda2134.CLG.init.Blocks;
import panda2134.CLG.init.Items;
import panda2134.CLG.util.CLGReference;
import panda2134.CLG.util.Mods;
import buildcraft.BuildCraftCore;
import buildcraft.BuildCraftFactory;
import buildcraft.BuildCraftSilicon;
import buildcraft.BuildCraftTransport;
import buildcraft.core.recipes.AssemblyRecipeManager;
import cpw.mods.fml.common.registry.GameRegistry;

public class BCModule extends BaseModule {
	@Override
	public void postInit() {
		if (CLGReference.recipeState.get(Mods.IDs.BC))
			setBCCompatRecipes();
	}

	private void setBCCompatRecipes() {

		GameRegistry.addRecipe(new ShapedOreRecipe(
				panda2134.CLG.init.Blocks.blockCasing, "iqi", "qdq", "iqi",
				'i', "ingotIron", 'q', "blockQuartz", 'd', "chipsetDiamond"));
		Item chipset = BuildCraftSilicon.redstoneChipset;
		Item crystal = BuildCraftSilicon.redstoneCrystal;
		ItemStack refinery = new ItemStack(BuildCraftFactory.refineryBlock);

		ItemStack gatePulseX2 = new ItemStack(BuildCraftTransport.pipeGate, 2);
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setByte("logic", (byte) 1);
		nbt.setByte("mat", (byte) 5);
		NBTTagList l = new NBTTagList();
		l.appendTag(new NBTTagString("buildcraft:pulsar"));
		nbt.setTag("ex", l);
		gatePulseX2.setTagCompound(nbt);

		AssemblyRecipeManager.INSTANCE.addRecipe("GeneratorController",
				1000000, new ItemStack(Blocks.blockController), new Object[] {
						new ItemStack(chipset, 2, 0),
						new ItemStack(chipset, 2, 3),
						new ItemStack(crystal, 2),
						new ItemStack(net.minecraft.init.Blocks.glass),
						new ItemStack(chipset, 1, 4),
						new ItemStack(Blocks.blockCasing) });
		AssemblyRecipeManager.INSTANCE.addRecipe("LavaPump", 10000,
				new ItemStack(Blocks.blockLavaPump), new Object[] {
						new ItemStack(BuildCraftFactory.tankBlock, 2),
						new ItemStack(Blocks.blockCasing), gatePulseX2,
						refinery,
						new ItemStack(BuildCraftCore.goldGearItem, 2),
						new ItemStack(BuildCraftFactory.pumpBlock) });
		AssemblyRecipeManager.INSTANCE.addRecipe("GeneratorModule", 1000000,
				new ItemStack(Blocks.blockLavaGenerator), new Object[] {
						new ItemStack(crystal, 2),
						new ItemStack(BuildCraftFactory.tankBlock, 2),
						new ItemStack(Blocks.blockCasing),
						new ItemStack(BuildCraftCore.engineBlock, 1, 1),
						new ItemStack(BuildCraftCore.engineBlock, 2, 2),
						new ItemStack(BuildCraftFactory.floodGateBlock) });

		AssemblyRecipeManager.INSTANCE.addRecipe("EnergyHatch", 1000000,
				new ItemStack(Blocks.blockEnergyHatch), new Object[] {
						new ItemStack(chipset, 2, 3), new ItemStack(crystal),
						new ItemStack(chipset, 1, 2),
						new ItemStack(chipset, 2, 1),
						new ItemStack(BuildCraftCore.goldGearItem, 2),
						new ItemStack(Blocks.blockCasing) });

		GameRegistry.addRecipe(new ShapedOreRecipe(Items.itemHammer, " iA",
				" Gi", "s  ", 'i', "ingotIron", 'A', new ItemStack(
						BuildCraftTransport.gateCopier), 'G', "gearIron", 's',
				"stickWood"));
	}

}
