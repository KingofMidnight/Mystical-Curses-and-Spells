package net.kmidnight.mysticalcursesandspells.curses;

import net.kmidnight.mysticalcursesandspells.curses.custom.AbstractCurse;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

public class CurseManager {
    public static List<AbstractCurse> curses;

    public void CurseManager() {
        curses = new ArrayList<>();
    }

    public void addCurse(AbstractCurse curse) {
        this.curses.add(curse);
    }

    public void removeCurse(Curses curse) {
        this.curses.remove(curse);
    }

    public void applyCurse(Player player) {
        for (AbstractCurse curse : curses) {
            curse.cast(player);
        }
    }

    public static List<AbstractCurse> getAllCurses() {
        return curses;
    }
}
