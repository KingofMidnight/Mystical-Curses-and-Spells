package net.kmidnight.mysticalcursesandspells.curses.custom;


import net.kmidnight.mysticalcursesandspells.api.KMTier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Lifebane extends AbstractCurse {
    private Map<UUID, Double> originalHealthValues = new HashMap<>();

    @Override
    public void cast(LivingEntity pLivingEntity) {
        double currentMax = pLivingEntity.getMaxHealth();
        originalHealthValues.put(pLivingEntity.getUUID(), currentMax);
        pLivingEntity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(currentMax / 2.0);
    }

    @Override
    public void undo(LivingEntity pLivingEntity) {
        Double originalHealth = originalHealthValues.remove(pLivingEntity.getUUID());
        if (originalHealth != null) {
            pLivingEntity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(originalHealth);
        }
    }

    @Override
    public String getName() {
        return "Lifebane";
    }

    @Override
    public KMTier defaultTier() {
        return KMTier.ONE;
    }
}