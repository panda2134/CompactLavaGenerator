package panda2134.CLG.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.material.Material;
import panda2134.CLG.block.CLGCasing;
import panda2134.CLG.block.CLGController;
import panda2134.CLG.block.CLGLavaPump;
import panda2134.CLG.init.Blocks;



public class CLGReference {
	public static final String modid="CLG";
	public static final String modName="Compact Lava Generator";
	public static final String version="0.0.1";
	public static final String controllerName="CLGController";
	public static final String casingName="CLGCasing";
	public static final String generatorName="CLGLavaGenerator";
	public static final String hatchName="CLGEnergyHatch";
	public static final String hammerName="CLGHammer";
	public static final String pumpName="CLGLavaPump";
	public static boolean isServer;
	public static boolean isClient;
	public static final int unitPerGenerator=512;// 512 GU/generator 512eu/t 2048rf/t
	public static final int unitPerHatch=512;
	public static final int controllerStorage=300000000;
	public static final String[][][] CLGPattern=new String[][][]{
		{{"L","L","L"},{"L","L","L"},{"L","L","L"}},
		{{"B","B","B"},{"B","P","B"},{"B","B","B"}},
		{{"B","B","B"},{"B","A","B"},{"B","B","B"}},
		{{"B","B","B"},{"B","A","B"},{"B","B","B"}},
		{{"B","B","B"},{"B","B","B"},{"B","B","B"}}
};;
	
	public static void init() {
		isServer=FMLCommonHandler.instance().getEffectiveSide().isServer();
		isClient=FMLCommonHandler.instance().getEffectiveSide().isClient();
	}
	
	public static boolean isSpecialBlkForMulti(String str,Block blk){
		if(blk.getClass()==CLGLavaPump.class)
			return true;
		else if(blk.getMaterial()==Material.lava)
				return true;
		return false;
	}
	
	public static boolean isCLGMultiBlock(String str){
		if(str.equals("tile."+controllerName))
			return true;
		else if(str.equals("tile."+casingName))
			return true;
		else if(str.equals("tile."+generatorName))
			return true;
		else if(str.equals("tile."+hatchName))
			return true;
		return false;
	}
}
