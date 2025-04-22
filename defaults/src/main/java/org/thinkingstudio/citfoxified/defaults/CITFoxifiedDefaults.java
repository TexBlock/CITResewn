package org.thinkingstudio.citfoxified.defaults;

//? if >=1.20.4 {
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
//?} else if <1.20.4 {
/*import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.client.ConfigScreenHandler;
*///?}
//? if =1.20.4 {
/*import net.neoforged.fml.ModList;
import net.neoforged.neoforge.client.ConfigScreenHandler;
*///?}
import org.thinkingstudio.citfoxified.cit.impl.CITRegistrarImpl;
import org.thinkingstudio.citfoxified.extension.ConfigScreenRegistration;
import shcm.shsupercm.fabric.citresewn.api.CITGlobalProperties;
import shcm.shsupercm.fabric.citresewn.api.CITTypeContainer;
import shcm.shsupercm.fabric.citresewn.defaults.cit.conditions.*;
import shcm.shsupercm.fabric.citresewn.defaults.cit.types.TypeArmor;
import shcm.shsupercm.fabric.citresewn.defaults.cit.types.TypeElytra;
import shcm.shsupercm.fabric.citresewn.defaults.cit.types.TypeEnchantment;
import shcm.shsupercm.fabric.citresewn.defaults.cit.types.TypeItem;
import shcm.shsupercm.fabric.citresewn.defaults.config.CITResewnDefaultsModMenu;

//? if >=1.21 {
@Mod(value = CITFoxifiedDefaults.MOD_ID, dist = Dist.CLIENT)
//?} else if <1.21 {
/*@Mod(CITFoxifiedDefaults.MOD_ID)
 *///?}
public class CITFoxifiedDefaults {
    public static final String MOD_ID = "citfoxified_defaults";

    //? if >=1.21 {
    public CITFoxifiedDefaults(ModContainer modContainer) {
    //?} else if <1.21 {
    /*public CITFoxifiedDefaults() {
    *///?}

        //? if <1.21 {
        /*var modContainer = ModList.get().getModContainerById(CITFoxifiedDefaults.MOD_ID).orElseThrow();
        *///?}

        if (FMLLoader.getDist().isClient()) {
            //? if >=1.21 {
            modContainer.registerExtensionPoint(ConfigScreenRegistration.class, new CITResewnDefaultsModMenu());
            //?} else if <1.21 {
            /*modContainer.registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory(CITResewnDefaultsModMenu::registerConfigScreen));
            *///?}

            CITRegistrarImpl impl = new CITRegistrarImpl();

            impl.register(CITRegistrarImpl.INBUILT_NAMESPACE, ConditionComponents.CONTAINER);
            impl.register(CITRegistrarImpl.INBUILT_NAMESPACE, ConditionDamage.CONTAINER);
            impl.register(CITRegistrarImpl.INBUILT_NAMESPACE, ConditionDamageMask.CONTAINER);
            impl.register(CITRegistrarImpl.INBUILT_NAMESPACE, ConditionEnchantments.CONTAINER);
            impl.register(CITRegistrarImpl.INBUILT_NAMESPACE, ConditionEnchantmentLevels.CONTAINER);
            impl.register(CITRegistrarImpl.INBUILT_NAMESPACE, ConditionHand.CONTAINER);
            impl.register(CITRegistrarImpl.INBUILT_NAMESPACE, ConditionItems.CONTAINER);
            impl.register(CITRegistrarImpl.INBUILT_NAMESPACE, ConditionStackSize.CONTAINER);

            impl.register(CITRegistrarImpl.INBUILT_NAMESPACE, TypeArmor.CONTAINER);
            impl.register(CITRegistrarImpl.INBUILT_NAMESPACE, TypeElytra.CONTAINER);
            impl.register(CITRegistrarImpl.INBUILT_NAMESPACE, (CITTypeContainer<?>) TypeEnchantment.CONTAINER);
            impl.register(CITRegistrarImpl.INBUILT_NAMESPACE, (CITGlobalProperties) TypeEnchantment.CONTAINER);
            impl.register(CITRegistrarImpl.INBUILT_NAMESPACE, TypeItem.CONTAINER);
        }
    }
}
