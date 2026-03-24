package top.qwerty770.monument.datafix.mixin;

import com.mojang.datafixers.DataFixerBuilder;
import net.minecraft.util.datafix.DataFixers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.qwerty770.monument.datafix.MonumentDataFix;
import top.qwerty770.monument.datafix.api.DataFixerRegistry;

@Mixin(DataFixers.class)
public class DataFixersMixin {

    @Inject(method = "addFixers", at = @At("RETURN"))
    private static void addFixers(DataFixerBuilder builder, CallbackInfo ci){
        DataFixerRegistry.DATA_FIXERS.values().forEach(fix -> fix.accept(builder));
        MonumentDataFix.LOGGER.info("{} custom data fixers initialized by Monument Data Fix!", DataFixerRegistry.DATA_FIXERS.size());
    }
}
