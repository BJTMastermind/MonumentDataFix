package me.bjtmastermind.easy_data_fix.mixin;

import com.mojang.datafixers.DataFixerBuilder;

import me.bjtmastermind.easy_data_fix.EasyDataFixMod;
import me.bjtmastermind.easy_data_fix.api.DataFixerRegistry;
import net.minecraft.util.datafix.DataFixers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DataFixers.class)
public class DataFixersMixin {

    @Inject(method = "addFixers", at = @At("RETURN"))
    private static void addFixers(DataFixerBuilder builder, CallbackInfo ci){
        DataFixerRegistry.DATA_FIXERS.values().forEach(fix -> fix.accept(builder));
        EasyDataFixMod.LOGGER.info("{} custom data fixers initialized by Easy Data Fix!", DataFixerRegistry.DATA_FIXERS.size());
    }
}
