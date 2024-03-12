package net.kmidnight.mysticalcursesandspells.curses.custom;

import net.kmidnight.mysticalcursesandspells.api.KMTier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.ForgeConfigSpec;

import javax.annotation.Nullable;

public abstract class AbstractCurse {
    public abstract String getName();
    public abstract void cast(LivingEntity pLivingEntity);
    public abstract void undo(LivingEntity pLivingEntity);

    public KMTier getConfigTier() {
        return CURSE_TIER == null ? defaultTier() : KMTier.TIER_MAP.get(CURSE_TIER.get());
    }

    public KMTier getTier() {
        return KMTier.ONE;
    }

    public KMTier defaultTier() {
        return getTier();
    }

    public @Nullable ForgeConfigSpec.IntValue CURSE_TIER;


    public void buildConfigCurse(ForgeConfigSpec.Builder builder) {
        builder.comment("General settings").push("general");
        CURSE_TIER = builder.comment("The Curse Tier").defineInRange("curse_tier", defaultTier().value, 1, 8);
    }
}
