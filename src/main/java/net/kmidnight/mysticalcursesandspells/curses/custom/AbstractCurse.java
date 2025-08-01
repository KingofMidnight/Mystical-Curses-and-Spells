package net.kmidnight.mysticalcursesandspells.curses.custom;

import net.kmidnight.mysticalcursesandspells.api.CurseTier;
import net.minecraft.world.entity.LivingEntity;

public abstract class AbstractCurse {
    protected CurseTier activeTier;

    public abstract void cast(LivingEntity pLivingEntity);
    public abstract void undo(LivingEntity pLivingEntity);
    public abstract String getName();
    public abstract CurseTier getDefaultTier();
    public abstract CurseTier[] getSupportedTiers();

    public CurseTier getActiveTier() { return activeTier; }
    public void setActiveTier(CurseTier tier) {
        if (supportsTier(tier)) {
            this.activeTier = tier;
        }
    }

    public boolean supportsTier(CurseTier tier) {
        for (CurseTier supported : getSupportedTiers()) {
            if (supported.equals(tier)) return true;
        }
        return false;
    }

    public CurseTier CurseTier() {
        return getDefaultTier();
    }
}
