package panda2134.CLG;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import panda2134.CLG.init.Blocks;
import panda2134.CLG.init.Items;
import panda2134.CLG.init.TileEntities;
import panda2134.CLG.integration.IntegrationTypes;
import panda2134.CLG.network.CLGPacketHandler;
import panda2134.CLG.proxy.Proxy;
import panda2134.CLG.util.CLGReference;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = CLGReference.modid, name = CLGReference.modName, version = CLGReference.version, guiFactory = "panda2134.CLG.gui.CLGGuiFactory")
public class CLGMod {

	@Mod.Instance(CLGReference.modid)
	public static CLGMod INSTANCE;

	public static final Logger logger = LogManager.getLogger(CLGReference.modid);

	public static CreativeTabs tabCLG = new CreativeTabs("clg") {
		@Override
		public Item getTabIconItem() {
			return new ItemStack(Blocks.blockController).getItem();
		}
	};

	@SidedProxy(clientSide = "panda2134.CLG.proxy.ClientProxy", serverSide = "panda2134.CLG.proxy.CommonProxy")
	public static Proxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		CLGReference.init(event);

		Blocks.init();
		Items.init();

		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		FMLCommonHandler.instance().bus().register(INSTANCE);
		CLGReference.syncConfig();
		for (IntegrationTypes t : IntegrationTypes.values()) {
			if (t.getModule() != null)
				t.getModule().init();
		}
		TileEntities.init();
		CLGPacketHandler.init();
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		for (IntegrationTypes t : IntegrationTypes.values()) {
			if (t.getModule() != null)
				t.getModule().postInit();
		}
		proxy.postInit(event);
	}

}
