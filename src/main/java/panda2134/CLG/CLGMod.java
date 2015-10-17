package panda2134.CLG;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import panda2134.CLG.init.Blocks;
import panda2134.CLG.init.Items;
import panda2134.CLG.init.TileEntities;
import panda2134.CLG.network.CLGPacketHandler;
import panda2134.CLG.proxy.CommonProxy;
import panda2134.CLG.proxy.Proxy;
import panda2134.CLG.util.ModValue;

@Mod(modid=ModValue.modid,name=ModValue.modName,version=ModValue.version)
public class CLGMod {
	
	public static CLGMod INSTANCE=new CLGMod();
	
	@SidedProxy(clientSide="panda2134.CLG.proxy.ClientProxy",serverSide="panda2134.CLG.proxy.CommonProxy")
	public static Proxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		ModValue.init();
		Blocks.init();
		Items.init();
		proxy.preInit(event);
	}
	@EventHandler
	public void init(FMLInitializationEvent event){
		TileEntities.init();
		
		proxy.init(event);
	}
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		CLGPacketHandler.init();
		System.out.println("server?"+ModValue.isServer);
		proxy.postInit(event);
	}
}
