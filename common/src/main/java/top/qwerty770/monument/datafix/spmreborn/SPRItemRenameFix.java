package top.qwerty770.monument.datafix.spmreborn;

import com.mojang.datafixers.schemas.Schema;
import net.minecraft.util.datafix.fixes.ItemRenameFix;
import org.jetbrains.annotations.NotNull;
import top.qwerty770.monument.datafix.MonumentDataFix;

import static top.qwerty770.monument.datafix.MonumentDataFix.DEBUG;

public class SPRItemRenameFix extends ItemRenameFix {
    public SPRItemRenameFix(Schema outputSchema, String name) {
        super(outputSchema, name);
    }

    @Override
    protected @NotNull String fixItem(String item) {
        if (item.startsWith("sweet_potato:")) {
            if (DEBUG) MonumentDataFix.LOGGER.debug("Renaming item {} to {}", item, item.replace("sweet_potato:", "spmreborn:"));
            return switch (item) {
                case "sweet_potato:enchanted_sapling" -> "spmreborn:enchanted_spruce_sapling";
                case "sweet_potato:enchanted_leaves" -> "spmreborn:enchanted_spruce_leaves";
                case "sweet_potato:enchanted_crop_seeds" -> "spmreborn:enchanted_wheat_seeds";
                case "sweet_potato:enchanted_tuber" -> "spmreborn:enchanted_potato";
                case "sweet_potato:enchanted_crop" -> "spmreborn:enchanted_carrot";
                default -> item.replace("sweet_potato:", "spmreborn:");
            };
        } else return item;
    }
}
