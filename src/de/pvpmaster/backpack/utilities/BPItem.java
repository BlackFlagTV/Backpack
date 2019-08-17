package de.pvpmaster.backpack.utilities;

import org.bukkit.inventory.ItemStack;

public class BPItem {

    private ItemStack itemStack;
    private int slot;

    public BPItem(ItemStack itemStack, int slot) {

        this.itemStack = itemStack;
        this.slot = slot;

    }


    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }
}
