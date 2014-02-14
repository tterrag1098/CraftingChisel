/**
 * 
 */
package tterrag.craftingChisel.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tterrag.craftingChisel.CraftingChisel;
import tterrag.craftingChisel.block.OmniCraftingTable;
import tterrag.craftingChisel.tile.TileOmniCraftingTable;

/**
 * @author Garrett Spicer-Davis
 */
public class ItemChisel extends Item
{	
	public ItemChisel()
	{
		super();
		setCreativeTab(((OmniCraftingTable)CraftingChisel.omniCraftingTable).getTab());
		setUnlocalizedName("craftingChisel");
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		System.out.println((world.getTileEntity(x, y, z) instanceof TileOmniCraftingTable ? ((TileOmniCraftingTable)world.getTileEntity(x, y, z)).passedBlock.getUnlocalizedName() : "nope") + world.isRemote);
		Block newBlock = world.getBlock(x, y, z);
		if (newBlock != null && newBlock.isOpaqueCube())
		{
			if (world.getTileEntity(x, y, z) == null)
			{
				int meta = world.getBlockMetadata(x, y, z);
				world.setBlock(x, y, z, CraftingChisel.omniCraftingTable);
				TileOmniCraftingTable tile = (TileOmniCraftingTable) world.getTileEntity(x, y, z);
				tile.passedBlock = newBlock;
				tile.blockMeta = meta;
				world.markBlockForUpdate(x, y, z);
				return !world.isRemote;
			}	
			else 
			{
				System.out.println(world.getTileEntity(x, y, z) instanceof TileOmniCraftingTable ? ((TileOmniCraftingTable)world.getTileEntity(x, y, z)).passedBlock.getUnlocalizedName() : "nope");
			}
		}
		return false;
	}
}
