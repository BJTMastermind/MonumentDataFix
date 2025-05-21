package top.qwerty770.monument.datafix.api;

import net.minecraft.util.datafix.schemas.NamespacedSchema;
import org.jetbrains.annotations.ApiStatus;

import java.util.Map;
import java.util.Objects;
import java.util.function.UnaryOperator;

@SuppressWarnings("unused")
@ApiStatus.AvailableSince("1.0.0")
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
