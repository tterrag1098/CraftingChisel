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
		OmniCraftingTable overlay = (OmniCraftingTable) CraftingChisel.omniCraftingTable;
		if (tile == null)
			return true;
		Block renderBlock = tile.passedBlock;
		IIcon icon1 = renderer.getBlockIconFromSideAndMetadata(renderBlock, tile.blockSide, tile.blockMeta);
		if (renderBlock.isOpaqueCube())
		{
			renderer.setOverrideBlockTexture(icon1);
		}
		renderer.renderStandardBlock(block, x, y, z);
		renderer.clearOverrideBlockTexture();
		/*
		IIcon i = overlay.getIcon(1);
		Tessellator tessellator = Tessellator.instance;
		tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 0.5F);
		renderer.renderFaceXNeg(overlay, x, y, z, i);
		renderer.renderFaceXPos(overlay, x, y, z, i);
		renderer.renderFaceZNeg(overlay, x, y, z, i);
		renderer.renderFaceZPos(overlay, x, y, z, i);
		renderer.renderFaceYNeg(overlay, x, y, z, overlay.getIcon(0));
		renderer.renderFaceYPos(overlay, x, y, z, overlay.getIcon(2));
		*/
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
