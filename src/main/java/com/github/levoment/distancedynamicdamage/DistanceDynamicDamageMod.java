package com.github.levoment.distancedynamicdamage;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DistanceDynamicDamageMod implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("distancedynamicdamage");
    // Variable holding the custom damage
    public static double DYNAMIC_DAMAGE = 1.5;


    @Override
    public void onInitialize() {
        // Create the config file. If it exist, the method will read from it
        ModConfigManager.createConfigFile();

        // Create a command to read the new config if changed

        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, environment) -> {
            dispatcher.register(CommandManager.literal("Distance")
                    .then(CommandManager.literal("Dynamic")
                            .then(CommandManager.literal("Damage")
                                    .then(CommandManager.literal("Reload")
                                            .requires(source -> source.hasPermissionLevel(4))
                                            .executes(context -> {
                                                ModConfigManager.readConfigFile();
                                                return 1;
                                            })))));
        });
    }
}