package net.kmidnight.mysticalcursesandspells.curse;

import net.kmidnight.mysticalcursesandspells.MidnightMcas;
import net.kmidnight.mysticalcursesandspells.curse.curses.Lifebane;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.nio.file.spi.FileSystemProvider;
import java.util.Collection;
import java.util.function.Supplier;

public class Curse {
    public static final DeferredRegister<Curse> CURSE =
        DeferredRegister.create(new ResourceLocation(MidnightMcas.MOD_ID, "curse"), MidnightMcas.MOD_ID);

    public static final Supplier<IForgeRegistry<Curse>> REGISTRY = CURSE.makeRegistry(RegistryBuilder::new);

    public static final RegistryObject<Curse> LIFEBANE = CURSE.register("Lifebane", () -> new Lifebane());


    public void applyCurse(Player player) {
    }

    public static void register(IEventBus eventBus) {
        CURSE.register(eventBus);
    }

    public static String getName() {
        /* Couldn't make the method to get the name of the Curses
        this will not be needed if i can get the Curse list to work  */
    }
}