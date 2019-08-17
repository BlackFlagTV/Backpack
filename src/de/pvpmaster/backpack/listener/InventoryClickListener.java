package de.pvpmaster.backpack.listener;

import de.pvpmaster.backpack.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Bukkit.getServer;

public class InventoryClickListener implements Listener {

    private Main plugin;

    public InventoryClickListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEvent(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        Inventory inventory = event.getInventory();
        player.sendMessage("b");

        System.out.println(event.getInventory().getType());
        if (event.getRawSlot() < 27 && event.getRawSlot() >= 0) {
            if (plugin.getInventoryMap().containsKey(player)) {
                if (inventory.getName().equals(plugin.getInventoryMap().get(player).getName())) {
                    player.sendMessage("a");

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        public void run() {
                            System.out.println("vallah " + event.getInventory().getItem(event.getRawSlot()));
                            plugin.getBackpackMap().get(player).seSlot(event.getSlot(), event.getInventory().getItem(event.getRawSlot()), player);
                        }
                    }, 5);



                }
            }
        }

    }

}