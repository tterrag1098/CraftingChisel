package tterrag.craftingChisel.util;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import tterrag.craftingChisel.tile.TileOmniCraftingTable;

public class PacketCraftingTable implements ICraftingPacket
{
	private int id, meta, x, y, z;
	
	public PacketCraftingTable(int id, int meta, int x, int y, int z)
	{
		this.id = id;
		this.meta = meta;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void encodeInto(ByteBuf buffer)
	{
		buffer.writeInt(id);
		buffer.writeInt(meta);
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
	}

	@Override
	public void decodeInto(ByteBuf buffer)
	{
		id = buffer.readInt();
		meta = buffer.readInt();
		x = buffer.readInt();
		y = buffer.readInt();
		z = buffer.readInt();
		
		TileOmniCraftingTable tile = (TileOmniCraftingTable) Minecraft.getMinecraft().theWorld.getTileEntity(x, y, z);
		tile.passedBlock = Block.getBlockById(id);
		tile.blockMeta = meta;
	}

	@Override
	public void handleClientSide(EntityPlayer player)
	{
		TileOmniCraftingTable tile = (TileOmniCraftingTable) Minecraft.getMinecraft().theWorld.getTileEntity(x, y, z);
		tile.passedBlock = Block.getBlockById(id);
		tile.blockMeta = meta;
	}

	@Override
	public void handleServerSide(EntityPlayer player)
	{}
}
