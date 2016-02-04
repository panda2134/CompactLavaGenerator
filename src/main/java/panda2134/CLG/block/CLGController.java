package panda2134.CLG.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import panda2134.CLG.CLGMod;
import panda2134.CLG.init.Blocks;
import panda2134.CLG.tileentity.TileEntityCLGController;
import panda2134.CLG.util.BlockFaceMetaHelper;
import panda2134.CLG.util.CLGReference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CLGController extends Block implements ITileEntityProvider {
	public IIcon[] icons = new IIcon[3];

	public CLGController() {
		super(Material.iron);
		this.setBlockName(CLGReference.controllerName);
		this.setBlockTextureName(CLGReference.modid + ":"
				+ CLGReference.controllerName);
		this.setStepSound(soundTypeMetal);
		this.setHardness(3);
		this.setResistance(6);
		this.setCreativeTab(CLGMod.tabCLG);
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icons[0] = reg.registerIcon(this.textureName + "_front_off");
		icons[1] = reg.registerIcon(this.textureName + "_front_on");
		icons[2] = reg.registerIcon(CLGReference.modid + ":"
				+ CLGReference.casingName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.block.Block#breakBlock(net.minecraft.world.World, int,
	 * int, int, net.minecraft.block.Block, int)
	 */
	@Override
	public void breakBlock(World world, int x, int y, int z, Block blk,
			int metadata) {
		if (blk.getUnlocalizedName() != Blocks.blockController
				.getUnlocalizedName())
			return;
		TileEntityCLGController te = (TileEntityCLGController) world
				.getTileEntity(x, y, z);
		if (te == null)
			return;
		te.doWait = false;
		te.forceNotFormed = true;
		te.updateEntity();
		super.breakBlock(world, x, y, z, blk, metadata);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 3 ? icons[1] : icons[2];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess iBlockAccess, int x, int y, int z,
			int side) {
		// Minecraft.getMinecraft().theWorld.
		// notifyBlockChange(x, y, z, iBlockAccess.getBlock(x, y, z));
		int face = iBlockAccess.getBlockMetadata(x, y, z) & 7;
		int formed = iBlockAccess.getBlockMetadata(x, y, z) >> 3;
		// boolean
		// formed=((TileEntityCLGController)(iBlockAccess.getTileEntity(x, y,
		// z))).formed;
		if (formed == 0) {
			return (face == side) ? icons[0] : icons[2];
		} else {
			return (face == side) ? icons[1] : icons[2];
		}
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLivingBase entity, ItemStack itemStack) {
		BlockFaceMetaHelper.setBlockFaceMeta(world, x, y, z, entity);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z,
			Block blk) {
		if (blk.getUnlocalizedName() != Blocks.blockController
				.getUnlocalizedName())
			return;
		TileEntityCLGController te = (TileEntityCLGController) world
				.getTileEntity(x, y, z);
		if (te == null)
			return;
		te.updateEntity();
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityCLGController();
	}

	@Override
	public boolean onBlockEventReceived(World worldIn, int x, int y, int z,
			int event, int eventParam) {
		super.onBlockEventReceived(worldIn, x, y, z, event, eventParam);
		TileEntity tileentity = worldIn.getTileEntity(x, y, z);
		return tileentity == null ? false : tileentity.receiveClientEvent(
				event, eventParam);
	}
}
