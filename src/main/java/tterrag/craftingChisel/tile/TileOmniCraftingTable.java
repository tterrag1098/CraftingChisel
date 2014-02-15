/**
 * TileStorageBlock
 *
 * @author Garrett Spicer-Davis
 */
package tterrag.craftingChisel.tile;

import tterrag.craftingChisel.CraftingChisel;
import tterrag.craftingChisel.util.PacketCraftingTable;
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
	public int blockSide, blockMeta, marker;
	
	public TileOmniCraftingTable()
	{
		passedBlock = Blocks.planks;
		blockSide = 0;
		blockMeta = 0;
		marker = 0;
		sendPacket();
	}
	
	private void sendPacket()
	{
		CraftingChisel.pipeline.sendToAll(new PacketCraftingTable(Block.getIdFromBlock(passedBlock), blockMeta, this.xCoord, this.yCoord, this.zCoord));
	}
}
