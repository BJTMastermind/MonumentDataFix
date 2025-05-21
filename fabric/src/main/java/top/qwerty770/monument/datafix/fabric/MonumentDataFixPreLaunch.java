package top.qwerty770.monument.datafix.fabric;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import top.qwerty770.monument.datafix.MonumentDataFix;

public class MonumentDataFixPreLaunch implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        MonumentDataFix.initDefaultDataFixers();
        MonumentDataFix.LOGGER.info("Monument Data Fix loading!");
    }
}
