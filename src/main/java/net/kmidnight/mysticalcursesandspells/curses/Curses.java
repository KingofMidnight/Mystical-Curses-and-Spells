package net.kmidnight.mysticalcursesandspells.curses;

import net.kmidnight.mysticalcursesandspells.MidnightMcas;
import net.kmidnight.mysticalcursesandspells.curses.custom.AbstractCurse;
import net.kmidnight.mysticalcursesandspells.curses.custom.Lifebane;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class Curses {
    public static final DeferredRegister<AbstractCurse> CURSES =
        DeferredRegister.create(new ResourceLocation(MidnightMcas.MOD_ID, "curse"), MidnightMcas.MOD_ID);

    public static final Supplier<IForgeRegistry<AbstractCurse>> REGISTRY = CURSES.makeRegistry(RegistryBuilder::new);

    public static final RegistryObject<AbstractCurse> LIFEBANE = CURSES.register("lifebane", () -> new Lifebane());

    public static void register(IEventBus eventBus) {
        CURSES.register(eventBus);
    }
}