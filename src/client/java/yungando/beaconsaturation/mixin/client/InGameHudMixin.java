package yungando.beaconsaturation.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
  @Shadow
  private int ticks;

  @Inject(method = "renderFood", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getHungerManager()Lnet/minecraft/entity/player/HungerManager;", ordinal = 1))
  public void renderFood(DrawContext context, PlayerEntity player, int top, int right, CallbackInfo ci,
      @Local(ordinal = 3) int j, @Local(ordinal = 4) LocalIntRef k) {
    if (player.hasStatusEffect(StatusEffects.SATURATION)) {
      if (j == (this.ticks % MathHelper.ceil(25f))) {
        k.set(k.get() - 2);
      }
    }
  }
}
