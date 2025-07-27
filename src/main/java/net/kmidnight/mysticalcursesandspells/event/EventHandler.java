package net.kmidnight.mysticalcursesandspells.event;

import net.kmidnight.mysticalcursesandspells.MidnightMcas;
import net.kmidnight.mysticalcursesandspells.commands.McasCommand;
import net.kmidnight.mysticalcursesandspells.curses.Curses;
import net.kmidnight.mysticalcursesandspells.curses.custom.AbstractCurse;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = MidnightMcas.MOD_ID)
public class EventHandler {

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        UUID playerUUID = event.getEntity().getUUID();
        Set<String> curses = Curses.getPlayerCurses(playerUUID);
        for (String curseName : curses) {
            try {
                ResourceLocation curseId = new ResourceLocation(curseName.contains(":") ? curseName : "mysticalcursesandspells:" + curseName);
                AbstractCurse curse = Curses.REGISTRY.get().getValue(curseId);
                if (curse != null && event.getEntity() instanceof LivingEntity) {
                    curse.cast((LivingEntity) event.getEntity());
                }
            } catch (Exception e) {
                System.err.println("Failed to reapply curse " + curseName + " to player " + event.getEntity().getName().getString());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent event) {
    }

    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event) {
        McasCommand.register(event.getDispatcher());
    }

    private EventHandler() {
    }
}
