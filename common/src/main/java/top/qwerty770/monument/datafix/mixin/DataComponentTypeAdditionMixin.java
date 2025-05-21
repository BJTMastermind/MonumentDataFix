package top.qwerty770.monument.datafix.mixin;

import com.mojang.serialization.Dynamic;
import net.minecraft.util.datafix.fixes.ItemStackComponentizationFix;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.qwerty770.monument.datafix.api.DataFixerRegistry;

@Mixin(ItemStackComponentizationFix.class)
public class DataComponentTypeAdditionMixin {
    @Inject(at = @At("RETURN"), method = "fixItemStack(Lnet/minecraft/util/datafix/fixes/ItemStackComponentizationFix$ItemStackData;Lcom/mojang/serialization/Dynamic;)V")
    private static void fixItemStackInject(ItemStackComponentizationFix.ItemStackData itemStackData, Dynamic<?> tag, CallbackInfo ci) {
        DataFixerRegistry.ADDITIONS.values().forEach(addition -> addition.apply(itemStackData, tag));
    }
}
