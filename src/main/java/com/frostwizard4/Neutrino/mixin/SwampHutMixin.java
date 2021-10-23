package com.frostwizard4.Neutrino.mixin;

import com.frostwizard4.Neutrino.NeutrinoMain;
import com.frostwizard4.Neutrino.registry.BlockRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.structure.DesertTempleGenerator;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.structure.StructurePieceWithDimensions;
import net.minecraft.structure.SwampHutGenerator;
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

@Mixin(SwampHutGenerator.class)
public abstract class SwampHutMixin extends StructurePieceWithDimensions {
    protected SwampHutMixin(StructurePieceType type, int x, int y, int z, int width, int height, int depth, Direction orientation) {
        super(type, x, y, z, width, height, depth, orientation);
    }

    @Inject(at = @At("RETURN"), method = "generate")
    public void neutrino$addDaturaFlower(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        this.addBlock(world, BlockRegistry.DATURA.getDefaultState(), 2, 2, 0, boundingBox);
    }
}