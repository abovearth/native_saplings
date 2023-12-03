package net.mcreator.nativesaplings.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.level.BlockEvent;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class PrintcropeventProcedure {
	@SubscribeEvent
	public static void onCropGrowPre(BlockEvent.CropGrowEvent.Pre event) {
		execute(event);
	}

	public static void execute() {
		execute(null);
	}

	private static void execute(@Nullable Event event) {
	}
}
