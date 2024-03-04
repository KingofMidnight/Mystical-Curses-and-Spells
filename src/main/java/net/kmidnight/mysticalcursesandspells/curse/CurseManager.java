package net.kmidnight.mysticalcursesandspells.curse;

import java.util.ArrayList;
import java.util.List;

public class CurseManager {
    public List<Curse> curses;

    public CurseManager() {
        curses = new ArrayList<>();
    }

    public void addCurse(Curse curse) {
        curses.add(curse);
    }

    public void removeCurse(Curse curse) {
        curses.remove(curse);
    }

    public List<Curse> getAllCurses() {
        return curses;
    }
}
