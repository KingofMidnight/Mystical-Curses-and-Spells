package net.kmidnight.mysticalcursesandspells.event;

import net.kmidnight.mysticalcursesandspells.MidnightMcas;
import net.kmidnight.mysticalcursesandspells.command.McasCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MidnightMcas.MOD_ID)
public class EventHandler {

    @SubscribeEvent
    public static void commandRegister(RegisterCommandsEvent event) {
        McasCommand.register(event.getDispatcher());
    }
    private EventHandler() {
    }
}
