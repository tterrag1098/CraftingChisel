package tterrag.craftingChisel.util;

import tterrag.craftingChisel.tile.TileOmniCraftingTable;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class PacketCraftingTable extends AbstractPacket
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
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		buffer.writeInt(id);
		buffer.writeInt(meta);
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		id = buffer.readInt();
		meta = buffer.readInt();
		x = buffer.readInt();
		y = buffer.readInt();
		z = buffer.readInt();
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
