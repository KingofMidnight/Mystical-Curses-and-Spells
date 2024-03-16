package net.kmidnight.mysticalcursesandspells.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.kmidnight.mysticalcursesandspells.curses.Curses;
import net.kmidnight.mysticalcursesandspells.curses.custom.AbstractCurse;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.world.entity.LivingEntity;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class McasCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("mcas")
            .requires((source) -> source.hasPermission(2))
            .then(Commands.literal("curse")
                .then(Commands.argument("target", EntityArgument.players())
                    .then(Commands.literal("list")
                    )
                    .then(Commands.literal("grant").executes(pCommandSourceStack -> {
                        return cast(pCommandSourceStack.getSource(), EntityArgument.getPlayers(pCommandSourceStack, "target"));
                    }))
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
        List<AbstractCurse> curses = Curses.getAllCurses();
        return 0;
    }

    private static int cast(CommandSourceStack pSource, Collection<? extends LivingEntity> pTargets) {
        Iterator targets = pTargets.iterator();

        while(targets.hasNext()) {
            Curses.LIFEBANE.get().cast((LivingEntity) targets.next());
        }

        return pTargets.size();
    }
}
