package com.frostwizard4.Neutrino.mixin;

import com.frostwizard4.Neutrino.registry.BlockRegistry;
import net.minecraft.structure.DesertTempleGenerator;
import net.minecraft.structure.ShiftableStructurePiece;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(DesertTempleGenerator.class)
public abstract class DesertTempleMixin extends ShiftableStructurePiece {
    protected DesertTempleMixin(StructurePieceType type, int x, int y, int z, int width, int height, int depth, Direction orientation) {
        super(type, x, y, z, width, height, depth, orientation);
    }

    @Inject(at = @At("RETURN"), method = "generate")
    public void addTerracottaPot(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        this.addBlock(world, BlockRegistry.DUNGEONS_POT.getDefaultState(), 5, 1, 10, boundingBox);
        this.addBlock(world, BlockRegistry.DUNGEONS_POT.getDefaultState(), 15, 1, 10, boundingBox);
        this.addBlock(world, BlockRegistry.DUNGEONS_POT.getDefaultState(), 10, 1, 17, boundingBox);
        this.addBlock(world, BlockRegistry.SHATTERED_SWORD_SHRINE.getDefaultState(), 10, 0, 10, boundingBox);
    }
}