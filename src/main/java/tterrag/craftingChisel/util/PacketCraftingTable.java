package tterrag.craftingChisel.util;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import tterrag.craftingChisel.tile.TileOmniCraftingTable;

public class PacketCraftingTable implements IStoragePacket
{
	private int id, meta, x, y, z;
	
	public PacketCraftingTable()
	{
		this.id = 0;
		this.meta = 0;
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
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
		if (tile != null)
		{
			tile.passedBlock = Block.getBlockById(id);
			tile.blockMeta = meta;
			Minecraft.getMinecraft().theWorld.markBlockForUpdate(x, y, z);
		}
	}
}
