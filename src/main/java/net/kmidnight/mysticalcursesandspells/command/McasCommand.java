package net.kmidnight.mysticalcursesandspells.command;

import com.mojang.brigadier.CommandDispatcher;
import net.kmidnight.mysticalcursesandspells.curse.Curse;
import net.kmidnight.mysticalcursesandspells.curse.CurseManager;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;

import java.util.List;

public class McasCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("mcas")
            .requires((source) -> source.hasPermission(2))
            .then(Commands.literal("curse")
                .then(Commands.argument("target", EntityArgument.players())
                    .then(Commands.literal("list")
                    )
                    .then(Commands.literal("grant")
                    )
                    .then(Commands.literal("removal")
                    )
                )
            )
            .then(Commands.literal("spell")
                .then(Commands.argument("target", EntityArgument.players())
                    .then(Commands.literal("list")
                    )
                    .then(Commands.literal("grant")
                    )
                    .then(Commands.literal("removal")
                    )
                )
            )
            .then(Commands.literal("mana-type")
                .then(Commands.argument("target", EntityArgument.players())
                    .then(Commands.literal("has")
                    )
                    .then(Commands.literal("grant")
                    )
                    .then(Commands.literal("removal")
                    )
                )
            )
        );
    }

    public static int listCurses(CommandSourceStack source) {
        List<Curse> curses = CurseManager.getAllCurses();
        return ; // so far i don't think this works
    }
}
