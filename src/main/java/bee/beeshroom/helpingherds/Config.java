package bee.beeshroom.helpingherds;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

//thank-you mcjty

@Mod.EventBusSubscriber
public class Config {

	public static ForgeConfigSpec COMMON_CONFIG;
	//public static ForgeConfigSpec CLIENT_CONFIG;
	
	//
	public static final String CATEGORY_SETTINGS = "settings";

	public static ForgeConfigSpec.BooleanValue PUNCH_CHICKEN_GET_FEATHER;
	public static ForgeConfigSpec.BooleanValue FEATHER_SHED;
	public static ForgeConfigSpec.BooleanValue FARM_ANIMAL_DROPS;
//	public static ForgeConfigSpec.BooleanValue HEAL_FARM_ANIMALS;
	public static ForgeConfigSpec.BooleanValue TEMPT_HORSE_AND_LLAMA;
	public static ForgeConfigSpec.BooleanValue LLAMA_BODYGUARD;
	public static ForgeConfigSpec.BooleanValue LLAMA_ARMOR;
	public static ForgeConfigSpec.BooleanValue LLAMA_SPIT_BUFF;
	public static ForgeConfigSpec.BooleanValue WANDERING_TRADER_SQUAD;
	public static ForgeConfigSpec.BooleanValue ATTACH_LLAMAS_TO_WANDERING_TRADER;
	public static ForgeConfigSpec.BooleanValue RIDER_BREAK_SPEED;
	public static ForgeConfigSpec.BooleanValue SLOBBERED_EFFECT;
	public static ForgeConfigSpec.BooleanValue ALL_LLAMAS_MAX_SLOTS;

	//
	//public static final String CATEGORY_CLIENT = "client";

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
				.comment("Llama spit has more knockback (Default: true)")
				.define("enableLlamaSpitBuff", true);

		SLOBBERED_EFFECT = COMMON_BUILDER
				.comment("Being spit on by a Llama will apply an effect that slows the target down (Default: true)")
				.define("enableSlobberedEffect", true);	
		
		WANDERING_TRADER_SQUAD = COMMON_BUILDER
				.comment("Crouch and face the sky while holding a Wandering Trader Spawn egg to summon a Wandering Trader with 2 llamas (Default: true)")
				.define("enableWanderingTraderSquad", true);
		
		ATTACH_LLAMAS_TO_WANDERING_TRADER = COMMON_BUILDER
				.comment("Interact with a Wandering Trader while holding a Trader Llama spawn egg to attach a Trade Llama to it (Default: true)")
				.define("enableAttachLlamasToWanderingTrader", true);
		
		RIDER_BREAK_SPEED = COMMON_BUILDER
				.comment("Breaking blocks while riding a mob or being in a Minecart/Boat won't be slow (Default: true)")
				.define("enableRiderBreakSpeed", true);
		
		ALL_LLAMAS_MAX_SLOTS = COMMON_BUILDER
				.comment("Every Llama with a chest equipped will have the maximum number of chest slots (Default: true)")
				.define("enableLlamaMaxChestSlots", true);
		
		//
		
		COMMON_BUILDER.pop();
		
		COMMON_CONFIG = COMMON_BUILDER.build();

		//ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

		//CLIENT_BUILDER.comment("Client settings").push(CATEGORY_CLIENT);

		//CLIENT_CONFIG = CLIENT_BUILDER.build();
	}
	
	
	@SubscribeEvent
	public static void onLoad(final ModConfig.Loading configEvent) {
	}

	@SubscribeEvent
	public static void onReload(final ModConfig.Reloading configEvent) {
	}
	
}
