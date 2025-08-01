package net.kmidnight.mysticalcursesandspells.spells.custom;

import net.kmidnight.mysticalcursesandspells.api.CurseTier;
import net.minecraft.world.entity.LivingEntity;

public abstract class AbstractSpell {
    public abstract String getName();
    public abstract void cast(LivingEntity pLivingEntity);
    public abstract CurseTier SpellTier();
}
