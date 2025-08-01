package net.kmidnight.mysticalcursesandspells.curses.custom;

import net.kmidnight.mysticalcursesandspells.api.CurseTier;
import net.minecraft.world.entity.LivingEntity;

public class TheOneAndTheChance extends AbstractCurse{
    @Override
    public void cast(LivingEntity pLivingEntity) {
        double realTntChance = switch (getActiveTier().value) {
            case 1 -> 0.25;
            case 3 -> 0.50;
            default -> 0.25;
        };
    }

    @Override
    public void undo(LivingEntity pLivingEntity) {

    }

    @Override
    public String getName() {
        return "TheOneAndTheChance";
    }

    @Override
    public CurseTier getDefaultTier() {
        return CurseTier.ONE;
    }

    @Override
    public CurseTier[] getSupportedTiers() {
        return new CurseTier[]{CurseTier.ONE,CurseTier.THREE};
    }
}
