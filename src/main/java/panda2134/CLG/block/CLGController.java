package panda2134.CLG.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import panda2134.CLG.tileentity.TileEntityCLGController;
import panda2134.CLG.util.CLGReference;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CLGController extends Block implements ITileEntityProvider{
	public IIcon[] icons=new IIcon[3];
	
	public CLGController(){
		super(Material.iron);
		this.setBlockName(CLGReference.controllerName);
		this.setBlockTextureName(CLGReference.modid+":"
						+CLGReference.controllerName);
		this.setStepSound(soundTypeMetal);
		this.setHardness(3);
		this.setResistance(6);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg){
		icons[0]=reg.registerIcon(this.textureName+"_front_off");
		icons[1]=reg.registerIcon(this.textureName+"_front_on");
		icons[2]=reg.registerIcon(CLGReference.modid+":"
									+CLGReference.casingName);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side,int meta){
		return side==3?icons[1]:icons[2];
	}
	
	@Override
    public IIcon getIcon(IBlockAccess iBlockAccess, int x, int y, int z,
            int side) {
		//TODO:remove this
		Minecraft.getMinecraft().theWorld.notifyBlockChange(x, y, z, iBlockAccess.getBlock(x, y, z));
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
		int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (l == 1)
        {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if (l == 2)
        {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (l == 3)
        {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }
	}
	
	@Override
	public void onNeighborBlockChange(World world,int x,int y,int z,
													Block blk){
		
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityCLGController();
	}
}
