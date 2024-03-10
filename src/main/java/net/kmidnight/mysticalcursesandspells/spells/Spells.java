package net.kmidnight.mysticalcursesandspells.spells;

import net.kmidnight.mysticalcursesandspells.MidnightMcas;
import net.kmidnight.mysticalcursesandspells.spells.custom.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class Spells {
    public static final DeferredRegister<AbstractSpell> SPELLS = DeferredRegister.create(new ResourceLocation(MidnightMcas.MOD_ID, "curse"), MidnightMcas.MOD_ID);

    public static final Supplier<IForgeRegistry<AbstractSpell>> REGISTRY = SPELLS.makeRegistry(RegistryBuilder::new);

    public static final RegistryObject<AbstractSpell> FIRELIGHT = SPELLS.register("Firelight", () -> new Firelight());

}
