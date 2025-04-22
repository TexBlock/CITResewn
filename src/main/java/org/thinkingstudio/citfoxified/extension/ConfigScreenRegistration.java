package org.thinkingstudio.citfoxified.extension;

//? if >=1.21 {
import net.neoforged.fml.IExtensionPoint;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
//?}

public interface ConfigScreenRegistration /*? >=1.21 {*/extends IExtensionPoint/*?}*/ {
    //? if >=1.21 {
    IConfigScreenFactory getModConfigScreenFactory();
    //?}
}
