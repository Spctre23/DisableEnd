package disableend.mixin;

import disableend.config.ConfigManager;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.EnderEyeItem;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderEyeItem.class)
public class EnderEyeItemMixin
{
    @Inject(at = @At("HEAD"), method = "useOn", cancellable = true)
    private void useOn(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir)
    {
        if (ConfigManager.get().disableLightingEndPortal)
        {
            context.getPlayer().sendSystemMessage(Component.literal("Lighting the end portal is currently disabled."));
            cir.cancel();
        }
    }
}