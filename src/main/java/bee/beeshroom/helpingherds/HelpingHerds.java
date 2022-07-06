package bee.beeshroom.helpingherds;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bee.beeshroom.helpingherds.core.init.BlockInit;
import bee.beeshroom.helpingherds.core.init.EffectsInit;
import bee.beeshroom.helpingherds.core.init.ItemInit;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(HelpingHerds.MOD_ID)
public class HelpingHerds {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "helpingherds";
	//public static final ItemGroup HELPINGHERDS_GROUP = new HelpingHerdsGroup("helpingherdsgroup");

	public HelpingHerds() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
	//	bus.addListener(this::setup);
		bus.addListener(this::clientSetup);
		//MinecraftForge.EVENT_BUS.register(this);

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
	//	ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
		
	//	Enchantments.ENCHANTMENTS.register(bus);
		ItemInit.ITEMS.register(bus);
		BlockInit.BLOCKS.register(bus);

		EffectsInit.EFFECTS.register(bus);

		//MinecraftForge.EVENT_BUS.register(this);

		//BiomeFeatures.FEATURES.register(bus);
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	 private void clientSetup(final FMLClientSetupEvent event)
	    {
	        RenderTypeLookup.setRenderLayer(BlockInit.SCATTERED_HAY.get(), RenderType.cutout());
	     } 
	 
	/*	private void setup(final FMLCommonSetupEvent event) {// K9#8016
			DeferredWorkQueue.runLater(() -> {
				ComposterBlock.registerCompostable(0.6f, BlockInit.JAZZ_LEAVES.get());
			});
			DeferredWorkQueue.runLater(() -> {
				ComposterBlock.registerCompostable(0.4f, ItemInit.SEED_ITEM.get());
			});
		}*/

	/* public static class HelpingHerdsGroup extends ItemGroup {

		public HelpingHerdsGroup(String label) {
			super(label);
		}

		@Override
		public ItemStack makeIcon() {
			return Items.WHEAT.getDefaultInstance(); 
		}  
	} */
}