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
    private static final Map<UUID, Map<String, Integer>> playerCurses = new HashMap<>();

    public static final DeferredRegister<AbstractCurse> CURSES =
            DeferredRegister.create(new ResourceLocation(MidnightMcas.MOD_ID, "curse"), MidnightMcas.MOD_ID);

    public static final Supplier<IForgeRegistry<AbstractCurse>> REGISTRY =
            CURSES.makeRegistry(() -> new RegistryBuilder<AbstractCurse>()
                    .setName(new ResourceLocation(MidnightMcas.MOD_ID, "curse")));

    public static final RegistryObject<AbstractCurse> LIFEBANE = CURSES.register("lifebane", () -> new Lifebane());

    public static void addCurseToPlayer(UUID playerUUID, String curseName, int tier) {
        playerCurses.computeIfAbsent(playerUUID, k -> new HashMap<>()).put(curseName, tier);
    }

    public static Set<String> getPlayerCurses(UUID playerUUID) {
        Map<String, Integer> curses = playerCurses.get(playerUUID);
        return curses != null ? curses.keySet() : new HashSet<>();
    }

    public static Map<String, Integer> getPlayerCursesWithTiers(UUID playerUUID) {
        return playerCurses.getOrDefault(playerUUID, new HashMap<>());
    }

    public static int getPlayerCurseTier(UUID playerUUID, String curseName) {
        Map<String, Integer> curses = playerCurses.get(playerUUID);
        return curses != null ? curses.getOrDefault(curseName, 1) : 1;
    }

    public static void removeCurseFromPlayer(UUID playerUUID, String curseName) {
        Map<String, Integer> curses = playerCurses.get(playerUUID);
        if (curses != null) {
            curses.remove(curseName);
            if (curses.isEmpty()) {
                playerCurses.remove(playerUUID);
            }
        }
    }

    public static boolean hasPlayerCurse(UUID playerUUID, String curseName) {
        Map<String, Integer> curses = playerCurses.get(playerUUID);
        return curses != null && curses.containsKey(curseName);
    }

    public static ResourceLocation getCurseId(String curseName) {
        return new ResourceLocation(curseName.contains(":") ? curseName : MidnightMcas.MOD_ID + ":" + curseName);
    }

    public static Stream<AbstractCurse> streamAll() {
        return REGISTRY.get().getValues().stream();
    }

    public static Stream<ResourceLocation> streamIds() {
        return REGISTRY.get().getKeys().stream();
    }

    public static void register(IEventBus eventBus) {
        CURSES.register(eventBus);
    }
}