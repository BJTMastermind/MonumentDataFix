package top.qwerty770.monument.datafix.spmreborn;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Dynamic;
import net.minecraft.util.datafix.fixes.References;

public class SPRCriteriaRenameFix extends DataFix {
    private final String name;

    public SPRCriteriaRenameFix(Schema outputSchema, String name) {
        super(outputSchema, false);
        this.name = name;
    }

    @Override
    protected TypeRewriteRule makeRule() {
        return this.fixTypeEverywhereTyped(
                this.name, this.getInputSchema().getType(References.ADVANCEMENTS), typed -> typed.update(DSL.remainderFinder(), this::fixAdvancements)
        );
    }

    private Dynamic<?> fixAdvancements(Dynamic<?> advancementData) {
        return advancementData.update(
                "minecraft:husbandry/balanced_diet",
                dynamic -> dynamic.update(
                        "criteria",
                        dynamicx -> dynamicx.updateMapValues(
                                pair -> pair.mapFirst(
                                        dynamicxx -> DataFixUtils.orElse(
                                                dynamicxx.asString().map(string -> {
                                                    if (string.startsWith("sweet_potato:balanced_diet__food$")) {
                                                        return dynamicxx.createString(string
                                                                .replace("sweet_potato:balanced_diet__food$", "spmreborn:balanced_diet_food_")
                                                                .replace("sweet_potato:", ""));
                                                    } else if (string.startsWith("spmreborn:balanced_diet__food_")) {
                                                        return dynamicxx.createString(string
                                                                .replace("spmreborn:balanced_diet__food_", "spmreborn:balanced_diet_food_")
                                                                .replace("_spmreborn:", "_"));
                                                    } else return dynamicxx.createString(string);
                                                }).result(), dynamicxx
                                        )
                                )
                        )
                )
        );
    }
}
