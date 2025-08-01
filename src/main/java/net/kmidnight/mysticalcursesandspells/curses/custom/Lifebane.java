package net.kmidnight.mysticalcursesandspells.curses.custom;


import net.kmidnight.mysticalcursesandspells.api.CurseTier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class Lifebane extends AbstractCurse {
    private static final UUID LIFEBANE_MODIFIER_UUID = UUID.fromString("e8c1c99f-6fe1-4e95-b1c9-7b1a272e09c3");

    @Override
    public void cast(LivingEntity entity) {
        var attribute = entity.getAttribute(Attributes.MAX_HEALTH);
        if (attribute != null && attribute.getModifier(LIFEBANE_MODIFIER_UUID) == null) {
            AttributeModifier modifier = new AttributeModifier(
                    LIFEBANE_MODIFIER_UUID, "Lifebane curse", -0.5, AttributeModifier.Operation.MULTIPLY_BASE
            );
            attribute.addPermanentModifier(modifier);
        }
    }

    @Override
    public void undo(LivingEntity entity) {
        var attribute = entity.getAttribute(Attributes.MAX_HEALTH);
        if (attribute != null && attribute.getModifier(LIFEBANE_MODIFIER_UUID) != null) {
            attribute.removeModifier(LIFEBANE_MODIFIER_UUID);
        }
    }

    @Override
    public String getName() {
        return "Lifebane";
    }

    @Override
    public CurseTier getDefaultTier() {
        return CurseTier.ONE;
    }

    @Override
    public CurseTier[] getSupportedTiers() {
        return new CurseTier[]{CurseTier.ONE};
    }
}