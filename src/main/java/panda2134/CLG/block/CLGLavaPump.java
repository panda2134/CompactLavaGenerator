package panda2134.CLG.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import panda2134.CLG.CLGMod;
import panda2134.CLG.tileentity.TileEntityCLGPump;
import panda2134.CLG.util.CLGReference;

public class CLGLavaPump extends Block {

	public IIcon[] icons = new IIcon[2];

	public CLGLavaPump() {
		super(Material.iron);
		this.setBlockName(CLGReference.pumpName);
		this.setBlockTextureName(CLGReference.modid + ":"
				+ CLGReference.pumpName);
		this.setStepSound(soundTypeMetal);
		this.setHardness(3);
		this.setResistance(6);
		this.setCreativeTab(CLGMod.tabCLG);
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icons[0] = reg.registerIcon(this.getTextureName());
		icons[1] = reg.registerIcon(CLGReference.modid + ":"
				+ CLGReference.casingName);
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return side == 0 ? icons[0] : icons[1];
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.block.Block#createTileEntity(net.minecraft.world.World,
	 * int)
	 */
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityCLGPump();
	}

}
