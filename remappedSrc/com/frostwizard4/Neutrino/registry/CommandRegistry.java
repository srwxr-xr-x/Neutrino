package com.frostwizard4.Neutrino.registry;

import com.frostwizard4.Neutrino.PlayerEntityAccess;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class CommandRegistry {
    public static void init() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(literal("soullevel").requires(source -> source.hasPermissionLevel(2)).then(argument("amount", IntegerArgumentType.integer(0, 3000)).executes(ctx -> {
                ServerPlayerEntity source = ctx.getSource().getPlayer();
                ((PlayerEntityAccess) source).neutrino$setPowerCount(IntegerArgumentType.getInteger(ctx, "amount") * 10);
                source.sendMessage(Text.of("§l§9Soul level§r§7 is set to " + (IntegerArgumentType.getInteger(ctx, "amount")) + "!"), false);
                return 1;
            })));
        });
    }
}
