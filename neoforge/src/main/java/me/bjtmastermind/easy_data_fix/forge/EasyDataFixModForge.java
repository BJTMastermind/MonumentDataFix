package me.bjtmastermind.easy_data_fix.forge;

import me.bjtmastermind.easy_data_fix.EasyDataFixMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod("easy_data_fix")
public class EasyDataFixModForge {

    public EasyDataFixModForge(IEventBus modBus) {
        EasyDataFixMod.LOGGER.info("Easy Data Fix loading!");
    }
}
