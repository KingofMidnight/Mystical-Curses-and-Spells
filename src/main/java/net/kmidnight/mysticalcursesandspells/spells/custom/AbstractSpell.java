package net.kmidnight.mysticalcursesandspells.spells.custom;

import net.kmidnight.mysticalcursesandspells.api.KMTier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.ForgeConfigSpec;

import javax.annotation.Nullable;

public abstract class AbstractSpell {
    public abstract String getName();
    public abstract void cast(LivingEntity pLivingEntity);

    public KMTier getConfigTier() {
        return SPELL_TIER == null ? defaultTier() : KMTier.TIER_MAP.get(SPELL_TIER.get());
    }

    public KMTier getTier() {
        return KMTier.ONE;
    }

    public KMTier defaultTier() {
        return getTier();
    }

    public @Nullable ForgeConfigSpec.IntValue SPELL_TIER;


    public void buildConfigSpell(ForgeConfigSpec.Builder builder) {
        builder.comment("General settings").push("general");
        SPELL_TIER = builder.comment("The Spell Tier").defineInRange("spell_tier", defaultTier().value, 1, 8);
    }
}
