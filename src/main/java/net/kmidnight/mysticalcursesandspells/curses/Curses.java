package net.kmidnight.mysticalcursesandspells.curses;

import net.kmidnight.mysticalcursesandspells.MidnightMcas;
import net.kmidnight.mysticalcursesandspells.curses.custom.AbstractCurse;
import net.kmidnight.mysticalcursesandspells.curses.custom.Lifebane;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Curses {
    private static final Map<UUID, Set<String>> playerCurses = new HashMap<>();

    public static final DeferredRegister<AbstractCurse> CURSES =
            DeferredRegister.create(new ResourceLocation(MidnightMcas.MOD_ID, "curse"), MidnightMcas.MOD_ID);

    public static final Supplier<IForgeRegistry<AbstractCurse>> REGISTRY =
            CURSES.makeRegistry(() -> new RegistryBuilder<AbstractCurse>()
                    .setName(new ResourceLocation(MidnightMcas.MOD_ID, "curse")));

    public static final RegistryObject<AbstractCurse> LIFEBANE = CURSES.register("lifebane", () -> new Lifebane());

    public static void addCurseToPlayer(UUID playerUUID, String curseName) {
        playerCurses.computeIfAbsent(playerUUID, k -> new HashSet<>()).add(curseName);
    }

    public static Set<String> getPlayerCurses(UUID playerUUID) {
        return playerCurses.getOrDefault(playerUUID, new HashSet<>());
    }

    public static void removeCurseFromPlayer(UUID playerUUID, String curseName) {
        Set<String> curses = playerCurses.get(playerUUID);
        if (curses != null) {
            curses.remove(curseName);
            if (curses.isEmpty()) {
                playerCurses.remove(playerUUID);
            }
        }
    }

    public static Stream<ResourceLocation> streamIds() {
        return REGISTRY.get().getKeys().stream();
    }

    public static void register(IEventBus eventBus) {
        CURSES.register(eventBus);
    }
}