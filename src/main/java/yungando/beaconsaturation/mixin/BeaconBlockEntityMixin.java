package yungando.beaconsaturation.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BeaconBlockEntity.class)
public class BeaconBlockEntityMixin {
  @Inject(method = "applyEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z", shift = At.Shift.AFTER, ordinal = 1))
  private static void applyEffects(Level world, BlockPos pos, int beaconLevel, Holder<MobEffect> primaryEffect, Holder<MobEffect> secondaryEffect, CallbackInfo ci, @Local(name = "player") Player player, @Local(name = "durationTicks") int durationTicks) {
    if (secondaryEffect.equals(MobEffects.REGENERATION)) {
      player.addEffect(new MobEffectInstance(MobEffects.SATURATION, durationTicks, 0, true, true));
    }
  }
}
