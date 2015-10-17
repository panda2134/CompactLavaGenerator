package panda2134.CLG.network;

import panda2134.CLG.tileentity.TileEntityCLGController;
import panda2134.CLG.util.ModValue;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class CLGMultiblockMessage implements IMessage {

	public CLGMultiblockMessage() {
		// TODO Auto-generated constructor stub
	}
	
	private boolean formed;
	private int x,y,z;
	
	public CLGMultiblockMessage(boolean formed,int x,int y,int z){
		this.formed=formed;
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		formed=buf.readBoolean();
		x=buf.readInt();
		y=buf.readInt();
		z=buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(formed);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
	}
	
	public static class CLGMultiblockMessageHandler implements IMessageHandler<CLGMultiblockMessage,IMessage>{

		@Override
		public IMessage onMessage(CLGMultiblockMessage message,
				MessageContext ctx) {
			System.out.println("onMessage"+ModValue.isServer);
			if(message.formed){
				TileEntityCLGController tile=(TileEntityCLGController)Minecraft.getMinecraft()
						.theWorld.getTileEntity(message.x, message.y, message.z);
				tile.formed=true;
			}
			return null;
		}
		
	}

}
