package com.EnergyReactors.core.container;

import com.EnergyReactors.core.blocks.TileEntityGeneratorRedstone;
import com.EnergyReactors.core.container.slot.SlotGeneratorRedstone;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerGeneratorRedstone extends Container {

	private TileEntityGeneratorRedstone tileentity;
	
	public ContainerGeneratorRedstone(IInventory playerInv, TileEntityGeneratorRedstone tileentity) {
		this.tileentity = tileentity;
		
		this.addSlotToContainer(new SlotGeneratorRedstone(tileentity, 0, 8, 17));

		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 9; ++x) {
				this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
			}
		}

		for (int x = 0; x < 9; ++x) {
			this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileentity.isUseableByPlayer(player);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
		ItemStack previous = null;
		Slot slot = (Slot) this.inventorySlots.get(fromSlot);

		if (slot != null && slot.getHasStack()) {
			ItemStack current = slot.getStack();
		        previous = current.copy();

		        if (fromSlot < 1) {
		        	if (!this.mergeItemStack(current, 1, this.inventorySlots.size(), true)){
	                    		return null;
	                	}
		        } else if (SlotGeneratorRedstone.isItemValidForSlot(current)) {
		            if (!this.mergeItemStack(current, 0, 1, false))
		                return null;
		        } else return null;

		        if (current.stackSize == 0)
		            slot.putStack((ItemStack) null);
		        else
		            slot.onSlotChanged();
		}
		return previous;
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		if(this.tileentity.getStackInSlot(0) != this.inventorySlots.get(0)){
			
		}
	}

}
