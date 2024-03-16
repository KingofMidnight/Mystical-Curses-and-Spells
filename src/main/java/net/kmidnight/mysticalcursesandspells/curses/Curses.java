package net.kmidnight.mysticalcursesandspells.curses;

import net.kmidnight.mysticalcursesandspells.MidnightMcas;
import net.kmidnight.mysticalcursesandspells.curses.custom.AbstractCurse;
import net.kmidnight.mysticalcursesandspells.curses.custom.Lifebane;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Curses {
    public static List<AbstractCurse> curses;

    public void CurseList() {
        curses = new ArrayList<>();
    }

    public static final DeferredRegister<AbstractCurse> CURSES = DeferredRegister.create(new ResourceLocation(MidnightMcas.MOD_ID, "curse"), MidnightMcas.MOD_ID);

    public static final Supplier<IForgeRegistry<AbstractCurse>> REGISTRY = CURSES.makeRegistry(RegistryBuilder::new);

    public static final RegistryObject<AbstractCurse> LIFEBANE = CURSES.register("Lifebane", () -> new Lifebane());

    public static void register(IEventBus eventBus) {
        CURSES.register(eventBus);
    }

    public void addCurse(AbstractCurse curse) {
        curses.add(curse);
    }

    public void removeCurse(Curses curse) {
        curses.remove(curse);
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