package net.kmidnight.mysticalcursesandspells.curse;

import net.kmidnight.mysticalcursesandspells.MidnightMcas;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Supplier;

public class Curse {

    public static final DeferredRegister<Curse> CURSE = DeferredRegister.create(new ResourceLocation(MidnightMcas.MOD_ID, "curse"), MidnightMcas.MOD_ID);

    public static final Supplier<IForgeRegistry<Curse>> REGISTRY = CURSE.makeRegistry(RegistryBuilder::new);
}