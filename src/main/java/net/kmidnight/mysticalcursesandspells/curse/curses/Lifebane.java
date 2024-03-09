package net.kmidnight.mysticalcursesandspells.curse.curses;

import net.kmidnight.mysticalcursesandspells.curse.Curse;
import net.minecraft.world.entity.player.Player;

public class Lifebane extends Curse {
    public void applyCurse(Player player) {
        int currentHealth = (int) player.getHealth();
        int reducedHealth = currentHealth / 2;
        player.setHealth(reducedHealth);
    }
}
