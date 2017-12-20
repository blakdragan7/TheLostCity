package com.blakdragan7.TheLostCity.Client.Render.Tile;

import org.lwjgl.opengl.GL11;

import com.blakdragan7.TheLostCity.Common.Block.TLCBlocks;
import com.blakdragan7.TheLostCity.Common.Block.Tile.TileEntityCrystal;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class CrystalTESR extends TileEntitySpecialRenderer<TileEntityCrystal> {
	@Override
	public void render(TileEntityCrystal te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushAttrib();
		GlStateManager.pushMatrix();
		
		GlStateManager.translate(x, y, z);
		GlStateManager.disableRescaleNormal();
		
		renderHandles(te);
		
		GlStateManager.popMatrix();
        GlStateManager.popAttrib();
	}
	
	private void renderHandles(TileEntityCrystal te) {
        GlStateManager.pushMatrix();

        GlStateManager.translate(.5, 0, .5);
        long angle = (System.currentTimeMillis() / 10) % 360;
        GlStateManager.rotate(angle, 0, 1, 0);

        RenderHelper.disableStandardItemLighting();
        this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        if (Minecraft.isAmbientOcclusionEnabled()) {
            GlStateManager.shadeModel(GL11.GL_SMOOTH);
        } else {
            GlStateManager.shadeModel(GL11.GL_FLAT);
        }

        World world = te.getWorld();
        // Translate back to local view coordinates so that we can do the acual rendering here
        GlStateManager.translate(-te.getPos().getX(), -te.getPos().getY(), -te.getPos().getZ());

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);

        IBlockState state = TLCBlocks.crystalBlock.getDefaultState();
        BlockRendererDispatcher dispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
        IBakedModel model = dispatcher.getModelForState(state);
        dispatcher.getBlockModelRenderer().renderModel(world, model, state, te.getPos(), bufferBuilder, true);
        tessellator.draw();

        RenderHelper.enableStandardItemLighting();
        GlStateManager.popMatrix();
    }
	
	private void renderItem(TileEntityCrystal te) {
        ItemStack stack = te.getStack();
        if (!stack.isEmpty()) {
            RenderHelper.enableStandardItemLighting();
            GlStateManager.enableLighting();
            GlStateManager.pushMatrix();
            // Translate to the center of the block and .9 points higher
            GlStateManager.translate(.5, .9, .5);
            GlStateManager.scale(.4f, .4f, .4f);

            Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.NONE);

            GlStateManager.popMatrix();
        }
    }
}
