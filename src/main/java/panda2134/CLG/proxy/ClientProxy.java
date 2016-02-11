package panda2134.CLG.proxy;

import panda2134.CLG.render.CLGPumpTESR;
import panda2134.CLG.tileentity.TileEntityCLGPump;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends Proxy {

	/*
	 * (non-Javadoc)
	 * 
	 * @see panda2134.CLG.proxy.Proxy#init(cpw.mods.fml.common.event.
	 * FMLInitializationEvent)
	 */
	@Override
	public void init(FMLInitializationEvent event) {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCLGPump.class,
				new CLGPumpTESR());
		super.init(event);
	}

}
