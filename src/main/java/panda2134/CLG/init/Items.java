package panda2134.CLG.init;

import net.minecraft.item.Item;
import panda2134.CLG.item.CLGHammer;
import panda2134.CLG.util.CLGReference;
import cpw.mods.fml.common.registry.GameRegistry;

public class Items {
	public static Item itemHammer;
	public static void init(){
		itemHammer=new CLGHammer();
		GameRegistry.registerItem(itemHammer, CLGReference.hammerName);
	}
}
