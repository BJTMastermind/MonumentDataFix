package top.qwerty770.monument.datafix.spmreborn;

import com.mojang.datafixers.schemas.Schema;
import net.minecraft.util.datafix.fixes.BlockRenameFix;
import org.jetbrains.annotations.NotNull;
import top.qwerty770.monument.datafix.MonumentDataFix;

import static top.qwerty770.monument.datafix.MonumentDataFix.DEBUG;

public class SPRBlockRenameFix extends BlockRenameFix {
    public SPRBlockRenameFix(Schema outputSchema, String name) {
        super(outputSchema, name);
    }

    @Override
    protected @NotNull String renameBlock(String name) {
        if (name.startsWith("sweet_potato:")) {
            if (DEBUG) MonumentDataFix.LOGGER.debug("Renaming block {} to {}", name, name.replace("sweet_potato:", "spmreborn:"));
            return switch (name) {
                case "sweet_potato:enchanted_sapling" -> "spmreborn:enchanted_spruce_sapling";
                case "sweet_potato:enchanted_leaves" -> "spmreborn:enchanted_spruce_leaves";
                case "sweet_potato:enchanted_tubers" -> "spmreborn:enchanted_potatoes";
                case "sweet_potato:enchanted_crops" -> "spmreborn:enchanted_wheat";
                default -> name.replace("sweet_potato:", "spmreborn:");
            };
        } else return name;
    }
}
