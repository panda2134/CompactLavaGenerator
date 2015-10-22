package panda2134.CLG.block;

import panda2134.CLG.CLGMod;
import panda2134.CLG.util.CLGReference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class CLGCasing extends Block {
	public CLGCasing(){
		super(Material.iron);
		this.setBlockName(CLGReference.casingName);
		this.setBlockTextureName(CLGReference.modid+":"
						+CLGReference.casingName);
		this.setStepSound(soundTypeMetal);
		this.setHardness(3);
		this.setResistance(6);
		this.setCreativeTab(CLGMod.tabCLG);
	}
}
