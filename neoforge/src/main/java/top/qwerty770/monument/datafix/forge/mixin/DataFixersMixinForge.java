package top.qwerty770.monument.datafix.forge.mixin;

import com.mojang.datafixers.DataFixerBuilder;
import net.minecraft.util.datafix.DataFixers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.qwerty770.monument.datafix.MonumentDataFix;

@Mixin(DataFixers.class)
public class DataFixersMixinForge {
    @Inject(at = @At("HEAD"), method = "addFixers")
    private static void addFixers(DataFixerBuilder builder, CallbackInfo ci){
        MonumentDataFix.initDefaultDataFixers();
        MonumentDataFix.LOGGER.info("Monument Data Fix loading!");
    }
}
