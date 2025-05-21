package top.qwerty770.monument.datafix.spmreborn;

import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.templates.TypeTemplate;
import net.minecraft.util.datafix.schemas.NamespacedSchema;

import java.util.Map;
import java.util.function.Supplier;

public class SPRNewChoicesSchema extends NamespacedSchema {
    public SPRNewChoicesSchema(int versionKey, Schema parent) {
        super(versionKey, parent);
    }

    @Override
    public Map<String, Supplier<TypeTemplate>> registerBlockEntities(Schema schema) {
        Map<String, Supplier<TypeTemplate>> map = super.registerBlockEntities(schema);
        map.put("spmreborn:grinder", map.remove("sweet_potato:grinder"));
        map.put("spmreborn:magic_cube", map.remove("sweet_potato:magic_cube"));
        return map;
    }
}
