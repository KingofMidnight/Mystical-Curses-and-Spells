package net.kmidnight.mysticalcursesandspells.item;

import net.kmidnight.mysticalcursesandspells.MidnightMcas;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MidnightMcas.MOD_ID);

    public static final RegistryObject<Item> MCASMAGICBOOK = ITEMS.register("mcasmagicbook",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
