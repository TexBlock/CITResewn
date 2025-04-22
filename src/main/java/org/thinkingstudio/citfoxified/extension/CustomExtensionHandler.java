package org.thinkingstudio.citfoxified.extension;

//? if >=1.21 {
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
//?}

public class CustomExtensionHandler {
    //? if >=1.21 {
    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(FMLCommonSetupEvent.class, event -> {
            ModList.get().forEachModContainer((modId, container) -> {
                var configScreenRegistration = container.getCustomExtension(ConfigScreenRegistration.class);
                configScreenRegistration.ifPresent(extension -> {
                    container.registerExtensionPoint(IConfigScreenFactory.class, extension.getModConfigScreenFactory());
                });
            });
        });
    }
    //?}
}
