package tterrag.craftingChisel.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.world.World;


public class BetterContainerWorkbench extends ContainerWorkbench
{
	public int posX, posY, posZ;
	public BetterContainerWorkbench(InventoryPlayer par1InventoryPlayer, World par2World, int par3, int par4, int par5)
	{
		super(par1InventoryPlayer, par2World, par3, par4, par5);
		this.posX = par3;
		this.posY = par4;
		this.posZ = par5;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	{
		 return par1EntityPlayer.getDistanceSq((double)this.posX + 0.5D, (double)this.posY + 0.5D, (double)this.posZ + 0.5D) <= 64.0D;
	}
}
