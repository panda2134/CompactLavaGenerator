package panda2134.CLG.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import panda2134.CLG.CLGMod;
import panda2134.CLG.util.BlockFaceMetaHelper;
import panda2134.CLG.util.CLGReference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CLGLavaGenerator extends Block {

	public IIcon[] icons=new IIcon[3];
	
	public CLGLavaGenerator() {
		super(Material.iron);
		this.setBlockName(CLGReference.generatorName);
		this.setBlockTextureName(CLGReference.modid+":"
						+CLGReference.generatorName);
		this.setStepSound(soundTypeMetal);
		this.setHardness(3);
		this.setResistance(6);
		this.setCreativeTab(CLGMod.tabCLG);
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack){
		BlockFaceMetaHelper.setBlockFaceMeta(world, x, y, z, entity);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg){
		icons[0]=reg.registerIcon(this.textureName+"_off");
		icons[1]=reg.registerIcon(this.textureName+"_on");
		icons[2]=reg.registerIcon(CLGReference.modid+":"
									+CLGReference.casingName);
	}
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side,int meta){
		return side==3?icons[1]:icons[2];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess iBlockAccess, int x, int y, int z,
            int side) {
		
		int face=(iBlockAccess.getBlockMetadata(x, y, z)) & 7;
		int formed=((iBlockAccess.getBlockMetadata(x, y, z)) >> 3);
		if(formed==0){
			return (face==side)?icons[0]:icons[2];
		}else{
			return (face==side)?icons[1]:icons[2];
		}
	}
}
