package net.kmidnight.mysticcursesandspells.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class McasCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("mcas")
                .then(Commands.literal("curse"))
                        .then(Commands.literal("list"))
                        .then(Commands.literal("grant"))
                        .then(Commands.literal("removal"))
                .then(Commands.literal("spell"))
                        .then(Commands.literal("list"))
                        .then(Commands.literal("grant"))
                        .then(Commands.literal("removal"))
                .then(Commands.literal("mana-type"))
                        .then(Commands.literal("list"))
                        .then(Commands.literal("grant"))
                        .then(Commands.literal("removal"))
        );
    }
}
