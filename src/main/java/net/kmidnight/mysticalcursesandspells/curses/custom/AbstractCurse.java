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
        return CurseTier = null ? defaultTier() : KMTier.TIER_MAP.get(KMTier.get);
    }

    public KMTier getTier() {
        return KMTier.ONE;
    }

    public KMTier defaultTier() {
        return getTier();
    }

}
