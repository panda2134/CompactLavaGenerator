package panda2134.CLG.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import panda2134.CLG.util.CLGReference;

public class CLGPacketHandler {
	public static SimpleNetworkWrapper INSTANCE;
	
	public static void init() {
		INSTANCE=NetworkRegistry.INSTANCE
				.newSimpleChannel(CLGReference.modid.toLowerCase());
		INSTANCE.registerMessage(CLGMultiblockMessage.CLGMultiblockMessageHandler.class,
				CLGMultiblockMessage.class, 0, Side.CLIENT);
	}
	
}
