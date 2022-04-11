package bee.beeshroom.helpingherds.core.event;

import java.util.Random;

import bee.beeshroom.helpingherds.Config;
import bee.beeshroom.helpingherds.HelpingHerds;
import bee.beeshroom.helpingherds.goals.LlamaProtectOwnerGoal;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.passive.horse.DonkeyEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.passive.horse.LlamaEntity;
import net.minecraft.entity.passive.horse.MuleEntity;
import net.minecraft.entity.passive.horse.SkeletonHorseEntity;
import net.minecraft.entity.passive.horse.TraderLlamaEntity;
import net.minecraft.entity.projectile.LlamaSpitEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = HelpingHerds.MOD_ID)
public class HelpingHerdsEvents {
	
	@SubscribeEvent
	public static void PunchChicken(LivingDamageEvent event)
	{
		Entity entity = event.getEntity();
		DamageSource source = event.getSource();
		LivingEntity attacker = (LivingEntity)source.getEntity();
			
		if (Config.PUNCH_CHICKEN_GET_FEATHER.get())
		{
		Random random = ((LivingEntity) entity).getRandom();
		if (entity instanceof ChickenEntity && !((LivingEntity) entity).isBaby()
				&& entity.level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT))
		{
			if (random.nextInt(10) < 4)
				{
				   entity.push(0, 0.2D, 0);
			       entity.spawnAtLocation(Items.FEATHER);
				}
		}
		}
		
		if (Config.LLAMA_ARMOR.get())
		{
		if (entity instanceof LlamaEntity || entity instanceof LlamaSpitEntity)
		{
			if (((LlamaEntity) entity).isWearingArmor() || entity instanceof TraderLlamaEntity)
					{
				event.setAmount(event.getAmount() - 1.0f);
					}
		}
		}
		
		if (Config.LLAMA_SPIT_BUFF.get())
		{
			//this is a hacky way of doing it that would have a bunch of issues vvv
			//(source.isProjectile() && entity.getLastHurtByMob() instanceof LlamaEntity) 
			if (attacker instanceof LlamaEntity || entity instanceof LlamaSpitEntity)//&& entity.getLastHurtByMob() instanceof LlamaEntity)
			{
			//	Random random = ((LivingEntity) entity).getRandom();
				((LivingEntity) entity).addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 150, 0));
			//	if (random.nextInt(10) < 2) {
				((LivingEntity)entity).push(.55D, .55D, .55D);
				event.setAmount(event.getAmount() + 0.5f);
			//}
			}
		
		
		//llama spit does bonus damage to Blaze
		if (entity instanceof BlazeEntity)
		{
			if (attacker instanceof LlamaEntity )//&& entity.getLastHurtByMob() instanceof LlamaEntity)
			{
				event.setAmount(event.getAmount() + 2f);
				entity.clearFire();
			}
		}
		}
	}
	
/*	@SubscribeEvent
	public static void ProjectileEvent(ProjectileImpactEvent event)
	{
		Entity entity = event.getEntity();
		if (entity instanceof LivingEntity && ((LivingEntity) entity).getLastHurtByMob() instanceof LlamaEntity)
		{
			((LivingEntity) entity).addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 150, 0));	
		}
	} */
	
	@SubscribeEvent
	public static void ChickenShedFeathers(LivingUpdateEvent event)
	{
		if (Config.FEATHER_SHED.get())
		{
		LivingEntity entity = event.getEntityLiving();
	//	ChickenEntity chicken = (ChickenEntity) event.getEntityLiving();
		Random random = entity.getRandom();
		if (entity instanceof ChickenEntity && !entity.isBaby()
				&& entity.level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT))
		{
			if (random.nextInt(9500) == 0)
				{
			       entity.spawnAtLocation(Items.FEATHER);
				}
		}
		}
	} 
	
	@SubscribeEvent
	public static void AnimalAlwaysDropsSomething(LivingDropsEvent event)
	{
		if (Config.FARM_ANIMAL_DROPS.get())
		{
		LivingEntity entity = event.getEntityLiving();
		
		if (entity.level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT))
				{
	//	CowEntity cow = (CowEntity) event.getEntityLiving();
		if ((entity instanceof CowEntity || entity instanceof HorseEntity || entity instanceof DonkeyEntity || entity instanceof MuleEntity)
				&& !entity.isBaby())
			{
				if (event.getDrops().isEmpty())
				{
			     entity.spawnAtLocation(Items.LEATHER);
				}
			}
		
		if (entity instanceof ChickenEntity && !entity.isBaby())
			{
				if (event.getDrops().isEmpty())
				{
				 entity.spawnAtLocation(Items.FEATHER);
				}
			}
			
		if (entity instanceof RabbitEntity && !entity.isBaby())
			{
				//if (!event.getDrops().contains(Items.RABBIT_HIDE))
				if (event.getDrops().isEmpty())
				{
				 entity.spawnAtLocation(Items.RABBIT_HIDE);
				}
			}		
		}
				}
	}
	

	
	 @SubscribeEvent
	public static void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if (Config.TEMPT_HORSE_AND_LLAMA.get())
		{
		World world = event.getWorld();
		Entity entity = event.getEntity();
				
		if (!world.isClientSide)
		{
			if (entity instanceof LlamaEntity)
			{
				((CreatureEntity) entity).goalSelector.addGoal(4, new TemptGoal((CreatureEntity) entity, 1.1D, Ingredient.of(Blocks.HAY_BLOCK.asItem()), false));
			}
			if ((entity instanceof HorseEntity || entity instanceof DonkeyEntity || entity instanceof MuleEntity) 
				&& !(entity instanceof LlamaEntity || entity instanceof SkeletonHorseEntity))
			{
				((CreatureEntity) entity).goalSelector.addGoal(4, new TemptGoal((CreatureEntity) entity, 1.1D, Ingredient.of(Items.CARROT, Items.APPLE, Items.GOLDEN_CARROT, Items.GOLDEN_APPLE, Items.ENCHANTED_GOLDEN_APPLE), false));
			}
		} 
		}
	} 
	
	
			@SubscribeEvent
			public static void LlamaBodyGuards(EntityJoinWorldEvent event)
			{
				if (Config.LLAMA_BODYGUARD.get())
				{
				Entity entity = event.getEntity();
			//	LivingEntity llama = (LivingEntity) event.getEntity();
				{		
					if (entity instanceof LlamaEntity)
					{
						LlamaEntity llamaentity = (LlamaEntity) event.getEntity();
						//llamaentity.goalSelector.addGoal(1, new PanicGoal(llamaentity, 2.0D));
						llamaentity.targetSelector.addGoal(1, new LlamaProtectOwnerGoal(llamaentity));
					//	llamaentity.targetSelector.addGoal(2, new LlamaAttackWhenTargetted(llamaentity));
					}
				} 
				}
			} 
			
			@SubscribeEvent
			public static void UnStuckLlamaBodyGuards(LivingDeathEvent event)
			{
				if (Config.LLAMA_BODYGUARD.get())
				{
				LivingEntity entity = event.getEntityLiving();
				LivingEntity killer = entity.getKillCredit();
				
				if (killer instanceof LlamaEntity)
				{
				//	killer.moveTo(killer.getX(), killer.getY() + 1D, killer.getZ());
					//killer.setOnGround(false);
					killer.push(0, 0.1D, 0);
					//((LlamaEntity) killer).setTarget(null);
				}
				if (entity.getLastHurtByMob() instanceof LlamaEntity)
				{
					entity.getLastHurtByMob().push(0, 0.1D, 0);
				}
				if (entity.getLastHurtMob() instanceof LlamaEntity)
				{
					entity.getLastHurtMob().push(0, 0.1D, 0);
				}
				}
			} 
			
			
		/*	@SubscribeEvent
			public static void UnstuckLlama (LivingHurtEvent event) 
			{	
				LivingEntity entity = event.getEntityLiving();
				DamageSource source = event.getSource();
				Entity sourceentity = source.getEntity();	
				
				if (entity instanceof LlamaEntity)  
				{
					 if (sourceentity instanceof LivingEntity)
			         {
			        	 LivingEntity attacker = (LivingEntity) sourceentity;
			        	 
					((LlamaEntity) entity).setTarget(sourceentity);
				}
				}
			} */
	
/*	@SubscribeEvent
	public static void HealYourFarmFriends(EntityInteractSpecific event)
	{
		PlayerEntity player = event.getPlayer();
		Entity target = event.getTarget();
		LivingEntity entity = (LivingEntity) target.getEntity();
		Hand hand = event.getHand();
		ItemStack itemstack = event.getItemStack();
		int heal_amount = 2;
		
	      if (itemstack.getItem() == Items.WHEAT 
	    		  && target instanceof SheepEntity || target instanceof CowEntity) {
	    	  entity.heal(heal_amount);
	    	  if (!player.abilities.instabuild) {
	    		  itemstack.shrink(1);
	    	      }
	      }
	      if (itemstack.getItem() == Items.CARROT 
	    		  && target instanceof PigEntity) {
	    	  entity.heal(heal_amount);
	    	  if (!player.abilities.instabuild) {
	    		  itemstack.shrink(1);
	    	      }
	      }
	      if (itemstack.getItem() == Items.WHEAT_SEEDS || itemstack.getItem() == Items.MELON_SEEDS || itemstack.getItem() == Items.PUMPKIN_SEEDS || itemstack.getItem() == Items.BEETROOT_SEEDS 
	    		  && target instanceof ChickenEntity) {
	    	  entity.heal(heal_amount);
	    	  if (!player.abilities.instabuild) {
	    		  itemstack.shrink(1);
	    	      }
	      }
		
	} */

}