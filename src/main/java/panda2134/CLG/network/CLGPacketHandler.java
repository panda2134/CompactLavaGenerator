package panda2134.CLG.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import panda2134.CLG.util.ModValue;

public class CLGPacketHandler {
	public static final SimpleNetworkWrapper INSTANCE=NetworkRegistry.INSTANCE
											.newSimpleChannel(ModValue.modid);
	
	public CLGPacketHandler() {
		// TODO Auto-generated constructor stub
		INSTANCE.registerMessage(CLGMultiblockMessage.CLGMultiblockMessageHandler.class,
				CLGMultiblockMessage.class, 0, Side.CLIENT);
	}
	
}
