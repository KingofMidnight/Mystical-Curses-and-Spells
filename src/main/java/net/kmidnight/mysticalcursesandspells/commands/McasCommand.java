package net.kmidnight.mysticalcursesandspells.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.kmidnight.mysticalcursesandspells.curses.Curses;
import net.kmidnight.mysticalcursesandspells.curses.custom.AbstractCurse;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class McasCommand {

    /**
     * Registers all mcas commands with the command dispatcher
     *
     * @param dispatcher The command dispatcher to register commands with
     */
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("mcas")
                .requires(source -> source.hasPermission(2)) // Requires OP level 2
                .then(Commands.literal("curse")
                        .then(Commands.argument("target", EntityArgument.players())
                                .then(Commands.literal("list")
                                        .executes(context -> listPlayerCurses(context))
                                )
                                .then(Commands.literal("grant")
                                        .then(Commands.argument("curse", StringArgumentType.string())
                                                .suggests(CURSE_IDS)
                                                .executes(context -> grantCurse(context))
                                        )
                                )
                                .then(Commands.literal("remove")
                                        .then(Commands.argument("curse", StringArgumentType.string())
                                                .suggests(CURSE_IDS)
                                                .executes(context -> removeCurse(context))
                                        )
                                )
                                .then(Commands.literal("clear")
                                        .executes(context -> clearCurses(context))
                                )
                        )
                        .then(Commands.literal("list_all")
                                .executes(context -> listAllCurses(context))
                        )
                )
                .then(Commands.literal("spell")
                        .then(Commands.argument("target", EntityArgument.players())
                                .then(Commands.literal("list")
                                        .executes(context -> {
                                            context.getSource().sendSuccess(() ->
                                                    Component.literal("Spell system not yet implemented."), false);
                                            return 1;
                                        })
                                )
                                .then(Commands.literal("grant")
                                        .executes(context -> {
                                            context.getSource().sendSuccess(() ->
                                                    Component.literal("Spell system not yet implemented."), false);
                                            return 1;
                                        })
                                )
                                .then(Commands.literal("remove")
                                        .executes(context -> {
                                            context.getSource().sendSuccess(() ->
                                                    Component.literal("Spell system not yet implemented."), false);
                                            return 1;
                                        })
                                )
                        )
                )
                .then(Commands.literal("mana-type")
                        .then(Commands.argument("target", EntityArgument.players())
                                .then(Commands.literal("has")
                                        .executes(context -> {
                                            context.getSource().sendSuccess(() ->
                                                    Component.literal("Mana system not yet implemented."), false);
                                            return 1;
                                        })
                                )
                                .then(Commands.literal("grant")
                                        .executes(context -> {
                                            context.getSource().sendSuccess(() ->
                                                    Component.literal("Mana system not yet implemented."), false);
                                            return 1;
                                        })
                                )
                                .then(Commands.literal("remove")
                                        .executes(context -> {
                                            context.getSource().sendSuccess(() ->
                                                    Component.literal("Mana system not yet implemented."), false);
                                            return 1;
                                        })
                                )
                        )
                )
                .then(Commands.literal("help")
                        .executes(context -> showHelp(context))
                )
                .executes(context -> showHelp(context))
        );
    }

    // Lists all curses currently active on the specified players
    private static int listPlayerCurses(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        Collection<ServerPlayer> targets = EntityArgument.getPlayers(context, "target");
        CommandSourceStack source = context.getSource();
        if (targets.isEmpty()) {
            source.sendFailure(Component.literal("No valid targets found."));
            return 0;
        }
        for (ServerPlayer player : targets) {
            Set<String> playerCurses = Curses.getPlayerCurses(player.getUUID());
            if (playerCurses.isEmpty()) {
                source.sendSuccess(() ->
                        Component.literal("Player " + player.getName().getString() + " has no active curses."), false);
            } else {
                source.sendSuccess(() ->
                        Component.literal("Active curses for " + player.getName().getString() + ": " +
                                String.join(", ", playerCurses)), false);
            }
        }
        return targets.size();
    }

    // Grants a specific curse to the target players
    private static int grantCurse(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        Collection<ServerPlayer> targets = EntityArgument.getPlayers(context, "target");
        String curseName = StringArgumentType.getString(context, "curse_name");
        CommandSourceStack source = context.getSource();

        if (targets.isEmpty()) {
            source.sendFailure(Component.literal("No valid targets found."));
            return 0;
        }

        AbstractCurse curse = getCurse(context);
        if (curse == null) {
            source.sendFailure(Component.literal("Unknown curse: " + curseName));
            return 0;
        }

        int successCount = 0;
        for (ServerPlayer player : targets) {
            try {
                // Apply the curse
                curse.cast(player);

                // Track the curse
                Curses.addCurseToPlayer(player.getUUID(), curseName);

                source.sendSuccess(() ->
                                Component.literal("Applied curse '" + curseName + "' to " + player.getName().getString()),
                        true);

                // Notify the target player
                player.sendSystemMessage(Component.literal("You have been cursed with: " + curseName));

                successCount++;
            } catch (Exception e) {
                source.sendFailure(Component.literal("Failed to apply curse '" + curseName + "' to " +
                        player.getName().getString() + ": " + e.getMessage()));
            }
        }

        return successCount;
    }

    /**
     * Removes a specific curse from the target players
     */
    private static int removeCurse(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        Collection<ServerPlayer> targets = EntityArgument.getPlayers(context, "target");
        String curseName = StringArgumentType.getString(context, "curse_name");
        CommandSourceStack source = context.getSource();

        if (targets.isEmpty()) {
            source.sendFailure(Component.literal("No valid targets found."));
            return 0;
        }

        AbstractCurse curse = getCurse(context);
        if (curse == null) {
            source.sendFailure(Component.literal("Unknown curse: " + curseName));
            return 0;
        }

        int successCount = 0;
        for (ServerPlayer player : targets) {
            Set<String> playerCurses = Curses.getPlayerCurses(player.getUUID());

            if (!playerCurses.contains(curseName)) {
                source.sendFailure(Component.literal("Player " + player.getName().getString() +
                        " does not have curse: " + curseName));
                continue;
            }

            try {
                // Remove the curse effect
                curse.undo(player);

                // Remove from tracking
                Curses.removeCurseFromPlayer(player.getUUID(), curseName);

                source.sendSuccess(() ->
                                Component.literal("Removed curse '" + curseName + "' from " + player.getName().getString()),
                        true);

                // Notify the target player
                player.sendSystemMessage(Component.literal("The curse '" + curseName + "' has been lifted from you."));

                successCount++;
            } catch (Exception e) {
                source.sendFailure(Component.literal("Failed to remove curse '" + curseName + "' from " +
                        player.getName().getString() + ": " + e.getMessage()));
            }
        }

        return successCount;
    }

    /**
     * Removes all curses from the target players
     */
    private static int clearCurses(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        Collection<ServerPlayer> targets = EntityArgument.getPlayers(context, "target");
        CommandSourceStack source = context.getSource();

        if (targets.isEmpty()) {
            source.sendFailure(Component.literal("No valid targets found."));
            return 0;
        }

        int totalRemoved = 0;
        for (ServerPlayer player : targets) {
            Set<String> playerCurses = Curses.getPlayerCurses(player.getUUID());

            if (playerCurses.isEmpty()) {
                source.sendSuccess(() ->
                                Component.literal("Player " + player.getName().getString() + " has no curses to clear."),
                        false);
                continue;
            }

            var ref = new Object() {
                int removedCount = 0;
            };
            // Create a copy to avoid concurrent modification
            Set<String> cursesToRemove = Set.copyOf(playerCurses);

            for (String curseName : cursesToRemove) {
                AbstractCurse curse = getCurse(context);
                if (curse != null) {
                    try {
                        curse.undo(player);
                        Curses.removeCurseFromPlayer(player.getUUID(), curseName);
                        ref.removedCount++;
                    } catch (Exception e) {
                        source.sendFailure(Component.literal("Failed to remove curse '" + curseName +
                                "' from " + player.getName().getString() + ": " + e.getMessage()));
                    }
                }
            }

            if (ref.removedCount > 0) {
                source.sendSuccess(() ->
                                Component.literal("Cleared " + ref.removedCount + " curse(s) from " + player.getName().getString()),
                        true);

                player.sendSystemMessage(Component.literal("All curses have been lifted from you."));
                totalRemoved += ref.removedCount;
            }
        }

        return totalRemoved;
    }

    /**
     * Lists all available curses in the system
     */
    private static int listAllCurses(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();

        // For now, we only have lifebane. This can be expanded as more curses are added.
        source.sendSuccess(() ->
                        Component.literal("Available curses: lifebane"),
                false);

        // Future implementation could look like this:
        // List<AbstractCurse> allCurses = Curses.getAllCurses();
        // if (allCurses.isEmpty()) {
        //     source.sendSuccess(() -> Component.literal("No curses are currently available."), false);
        // } else {
        //     StringBuilder curseList = new StringBuilder("Available curses: ");
        //     for (int i = 0; i < allCurses.size(); i++) {
        //         if (i > 0) curseList.append(", ");
        //         curseList.append(allCurses.get(i).getClass().getSimpleName().toLowerCase());
        //     }
        //     source.sendSuccess(() -> Component.literal(curseList.toString()), false);
        // }

        return 1;
    }

    // Shows help information for the mcas command
    private static int showHelp(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();

        source.sendSuccess(() -> Component.literal("=== Mystical Curses and Spells Commands ==="), false);
        source.sendSuccess(() -> Component.literal("/mcas curse <player> list - List active curses on player"), false);
        source.sendSuccess(() -> Component.literal("/mcas curse <player> grant <curse> - Grant curse to player"), false);
        source.sendSuccess(() -> Component.literal("/mcas curse <player> remove <curse> - Remove curse from player"), false);
        source.sendSuccess(() -> Component.literal("/mcas curse <player> clear - Remove all curses from player"), false);
        source.sendSuccess(() -> Component.literal("/mcas curse listall - List all available curses"), false);
        source.sendSuccess(() -> Component.literal("/mcas spell <player> ... - Spell commands (not yet implemented)"), false);
        source.sendSuccess(() -> Component.literal("/mcas mana-type <player> ... - Mana commands (not yet implemented)"), false);
        source.sendSuccess(() -> Component.literal("/mcas help - Show this help message"), false);

        return 1;
    }

    private static AbstractCurse getCurse(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ResourceLocation id = ResourceLocationArgument.getId(ctx, "curse");
        AbstractCurse curse = Curses.REGISTRY.get().getValue(id);
        if (curse == null) {
            throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.dispatcherUnknownArgument().create();
        }
        return curse;
    }

    public static final SuggestionProvider<CommandSourceStack> CURSE_IDS = (
        context, builder) -> SharedSuggestionProvider.suggest(
            Curses.streamIds().map(ResourceLocation::getPath), builder);
}