package de.pvpmaster.backpack.listener;

import de.pvpmaster.backpack.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryCloseListener implements Listener {

    private Main plugin;

    public InventoryCloseListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEvent(InventoryCloseEvent event) {

        Player player = (Player) event.getPlayer();

        System.out.println("listener");
        if (!plugin.getInventoryMap().containsKey(player)) return;
        System.out.println("ist vorhanden");
        System.out.println(plugin.getInventoryMap().get(player));
        if (plugin.getInventoryMap().get(player) == event.getInventory()) {
            System.out.println("ist richtiges Inventar");

            Inventory inventory = event.getInventory();

            for (int i = 0; i < 27; i++) {

                ItemStack itemStack = inventory.getItem(i);
                plugin.getConfig().set(event.getPlayer().getUniqueId().toString() + "." + i, itemStack);
                plugin.saveConfig();

            }

        }

    }

}