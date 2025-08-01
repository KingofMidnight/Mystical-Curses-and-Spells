package net.kmidnight.mysticalcursesandspells.spells.custom;

import net.kmidnight.mysticalcursesandspells.api.CurseTier;
import net.minecraft.world.entity.LivingEntity;

public class Firelight extends AbstractSpell {

    @Override
    public void cast(LivingEntity pLivingEntity) {
        /* public void onEntityHit() {
        }

        public void onBlockHit() {
        } */
    }

    @Override
    public String getName() {
        return "Firelight";
    }

    @Override
    public CurseTier SpellTier() {
        return CurseTier.ONE;
    }
}
