/**
 * UltimateStorage
 *
 * @author Garrett Spicer-Davis
 */
package tterrag.craftingChisel;

import java.util.EnumMap;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import tterrag.craftingChisel.block.OmniCraftingTable;
import tterrag.craftingChisel.item.ItemChisel;
import tterrag.craftingChisel.lib.Reference;
import tterrag.craftingChisel.renderer.OmniCraftingTableRenderer;
import tterrag.craftingChisel.tile.TileOmniCraftingTable;
import tterrag.craftingChisel.util.ChannelHandler;
import tterrag.craftingChisel.util.CraftingChiselGuiHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

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
	
	public static EnumMap<Side, FMLEmbeddedChannel> channels = NetworkRegistry.INSTANCE.newChannel("craftingChisel", new ChannelHandler());
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event)
	{
		register();
	}

	@EventHandler
	public static void init(FMLPreInitializationEvent event)
	{
		renderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new OmniCraftingTableRenderer());
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new CraftingChiselGuiHandler());
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
