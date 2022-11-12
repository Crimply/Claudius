package me.crimp.claudius.mixins.mixins;


import me.crimp.claudius.Claudius;
import me.crimp.claudius.mod.modules.render.SwingSpeed;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(EntityLivingBase.class)
public abstract class EntityLivingBaseMixin extends Entity {
    public EntityLivingBaseMixin(World world) {
        super(world);
    }
    @Shadow public abstract boolean isPotionActive(Potion potionIn);
    @Shadow @Nullable
    public abstract PotionEffect getActivePotionEffect(Potion potionIn);

    @Shadow
    protected abstract boolean isPlayer();

    /**
     * Controls how fast the swinging animation is.
     * Modified by numerous swing speed parameters controlled within the config.
     */
    @Inject(method = "getArmSwingAnimationEnd", at = @At(value = "HEAD"), cancellable = true)
    protected void onGetArmSwingAnimationEnd(CallbackInfoReturnable<Integer> callback) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        if (player == null || !this.isPlayer())
            return;

        if (SwingSpeed.INSTANCE.isEnabled()){
            callback.setReturnValue(6);
        } else {
            callback.setReturnValue(20);
        }

    }
}