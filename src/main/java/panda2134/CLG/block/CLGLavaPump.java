package panda2134.CLG.block;

import panda2134.CLG.util.ModValue;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class CLGLavaPump extends Block {
	
	public IIcon[] icons=new IIcon[2];
	
	public CLGLavaPump() {
		super(Material.iron);
		this.setBlockName(ModValue.pumpName);
		this.setBlockTextureName(ModValue.modid+":"
						+ModValue.pumpName);
		this.setStepSound(soundTypeMetal);
		this.setHardness(3);
		this.setResistance(6);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg){
		icons[0]=reg.registerIcon(this.getTextureName());
		icons[1]=reg.registerIcon(ModValue.modid+":"+ModValue.casingName);
	}
	
	@Override
	public IIcon getIcon(int side,int meta){
		return side==0?icons[0]:icons[1];
	}
}
