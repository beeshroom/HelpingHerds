package bee.beeshroom.helpingherds;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

//thank-you mcjty

@Mod.EventBusSubscriber
public class Config {

	public static ForgeConfigSpec COMMON_CONFIG;
	public static ForgeConfigSpec CLIENT_CONFIG;
	
	//
	public static final String CATEGORY_SETTINGS = "settings";

	public static ForgeConfigSpec.BooleanValue PUNCH_CHICKEN_GET_FEATHER;
	public static ForgeConfigSpec.BooleanValue FEATHER_SHED;
	public static ForgeConfigSpec.BooleanValue FARM_ANIMAL_DROPS;
	public static ForgeConfigSpec.BooleanValue TEMPT_HORSE_AND_LLAMA;
	public static ForgeConfigSpec.BooleanValue LLAMA_BODYGUARD;
	public static ForgeConfigSpec.BooleanValue LLAMA_ARMOR;
	public static ForgeConfigSpec.BooleanValue LLAMA_SPIT_BUFF;

	//
	public static final String CATEGORY_CLIENT = "client";

	static {
		ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
		COMMON_BUILDER
				.comment("Settings").push(CATEGORY_SETTINGS);
		
		//
		PUNCH_CHICKEN_GET_FEATHER = COMMON_BUILDER
				.comment("Sometimes chickens will spawn feathers when taking damage (Default: true)")
				.define("enablePunchChickenGetFeather", true);
		
		FEATHER_SHED = COMMON_BUILDER
				.comment("Chickens will shed feathers every so often (Default: true)")
				.define("enableChickenShed", true);
		
		FARM_ANIMAL_DROPS = COMMON_BUILDER
				.comment("Cows, Chickens, and Rabbits will ALWAYS drop at least one item [If they were going to drop nothing, they will drop a Leather, Feather, or Rabbit Hide respectively] (Default: true)")
				.define("enableFarmAnimalDrops", true);
		
		TEMPT_HORSE_AND_LLAMA = COMMON_BUILDER
				.comment("Horses will follow apples and carrots; Llamas will follow hay blocks (Default: true)")
				.define("enableTemptHorseAndLlama", true);
		
		LLAMA_BODYGUARD = COMMON_BUILDER
				.comment("When being lead, a tamed llama will attack any foe who damages the player who is holding the lead (Default: true)")
				.define("enableLlamaBodyguard", true);
		
		LLAMA_ARMOR = COMMON_BUILDER
				.comment("When equipped with a carpet, llamas will take slightly less damage (Default: true)")
				.define("enableLlamaArmor", true);
		
		LLAMA_SPIT_BUFF = COMMON_BUILDER
				.comment("Llama spit has more knockback and applies a slowness effect for a few seconds (Default: true)")
				.define("enableLlamaSpitBuff", true);
		
		
		
		//
		
		COMMON_BUILDER.pop();
		
		COMMON_CONFIG = COMMON_BUILDER.build();

		ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

		CLIENT_BUILDER.comment("Client settings").push(CATEGORY_CLIENT);

		CLIENT_CONFIG = CLIENT_BUILDER.build();
	}
	
	
	@SubscribeEvent
	public static void onLoad(final ModConfig.Loading configEvent) {
	}

	@SubscribeEvent
	public static void onReload(final ModConfig.Reloading configEvent) {
	}
	
}
