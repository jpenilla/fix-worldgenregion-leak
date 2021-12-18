package xyz.jpenilla.fixworldgenregionleak.mixin;

import java.util.function.Supplier;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.NoiseChunk;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseSampler;
import net.minecraft.world.level.levelgen.blending.Blender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkAccess.class)
abstract class ChunkAccessMixin {
    @Inject(
            at = @At("HEAD"),
            method = "getOrCreateNoiseChunk(Lnet/minecraft/world/level/levelgen/NoiseSampler;Ljava/util/function/Supplier;Lnet/minecraft/world/level/levelgen/NoiseGeneratorSettings;Lnet/minecraft/world/level/levelgen/Aquifer$FluidPicker;Lnet/minecraft/world/level/levelgen/blending/Blender;)Lnet/minecraft/world/level/levelgen/NoiseChunk;"
    )
    public void injectGetNoiseChunk(
            final NoiseSampler noiseSampler,
            final Supplier<NoiseChunk.NoiseFiller> supplier,
            final NoiseGeneratorSettings noiseGeneratorSettings,
            final Aquifer.FluidPicker fluidPicker,
            final Blender blender,
            final CallbackInfoReturnable<NoiseChunk> cir
    ) {
        if (blender != Blender.empty()) {
            cir.setReturnValue(NoiseChunk.forChunk((ChunkAccess) (Object) this, noiseSampler, supplier, noiseGeneratorSettings, fluidPicker, blender));
        }
    }
}
