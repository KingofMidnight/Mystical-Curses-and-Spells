package net.kmidnight.mysticcursesandspells.event;

import net.kmidnight.mysticcursesandspells.MidnightMcas;
import net.kmidnight.mysticcursesandspells.command.McasCommand;
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
