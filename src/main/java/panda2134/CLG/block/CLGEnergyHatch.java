package panda2134.CLG.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import panda2134.CLG.CLGMod;
import panda2134.CLG.tileentity.TileEntityEnergyHatch;
import panda2134.CLG.util.CLGReference;

public class CLGEnergyHatch extends Block implements ITileEntityProvider {

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.block.Block#rotateBlock(net.minecraft.world.World,
	 * int, int, int, net.minecraftforge.common.util.ForgeDirection)
	 */
	@Override
	public boolean rotateBlock(World worldObj, int x, int y, int z,
			ForgeDirection axis) {
		int metadata = worldObj.getBlockMetadata(x, y, z);
		metadata = ForgeDirection.getOrientation(metadata).getRotation(axis)
				.ordinal();
		worldObj.setBlockMetadataWithNotify(x, y, z, metadata, 2);
		return true;
	}

	public IIcon[] icons = new IIcon[2];

	public CLGEnergyHatch() {
		super(Material.iron);
		this.setBlockName(CLGReference.hatchName);
		this.setBlockTextureName(CLGReference.modid + ":"
				+ CLGReference.hatchName);
		this.setStepSound(soundTypeMetal);
		this.setHardness(3);
		this.setResistance(6);
		this.setCreativeTab(CLGMod.tabCLG);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.block.Block#getIcon(net.minecraft.world.IBlockAccess,
	 * int, int, int, int)
	 */
	@Override
	public IIcon getIcon(IBlockAccess iBlockAccess, int x, int y, int z,
			int side) {
		// TODO Auto-generated method stub
		return side == iBlockAccess.getBlockMetadata(x, y, z) ? icons[0]
				: icons[1];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.block.Block#getIcon(int, int)
	 */
	@Override
	public IIcon getIcon(int side, int meta) {
		return side == 3 ? icons[0] : icons[1];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.block.Block#registerBlockIcons(net.minecraft.client.renderer
	 * .texture.IIconRegister)
	 */
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icons[0] = reg.registerIcon(this.textureName);
		icons[1] = reg.registerIcon(CLGReference.modid + ":"
				+ CLGReference.casingName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.block.Block#onBlockPlacedBy(net.minecraft.world.World,
	 * int, int, int, net.minecraft.entity.EntityLivingBase,
	 * net.minecraft.item.ItemStack)
	 */
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLivingBase entity, ItemStack stack) {
		world.setBlockMetadataWithNotify(x, y, z,
				BlockPistonBase.determineOrientation(world, x, y, z, entity), 2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.block.Block#createTileEntity(net.minecraft.world.World,
	 * int)
	 */
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		// TODO Auto-generated method stub
		return new TileEntityEnergyHatch();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.block.Block#onNeighborBlockChange(net.minecraft.world.World
	 * , int, int, int, net.minecraft.block.Block)
	 */
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z,
			Block blk) {
		// TODO Auto-generated method stub
		super.onNeighborBlockChange(world, x, y, z, blk);

	}

}
