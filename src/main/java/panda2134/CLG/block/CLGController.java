package panda2134.CLG.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import panda2134.CLG.tileentity.TileEntityCLGController;
import panda2134.CLG.util.ModValue;
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

public class CLGController extends Block implements ITileEntityProvider{
	public IIcon[] icons=new IIcon[3];
	
	public CLGController(){
		super(Material.iron);
		this.setBlockName(ModValue.controllerName);
		this.setBlockTextureName(ModValue.modid+":"
						+ModValue.controllerName);
		this.setStepSound(soundTypeMetal);
		this.setHardness(3);
		this.setResistance(6);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg){
		icons[0]=reg.registerIcon(this.textureName+"_front_off");
		icons[1]=reg.registerIcon(this.textureName+"_front_on");
		icons[2]=reg.registerIcon(ModValue.modid+":"
									+ModValue.casingName);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side,int meta){
		return side==3?icons[1]:icons[2];
	}
	
	@Override
    public IIcon getIcon(IBlockAccess iBlockAccess, int x, int y, int z,
            int side) {
		int face=iBlockAccess.getBlockMetadata(x, y, z);
		boolean formed=((TileEntityCLGController)(iBlockAccess.getTileEntity(x, y, z))).formed;
		if(!formed){
			return (face==side)?icons[0]:icons[2];
		}else{
			return (face==side)?icons[1]:icons[2];
		}
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack){
		double deltaX=entity.posX-x;
		double deltaZ=entity.posZ-z;
		double angle=(entity.rotationYaw+45.0)%360;
		int face=0;
		if(angle>0.0 && angle<=90.0)
			face=2;
		else if(angle>90.0 && angle<=180.0)
			face=5;
		else if(angle>180.0 && angle<=270.0)
			face=3;
		else
			face=4;
		world.setBlockMetadataWithNotify(x, y, z, face, 2);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityCLGController();
	}
}
