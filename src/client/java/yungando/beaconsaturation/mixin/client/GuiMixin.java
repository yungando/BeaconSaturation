package yungando.beaconsaturation.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {
  @Shadow
  private int tickCount;

  @Inject(method = "extractFood", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;getFoodData()Lnet/minecraft/world/food/FoodData;", ordinal = 1))
  public void renderFood(GuiGraphicsExtractor graphics, Player player, int yLineBase, int xRight, CallbackInfo ci, @Local(name = "i") int j, @Local(name = "yo") LocalIntRef k) {
    if (player.hasEffect(MobEffects.SATURATION)) {
      if (j == (this.tickCount % Mth.ceil(25f))) {
        k.set(k.get() - 2);
      }
    }
  }
}
