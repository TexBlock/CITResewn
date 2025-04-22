package org.thinkingstudio.citfoxified.helper;

//? if >=1.20.4 {
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforgespi.language.IModInfo;
//?} else if <1.20.4 {
/*import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.forgespi.language.IModInfo;
*///?}

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class ModPlatformHelper {
    public static boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    public static Optional<? extends ModContainer> getModContainer(String modId) {
        return ModList.get().getModContainerById(modId);
    }

    public static boolean isDevelopmentEnvironment() {
        return !FMLLoader.isProduction();
    }

    public static Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get();
    }

    public static List<IModInfo> getAllMods() {
        return ModList.get().getMods();
    }
}
