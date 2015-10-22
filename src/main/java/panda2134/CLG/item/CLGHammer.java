package panda2134.CLG.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import panda2134.CLG.CLGMod;
import panda2134.CLG.init.Blocks;
import panda2134.CLG.network.CLGPacketHandler;
import panda2134.CLG.tileentity.TileEntityCLGController;
import panda2134.CLG.util.CLGReference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class CLGHammer extends Item {

	public CLGHammer() {
		this.setUnlocalizedName(CLGReference.hammerName);
		this.setTextureName(CLGReference.modid+":"+CLGReference.hammerName);
		this.setCreativeTab(CLGMod.tabCLG);
	}
	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float px, float py, float pz){
		TileEntity te=world.getTileEntity(x, y, z);
		if(world.isRemote) return true;
		if(te instanceof TileEntityCLGController)
			((TileEntityCLGController)te).onHitByHammer(player);
		
		return true;
	}
}
