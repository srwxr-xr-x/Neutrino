package com.frostwizard4.Neutrino.registry;

import com.frostwizard4.Neutrino.effects.SnakePoisonStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class StatusEffectRegistry {
    public static final StatusEffect SNAKE_POISON = new SnakePoisonStatusEffect();

    public static void init() {
        Registry.register(Registry.STATUS_EFFECT, new Identifier("neutrino", "snake_poison"), SNAKE_POISON);
    }
}
