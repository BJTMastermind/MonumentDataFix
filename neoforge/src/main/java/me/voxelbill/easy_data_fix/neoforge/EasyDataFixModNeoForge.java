package me.voxelbill.easy_data_fix.neoforge;

import me.voxelbill.easy_data_fix.common.EasyDataFixMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(EasyDataFixMod.MOD_ID)
public class EasyDataFixModNeoForge {

    public EasyDataFixModNeoForge(IEventBus modEventBus) {
        EasyDataFixMod.LOGGER.info("Easy Data Fix loading!");
    }
}