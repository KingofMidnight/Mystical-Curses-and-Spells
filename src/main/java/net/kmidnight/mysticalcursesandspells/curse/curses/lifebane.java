package net.kmidnight.mysticalcursesandspells.curse.curses;

import net.minecraft.world.entity.player.Player;

public class lifebane {
    public void applyCurse(Player player) {
        int currentHealth = (int) player.getHealth();
        int reducedHealth = currentHealth / 2;
        player.setHealth(reducedHealth);
    }
}
