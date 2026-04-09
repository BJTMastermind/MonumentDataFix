package me.bjtmastermind.easy_data_fix.api;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixerBuilder;

import me.bjtmastermind.easy_data_fix.EasyDataFixMod;

public class DataFixerRegistry {
    public static Map<String, CustomDataFixer> DATA_FIXERS = new HashMap<>();

    public static void addDataFix(String name, CustomDataFixer dataFixer) {
        if (!DATA_FIXERS.containsKey(name)) {
            DATA_FIXERS.put(name, dataFixer);
            if (EasyDataFixMod.DEBUG) EasyDataFixMod.LOGGER.debug("[DEV-ENV-DEBUG]: Registered custom data fixer \"{}\"", name);
        } else {
            EasyDataFixMod.LOGGER.info("Data fixer \"{}\" already registered, ignoring", name);
        }
    }

    public static void addDataFix(String name, Function<DataFixerBuilder, DataFix> dataFixer) {
        addDataFix(name, builder -> {
            builder.addFixer(dataFixer.apply(builder));
        });
    }

    @FunctionalInterface
    public interface CustomDataFixer {
        void accept(DataFixerBuilder builder);
    }
}
