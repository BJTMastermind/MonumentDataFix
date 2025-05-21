package top.qwerty770.monument.datafix.api;

import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixerBuilder;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.templates.TypeTemplate;
import com.mojang.serialization.Dynamic;
import net.minecraft.util.datafix.fixes.ItemStackComponentizationFix;
import org.jetbrains.annotations.ApiStatus;
import top.qwerty770.monument.datafix.MonumentDataFix;

import java.util.HashMap;
import java.util.Map;
import java.util.SequencedMap;
import java.util.function.Function;
import java.util.function.Supplier;

import static top.qwerty770.monument.datafix.MonumentDataFix.DEBUG;

@SuppressWarnings("unused")
@ApiStatus.AvailableSince("1.0.0")
public class DataFixerRegistry {
    public static Map<String, CustomDataFixer> DATA_FIXERS = new HashMap<>();
    public static Map<String, DataComponentTypeAddition> ADDITIONS = new HashMap<>();
    public static Map<String, DataComponentTypeInjection> INJECTIONS = new HashMap<>();

    public static void addDataFix(String name, CustomDataFixer dataFixer) {
        if (!DATA_FIXERS.containsKey(name)) {
            DATA_FIXERS.put(name, dataFixer);
            if (DEBUG) MonumentDataFix.LOGGER.debug("Registered custom data fixer \"{}\"", name);
        } else {
            MonumentDataFix.LOGGER.info("Data fixer \"{}\" already registered, ignoring", name);
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
            if (DEBUG) MonumentDataFix.LOGGER.debug("Registered custom data component type addition \"{}\"", name);
        } else {
            MonumentDataFix.LOGGER.info("Data component type addition \"{}\" already registered, ignoring", name);
        }
    }

    public static void addDataComponentTypeInjection(String name, DataComponentTypeInjection injection) {
        if (!INJECTIONS.containsKey(name)) {
            INJECTIONS.put(name, injection);
            if (DEBUG) MonumentDataFix.LOGGER.debug("Registered custom data component type injection \"{}\"", name);
        } else {
            MonumentDataFix.LOGGER.info("Data component type injection \"{}\" already registered, ignoring", name);
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
