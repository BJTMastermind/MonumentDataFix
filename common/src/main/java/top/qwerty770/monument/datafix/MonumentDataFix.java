package top.qwerty770.monument.datafix;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.schemas.Schema;
import net.minecraft.util.datafix.fixes.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.qwerty770.monument.datafix.api.DataFixerAPI;
import top.qwerty770.monument.datafix.api.DataFixerRegistry;
import top.qwerty770.monument.datafix.spmreborn.*;
import top.qwerty770.monument.datafix.xdi8.Xdi8NewChoicesSchema;

import java.util.Set;
import java.util.stream.Stream;

public class MonumentDataFix {
    public static final boolean DEBUG = false;
    public static Logger LOGGER = LoggerFactory.getLogger("MonumentDataFix");

    public static void initDefaultDataFixers() {
        DataFixerRegistry.addDataFix("Rename sweet_potato namespace to spmreborn", builder -> {
            Schema schema0 = builder.addSchema(3117, SPRSchema0::new);  // 1.19.1
            builder.addFixer(new AddNewChoices(schema0, "Add Sweet Potato block entities", References.BLOCK_ENTITY));
            Schema schema1 = builder.addSchema(3117, 1, SPRNewChoicesSchema::new);  // 1.19.1
            builder.addFixer(new AdvancementsRenameFix(schema1, false, "Rename advancements", string ->
                    string.startsWith("sweet_potato:") ? string.replace("sweet_potato:", "spmreborn:") : string));
            ImmutableMap<String, String> renameMap = ImmutableMap.<String, String>builder()
                    .put("sweet_potato:grinder", "spmreborn:grinder")
                    .put("sweet_potato:magic_cube", "spmreborn:magic_cube")
                    .build();
            builder.addFixer(BlockEntityRenameFix.create(schema1, "Rename block entities", DataFixerAPI.createRenamer(renameMap)));
            builder.addFixer(new SPRBlockRenameFix(schema1, "Rename blocks"));
            builder.addFixer(new SPRCriteriaRenameFix(schema1, "Rename advancement criteria"));
            builder.addFixer(new SPRItemRenameFix(schema1, "Rename items"));
            builder.addFixer(new NamespacedTypeRenameFix(schema1, "Rename recipes", References.RECIPE, string ->
                    string.startsWith("sweet_potato:") ? string.replace("sweet_potato:", "spmreborn:") : string));
        });/*
        DataFixerRegistry.addDataFix("Sweet Potato Reborn item stack componentization", builder -> { // 1.20.5 (24w09a)
            Schema schema1 = builder.addSchema(3818, 100, (version, schema) -> new DataComponentTypeInjectionSchema(version, schema){
                public SequencedMap<String, Supplier<TypeTemplate>> components(Schema schema) {
                    SequencedMap<String, Supplier<TypeTemplate>> sequencedMap = new LinkedHashMap<>();
                    sequencedMap.put("spmreborn:status_effects", () -> DSL.list(DSL.optionalFields("id", DSL.string().buildTemplate(), "duration", DSL.intType().buildTemplate(), "amplifier", DSL.intType().buildTemplate(), "chance", DSL.floatType().buildTemplate())));
                    return sequencedMap;
                }
            });
            builder.addFixer(new WriteAndReadFix(schema1, "Inject Sweet Potato Reborn data component types", References.DATA_COMPONENTS));
            Schema schema2 = builder.addSchema(3818, 101, DataComponentTypeAdditionSchema::new);
            builder.addFixer(new SPRItemStackComponentizationFix(schema2));
        });*/
        DataFixerRegistry.addDataFix("Add Xdi8 Aho block entities & entities", builder -> {
            Schema schema = builder.addSchema(2976, Xdi8NewChoicesSchema::new);  // 1.18.2
            builder.addFixer(new AddNewChoices(schema, "Add Xdi8 Aho block entities", References.BLOCK_ENTITY));
            builder.addFixer(new AddNewChoices(schema, "Add Xdi8 Aho entities", References.ENTITY));
        });/*
        DataFixerRegistry.addDataFix("Xdi8 Aho item stack componentization", builder -> {  // 1.20.5 (24w09a)
            Schema schema1 = builder.addSchema(3818, 102, (version, schema) -> new DataComponentTypeInjectionSchema(version, schema){
                public SequencedMap<String, Supplier<TypeTemplate>> components(Schema schema) {
                    SequencedMap<String, Supplier<TypeTemplate>> sequencedMap = new LinkedHashMap<>();
                    sequencedMap.put("firefly8:stored_items", () -> DSL.list(References.ITEM_STACK.in(schema)));
                    return sequencedMap;
                }
            });
            builder.addFixer(new WriteAndReadFix(schema1, "Inject Xdi8 Aho data component types", References.DATA_COMPONENTS));
            Schema schema2 = builder.addSchema(3818, 103, DataComponentTypeAdditionSchema::new);
            builder.addFixer(new Xdi8ItemStackComponentizationFix(schema2));
        });*/

        DataFixerRegistry.addDataComponentTypeAddition("Sweet Potato Reborn item stack componentization",
                (itemStackData, tag) -> {
                    final Set<String> ENCHANTED_SWEET_POTATOES = Set.of(
                            "sweet_potato:enchanted_purple_potato",
                            "sweet_potato:enchanted_red_potato",
                            "sweet_potato:enchanted_white_potato"
                    );
                    if (itemStackData.is(ENCHANTED_SWEET_POTATOES)) {
                        itemStackData.moveTagToComponent("statusEffects", "spmreborn:status_effects");
                        itemStackData.moveTagToComponent("displayIndex", "spmreborn:display_index");
                    }
                });
        DataFixerRegistry.addDataComponentTypeAddition("Xdi8 Aho item stack componentization",
                (itemStackData, tag) -> {
                    if (itemStackData.is("firefly8:xdi8aho")) {
                        itemStackData.moveTagToComponent("Totem", "firefly8:totem", tag.createString(""));
                    }
                    if (itemStackData.is("firefly8:bundler")) {
                        itemStackData.moveTagToComponent("StoredItems", "firefly8:stored_items", tag.createList(Stream.empty()));
                    }
                    if (itemStackData.is("firefly8:tinted_firefly_bottle")) {
                        itemStackData.moveTagToComponent("Fireflies", "firefly8:fireflies");
                    }
                });

        DataFixerRegistry.addDataComponentTypeInjection("Inject Sweet Potato Reborn data component types",
                (schema, sequencedMap) ->
                        sequencedMap.put("spmreborn:status_effects", () -> DSL.list(
                                DSL.optionalFields("id", DSL.string().buildTemplate(),
                                "duration", DSL.intType().buildTemplate(),
                                "amplifier", DSL.intType().buildTemplate(),
                                "chance", DSL.floatType().buildTemplate())
                )));
        DataFixerRegistry.addDataComponentTypeInjection("Inject Xdi8 Aho data component types",
                (schema, sequencedMap) ->
                        sequencedMap.put("firefly8:stored_items", () -> DSL.list(References.ITEM_STACK.in(schema))));
    }
}
