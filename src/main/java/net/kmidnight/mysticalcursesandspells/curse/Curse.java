package net.kmidnight.mysticalcursesandspells.curse;

import net.kmidnight.mysticalcursesandspells.MidnightMcas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;
import net.kmidnight.mysticalcursesandspells.curse.curses.*;

import java.util.function.Supplier;

public class Curse {

    public static final DeferredRegister<Curse> CURSE = DeferredRegister.create(new ResourceLocation(MidnightMcas.MOD_ID, "curse"), MidnightMcas.MOD_ID);

    public static final Supplier<IForgeRegistry<Curse>> REGISTRY = CURSE.makeRegistry(RegistryBuilder::new);

    public static final RegistryObject<Curse> LIFEBANE = CURSE.register("lifebane", () -> new Lifebane());

    public static void init() {
        CURSE.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public void applyCurse(Player player) {
    }
}