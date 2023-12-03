
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.nativesaplings.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.Item;

import net.mcreator.nativesaplings.item.BiologyBookItem;
import net.mcreator.nativesaplings.NativeSaplingsMod;

public class NativeSaplingsModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, NativeSaplingsMod.MODID);
	public static final RegistryObject<Item> BIOLOGY_BOOK = REGISTRY.register("biology_book", () -> new BiologyBookItem());
}
