package net.kmidnight.mysticalcursesandspells.curse;

import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

public class CurseManager {
    public List<Curse> curse;

    public CurseManager() {
        curse = new ArrayList<>();
    }

    public void addCurse(Curse curse) {
        this.curse.add(curse);
    }

    public void removeCurse(Curse curse) {
        this.curse.remove(curse);
    }

    public void applyCurses(Curse curse, Player player) {
        curse.applyCurse(player);
    }

    public List<Curse> getAllCurses() {
        return curse;
    }
}
