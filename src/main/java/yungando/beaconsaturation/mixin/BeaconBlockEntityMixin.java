package yungando.beaconsaturation.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BeaconBlockEntity.class)
public class BeaconBlockEntityMixin {
  @Inject(method = "applyPlayerEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;)Z", shift = At.Shift.AFTER, ordinal = 1))
  private static void applyPlayerEffects(World world, BlockPos pos, int beaconLevel,
      RegistryEntry<StatusEffect> primaryEffect, RegistryEntry<StatusEffect> secondaryEffect, CallbackInfo ci,
      @Local PlayerEntity playerEntity, @Local(ordinal = 2) int j) {
    if (secondaryEffect.equals(StatusEffects.REGENERATION)) {
      playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, j, 0, true, true));
    }
  }
}
