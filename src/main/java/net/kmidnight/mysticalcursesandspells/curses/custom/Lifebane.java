package net.kmidnight.mysticalcursesandspells.curses.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class Lifebane extends AbstractCurse {
    @Override
    public void cast(LivingEntity pLivingEntity) {
        pLivingEntity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(pLivingEntity.getMaxHealth() / 2.0f);
    }

    @Override
    public void undo(LivingEntity pLivingEntity) {
        pLivingEntity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(pLivingEntity.getMaxHealth() * 2);
    }

    @Override
    public String getName() {
        return "lifebane";
    }
}
