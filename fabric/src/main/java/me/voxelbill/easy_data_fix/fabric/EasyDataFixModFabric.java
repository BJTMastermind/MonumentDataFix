package me.voxelbill.easy_data_fix.fabric;

import me.voxelbill.easy_data_fix.common.EasyDataFixMod;
import net.fabricmc.api.ModInitializer;

public class EasyDataFixModFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        EasyDataFixMod.LOGGER.info("Easy Data Fix loading!");
    }
}
