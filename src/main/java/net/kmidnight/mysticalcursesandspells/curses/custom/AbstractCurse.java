package net.kmidnight.mysticalcursesandspells.curses.custom;

import net.minecraft.world.entity.LivingEntity;

public abstract class AbstractCurse {
    public abstract String getName();
    public abstract void cast(LivingEntity pLivingEntity);
    public abstract void undo(LivingEntity pLivingEntity);
}
