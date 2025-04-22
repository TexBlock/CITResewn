package shcm.shsupercm.fabric.citresewn.defaults.config;

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

public class CITResewnDefaultsModMenu /*? >=1.21 {*/implements ConfigScreenRegistration/*?}*/ {
    //? if >=1.21 {
    @Override
    public IConfigScreenFactory getModConfigScreenFactory() {
        if (ModPlatformHelper.isModLoaded("cloth_config"))
            return new ClothConfigOpenImpl().getModConfigScreenFactory();

        return (modContainer, parent) -> {
            return new NoticeScreen(() -> MinecraftClient.getInstance().setScreen(parent), Text.of("CIT Resewn: Defaults"), Text.of("CIT Resewn requires Cloth Config to be able to show the config."));
        };
    }
    //?} else if <1.21 {
    /*public static Screen registerConfigScreen(MinecraftClient client, Screen parent) {
        if (ModPlatformHelper.isModLoaded("cloth_config"))
            return new ClothConfigOpenImpl().registerConfigScreen(parent);

        return new NoticeScreen(() -> MinecraftClient.getInstance().setScreen(parent), Text.of("CIT Resewn: Defaults"), Text.of("CIT Resewn requires Cloth Config to be able to show the config."));
    }
    *///?}

    private static class ClothConfigOpenImpl /*? >=1.21 {*/implements ConfigScreenRegistration/*?}*/ {
        //? if >=1.21 {
        @Override
        public IConfigScreenFactory getModConfigScreenFactory() {
            return (modContainer, parent) -> CITResewnDefaultsConfigScreenFactory.create(parent);
        }
        //?} else if <1.21 {
        /*public Screen registerConfigScreen(Screen parent) {
            return CITResewnDefaultsConfigScreenFactory.create(parent);
        }
        *///?}
    }
}
