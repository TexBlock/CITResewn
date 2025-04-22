package shcm.shsupercm.fabric.citresewn.defaults;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import shcm.shsupercm.fabric.citresewn.defaults.cit.types.TypeArmor;
import shcm.shsupercm.fabric.citresewn.defaults.cit.types.TypeElytra;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Holder for utility compatibility methods for use in other mods.
 */
public abstract class CITResewnDefaultsCompatAPI {
    /**
     * Registers a slot redirect for type=armor
     * @param redirect returns the currently visible armor item in the given equipment slot or null to not redirect.
     */
    protected final void typeArmorRedirectSlotGetter(BiFunction<LivingEntity, EquipmentSlot, ItemStack> redirect) {
        TypeArmor.CONTAINER.getItemInSlotCompatRedirects.add(redirect);
    }

    /**
     * Registers a slot redirect for type=elytra
     * @param redirect returns the currently visible elytra item or null to not redirect.
     */
    protected final void typeElytraRedirectSlotGetter(Function<LivingEntity, ItemStack> redirect) {
        TypeElytra.CONTAINER.getItemInSlotCompatRedirects.add(redirect);
    }
}
