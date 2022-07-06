package bee.beeshroom.helpingherds.core.event;

import java.util.Collection;
import java.util.Random;

import bee.beeshroom.helpingherds.Config;
import bee.beeshroom.helpingherds.HelpingHerds;
import bee.beeshroom.helpingherds.core.init.EffectsInit;
import bee.beeshroom.helpingherds.goals.LlamaAttacksWithOwnerGoal;
import bee.beeshroom.helpingherds.goals.LlamaProtectOwnerGoal;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.LlamaSpitEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteractSpecific;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = HelpingHerds.MOD_ID)
public class HelpingHerdsEvents {
	
	@SubscribeEvent
	public static void BreakSpeed (net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed event)
	{
		PlayerEntity player = event.getPlayer();
		ItemStack stack = player.getMainHandItem();
		Item item = stack.getItem();
		BlockState state = event.getState();
		
		if (Config.RIDER_BREAK_SPEED.get())
		{
		if (player.isPassenger())
		{
			event.setNewSpeed(item.getDestroySpeed(stack, state));
		}
		}
	}
	
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
//												why is this here???? vvv
		if (entity instanceof LlamaEntity) //|| entity instanceof LlamaSpitEntity)
		{
			if (((LlamaEntity) entity).isWearingArmor() || entity instanceof TraderLlamaEntity)
					{
				event.setAmount(event.getAmount() - 1.0f);
					}
		}
		}
		
		if (Config.LLAMA_SPIT_BUFF.get())
		{
			if (attacker instanceof LlamaEntity) 
			{
				Random random = ((LivingEntity) entity).getRandom();
//I referenced CreeperEntity code for this
				Collection<EffectInstance> collection = attacker.getActiveEffects();
			
				if (Config.SLOBBERED_EFFECT.get())
				{
				((LivingEntity) entity).addEffect(new EffectInstance(EffectsInit.SLOBBERED.get(), 100, 0));
				 }
				
				 if (!collection.isEmpty()) {
					 for(EffectInstance effectinstance : collection) {
				            ((LivingEntity) entity).addEffect(new EffectInstance(effectinstance));
				         }
				 }
				
				
				((LivingEntity)entity).push(.30D, .20D, .30D);
				event.setAmount(event.getAmount() + 0.5f);
				
				if (random.nextInt(3) == 0) {
					entity.clearFire();
				}
				
			//}
				
			/*	if (entity instanceof MobEntity)
				{
				 ((MobEntity)entity).setTarget(null);
				 ((LivingEntity) entity).setLastHurtByMob(null);
				 ((LivingEntity) entity).setLastHurtByPlayer(null);
				 ((MobEntity)entity).setTarget(null);
				} */
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
		
	/*	if (Config.LLAMA_BODYGUARD.get())
		{
		if (attacker instanceof LlamaEntity)
		{
			//if (attacker.getLastHurtMobTimestamp() > 20)
		//	{
			((LlamaEntity) attacker).setTarget(null);
			((LlamaEntity) attacker).setLastHurtByMob(null);
			((LlamaEntity) attacker).setLastHurtMob(null);
		//}
			//attacker.push(0, 0.1D, 0);
		} } */
		
/*		if (Config.LLAMA_BODYGUARD.get())
			{
		if (attacker instanceof LlamaEntity)
			{
			
			if (entity instanceof MobEntity) {
				if (!(((MobEntity) entity).getTarget() instanceof LlamaEntity)) {
			
					attacker.push(0, 0.1D, 0);
				}
			}
			*/
		/*	if (entity instanceof MobEntity)
				{
				((MobEntity)entity).setTarget(null);
				 ((LivingEntity) entity).setLastHurtByMob(null);
				 ((LivingEntity) entity).setLastHurtMob(null);
				 ((MobEntity)entity).setTarget(null);
				} */ 
		}
	
	@SubscribeEvent
	public static void ChickenShedFeathers(LivingUpdateEvent event)
	{
		LivingEntity entity = event.getEntityLiving();
		
		if (Config.FEATHER_SHED.get())
		{
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
		 //this causes CRASH
	/*	 if (Config.LLAMA_BODYGUARD.get())
			{
			 Entity entity = event.getEntity();
			 if (entity instanceof TraderLlamaEntity)
			 {
				// if (((TraderLlamaEntity) entity).isLeashed())
			//	 {
				 ((TraderLlamaEntity) entity).setChest(true);
				 ((TraderLlamaEntity) entity).setTamed(true);
				 ((TraderLlamaEntity) entity).setOwnerUUID(null); 
				 //	 }
			 }
			} */
		 if (Config.ALL_LLAMAS_MAX_SLOTS.get())
			{
				Entity entity = event.getEntity();
						if (entity instanceof LlamaEntity)
						{
							LlamaEntity llamaentity = (LlamaEntity) event.getEntity();
							llamaentity.setStrength(5);
						}
			}
		 
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
					llamaentity.targetSelector.addGoal(1, new LlamaAttacksWithOwnerGoal(llamaentity));
				//	llamaentity.targetSelector.addGoal(1, (new HurtByTargetGoal(llamaentity)).setAlertOthers());
				}
			/*	if (entity instanceof TraderLlamaEntity)
				{
					TraderLlamaEntity llamaentity = (TraderLlamaEntity) event.getEntity();
					llamaentity.targetSelector.removeGoal(TraderLlamaEntity.FollowTraderGoal);
				} */
			} 
			}
		  
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
		
		if (Config.LLAMA_BODYGUARD.get())
		{
		//World world = event.getWorld();
		Entity entity = event.getEntity();
		// (!world.isClientSide)
		//{
		if (entity instanceof LlamaSpitEntity)
		{			
		//	if (((LlamaSpitEntity) entity).getOwner() != null)
			//	{
		//	((LlamaSpitEntity) entity).getOwner().push(0, 0.1D, 0);
			
		if (((LlamaSpitEntity) entity).getOwner() instanceof MobEntity)
		{
			((LlamaSpitEntity) entity).getOwner().push(0, 0.1D, 0);
			
	//		if (((MobEntity) ((LlamaSpitEntity) entity).getOwner()).getLeashHolder() != null) {
				
				
	//		if (((MobEntity) ((LlamaSpitEntity) entity).getOwner()).getTarget() != null)
				//	{

			if (((LivingEntity) ((LlamaSpitEntity) entity).getOwner()).getLastHurtByMobTimestamp() > 40)
			{
		//		((LlamaSpitEntity) entity).getOwner().push(0, 0.1D, 0);
				((MobEntity) ((LlamaSpitEntity) entity).getOwner()).setLastHurtMob(null);
				((MobEntity) ((LlamaSpitEntity) entity).getOwner()).setLastHurtByMob(null);
				((MobEntity) ((LlamaSpitEntity) entity).getOwner()).setTarget(null);
			}
			if (((LivingEntity) ((LlamaSpitEntity) entity).getOwner()).getLastHurtMobTimestamp() > 30)
			{
		//		((LlamaSpitEntity) entity).getOwner().push(0, 0.1D, 0);
				((MobEntity) ((LlamaSpitEntity) entity).getOwner()).setLastHurtMob(null);
				((MobEntity) ((LlamaSpitEntity) entity).getOwner()).setLastHurtByMob(null);
				((MobEntity) ((LlamaSpitEntity) entity).getOwner()).setTarget(null);
			}
			
			
			
	//		if (((MobEntity) ((LlamaSpitEntity) entity).getOwner()).getTarget() instanceof MobEntity) {
				if (((MobEntity) ((LlamaSpitEntity) entity).getOwner()).getTarget() != null)
				{
				if (!((MobEntity) ((LlamaSpitEntity) entity).getOwner()).getTarget().isAlive())
				{
			//		((LlamaSpitEntity) entity).getOwner().push(0, 0.1D, 0);
					((MobEntity) ((LlamaSpitEntity) entity).getOwner()).setLastHurtMob(null);
					((MobEntity) ((LlamaSpitEntity) entity).getOwner()).setLastHurtByMob(null);
					((MobEntity) ((LlamaSpitEntity) entity).getOwner()).setTarget(null);
				} }
				
				if (((MobEntity) ((LlamaSpitEntity) entity).getOwner()).getLastHurtByMob() != null)
				{
				if (!((MobEntity) ((LlamaSpitEntity) entity).getOwner()).getLastHurtByMob().isAlive())
				{
			//		((LlamaSpitEntity) entity).getOwner().push(0, 0.1D, 0);
					((MobEntity) ((LlamaSpitEntity) entity).getOwner()).setLastHurtMob(null);
					((MobEntity) ((LlamaSpitEntity) entity).getOwner()).setLastHurtByMob(null);
					((MobEntity) ((LlamaSpitEntity) entity).getOwner()).setTarget(null); 
				} }
				
				if (((MobEntity) ((LlamaSpitEntity) entity).getOwner()).getLastHurtMob() != null)
				{
				if (!((MobEntity) ((LlamaSpitEntity) entity).getOwner()).getLastHurtMob().isAlive())
				{
				//	((LlamaSpitEntity) entity).getOwner().push(0, 0.1D, 0);
					((MobEntity) ((LlamaSpitEntity) entity).getOwner()).setLastHurtMob(null);
					((MobEntity) ((LlamaSpitEntity) entity).getOwner()).setLastHurtByMob(null);
					((MobEntity) ((LlamaSpitEntity) entity).getOwner()).setTarget(null); 
				} }
				
				if (((MobEntity) ((LlamaSpitEntity) entity).getOwner()).getTarget() != null)
				{
				if (!((MobEntity) ((LlamaSpitEntity) entity).getOwner()).getTarget().isAlive())
				{
			//		((LlamaSpitEntity) entity).getOwner().push(0, 0.1D, 0);
					((MobEntity) ((LlamaSpitEntity) entity).getOwner()).setLastHurtMob(null);
					((MobEntity) ((LlamaSpitEntity) entity).getOwner()).setLastHurtByMob(null);
					((MobEntity) ((LlamaSpitEntity) entity).getOwner()).setTarget(null); 
				} }

			if (((LlamaSpitEntity) entity).getOwner() instanceof TraderLlamaEntity)
			{
			//	if (((MobEntity) ((LlamaSpitEntity) entity).getOwner()).getLeashHolder() != null) {
				//	if (((MobEntity) ((LlamaSpitEntity) entity).getOwner()).getLeashHolder() instanceof WanderingTraderEntity) {
						if (((LivingEntity) ((LlamaSpitEntity) entity).getOwner()).getLastHurtMobTimestamp() > 20)
						{
				//		((LlamaSpitEntity) entity).getOwner().push(0, 0.1D, 0);
						((MobEntity) ((LlamaSpitEntity) entity).getOwner()).setLastHurtMob(null);
						((MobEntity) ((LlamaSpitEntity) entity).getOwner()).setLastHurtByMob(null);
						((MobEntity) ((LlamaSpitEntity) entity).getOwner()).setTarget(null);
						}
						
						if (((LivingEntity) ((LlamaSpitEntity) entity).getOwner()).getLastHurtByMobTimestamp() > 40)
						{
				//			((LlamaSpitEntity) entity).getOwner().push(0, 0.1D, 0);
							((MobEntity) ((LlamaSpitEntity) entity).getOwner()).setLastHurtMob(null);
							((MobEntity) ((LlamaSpitEntity) entity).getOwner()).setLastHurtByMob(null);
							((MobEntity) ((LlamaSpitEntity) entity).getOwner()).setTarget(null);
						}
					}
												}
			}
		}
	} 
		
			@SubscribeEvent
			public static void SummonWanderingTrader(RightClickItem event)
			{
				if (Config.WANDERING_TRADER_SQUAD.get())
				{
				PlayerEntity player = event.getPlayer();
				//Hand hand = event.getHand();
				ItemStack stack = player.getMainHandItem();
				Item item = stack.getItem();
		//		ServerWorld world;

				
				if (item == Items.WANDERING_TRADER_SPAWN_EGG && player.isCrouching())
				{
					
// see: net.minecraft.world.spawner.WanderingTraderSpawner 
					
					 WanderingTraderEntity wanderingtraderentity = EntityType.WANDERING_TRADER.create(player.level);
					 wanderingtraderentity.moveTo(player.getX(), player.getY(), player.getZ(), player.yRot, player.xRot);
					 player.level.addFreshEntity(wanderingtraderentity);
					 
					 if (wanderingtraderentity != null) {
						 
						   TraderLlamaEntity traderllamaentity = EntityType.TRADER_LLAMA.create(player.level);
		            	   traderllamaentity.moveTo(player.getX(), player.getY(), player.getZ(), player.yRot, player.xRot);
		            	   Random random = traderllamaentity.getRandom();
		            	   traderllamaentity.setVariant(random.nextInt(2) + 1);
							 player.level.addFreshEntity(traderllamaentity);
			            	   
		            	   if (traderllamaentity != null) {
		                      traderllamaentity.setLeashedTo(wanderingtraderentity, true);
		                   }
		            	   
		            	   TraderLlamaEntity traderllamaentity2 = EntityType.TRADER_LLAMA.create(player.level);
		            	   traderllamaentity2.moveTo(player.getX(), player.getY(), player.getZ(), player.yRot, player.xRot);
		            	   Random random2 = traderllamaentity.getRandom();
		            	   traderllamaentity2.setVariant(random2.nextInt(2) + 1);
							 player.level.addFreshEntity(traderllamaentity2);
			            	   
		            	   if (traderllamaentity2 != null) {
		                      traderllamaentity2.setLeashedTo(wanderingtraderentity, true);
		                   }

			               //serverLevelData.setWanderingTraderId(wanderingtraderentity.getUUID());
			               wanderingtraderentity.setDespawnDelay(48000);
			               wanderingtraderentity.setWanderTarget(player.blockPosition());
			               wanderingtraderentity.restrictTo(player.blockPosition(), 16);
			               //return true;
			            }
					
					 if (!player.abilities.instabuild) {
			               stack.shrink(1);
			            }
					 
		        }				
				}
			}
			
			
			//Im shocked that this works
			
			@SubscribeEvent
			public static void GiveTraderSomeLlamas(EntityInteractSpecific event)
			{
				PlayerEntity player = event.getPlayer();
				Entity target = event.getTarget();
				Hand hand = event.getHand();
				ItemStack itemstack = event.getItemStack();
				
				if (Config.ATTACH_LLAMAS_TO_WANDERING_TRADER.get()) 
				{
				
				if (itemstack.getItem() == Items.TRADER_LLAMA_SPAWN_EGG)
				{
					
					 if (target instanceof WanderingTraderEntity) {
							LivingEntity entity = (LivingEntity) target.getEntity();
							
					     ((WanderingTraderEntity) entity).setDespawnDelay(48000);
			             ((WanderingTraderEntity) entity).setWanderTarget(player.blockPosition());
			             ((WanderingTraderEntity) entity).restrictTo(player.blockPosition(), 16);

						   TraderLlamaEntity traderllamaentity = EntityType.TRADER_LLAMA.create(player.level);
		            	   traderllamaentity.moveTo(player.getX(), player.getY(), player.getZ(), player.yRot, player.xRot);
		            	   Random random = traderllamaentity.getRandom();
		            	   traderllamaentity.setVariant(random.nextInt(2) + 1);
							 player.level.addFreshEntity(traderllamaentity);
			            	   
		            	   if (traderllamaentity != null) {
		                      traderllamaentity.setLeashedTo(target, true);
		                   }
		            	   
		            	     ((WanderingTraderEntity) entity).setDespawnDelay(48000);
				             ((WanderingTraderEntity) entity).setWanderTarget(player.blockPosition());
				             ((WanderingTraderEntity) entity).restrictTo(player.blockPosition(), 16);
			            }	
					 
					 if (!player.abilities.instabuild) {
						 itemstack.shrink(1);
			            }
					 
		        }

				}
				
			/*	if (target instanceof LlamaSpitEntity)
				{
					if (itemstack.getItem() == Items.GLASS_BOTTLE) {
			            itemstack.shrink(1);
			            target.playSound(SoundEvents.BOTTLE_FILL, 1.0F, 1.0F);
			            if (itemstack.isEmpty()) {
			             //  player.setItemInHand(hand, new ItemStack(Items.HONEY_BOTTLE));
			               player.setItemInHand(hand, PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.SLOWNESS));
			            } else if (!player.inventory.add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.SLOWNESS))) {
			              // player.drop(new ItemStack(Items.HONEY_BOTTLE), false);
				        	 player.drop(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.SLOWNESS), false);

			            }
					}
				} */
				
			}
			
			
		/*	@SubscribeEvent
			public static void LeadSnaps(EntityLeaveWorldEvent event)
			{
				Entity entity = event.getEntity();
				
				if (entity instanceof LeashKnotEntity)
				{
					entity.playSound(SoundEvents.LEASH_KNOT_BREAK, 1.0f, 1.0f);
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
			
	/*		@SubscribeEvent
			public static void HealYourFarmFriends(EntityInteractSpecific event)
			{
				if (Config.HEAL_FARM_ANIMALS.get())
				{
				PlayerEntity player = event.getPlayer();
				Entity target = event.getTarget();
				LivingEntity entity = (LivingEntity) target.getEntity();
				Hand hand = event.getHand();
				ItemStack itemstack = event.getItemStack();
				int heal_amount = 2;
				
			      if (itemstack.getItem() == Items.GLISTERING_MELON_SLICE 
			    		  && target instanceof SheepEntity || target instanceof CowEntity
			    		  || target instanceof PigEntity  || target instanceof ChickenEntity
			    		  || target instanceof HorseEntity  || target instanceof RabbitEntity
			    	  ) 
			      		{
			    	  entity.heal(heal_amount);
			    	  if (!player.abilities.instabuild) {
			    		  itemstack.shrink(1);
			    	      								}
			      		}
				} 
			} */

}