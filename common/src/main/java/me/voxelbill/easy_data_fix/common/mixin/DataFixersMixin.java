package me.voxelbill.easy_data_fix.common.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.datafixers.DataFixerBuilder;

import me.voxelbill.easy_data_fix.common.EasyDataFixMod;
import me.voxelbill.easy_data_fix.common.api.DataFixerRegistry;
import net.minecraft.util.datafix.DataFixers;
import net.minecraft.util.filefix.FileFixerUpper;

@Mixin(DataFixers.class)
public class DataFixersMixin {

    @Inject(method = "addFixers", at = @At("RETURN"))
    private static void addFixers(DataFixerBuilder builder, FileFixerUpper.Builder fileFixerBuilder, CallbackInfo ci) {
        DataFixerRegistry.DATA_FIXERS.values().forEach(fix -> fix.accept(builder));
        EasyDataFixMod.LOGGER.info("{} custom data fixers initialized by Easy Data Fix!", DataFixerRegistry.DATA_FIXERS.size());
    }
}
