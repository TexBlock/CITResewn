package shcm.shsupercm.fabric.citresewn.config;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.NoticeScreen;
import net.minecraft.text.Text;
//? if >=1.21 {
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
//?} else if <1.21 {
/*import net.minecraft.client.gui.screen.Screen;
*///?}
import org.thinkingstudio.citfoxified.extension.ConfigScreenRegistration;
import org.thinkingstudio.citfoxified.helper.ModPlatformHelper;

/**
 * Mod Menu config button integration.
 */
public class CITResewnModMenu /*? >=1.21 {*/implements ConfigScreenRegistration/*?}*/ {
    //? if >=1.21 {
    @Override
    public IConfigScreenFactory getModConfigScreenFactory() {
        if (ModPlatformHelper.isModLoaded("cloth_config"))
            return (modContainer, parent) -> CITResewnConfigScreenFactory.create(parent);

        return (modContainer, parent) -> {
            return new NoticeScreen(() -> MinecraftClient.getInstance().setScreen(parent), Text.of("CIT Resewn"), Text.of("CIT Resewn requires Cloth Config to be able to show the config."));
        };
    }
    //?} else if <1.21 {
    /*public static Screen registerConfigScreen(MinecraftClient client, Screen parent) {
        if (ModPlatformHelper.isModLoaded("cloth_config"))
            return CITResewnConfigScreenFactory.create(parent);

        return new NoticeScreen(() -> MinecraftClient.getInstance().setScreen(parent), Text.of("CIT Resewn"), Text.of("CIT Resewn requires Cloth Config to be able to show the config."));
    }
    *///?}
}
