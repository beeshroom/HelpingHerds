package bee.beeshroom.helpingherds.common.effects;

import bee.beeshroom.helpingherds.HelpingHerds;
import bee.beeshroom.helpingherds.core.init.EffectsInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HelpingHerds.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SlobberedEffect extends Effect
{

	public SlobberedEffect() 
	{
		super(EffectType.HARMFUL,  0xc1e9d5);
		//this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", (double)-0.50F, AttributeModifier.Operation.MULTIPLY_TOTAL);
		this.addAttributeModifier(Attributes.LUCK, "CC5AF142-2BD2-4215-B636-2605AED11727", -1.0D, AttributeModifier.Operation.ADDITION);
	}

	

@SubscribeEvent
public static void onLivingUpdate(LivingUpdateEvent event) 
	{
	LivingEntity entity = event.getEntityLiving();
	
	 if (entity.hasEffect(EffectsInit.SLOBBERED.get()))
     {
		 
	/*		    for(int i = 0; i < 1; ++i) {
		//	 if (entity.level.isClientSide) {
			   entity.level.addParticle(ParticleTypes.DRIPPING_WATER, entity.getRandomX(1.0D), entity.getRandomY() + 0.5D, entity.getRandomZ(1.0D), 1.02D, 1.02D, 1.02D);
			    }
			*/
		
		 double d0 = Math.abs(entity.getDeltaMovement().y);
	      if (d0 < 0.1D )//&& !entity.isSteppingCarefully()) 
	      {
	         double d1 = 0.2D + d0 * 0.2D;
	         entity.setDeltaMovement(entity.getDeltaMovement().multiply(d1, 0.6D, d1));
	      }
		 
//  this seems a little much
		/* if (entity.isSprinting())
		 {
		 entity.setSprinting(false);
		 } */
	      if (entity.isOnFire())
			 {
				 entity.clearFire();
			 }
	      
	/*      
		 if (entity instanceof MobEntity)
		 {
		
			 entity.setLastHurtByMob(null);
			 entity.setLastHurtMob(null);
			 
			 if ( 
			 (((MobEntity) entity).getTarget() instanceof LlamaEntity) 
			 || (!(((MobEntity) entity).getTarget() instanceof PlayerEntity))
			 )
			 {
			 
	//			 Random random = entity.getRandom();
	//			 if (random.nextInt(3) == 0)
	//					 {
				((MobEntity)entity).setTarget(null);
				 entity.setLastHurtByMob(null);
				 entity.setLastHurtMob(null);
				// entity.setLastHurtByPlayer(null);
				 ((MobEntity)entity).setTarget(null);
		//				 }
				 
			 }
				
			
	//oops this just makes them float up into the sky for the whole time the effect is active,, lol
		 //	 entity.push(0, 0.1D, 0);
		 }		 
		 */
		 
}


} 
}
 