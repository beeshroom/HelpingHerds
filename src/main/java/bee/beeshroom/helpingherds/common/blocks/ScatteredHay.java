package bee.beeshroom.helpingherds.common.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class ScatteredHay extends Block {
	   protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);

	   public ScatteredHay(AbstractBlock.Properties p_i48290_2_) {
	      super(p_i48290_2_);
	   }

	   public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
	      return SHAPE;
	   }

	   @SuppressWarnings("deprecation")
	public BlockState updateShape(BlockState p_196271_1_, Direction p_196271_2_, BlockState p_196271_3_, IWorld p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
	      return !p_196271_1_.canSurvive(p_196271_4_, p_196271_5_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_196271_1_, p_196271_2_, p_196271_3_, p_196271_4_, p_196271_5_, p_196271_6_);
	   }

	/*   public boolean canSurvive(BlockState p_196260_1_, IWorldReader p_196260_2_, BlockPos p_196260_3_) {
	      return !p_196260_2_.isEmptyBlock(p_196260_3_.below());
	   } */
	   
	   //copied from "RedstoneWireBlock" (Redstone Dust)
	   public boolean canSurvive(BlockState p_196260_1_, IWorldReader p_196260_2_, BlockPos p_196260_3_) {
		      BlockPos blockpos = p_196260_3_.below();
		      BlockState blockstate = p_196260_2_.getBlockState(blockpos);
		      return this.canSurviveOn(p_196260_2_, blockpos, blockstate);
		   }

		   private boolean canSurviveOn(IBlockReader p_235552_1_, BlockPos p_235552_2_, BlockState p_235552_3_) {
		      return p_235552_3_.isFaceSturdy(p_235552_1_, p_235552_2_, Direction.UP) || p_235552_3_.is(Blocks.HOPPER);
		   }
		   
		   // Hay Block's stats are 60, 20. See: "FireBlock.class" and then look at "net.minecraftforge.common.extensions.IForgeBlock" for these two methods vvv 
		   
		   @Override
		   public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face)
		    {
		        return 60;
		    }
		   
		   @Override
		   public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face)
		    {
		        return 20;
		    }
		   
		 /*  public boolean canBeReplaced(BlockState p_196253_1_, BlockItemUseContext p_196253_2_) {
			          return true;
			         }*/
		
	}