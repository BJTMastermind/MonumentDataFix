package me.bjtmastermind.easy_data_fix.mixin;

import java.util.SequencedMap;
import java.util.function.Supplier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.templates.TypeTemplate;

import me.bjtmastermind.easy_data_fix.api.DataFixerRegistry;
import net.minecraft.util.datafix.schemas.V3818_3;

@Mixin(V3818_3.class)
public class DataComponentTypeInjectionMixin {

    @Inject(method = "components", at = @At("RETURN"))
    private static void componentsInject(Schema schema, CallbackInfoReturnable<SequencedMap<String, Supplier<TypeTemplate>>> cir) {
        SequencedMap<String, Supplier<TypeTemplate>> sequencedMap = cir.getReturnValue();
        DataFixerRegistry.INJECTIONS.values().forEach(injection -> injection.apply(schema, sequencedMap));
    }
}
