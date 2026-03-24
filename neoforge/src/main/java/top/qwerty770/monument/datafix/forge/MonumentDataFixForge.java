package top.qwerty770.monument.datafix.forge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import top.qwerty770.monument.datafix.MonumentDataFix;

@Mod("monument_data_fix")
public class MonumentDataFixForge {

    public MonumentDataFixForge(IEventBus modBus) {
        MonumentDataFix.LOGGER.info("Monument Data Fix loading!");
    }
}
