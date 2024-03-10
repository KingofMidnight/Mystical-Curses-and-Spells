package net.kmidnight.mysticalcursesandspells.spells;

import net.kmidnight.mysticalcursesandspells.spells.custom.AbstractSpell;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

public class SpellManager {
    public static List<AbstractSpell> spells;

    public void SpellManager() {
        spells = new ArrayList<>();
    }

    public void addSpell(AbstractSpell spell) {
        this.spells.add(spell);
    }

    public void removeSpellfrombook(Spells spell) {
        this.spells.remove(spell);
    }

    public void applySpelltobook(Player player) {
        for (AbstractSpell spell : spells) {
            spell.cast(player);
        }
    }

    public static List<AbstractSpell> getAllSpells() {
        return spells;
    }
}
