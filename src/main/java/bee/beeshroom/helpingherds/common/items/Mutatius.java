/*
//WACK

package bee.beeshroom.helpingherds.common.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;

public class Mutatius extends Item {

	public Mutatius(Properties properties) {
		super(properties);
	}

	 public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
	      if (entity instanceof LivingEntity && entity.isAlive()) {
	    	  LivingEntity livingentity = (LivingEntity)entity;
	    	// livingentity.getLootTable();
	    	 livingentity.spawnAtLocation((IItemProvider) livingentity.getLootTable());
	    	  
	    	  stack.shrink(1);
	    	  return ActionResultType.sidedSuccess(player.level.isClientSide);
	      }
		return ActionResultType.PASS;
	      
	 }
	 
} */
	/* public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
	      if (entity instanceof LivingEntity && entity.isAlive()) {
	    	  
	    	  LivingEntity livingentity = (LivingEntity)entity;
	    	  //Random random = livingentity.getRandom();
	    	//  DamageSource playerentity = (DamageSource)player;
	    	 // DamageSource damagesource;
	            if (!player.level.isClientSide) {
	            //	livingentity.equipSaddle(SoundCategory.NEUTRAL);
	            	livingentity.getLootTable();
	            	// livingentity.dropAllDeathLoot(playerentity);
	            	Random random = livingentity.getRandom();
	                BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
	                blockpos$mutable.set(livingentity.blockPosition());
	                livingentity.randomTeleport((double)(blockpos$mutable.getX() + random.nextInt(11) - 5), (double)(blockpos$mutable.getY() + random.nextInt(5) - 2), (double)(blockpos$mutable.getZ() + random.nextInt(11) - 5), false);
	                blockpos$mutable.set(livingentity.blockPosition());
	            	
	            	//   LootTable loottable = livingentity.level.getServer().getLootTables().get(LootTables.);
	            	//   ResourceLocation loottable = livingentity.getLootTable();
	              //     LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld)livingentity.level)).withParameter(LootParameters.ORIGIN, livingentity.position()).withParameter(LootParameters.THIS_ENTITY, livingentity).withRandom(random);

	               stack.shrink(1);
	            
	             //  for(ItemStack itemstack : loottable.getRandomItems(lootcontext$builder.create(LootParameterSets.GIFT))) {
	            	   livingentity.level.addFreshEntity(new ItemEntity(livingentity.level, (double)blockpos$mutable.getX() - (double)MathHelper.sin(livingentity.yBodyRot * ((float)Math.PI / 180F)), (double)blockpos$mutable.getY(), (double)blockpos$mutable.getZ() + (double)MathHelper.cos(livingentity.yBodyRot * ((float)Math.PI / 180F)) ));
	            	//        }
	          /*     int i = 1 + this.random.nextInt(3);

	               for(int j = 0; j < i; ++j) {
	                  ItemEntity itementity = this.spawnAtLocation(livingentity.getLootTable().get(this.getColor()), 1);
	                  if (itementity != null) {
	                     itementity.setDeltaMovement(itementity.getDeltaMovement().add((double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F), (double)(this.random.nextFloat() * 0.05F), (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F)));
	             
	                  
	               }

	            return ActionResultType.sidedSuccess(player.level.isClientSide);
	         
	      }

	      return ActionResultType.PASS;
	   }
	 
}
*/