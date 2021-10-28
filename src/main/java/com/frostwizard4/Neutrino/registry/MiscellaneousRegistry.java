package com.frostwizard4.Neutrino.registry;

import com.frostwizard4.Neutrino.NeutrinoMain;
import com.frostwizard4.Neutrino.misc.Config;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;

public class MiscellaneousRegistry {
    public static String[] attackExcludedEntities;
    public static void init() {
        attackExcludedEntities = new String[] {"minecraft:slime", "tconstruct:blueslime", "thaumcraft:thaumslime"};

        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (entity.getEntityWorld().isClient) {
                return ActionResult.PASS;
            }
            for (String id : attackExcludedEntities) {
                Entity attacker = player.getAttacker();
                if (attacker == null)
                    break;
                Identifier loc = EntityType.getId(attacker.getType());
                if (loc == null)
                    break;
                int starIndex = id.indexOf('*');
                if (starIndex != -1) {
                    if (loc.toString().contains(id.substring(0, starIndex))) {
                        return ActionResult.PASS;
                    }
                } else if (loc.toString().equals(id)) {
                    return ActionResult.PASS;
                }

            }

            if(Config.lines.get(2).endsWith("Off")) {
                entity.timeUntilRegen = 0;
            } else if(Config.lines.get(2).endsWith("Half")) {
                entity.timeUntilRegen = 5;
            }
            return ActionResult.PASS;
        });
    }
}
