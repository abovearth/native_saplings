package net.mcreator.nativesaplings.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.level.SaplingGrowTreeEvent;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

import net.mcreator.nativesaplings.init.NativeSaplingsModGameRules;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class NonNativeSaplingsDontGrowProcedure {
	@SubscribeEvent
	public static void onSaplingGrow(SaplingGrowTreeEvent event) {
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ());
	}

	public static void execute(LevelAccessor world, double x, double y, double z) {
		execute(null, world, x, y, z);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z) {
		String SaplingName = "";
		if (world.getLevelData().getGameRules().getBoolean(NativeSaplingsModGameRules.PLANT_SAPLINGS_ONLY_IN_NATIVE_BIOMES)) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BlockTags.create(new ResourceLocation("native_saplings:saplings_to_check")))) {
				SaplingName = "native_saplings:" + (ForgeRegistries.BLOCKS.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).replace(":", "_");
				if (!world.getBiome(BlockPos.containing(x, y, z)).is(TagKey.create(Registries.BIOME, new ResourceLocation((SaplingName).toLowerCase(java.util.Locale.ENGLISH))))) {
					if (event != null && event.hasResult()) {
						event.setResult(Event.Result.DENY);
					}
					{
						BlockPos _pos = BlockPos.containing(x, y, z);
						Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y, z), null);
						world.destroyBlock(_pos, false);
					}
				}
			}
		}
	}
}
