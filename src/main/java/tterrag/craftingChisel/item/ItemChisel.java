/**
 * 
 */
package tterrag.craftingChisel.item;

import java.util.HashSet;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import tterrag.craftingChisel.CraftingChisel;
import tterrag.craftingChisel.block.OmniCraftingTable;
import tterrag.craftingChisel.tile.TileOmniCraftingTable;

/**
 * @author Garrett Spicer-Davis
 */
public class ItemChisel extends ItemTool
{	
	public ItemChisel()
	{
		super(4, ToolMaterial.IRON, new HashSet());
		setCreativeTab(CreativeTabs.tabTools);
		setUnlocalizedName("craftingChisel");
		setMaxDamage(120);
	}
	
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon("craftingchisel:chisel");
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		Block newBlock = world.getBlock(x, y, z);
		if (newBlock == CraftingChisel.omniCraftingTable)
			((OmniCraftingTable) newBlock).setStats(world, x, y, z);
		if (!world.isRemote)
		{
			if (newBlock != null && newBlock.isOpaqueCube() && world.getTileEntity(x, y, z) == null)
			{
				int meta = world.getBlockMetadata(x, y, z);
				world.setBlock(x, y, z, CraftingChisel.omniCraftingTable);
				world.setBlockMetadataWithNotify(x, y, z, meta, 3);
				TileOmniCraftingTable tile = (TileOmniCraftingTable) world.getTileEntity(x, y, z);
				tile.passedBlock = newBlock;
				tile.blockMeta = meta;
				world.markBlockForUpdate(x, y, z);
				stack.damageItem(1, player);
				return true;
			}
		}
		else
		{
			if (newBlock != null && ((newBlock.isOpaqueCube() && (world.getTileEntity(x, y, z) == null) || world.getTileEntity(x, y, z) instanceof TileOmniCraftingTable)))
			{
				player.playSound(newBlock.stepSound.getBreakSound(), 1.0f, 1.0f);
			}
		}
		return false;
	}
}
