package panda2134.CLG.util;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.config.Configuration;
import panda2134.CLG.block.CLGLavaPump;
import panda2134.CLG.integration.IntegrationTypes;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;


public class CLGReference {
	public static CLGReference INSTANCE = new CLGReference();

	public static final String modid = "CLG";
	public static final String modName = "Compact Lava Generator";
	public static final String version = "@VERSION@";
	public static final String controllerName = "CLGController";
	public static final String casingName = "CLGCasing";
	public static final String generatorName = "CLGLavaGenerator";
	public static final String hatchName = "CLGEnergyHatch";
	public static final String hammerName = "CLGHammer";
	public static final String pumpName = "CLGLavaPump";
	public static Map<String, Boolean> recipeState = new HashMap<String, Boolean>();
	public static final int unitPerGenerator = 512;// 512 GU/generator 512eu/t
													// 2048rf/t
	public static final int unitPerHatch = 512;
	public static final int controllerStorage = 10000000;
	public static boolean mustUseInNether;
	public static final String[][][] CLGPattern = new String[][][] {
			{ { "L", "L", "L" }, { "L", "L", "L" }, { "L", "L", "L" } },
			{ { "B", "B", "B" }, { "B", "P", "B" }, { "B", "B", "B" } },
			{ { "B", "B", "B" }, { "B", "A", "B" }, { "B", "B", "B" } },
			{ { "B", "B", "B" }, { "B", "A", "B" }, { "B", "B", "B" } },
			{ { "B", "B", "B" }, { "B", "B", "B" }, { "B", "B", "B" } } };
	public static Configuration cfg;

	public static void init(FMLPreInitializationEvent event) {
		FMLCommonHandler.instance().bus().register(INSTANCE);
		cfg = new Configuration(event.getSuggestedConfigurationFile());
		cfg.load();
	}

	public static void syncConfig() {

		// Read Values Here
		mustUseInNether = cfg
				.getBoolean("MustUseInNether", cfg.getCategory("general")
						.getName(), true,
						"Set this to false to enable the generator to run in any world");

		for (IntegrationTypes t : IntegrationTypes.values()) {
			if (t.mod.hasRecipe)
				recipeState.put(
						t.mod.id,
						cfg.getBoolean(t.mod.id, "recipes", true, t.mod.id
								+ " recipe"));
		}

		cfg.save();
	}

	public static boolean isSpecialBlkForMulti(String str, Block blk) {
		if (blk.getClass() == CLGLavaPump.class)
			return true;
		else if (blk.getMaterial() == Material.lava)
			return true;
		return false;
	}

	public static boolean isCLGMultiBlock(String str) {
		if (str.equals("tile." + controllerName))
			return true;
		else if (str.equals("tile." + casingName))
			return true;
		else if (str.equals("tile." + generatorName))
			return true;
		else if (str.equals("tile." + hatchName))
			return true;
		return false;
	}

	public static boolean isServer() {
		return FMLCommonHandler.instance().getEffectiveSide().isServer();
	}

	public static boolean isClient() {
		return FMLCommonHandler.instance().getEffectiveSide().isClient();
	}

	@SubscribeEvent
	public void onConfigChanged(
			ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if (eventArgs.modID.equals(CLGReference.modid))
			CLGReference.syncConfig();
	}
}
