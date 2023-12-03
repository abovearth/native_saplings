package net.mcreator.nativesaplings.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

public class BiologyBookRightclickedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		String SaplingName = "";
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("native_saplings:saplings_to_check")))) {
			SaplingName = "native_saplings:" + ((ForgeRegistries.ITEMS.getKey((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem()).toString()).replace(":", "_"));/*code*/
		}
	}
}
