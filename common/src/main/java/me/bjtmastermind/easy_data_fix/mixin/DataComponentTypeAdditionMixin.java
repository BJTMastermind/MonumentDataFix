package me.bjtmastermind.easy_data_fix.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.serialization.Dynamic;

import me.bjtmastermind.easy_data_fix.api.DataFixerRegistry;
import net.minecraft.util.datafix.fixes.ItemStackComponentizationFix;

@Mixin(ItemStackComponentizationFix.class)
public class DataComponentTypeAdditionMixin {

    @Inject(method = "fixItemStack(Lnet/minecraft/util/datafix/fixes/ItemStackComponentizationFix$ItemStackData;Lcom/mojang/serialization/Dynamic;)V", at = @At("RETURN"))
    private static void fixItemStackInject(ItemStackComponentizationFix.ItemStackData itemStackData, Dynamic<?> tag, CallbackInfo ci) {
        DataFixerRegistry.ADDITIONS.values().forEach(addition -> addition.apply(itemStackData, tag));
    }
}
