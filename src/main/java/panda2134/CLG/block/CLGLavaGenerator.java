package panda2134.CLG.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import panda2134.CLG.CLGMod;
import panda2134.CLG.util.BlockFaceMetaHelper;
import panda2134.CLG.util.CLGReference;
import panda2134.CLG.util.ParticleHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CLGLavaGenerator extends Block {

	public IIcon[] icons = new IIcon[3];

	public CLGLavaGenerator() {
		super(Material.iron);
		this.setBlockName(CLGReference.generatorName);
		this.setBlockTextureName(CLGReference.modid + ":"
				+ CLGReference.generatorName);
		this.setStepSound(soundTypeMetal);
		this.setHardness(3);
		this.setResistance(6);
		this.setCreativeTab(CLGMod.tabCLG);
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
		int metadata = worldObj.getBlockMetadata(x, y, z);
		int direction = metadata & 7;
		int state = metadata & 8;
		direction = ForgeDirection.getOrientation(direction)
				.getRotation(ForgeDirection.getOrientation(1)).ordinal();
		metadata = state | direction;
		worldObj.setBlockMetadataWithNotify(x, y, z, metadata, 2);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.block.Block#randomDisplayTick(net.minecraft.world.World,
	 * int, int, int, java.util.Random)
	 */
	@Override
	public void randomDisplayTick(World world, int x, int y, int z,
			Random random) {
		if (random.nextDouble() > 0.05)
			return;
		int metadata = world.getBlockMetadata(x, y, z);
		boolean on = ((metadata >> 3) & 1) != 0;
		ForgeDirection d = ForgeDirection.getOrientation(metadata & 7);
		if (on) {
			ParticleHelper.playSidedRandomParticle("flame", world, x, y, z, d,
					random);
			ParticleHelper.playSidedRandomParticle("smoke", world, x, y, z, d,
					random);
		}
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLivingBase entity, ItemStack itemStack) {
		BlockFaceMetaHelper.setBlockFaceMeta(world, x, y, z, entity);
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icons[0] = reg.registerIcon(this.textureName + "_off");
		icons[1] = reg.registerIcon(this.textureName + "_on");
		icons[2] = reg.registerIcon(CLGReference.modid + ":"
				+ CLGReference.casingName);
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

		int face = (iBlockAccess.getBlockMetadata(x, y, z)) & 7;
		int formed = ((iBlockAccess.getBlockMetadata(x, y, z)) >> 3);
		if (formed == 0) {
			return (face == side) ? icons[0] : icons[2];
		} else {
			return (face == side) ? icons[1] : icons[2];
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.block.Block#getMobilityFlag()
	 */
	@Override
	public int getMobilityFlag() {
		return 2;
	}
}
