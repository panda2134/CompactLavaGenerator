package panda2134.CLG.block;

import panda2134.CLG.util.ModValue;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class CLGCasing extends Block {
	public CLGCasing(){
		super(Material.iron);
		this.setBlockName(ModValue.casingName);
		this.setBlockTextureName(ModValue.modid+":"
						+ModValue.casingName);
		this.setStepSound(soundTypeMetal);
		this.setHardness(3);
		this.setResistance(6);
	}
}
