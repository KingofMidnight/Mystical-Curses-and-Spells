package net.kmidnight.mysticalcursesandspells.api;

import net.kmidnight.mysticalcursesandspells.MidnightMcas;
import net.minecraft.resources.ResourceLocation;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class CurseTier {
    public static final ConcurrentHashMap<Integer, CurseTier> TIER_MAP = new ConcurrentHashMap<>();

    public static CurseTier ONE = createTier(new ResourceLocation(MidnightMcas.MOD_ID, "one"), 1);
    public static CurseTier TWO = createTier(new ResourceLocation(MidnightMcas.MOD_ID, "two"), 2);
    public static CurseTier THREE = createTier(new ResourceLocation(MidnightMcas.MOD_ID, "three"), 3);
    public static CurseTier FOUR = createTier(new ResourceLocation(MidnightMcas.MOD_ID, "four"), 4);
    public static CurseTier FIVE = createTier(new ResourceLocation(MidnightMcas.MOD_ID, "five"), 5);
    public static CurseTier SIX = createTier(new ResourceLocation(MidnightMcas.MOD_ID, "six"), 6);
    public static CurseTier SEVEN = createTier(new ResourceLocation(MidnightMcas.MOD_ID, "seven"), 7);
    public static CurseTier EIGHT = createTier(new ResourceLocation(MidnightMcas.MOD_ID, "eight"), 8);

    public int value;
    public ResourceLocation id;

    public CurseTier(ResourceLocation id, int value) {
        this.value = value;
        this.id = id;
    }

    public static CurseTier createTier(ResourceLocation id, int value) {
        CurseTier tier = new CurseTier(id, value);
        TIER_MAP.put(value, tier);
        return tier;
    }

    public static CurseTier fromValue(int value) {
        return TIER_MAP.get(value);
    }

    public static CurseTier[] range(int minTier, int maxTier) {
        return TIER_MAP.values().stream()
                .filter(tier -> tier.value >= minTier && tier.value <= maxTier)
                .toArray(CurseTier[]::new);
    }

    public boolean isHigherThan(CurseTier other) {
        return this.value > other.value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CurseTier curseTier = (CurseTier) obj;
        return value == curseTier.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
