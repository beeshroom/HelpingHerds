
package bee.beeshroom.helpingherds.core.init;

import bee.beeshroom.helpingherds.HelpingHerds;
import bee.beeshroom.helpingherds.common.blocks.ScatteredHay;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			HelpingHerds.MOD_ID);
	
	//thank-you Gigaherz in the Minecraft Mod Development discord!!!
	
	/* public static final RegistryObject<Block> GOLEM_FLOWER = BLOCKS.register("golem_flower",
			() -> new GolemFlowerBlock(Effects.DAMAGE_BOOST, 2, AbstractBlock.Properties.of(Material.PLANT)
					.noCollission()
					.instabreak()
							)); */
	

	public static final RegistryObject<Block> SCATTERED_HAY = BLOCKS.register("scattered_hay",
			() -> new ScatteredHay(AbstractBlock.Properties.of(Material.CLOTH_DECORATION, MaterialColor.COLOR_YELLOW)
					.noCollission()
					//.instabreak()
					.strength(0.1F)
					.sound(SoundType.GRASS)
							));
	
}
