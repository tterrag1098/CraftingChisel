/**
 * 
 */
package tterrag.craftingChisel.renderer;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import tterrag.craftingChisel.CraftingChisel;
import tterrag.craftingChisel.block.OmniCraftingTable;
import tterrag.craftingChisel.tile.TileOmniCraftingTable;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

/**
 * @author Garrett
 *
 */
public class OmniCraftingTableRenderer implements ISimpleBlockRenderingHandler
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,
			RenderBlocks renderer)
	{
		renderer.renderBlockAsItem(Blocks.crafting_table, 0 , 1.0F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer)
	{
		TileOmniCraftingTable tile = (TileOmniCraftingTable) world.getTileEntity(x, y, z);
		if (tile == null)
			return true;
		Block renderBlock = tile.passedBlock;
		renderer.renderStandardBlock(renderBlock, x, y, z);
		renderer.renderStandardBlock(block, x, y, z);
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return CraftingChisel.renderID;
	}

}
