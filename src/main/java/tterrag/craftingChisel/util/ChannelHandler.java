package tterrag.craftingChisel.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;

public class ChannelHandler extends FMLIndexedMessageToMessageCodec<ICraftingPacket>
{

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ICraftingPacket msg, ByteBuf target) throws Exception
	{
		msg.encodeInto(target);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf source, ICraftingPacket msg)
	{
		msg.decodeInto(source);
	}
}
