/**
 * TileStorageBlock
 *
 * @author Garrett Spicer-Davis
 */
package tterrag.craftingChisel.tile;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Garrett Spicer-Davis
 * 
 */
public class TileOmniCraftingTable extends TileEntity
{
	public Block passedBlock = Blocks.air;
	public int blockMeta, marker;
	
	public TileOmniCraftingTable()
	{
		blockMeta = 0;
		marker = 0;
	}
	
	@Override
	public boolean canUpdate()
	{
	    return false;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setInteger("passedID", Block.getIdFromBlock(passedBlock));
		tag.setInteger("meta", blockMeta);
		tag.setInteger("marker", marker);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		passedBlock = Block.getBlockById(tag.getInteger("passedID"));
 		blockMeta = tag.getInteger("meta");
		marker = tag.getInteger("marker");
	}
	
	@Override
	public Packet getDescriptionPacket(){
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, tag);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		this.readFromNBT(pkt.func_148857_g());
	}
}
