package bee.beeshroom.helpingherds.core.init;

import bee.beeshroom.helpingherds.HelpingHerds;
import bee.beeshroom.helpingherds.common.effects.SlobberedEffect;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectsInit 
{
	public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, HelpingHerds.MOD_ID);

	public static final RegistryObject<Effect> SLOBBERED = EFFECTS.register("slobbered", SlobberedEffect::new);
	
}
