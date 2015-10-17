package panda2134.CLG.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import panda2134.CLG.util.ModValue;

public class CLGPacketHandler {
	public static SimpleNetworkWrapper INSTANCE;
	
	public static void init() {
		System.out.println("INIT PACKET");
		INSTANCE=NetworkRegistry.INSTANCE
				.newSimpleChannel(ModValue.modid);
		INSTANCE.registerMessage(CLGMultiblockMessage.CLGMultiblockMessageHandler.class,
				CLGMultiblockMessage.class, 0, Side.CLIENT);
	}
	
}
