package panda2134.CLG.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.crash.CrashReport;
import net.minecraft.init.Blocks;
import net.minecraft.util.ReportedException;
import net.minecraft.world.World;

public class MultiblockHelper {
	public static int getOppositeFace(int f){
		int[] opposite=new int[]{1,0,3,2,5,4};
		return opposite[f];
	}
	public static int getCheckCenterZ(int side,int centerZ,int relX,int relZ,int lenZ) throws Exception{
		if(side==2){
			return centerZ-relZ;
		}else if(side==3){
			return centerZ+lenZ-1;
		}else if(side==4){
			return centerZ+relX;
		}else if(side==5){
			return centerZ+relX;
		}else
			throw new Exception("Argument \"side\" illegal");
	}
	public static int getCheckCenterX(int side,int centerX,int relX,int relZ,int lenZ) throws Exception{
		if(side==2){
			return centerX-relX;
		}else if(side==3){
			return centerX-relX;
		}else if(side==4){
			return centerX-lenZ+1;
		}else if(side==5){
			return centerX-relZ;
		}else
			throw new Exception("Argument \"side\" illegal");
	}
	public static int getCheckCenterY(int centerY,int relY){
		return centerY-relY;
	}
	public static boolean checkPattern(String[][][] pattern,World world,
								int centerX,int centerY,int centerZ,
								int relX,int relY,int relZ,
								List blockList,Block mainblk){
		int checkX,checkY,checkZ;
		int offset=MultiblockHelper.getOppositeFace(world.getBlockMetadata(centerX, centerY, centerZ));
		try{
		checkX=MultiblockHelper.getCheckCenterX(offset, centerX, relX, relZ, pattern[0].length);
		checkY=MultiblockHelper.getCheckCenterY(centerY, relY);
		checkZ=MultiblockHelper.getCheckCenterZ(offset, centerZ, relX, relZ, pattern[0].length);
		}catch(Exception e){
			CrashReport cr=new CrashReport("Multiblock Error", e);
			throw new ReportedException(cr);
		}
		int realX,realY,realZ;
			for(int y=0;y<pattern.length;y++){
				for(int z=0;z<pattern[y].length;z++){
					for(int x=0;x<pattern[y][z].length;x++){
						realX=checkX+x;
						realY=checkY+y;
						realZ=checkZ-z;
						Block blk=world.getBlock(realX, realY, realZ);

						if(world.isAirBlock(realX, realY, realZ)){
							if(pattern[y][z][x]!="A"){
								return false;
							}
						}
						if(!world.isAirBlock(realX, realY, realZ)){
							if(pattern[y][z][x]=="A"){
								return false;
							}
						}
						if(pattern[y][x][z]!="B" && pattern[y][x][z]!="A"){
							if(!CLGReference.isSpecialBlkForMulti(pattern[y][x][z], blk))
								return false;
						}
						if(pattern[y][x][z]=="B" || pattern[y][x][z]=="A"){
							if(CLGReference.isSpecialBlkForMulti(pattern[y][x][z], blk))
								return false;
						}
						if(blockList.contains(blk.getClass()) && ((x==relX && y==relY && z==relZ && blk.getClass()==mainblk.getClass())||(x!=relX && y!=relY && z!=relZ && blk.getClass()!=mainblk.getClass())) ){
							if(pattern[y][z][x]=="A"){
							return false;
							}
						}
						
						}
					}
				}
		return true;
	}
	
}
