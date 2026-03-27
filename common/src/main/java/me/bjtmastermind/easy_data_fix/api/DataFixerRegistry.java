package me.bjtmastermind.easy_data_fix.api;

import static me.bjtmastermind.easy_data_fix.EasyDataFixMod.DEBUG;

import java.util.HashMap;
import java.util.Map;
import java.util.SequencedMap;
import java.util.function.Function;
import java.util.function.Supplier;

import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixerBuilder;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.templates.TypeTemplate;
import com.mojang.serialization.Dynamic;

import me.bjtmastermind.easy_data_fix.EasyDataFixMod;
import net.minecraft.util.datafix.fixes.ItemStackComponentizationFix;

public class DataFixerRegistry {
    public static Map<String, CustomDataFixer> DATA_FIXERS = new HashMap<>();
    public static Map<String, DataComponentTypeAddition> ADDITIONS = new HashMap<>();
    public static Map<String, DataComponentTypeInjection> INJECTIONS = new HashMap<>();

    public static void addDataFix(String name, CustomDataFixer dataFixer) {
        if (!DATA_FIXERS.containsKey(name)) {
            DATA_FIXERS.put(name, dataFixer);
            if (DEBUG) EasyDataFixMod.LOGGER.debug("Registered custom data fixer \"{}\"", name);
        } else {
            EasyDataFixMod.LOGGER.info("Data fixer \"{}\" already registered, ignoring", name);
        }
    }

    public static void addDataFix(String name, Function<DataFixerBuilder, DataFix> dataFixer) {
        addDataFix(name, builder -> {
            builder.addFixer(dataFixer.apply(builder));
        });
    }

    public static void addDataComponentTypeAddition(String name, DataComponentTypeAddition addition) {
        if (!ADDITIONS.containsKey(name)) {
            ADDITIONS.put(name, addition);
            if (DEBUG) EasyDataFixMod.LOGGER.debug("Registered custom data component type addition \"{}\"", name);
        } else {
            EasyDataFixMod.LOGGER.info("Data component type addition \"{}\" already registered, ignoring", name);
        }
    }

    public static void addDataComponentTypeInjection(String name, DataComponentTypeInjection injection) {
        if (!INJECTIONS.containsKey(name)) {
            INJECTIONS.put(name, injection);
            if (DEBUG) EasyDataFixMod.LOGGER.debug("Registered custom data component type injection \"{}\"", name);
        } else {
            EasyDataFixMod.LOGGER.info("Data component type injection \"{}\" already registered, ignoring", name);
        }
    }

    @FunctionalInterface
    public interface CustomDataFixer {
        void accept(DataFixerBuilder builder);
    }

    @FunctionalInterface
    public interface DataComponentTypeAddition {
        void apply(ItemStackComponentizationFix.ItemStackData itemStackData, Dynamic<?> tag);
    }

    @FunctionalInterface
    public interface DataComponentTypeInjection {
        void apply(Schema schema, SequencedMap<String, Supplier<TypeTemplate>> map);
    }
}
