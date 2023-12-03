package net.mcreator.nativesaplings.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.level.BlockEvent;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

import net.mcreator.nativesaplings.init.NativeSaplingsModGameRules;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class NonNativeCropsDontGrowProcedure {
	@SubscribeEvent
	public static void onCropGrowPre(BlockEvent.CropGrowEvent.Pre event) {
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ());
	}

	public static void execute(LevelAccessor world, double x, double y, double z) {
		execute(null, world, x, y, z);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z) {
		String SaplingName = "";
		BlockState blockBelow = Blocks.AIR.defaultBlockState();
		double BlockY = 0;
		if (world.getLevelData().getGameRules().getBoolean(NativeSaplingsModGameRules.PLANT_SAPLINGS_ONLY_IN_NATIVE_BIOMES)) {
			BlockY = y;
			if (world.isEmptyBlock(BlockPos.containing(x, y, z)) || (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.WATER || (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.WATER) {
				blockBelow = (world.getBlockState(BlockPos.containing(x, y - 1, z)));
				if (blockBelow.is(BlockTags.create(new ResourceLocation("native_saplings:grow_check_block_below")))) {
					BlockY = y - 1;
				}
			}
			if ((world.getBlockState(BlockPos.containing(x, BlockY, z))).is(BlockTags.create(new ResourceLocation("native_saplings:saplings_to_check")))) {
				SaplingName = "native_saplings:" + (ForgeRegistries.BLOCKS.getKey((world.getBlockState(BlockPos.containing(x, BlockY, z))).getBlock()).toString()).replace(":", "_");
				if (!world.getBiome(BlockPos.containing(x, BlockY, z)).is(TagKey.create(Registries.BIOME, new ResourceLocation((SaplingName).toLowerCase(java.util.Locale.ENGLISH))))) {
					if (event != null && event.hasResult()) {
						event.setResult(Event.Result.DENY);
					}
				}
			}
		}
	}
}
