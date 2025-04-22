package shcm.shsupercm.fabric.citresewn;

import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
//? if >=1.20.4 {
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;
//?} else if <1.20.4 {
/*import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
*///?}
import org.thinkingstudio.citfoxified.helper.ModPlatformHelper;
import shcm.shsupercm.fabric.citresewn.cit.*;
import shcm.shsupercm.fabric.citresewn.config.CITResewnConfig;
import shcm.shsupercm.fabric.citresewn.pack.format.PropertyKey;
import shcm.shsupercm.fabric.citresewn.pack.format.PropertyValue;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.text.Text.of;

/**
 * Logic for the /citresewn client command. Only enabled when Fabric API is present.<br>
 * Structure:
 * <pre>
 * /citresewn - General info command
 * /citresewn config - Opens the config gui(only when Cloth Config is present)
 * /citresewn analyze pack &lt;pack&gt; - Displays data for the given loaded cit pack.
 * </pre>
 */
public class CITResewnCommand {
    /**
     * @see shcm.shsupercm.fabric.citresewn.mixin.ChatScreenMixin
     */
    public static boolean openConfig = false;

    /**
     * Registers all of CIT Resewn's commands.
     */
    public static void register(IEventBus modEventBus) {
        //? if >=1.20.4 {
        modEventBus.addListener(RegisterClientCommandsEvent.class, event -> {
        //?} else if <1.20.4 {
        /*modEventBus.<RegisterClientCommandsEvent>addListener(event -> {
        *///?}
            var dispatcher = event.getDispatcher();

            dispatcher.register(
                    CommandManager.literal("citresewn").executes(context -> {
                                context.getSource().sendFeedback(() -> of("CIT Resewn v" + ModPlatformHelper.getModContainer("citresewn").orElseThrow().getModInfo().getVersion().toString() + ":"), false);
                                context.getSource().sendFeedback(() -> of("  Registered: " + CITRegistry.TYPES.values().stream().distinct().count() + " types and " + CITRegistry.CONDITIONS.values().stream().distinct().count() + " conditions"), false);

                                final boolean active = CITResewnConfig.INSTANCE.enabled && ActiveCITs.isActive();
                                context.getSource().sendFeedback(() -> of("  Active: " + (active ? "yes" : ("no, " + (CITResewnConfig.INSTANCE.enabled ? "no cit packs loaded" : "disabled in config")))), false);
                                if (active) {
                                    context.getSource().sendFeedback(() -> of("   Loaded: " + ActiveCITs.getActive().cits.values().stream().mapToLong(Collection::size).sum() + " CITs from " + ActiveCITs.getActive().cits.values().stream().flatMap(Collection::stream).map(cit -> cit.packName).distinct().count() + " resourcepacks"), false);
                                }
                                context.getSource().sendFeedback(() -> of(""), false);

                                return 1;
                            })
                            .then(literal("config")
                                    .executes(context -> { //citresewn config
                                        openConfig = true;

                                        return 1;
                                    }))
                            .then(literal("analyze")
                                    .then(literal("pack")
                                            .then(argument("pack", new LoadedCITPackArgument())
                                                    .executes(context -> { //citresewn analyze <pack>
                                                        final String pack = context.getArgument("pack", String.class);
                                                        if (ActiveCITs.isActive()) {
                                                            context.getSource().sendFeedback(() -> of("Analyzed CIT data of \"" + pack + "\u00a7r\":"), false);

                                                            List<Text> builder = new ArrayList<>();

                                                            for (Map.Entry<PropertyKey, Set<PropertyValue>> entry : ActiveCITs.getActive().globalProperties.properties.entrySet())
                                                                for (PropertyValue value : entry.getValue())
                                                                    if (value.packName().equals(pack))
                                                                        builder.add(of("  " + entry.getKey().toString() + (value.keyMetadata() == null ? "" : "." + value.keyMetadata()) + " = " + value.value()));
                                                            if (!builder.isEmpty()) {
                                                                context.getSource().sendFeedback(() -> of(" Global Properties:"), false);
                                                                for (Text text : builder)
                                                                    context.getSource().sendFeedback(() -> text, false);

                                                                builder.clear();
                                                            }

                                                            for (Map.Entry<Class<? extends CITType>, List<CIT<?>>> entry : ActiveCITs.getActive().cits.entrySet())
                                                                if (!entry.getValue().isEmpty()) {
                                                                    long count = entry.getValue().stream().filter(cit -> cit.packName.equals(pack)).count();
                                                                    if (count > 0)
                                                                        builder.add(of("  " + CITRegistry.idOfType(entry.getKey()).toString() + " = " + count));
                                                                }
                                                            if (!builder.isEmpty()) {
                                                                context.getSource().sendFeedback(() -> of(" Types:"), false);
                                                                for (Text text : builder)
                                                                    context.getSource().sendFeedback(() -> text, false);

                                                                builder.clear();
                                                            }

                                                            List<CITCondition> conditions = ActiveCITs.getActive().cits.values().stream()
                                                                    .flatMap(Collection::stream)
                                                                    .filter(cit -> cit.packName.equals(pack))
                                                                    .flatMap(cit -> Arrays.stream(cit.conditions))
                                                                    .toList();
                                                            if (!conditions.isEmpty())
                                                                context.getSource().sendFeedback(() -> of(" Utilizing " + conditions.size() + " conditions(" + conditions.stream().map(Object::getClass).distinct().count() + " unique condition types)"), false);
                                                        } else
                                                            context.getSource().sendFeedback(() -> of("Not active"), false);

                                                        return 1;
                                                    })
                                            )
                                    )
                            )
            );
        });
    }

    /**
     * Greedy string argument that is limited to cit pack names loaded in {@link shcm.shsupercm.fabric.citresewn.cit.ActiveCITs}.
     */
    private static class LoadedCITPackArgument implements ArgumentType<String> {
        @Override
        public String parse(StringReader reader) throws CommandSyntaxException {
            StringBuilder builder = new StringBuilder();
            while (reader.canRead())
                builder.append(reader.read());

            String pack = builder.toString().trim();

            if (!getPacks().contains(pack)) {
                LiteralMessage message = new LiteralMessage("Could not find CIT pack");
                throw new CommandSyntaxException(new SimpleCommandExceptionType(message), message);
            }

            return pack;
        }

        @Override
        public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
            return CompletableFuture.supplyAsync(() -> {
                for (String pack : getPacks()) {
                    builder.suggest(pack);
                }
                return builder.build();
            });
        }

        private static Set<String> getPacks() {
            if (ActiveCITs.isActive())
                return ActiveCITs.getActive().cits.values().stream()
                        .flatMap(Collection::stream)
                        .map(cit -> cit.packName)
                        .collect(Collectors.toSet());
            else
                return Collections.emptySet();
        }
    }
}
