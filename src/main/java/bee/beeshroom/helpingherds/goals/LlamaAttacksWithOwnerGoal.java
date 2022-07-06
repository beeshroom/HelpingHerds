package bee.beeshroom.helpingherds.goals;

import java.util.EnumSet;

import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.entity.passive.horse.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;

//Llamas Protect Owner ! Idea based on Wandering Trader's and their llama body guards. I always wanted to be able to have llama bodyguards too. Apparently that's difficult to impliment...

//This is hacked-together with heavy use of various goals the Llama and TraderLlama use and Wolf and such's ProtectOwner goals
// with some very messy and silly edits to suit my needs. It doesn't even completely work, sadly.
// (Look at "HelpingHerdsEvents.java" to see the way I had to make llamas get pushed after successfully defeating enemy mobs/ the player killing the llama's enemy/ etc 
// so that they wouldn't get stuck in one spot, spitting at nothing forever until the player pushed them themself)
// but, uh, it's more or less functional enough that I can be somewhat happy with it.
// - - -
//It does notably have some issues:
//1) Llamas will get stuck focusing on a foe and spit at it. And will keep spitting endlessly. Because of that, i have added several things to un-stuck them, such as Pushing them.
//2) For some reason, if you have more than one llama leashed, only the one you leashed *first* will defend you. the other ignores everything?? idk why...
//

public class LlamaAttacksWithOwnerGoal extends TargetGoal {
	   private final LlamaEntity llama;
	      private LivingEntity MobAttackedByOwner;
	      private int timestamp;

	      public LlamaAttacksWithOwnerGoal(LlamaEntity p_i50458_2_) {
	         super(p_i50458_2_, false);
	         this.llama = p_i50458_2_;
	         this.setFlags(EnumSet.of(Goal.Flag.TARGET));
	      }

	      public boolean canUse() {
	          if (this.llama.isTamed() && this.llama.isLeashed() 
	        		  && this.llama.getLeashHolder() instanceof LivingEntity) {
	             LivingEntity livingentity = (LivingEntity) this.llama.getLeashHolder();
	             if (livingentity == null) {
	                return false;
	             } 
	             if (!(this.llama.getLeashHolder() instanceof PlayerEntity)) {
		                return false;
		             }
	             else {
	                this.MobAttackedByOwner = livingentity.getLastHurtMob();
	                int i = livingentity.getLastHurtMobTimestamp();
	                return i != this.timestamp && this.canAttack(this.MobAttackedByOwner, EntityPredicate.DEFAULT);
	             }
	          } else {
	             return false;
	          }
	       }

	       public void start() {
	          this.mob.setTarget(this.MobAttackedByOwner);
	          LivingEntity livingentity = (LivingEntity) this.llama.getLeashHolder();
	          if (livingentity != null) {
	           //  this.timestamp = livingentity.getLastHurtMobTimestamp();
	        	  this.timestamp = 40;
	        //	  this.llama.setLastHurtByMob(null);
	          }

	          super.start();
	       }
	       
}