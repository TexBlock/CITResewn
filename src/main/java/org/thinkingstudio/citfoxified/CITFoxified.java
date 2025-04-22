package org.thinkingstudio.citfoxified;

//? if >=1.20.4 {
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
//?} else if <1.20.4 {
/*import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
*///?}
//? if =1.20.4 {
/*import net.neoforged.fml.ModList;
import net.neoforged.neoforge.client.ConfigScreenHandler;
*///?}
import org.thinkingstudio.citfoxified.extension.ConfigScreenRegistration;
import org.thinkingstudio.citfoxified.extension.CustomExtensionHandler;
import shcm.shsupercm.fabric.citresewn.CITResewn;
import shcm.shsupercm.fabric.citresewn.config.CITResewnModMenu;

import java.util.Objects;

//? if >=1.21 {
@Mod(value = CITFoxified.MOD_ID, dist = Dist.CLIENT)
//?} else if <1.21 {
/*@Mod(CITFoxified.MOD_ID)
*///?}
public class CITFoxified {
    public static final String MOD_ID = "citfoxified";

    //? if >=1.21 {
    public CITFoxified(ModContainer modContainer, IEventBus modEventBus) {
    //?} else if <1.20.4 {
    /*@SuppressWarnings("removal")
    public CITFoxified() {
    *///?} else if =1.20.4 {
    /*public CITFoxified(IEventBus modEventBus) {
    *///?}

        //? if <1.20.4 {
        /*var modContainer = ModList.get().getModContainerById(CITFoxified.MOD_ID).orElseThrow();
        var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        *///?} else if =1.20.4 {
        /*var modContainer = ModList.get().getModContainerById(CITFoxified.MOD_ID).orElseThrow();
        *///?}

        //? if >=1.21 {
        CustomExtensionHandler.register(Objects.requireNonNull(modEventBus));
        //?}
        if (FMLLoader.getDist().isClient()) {
            //? if >=1.21 {
            modContainer.registerExtensionPoint(ConfigScreenRegistration.class, new CITResewnModMenu());
            //?} else if <1.21 {
            /*modContainer.registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory(CITResewnModMenu::registerConfigScreen));
            *///?}
            CITResewn.onInitializeClient(modEventBus);
        }
    }
}
