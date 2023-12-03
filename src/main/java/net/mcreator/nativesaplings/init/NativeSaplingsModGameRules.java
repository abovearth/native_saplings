
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.nativesaplings.init;

import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.GameRules;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class NativeSaplingsModGameRules {
	public static final GameRules.Key<GameRules.BooleanValue> PLANT_SAPLINGS_ONLY_IN_NATIVE_BIOMES = GameRules.register("plantSaplingsOnlyInNativeBiomes", GameRules.Category.MISC, GameRules.BooleanValue.create(true));
}
