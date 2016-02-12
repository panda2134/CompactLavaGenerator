package panda2134.CLG.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import panda2134.CLG.util.CLGReference;

public class CLGPumpTESR extends TileEntitySpecialRenderer {

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z,
			float tick) {
		Tessellator tes = Tessellator.instance;
		ResourceLocation loc = new ResourceLocation(CLGReference.modid
				+ ":textures/blocks/PumpPipe.png");

		bindTexture(loc);
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		tes.startDrawingQuads();
		tes.setNormal(0.5F, 0.5F, 0.5F);

		tes.addVertexWithUV(0.375, 0, 0.375, 0.625, 0);
		tes.addVertexWithUV(0.375, -1, 0.375, 0.625, 1);
		tes.addVertexWithUV(0.375, -1, 0.625, 0.375, 1);
		tes.addVertexWithUV(0.375, 0, 0.625, 0.375, 0);
		tes.addVertexWithUV(0.375, 0, 0.625, 0.375, 0);
		tes.addVertexWithUV(0.375, -1, 0.625, 0.375, 1);
		tes.addVertexWithUV(0.375, -1, 0.375, 0.625, 1);
		tes.addVertexWithUV(0.375, 0, 0.375, 0.625, 0);

		tes.addVertexWithUV(0.625, 0, 0.375, 0.625, 0);
		tes.addVertexWithUV(0.625, -1, 0.375, 0.625, 1);
		tes.addVertexWithUV(0.625, -1, 0.625, 0.375, 1);
		tes.addVertexWithUV(0.625, 0, 0.625, 0.375, 0);
		tes.addVertexWithUV(0.625, 0, 0.625, 0.375, 0);
		tes.addVertexWithUV(0.625, -1, 0.625, 0.375, 1);
		tes.addVertexWithUV(0.625, -1, 0.375, 0.625, 1);
		tes.addVertexWithUV(0.625, 0, 0.375, 0.625, 0);

		tes.addVertexWithUV(0.375, 0, 0.375, 0.375, 0);
		tes.addVertexWithUV(0.625, 0, 0.375, 0.625, 0);
		tes.addVertexWithUV(0.625, -1, 0.375, 0.625, 1);
		tes.addVertexWithUV(0.375, -1, 0.375, 0.375, 1);
		tes.addVertexWithUV(0.375, -1, 0.375, 0.375, 1);
		tes.addVertexWithUV(0.625, -1, 0.375, 0.625, 1);
		tes.addVertexWithUV(0.625, 0, 0.375, 0.625, 0);
		tes.addVertexWithUV(0.375, 0, 0.375, 0.375, 0);

		tes.addVertexWithUV(0.375, 0, 0.625, 0.375, 0);
		tes.addVertexWithUV(0.625, 0, 0.625, 0.625, 0);
		tes.addVertexWithUV(0.625, -1, 0.625, 0.625, 1);
		tes.addVertexWithUV(0.375, -1, 0.625, 0.375, 1);
		tes.addVertexWithUV(0.375, -1, 0.625, 0.375, 1);
		tes.addVertexWithUV(0.625, -1, 0.625, 0.625, 1);
		tes.addVertexWithUV(0.625, 0, 0.625, 0.625, 0);
		tes.addVertexWithUV(0.375, 0, 0.625, 0.375, 0);

		tes.addVertexWithUV(0.375, -1, 0.625, 0, 0);
		tes.addVertexWithUV(0.625, -1, 0.625, 0.25, 0);
		tes.addVertexWithUV(0.625, -1, 0.375, 0.25, 0.25);
		tes.addVertexWithUV(0.375, -1, 0.375, 0, 0.25);
		tes.addVertexWithUV(0.375, -1, 0.375, 0, 0.25);
		tes.addVertexWithUV(0.625, -1, 0.375, 0.25, 0.25);
		tes.addVertexWithUV(0.625, -1, 0.625, 0.25, 0);
		tes.addVertexWithUV(0.375, -1, 0.625, 0, 0);

		tes.draw();
		tes.setNormal(-0.5F, -0.5F, -0.5F);
		GL11.glPopMatrix();
	}
}
