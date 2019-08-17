package de.pvpmaster.backpack.utilities;

import de.pvpmaster.backpack.Main;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class Backpack {

    private final Main plugin;

    public Backpack(Main plugin) {
        this.plugin = plugin;
    }

    private Map<Integer, ItemStack> items = new HashMap<>();
    private Player player;

    public Map<Integer, ItemStack> getItems() {
        return items;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void seSlot(int slot, ItemStack itemStack, Player p) {
        if (items.containsKey(slot)) {
            items.replace(slot, itemStack);
        } else {
            items.put(slot, itemStack);
        }

        plugin.getConfig().set(p.getUniqueId().toString() + "." + slot, itemStack);
        plugin.saveConfig();
    }
}
