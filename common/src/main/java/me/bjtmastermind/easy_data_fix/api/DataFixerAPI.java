package me.bjtmastermind.easy_data_fix.api;

import java.util.Map;
import java.util.Objects;
import java.util.function.UnaryOperator;

import net.minecraft.util.datafix.schemas.NamespacedSchema;

public class DataFixerAPI {
    public static UnaryOperator<String> createRenamerNoNamespace(Map<String, String> renameMap) {
        return string -> (String)renameMap.getOrDefault(string, string);
    }

    public static UnaryOperator<String> createRenamer(Map<String, String> renameMap) {
        return string -> (String)renameMap.getOrDefault(NamespacedSchema.ensureNamespaced(string), string);
    }

    public static UnaryOperator<String> createRenamer(String oldName, String newName) {
        return string3 -> Objects.equals(NamespacedSchema.ensureNamespaced(string3), oldName) ? newName : string3;
    }
}
