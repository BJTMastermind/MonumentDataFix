package top.qwerty770.monument.datafix.fabric;

import net.fabricmc.api.ModInitializer;
import top.qwerty770.monument.datafix.MonumentDataFix;

public class MonumentDataFixPreLaunch implements ModInitializer {

    @Override
    public void onInitialize() {
        MonumentDataFix.LOGGER.info("Monument Data Fix loading!");
    }
}
