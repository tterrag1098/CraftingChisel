/**
 * UltimateStorage
 *
 * @author Garrett Spicer-Davis
 */
package tterrag.craftingChisel;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tterrag.craftingChisel.block.OmniCraftingTable;
import tterrag.craftingChisel.item.ItemChisel;
import tterrag.craftingChisel.lib.Reference;
import tterrag.craftingChisel.renderer.OmniCraftingTableRenderer;
import tterrag.craftingChisel.tile.TileOmniCraftingTable;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Chisel to turn any block into a crafting table.
 * 
 * @author Garrett Spicer-Davis
 */
@Mod(modid = Reference.MODID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class CraftingChisel
{
	public static Block omniCraftingTable;
	public static Item chisel;
	public static int renderID;
	
	@Instance
	public static CraftingChisel instance;
		
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event)
	{
		register();
		
		GameRegistry.addRecipe(new ItemStack(chisel), new Object[]{
			" id",
			" si",
			"s  ",
			
			'i', Items.iron_ingot,
			'd', Items.diamond,
			's', Items.stick
		});
		
		GameRegistry.addRecipe(new ItemStack(chisel, 1, 0), new Object[]{
			"di ",
			"is ",
			"  s",
			
			'i', Items.iron_ingot,
			'd', Items.diamond,
			's', Items.stick
		});
	}

	@EventHandler
	public static void init(FMLPreInitializationEvent event)
	{
		renderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new OmniCraftingTableRenderer());
	}

	private static void register()
	{
		omniCraftingTable = new OmniCraftingTable();
		GameRegistry.registerBlock(omniCraftingTable, "omniCraftingTable");
		
		GameRegistry.registerTileEntity(TileOmniCraftingTable.class, "tileOmniCraftingTable");
		
		chisel = new ItemChisel();
		GameRegistry.registerItem(chisel, "craftingChisel");		
	}
}
