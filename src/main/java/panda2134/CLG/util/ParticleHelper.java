package panda2134.CLG.util;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ParticleHelper {
	public static void playSidedRandomParticle(String particle, World world,
			int xCoord, int yCoord, int zCoord, ForgeDirection direction,
			Random random) {
		float x = xCoord + 0.5F + direction.getOpposite().offsetX * 0.25F;
		float y = yCoord + random.nextFloat() + direction.getOpposite().offsetY
				* 0.25F;
		float z = zCoord + 0.5F + direction.getOpposite().offsetZ * 0.25F;
		float xDelta = random.nextFloat() % 0.4F;
		float zDelta = random.nextFloat() % 0.4F;
		x += direction.offsetX;
		y += direction.offsetY;
		z += direction.offsetZ;
		world.spawnParticle(particle, x + xDelta, y, z + zDelta, 0, 0, 0);
	}
}
