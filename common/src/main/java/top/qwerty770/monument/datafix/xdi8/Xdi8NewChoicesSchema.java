package top.qwerty770.monument.datafix.xdi8;

import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.templates.TypeTemplate;
import net.minecraft.util.datafix.schemas.NamespacedSchema;

import java.util.Map;
import java.util.function.Supplier;

public class Xdi8NewChoicesSchema extends NamespacedSchema {
    public Xdi8NewChoicesSchema(int versionKey, Schema parent) {
        super(versionKey, parent);
    }

    @Override
    public Map<String, Supplier<TypeTemplate>> registerBlockEntities(Schema schema) {
        Map<String, Supplier<TypeTemplate>> map = super.registerBlockEntities(schema);
        schema.registerSimple(map, "firefly8:portal_top");
        schema.registerSimple(map, "firefly8:back_portal_core");
        schema.registerSimple(map, "firefly8:redwood_sign");
        return map;
    }

    @Override
    public Map<String, Supplier<TypeTemplate>> registerEntities(Schema schema) {
        Map<String, Supplier<TypeTemplate>> map = super.registerEntities(schema);
        schema.registerSimple(map, "firefly8:firefly");
        return map;
    }
}
