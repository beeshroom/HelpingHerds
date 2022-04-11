package bee.beeshroom.helpingherds.core.init;

import bee.beeshroom.helpingherds.HelpingHerds;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			HelpingHerds.MOD_ID);

/*	public static final RegistryObject<SpawnEggItem> SKELETON_SPAWN_EGG = ITEMS.register("skeleton_head_spawn_egg",
			() -> new SpawnEggItem(EntityInit.SKELETON_HEAD, 0xe8edeb, 0x838584,
					new Item.Properties().tab(HelpingHerds.HELPINGHERDS_GROUP))); */
	
	//Block Items
	
/*	public static final RegistryObject<BlockItem> GOLEM_FLOWER = ITEMS.register("golem_flower",
			() -> new BlockItem(BlockInit.GOLEM_FLOWER.get(),
					new Item.Properties().tab(HelpingHerds.HELPINGHERDS_GROUP))); */
			
	public static final RegistryObject<BlockItem> SCATTERED_HAY = ITEMS.register("scattered_hay",
			() -> new BlockItem(BlockInit.SCATTERED_HAY.get(),
					new Item.Properties()
					//.tab(HelpingHerds.HELPINGHERDS_GROUP)));
					.tab(ItemGroup.TAB_DECORATIONS)));
			
/*
	public static final RegistryObject<Item> MUTATIUS = ITEMS.register("mutatius",
			() -> new Mutatius(new Item.Properties().tab(HelpingHerds.HELPINGHERDS_GROUP)));
	*/
	}
	
