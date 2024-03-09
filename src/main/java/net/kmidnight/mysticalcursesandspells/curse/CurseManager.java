package net.kmidnight.mysticalcursesandspells.curse;

import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

public class CurseManager {
    public List<Curse> curses;

    public void CurseManager() {
        curses = new ArrayList<>();
    }

    public void addCurse(Curse curse) {
        this.curses.add(curse);
    }

    public void removeCurse(Curse curse) {
        this.curses.remove(curse);
    }

    public void applyCurse(Player player) {
        for (Curse curse : curses) {
            curse.applyCurse(player);
        }
    }

    public List<Curse> getAllCurses() {
        return curses;
    }
}
