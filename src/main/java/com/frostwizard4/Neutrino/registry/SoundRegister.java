package com.frostwizard4.Neutrino.registry;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SoundRegister {

    public static final Identifier ENCHANTERS_TOME_ACTIVATE_ID = new Identifier("neutrino:enchanters_tome_activate");
    public static SoundEvent ENCHANTERS_TOME_ACTIVATE = new SoundEvent(ENCHANTERS_TOME_ACTIVATE_ID);

    public static final Identifier HARVESTER_ACTIVATE_ID = new Identifier("neutrino:harvester_activate");
    public static SoundEvent HARVESTER_ACTIVATE = new SoundEvent(HARVESTER_ACTIVATE_ID);

    public static final Identifier EAT_DEATH_CAP_MUSHROOM_ID = new Identifier("neutrino:eat_death_cap_mushroom");
    public static SoundEvent EAT_DEATH_CAP_MUSHROOM = new SoundEvent(EAT_DEATH_CAP_MUSHROOM_ID);

    public static final Identifier LIGHTNING_ROD_ACTIVATE_ID = new Identifier("neutrino:lightning_rod_activate");
    public static SoundEvent LIGHTNING_ROD_ACTIVATE = new SoundEvent(LIGHTNING_ROD_ACTIVATE_ID);

    public static final Identifier UPDRAFT_TOME_ACTIVATE_ID = new Identifier("neutrino:updraft_tome_activate");
    public static SoundEvent UPDRAFT_TOME_ACTIVATE = new SoundEvent(UPDRAFT_TOME_ACTIVATE_ID);

    public static final Identifier SOUL_HEALER_ACTIVATE_ID = new Identifier("neutrino:soul_healer_activate");
    public static SoundEvent SOUL_HEALER_ACTIVATE = new SoundEvent(SOUL_HEALER_ACTIVATE_ID);

    public static final Identifier WAR_HORN_USE_ID = new Identifier("neutrino:war_horn_use");
    public static SoundEvent WAR_HORN_USE = new SoundEvent(WAR_HORN_USE_ID);

    public static final Identifier DUCK_CALL_ID = new Identifier("neutrino:duck_call");
    public static SoundEvent DUCK_CALL = new SoundEvent(DUCK_CALL_ID);

    public static final Identifier WITHERLING_IDLE_ID = new Identifier("neutrino:witherling_idle");
    public static SoundEvent WITHERLING_IDLE = new SoundEvent(WITHERLING_IDLE_ID);

    public static final Identifier WITHERLING_ATTACK_ID = new Identifier("neutrino:witherling_attack");
    public static SoundEvent WITHERLING_ATTACK = new SoundEvent(WITHERLING_ATTACK_ID);

    public static void init() {
        Registry.register(Registry.SOUND_EVENT, ENCHANTERS_TOME_ACTIVATE_ID, ENCHANTERS_TOME_ACTIVATE);
        Registry.register(Registry.SOUND_EVENT, HARVESTER_ACTIVATE_ID, HARVESTER_ACTIVATE);
        Registry.register(Registry.SOUND_EVENT, LIGHTNING_ROD_ACTIVATE_ID, LIGHTNING_ROD_ACTIVATE);
        Registry.register(Registry.SOUND_EVENT, UPDRAFT_TOME_ACTIVATE_ID, UPDRAFT_TOME_ACTIVATE);
        Registry.register(Registry.SOUND_EVENT, SOUL_HEALER_ACTIVATE_ID, SOUL_HEALER_ACTIVATE);
        Registry.register(Registry.SOUND_EVENT, WAR_HORN_USE_ID, WAR_HORN_USE);

    }
}
