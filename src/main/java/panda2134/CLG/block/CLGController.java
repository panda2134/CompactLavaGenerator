package panda2134.CLG.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.ForgeDirection;
import panda2134.CLG.CLGMod;
import panda2134.CLG.init.Blocks;
import panda2134.CLG.tileentity.TileEntityCLGController;
import panda2134.CLG.util.BlockFaceMetaHelper;
import panda2134.CLG.util.CLGReference;
import panda2134.CLG.util.ChatStyles;
import panda2134.CLG.util.GeneratorMultiblockHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CLGController extends Block implements ITileEntityProvider {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.block.Block#onBlockPreDestroy(net.minecraft.world.World,
	 * int, int, int, int)
	 */
	@Override
	public void onBlockPreDestroy(World world, int x, int y, int z, int metadata) {
		TileEntity te = world.getTileEntity(x, y, z);
		if (te == null || world.isRemote)
			return;
		((TileEntityCLGController) (te)).deconstruct = true;
		GeneratorMultiblockHelper.countBlocksInRange(world,
				CLGReference.CLGPattern, x, y, z, 1, 1, 0,
				Blocks.blockLavaGenerator.getUnlocalizedName(), false);
	}

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

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 3 ? icons[1] : icons[2];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.block.Block#rotateBlock(net.minecraft.world.World,
	 * int, int, int, net.minecraftforge.common.util.ForgeDirection)
	 */
	@Override
	public boolean rotateBlock(World worldObj, int x, int y, int z,
			ForgeDirection axis) {

		// Update the state
		TileEntity te = worldObj.getTileEntity(x, y, z);
		if (te == null || worldObj.isRemote)
			return false;
		GeneratorMultiblockHelper.countBlocksInRange(worldObj,
				CLGReference.CLGPattern, x, y, z, 1, 1, 0,
				Blocks.blockLavaGenerator.getUnlocalizedName(), false);

		int metadata = worldObj.getBlockMetadata(x, y, z);
		int direction = metadata & 7;
		int state = metadata & 8;
		direction = ForgeDirection.getOrientation(direction)
				.getRotation(ForgeDirection.getOrientation(1)).ordinal();
		metadata = state | direction;
		worldObj.setBlockMetadataWithNotify(x, y, z, metadata, 2);
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess iBlockAccess, int x, int y, int z,
			int side) {
		int face = iBlockAccess.getBlockMetadata(x, y, z) & 7;
		int formed = iBlockAccess.getBlockMetadata(x, y, z) >> 3;
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
		int count = 0;
		final int range = 16;
		for (int i = x - range; i <= x + range; i++)
			for (int j = Math.min(0, y - range); j <= Math.min(256, y + range); j++)
				for (int k = z - range; k <= z + range; k++) {
					if (world.getBlock(i, j, k).getUnlocalizedName()
							.equals("tile." + CLGReference.controllerName))
						count++;
				}
		if (count > 1) {
			world.removeTileEntity(x, y, z);
			world.setBlockToAir(x, y, z);
			if (entity instanceof EntityPlayer
					&& !(entity instanceof FakePlayer)) {
				itemStack.stackSize++;
				if (!world.isRemote)
					((EntityPlayer) entity)
							.addChatComponentMessage(new ChatComponentTranslation(
									"tile.CLGController.cantplace")
									.setChatStyle(ChatStyles.error));
			}
		}

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
		te.updateState();
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
