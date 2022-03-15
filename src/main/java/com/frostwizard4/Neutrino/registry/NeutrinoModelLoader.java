package com.frostwizard4.Neutrino.registry;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3f;

public class NeutrinoModelLoader {
    public static final Identifier ELECTRICITY_ATLAS = new Identifier("neutrino", "textures/atlas/electricity.png");
    public static final SpriteIdentifier ELECTRICITY = new SpriteIdentifier(ELECTRICITY_ATLAS, new Identifier("neutrino", "block/electricity"));

    public static void renderElectricityOverlay(MinecraftClient client, MatrixStack matrices) {
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
        RenderSystem.depthFunc(519);
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableTexture();
        Sprite sprite = ELECTRICITY.getSprite();
        RenderSystem.setShaderTexture(0, sprite.getAtlas().getId());
        float f = sprite.getMinU();
        float g = sprite.getMaxU();
        float h = (f + g) / 2.0f;
        float i = sprite.getMinV();
        float j = sprite.getMaxV();
        float k = (i + j) / 2.0f;
        float l = sprite.getAnimationFrameDelta();
        float m = MathHelper.lerp(l, f, h);
        float n = MathHelper.lerp(l, g, h);
        float o = MathHelper.lerp(l, i, k);
        float p = MathHelper.lerp(l, j, k);
        float q = 1.0f;
        for (int r = 0; r < 2; ++r) {
            matrices.push();
            float s = -0.5f;
            float t = 0.5f;
            float u = -0.5f;
            float v = 0.5f;
            float w = -0.5f;
            matrices.translate((float) (-(r * 2 - 1)) * 0.24f, -0.3f, 0.0);
            matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion((float) (r * 2 - 1) * 10.0f));
            Matrix4f matrix4f = matrices.peek().getPositionMatrix();
            bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR_TEXTURE);
            bufferBuilder.vertex(matrix4f, -0.4f, -0.4f, -0.4f).color(1.0f, 1.0f, 1.0f, 0.7f).texture(n, p).next();
            bufferBuilder.vertex(matrix4f, 0.4f, -0.4f, -0.4f).color(1.0f, 1.0f, 1.0f, 0.7f).texture(m, p).next();
            bufferBuilder.vertex(matrix4f, 0.4f, 0.4f, -0.4f).color(1.0f, 1.0f, 1.0f, 0.7f).texture(m, o).next();
            bufferBuilder.vertex(matrix4f, -0.4f, 0.4f, -0.4f).color(1.0f, 1.0f, 1.0f, 0.7f).texture(n, o).next();
            bufferBuilder.end();
            BufferRenderer.draw(bufferBuilder);
            matrices.pop();
        }
    }
}
