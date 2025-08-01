package net.kmidnight.mysticalcursesandspells.curses.custom;

import net.kmidnight.mysticalcursesandspells.api.CurseTier;
import net.minecraft.world.entity.LivingEntity;

public abstract class AbstractCurse {
    public abstract void cast(LivingEntity pLivingEntity);
    public abstract void undo(LivingEntity pLivingEntity);
    public abstract String getName();
    public abstract CurseTier CurseTier();

}
