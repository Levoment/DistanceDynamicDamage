package com.github.levoment.distancedynamicdamage;

import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.util.Properties;

public class ModConfigManager {
    // Variable holding the config file name
    public static final String CONFIG_FILE_NAME = "distancedynamicdamage.properties";

    public static void createConfigFile() {
        // Create the file in memory
        File configFile = FabricLoader.getInstance().getConfigDir().resolve(CONFIG_FILE_NAME).toFile();
        if (!configFile.exists()) {
            // Create and save the config
            try (OutputStream outputStream = new FileOutputStream(configFile)) {
                Properties prop = new Properties();
                // Set the default distance dynamic damage multiplier key and value
                prop.setProperty("DynamicDamageMultiplier", "1.5");
                // Save the properties to the config file
                prop.store(outputStream, null);

            } catch (IOException io) {
                io.printStackTrace();
            }
        } else {
            readConfigFile();
        }
    }

    public static void readConfigFile() {
        // Create the file in memory
        File configFile = FabricLoader.getInstance().getConfigDir().resolve(CONFIG_FILE_NAME).toFile();
        // Read the data from the config file
        try (InputStream inputStream = new FileInputStream(configFile)) {
            Properties prop = new Properties();
            prop.load(inputStream);

            // Get the distance dynamic damage multiplier

            // Try to convert the value to a double
            double dynamicDamageMultiplier = Double.parseDouble(prop.getProperty("DynamicDamageMultiplier"));
            DistanceDynamicDamageMod.DYNAMIC_DAMAGE = dynamicDamageMultiplier;

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NumberFormatException numberFormatException) {
            DistanceDynamicDamageMod.LOGGER.error("There was an error reading the dynamic damage multiplier from the file: " + configFile.getPath());
        }
    }
}
