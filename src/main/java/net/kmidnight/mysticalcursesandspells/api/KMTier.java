package net.kmidnight.mysticalcursesandspells.api;

import net.kmidnight.mysticalcursesandspells.MidnightMcas;
import net.minecraft.resources.ResourceLocation;

import java.util.concurrent.ConcurrentHashMap;

public class KMTier {
    public static final ConcurrentHashMap<Integer, KMTier> TIER_MAP = new ConcurrentHashMap<>();

    public static KMTier ONE = createTier(new ResourceLocation(MidnightMcas.MOD_ID, "one"), 1);
    public static KMTier TWO = createTier(new ResourceLocation(MidnightMcas.MOD_ID, "two"), 2);
    public static KMTier THREE = createTier(new ResourceLocation(MidnightMcas.MOD_ID, "three"), 3);
    public static KMTier FOUR = createTier(new ResourceLocation(MidnightMcas.MOD_ID, "four"), 4);
    public static KMTier FIVE = createTier(new ResourceLocation(MidnightMcas.MOD_ID, "five"), 5);
    public static KMTier SIX = createTier(new ResourceLocation(MidnightMcas.MOD_ID, "six"), 6);
    public static KMTier SEVEN = createTier(new ResourceLocation(MidnightMcas.MOD_ID, "seven"), 7);
    public static KMTier EIGHT = createTier(new ResourceLocation(MidnightMcas.MOD_ID, "eight"), 8);

    public int value;
    public ResourceLocation id;

    public KMTier(ResourceLocation id, int value) {
        this.value = value;
        this.id = id;
    }

    public static KMTier createTier(ResourceLocation id, int value) {
        KMTier tier = new KMTier(id, value);
        TIER_MAP.put(value, tier);
        return tier;
    }

}
