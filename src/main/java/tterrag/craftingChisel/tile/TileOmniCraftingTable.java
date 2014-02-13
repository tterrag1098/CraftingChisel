/**
 * TileStorageBlock
 *
 * @author Garrett Spicer-Davis
 */
package tterrag.craftingChisel.tile;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Garrett Spicer-Davis
 * 
 */
public class TileOmniCraftingTable extends TileEntity
{
	public Block passedBlock;
	public int blockSide, blockMeta;
	
	public TileOmniCraftingTable()
	{
		passedBlock = Blocks.planks;
		blockSide = 0;
		blockMeta = 0;
	}

}
