package bee.beeshroom.helpingherds.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.potion.Effect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class GolemFlowerBlock extends FlowerBlock {

	public GolemFlowerBlock(Effect p_i49984_1_, int p_i49984_2_, Properties p_i49984_3_) {
		super(p_i49984_1_, p_i49984_2_, p_i49984_3_);
		// TODO Auto-generated constructor stub
	}
		
	  protected boolean mayPlaceOn(BlockState p_200014_1_, IBlockReader p_200014_2_, BlockPos p_200014_3_) {
	      return p_200014_1_.is(Blocks.SNOW_BLOCK);
	   }

}
