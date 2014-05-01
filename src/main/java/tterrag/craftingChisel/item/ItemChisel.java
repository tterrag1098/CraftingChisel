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
        super(4, ToolMaterial.IRON, new HashSet<Object>());
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
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        Block newBlock = world.getBlock(x, y, z);
        
        if (newBlock == CraftingChisel.omniCraftingTable)
            ((OmniCraftingTable) newBlock).setStats(world, x, y, z);

        if (newBlock != null && newBlock.isOpaqueCube() && world.getTileEntity(x, y, z) == null)
        {
            if (world.isRemote)
                player.playSound(newBlock.stepSound.getBreakSound(), 1.0f, 1.0f);

            int meta = world.getBlockMetadata(x, y, z);
            world.setBlock(x, y, z, CraftingChisel.omniCraftingTable, meta, 0);
            TileOmniCraftingTable tile = (TileOmniCraftingTable) world.getTileEntity(x, y, z);
            tile.passedBlock = newBlock;
            tile.blockMeta = meta;
            world.markBlockForUpdate(x, y, z);
            stack.damageItem(1, player);
            return true;
        }

        return false;
    }
}
