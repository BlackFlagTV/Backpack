package de.pvpmaster.backpack.listener;

import de.pvpmaster.backpack.Main;
import de.pvpmaster.backpack.utilities.Backpack;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class DeathListener implements Listener {

    private Main plugin;

    public DeathListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEvent(PlayerDeathEvent event) {

        Location location = event.getEntity().getLocation();
        Player player = event.getEntity();

        Backpack backpack = new Backpack(plugin);

        if (plugin.getBackpackMap().containsKey(player)) {
            backpack = plugin.getBackpackMap().get(player);
        } else {
            for (int i = 0; i < 27; i++) {
                if (plugin.getConfig().get(player.getUniqueId().toString() + "." +  i) != null) {
                    backpack.seSlot(i, (ItemStack) plugin.getConfig().get(player.getUniqueId().toString() + "." +  i), player);
                }
            }
        }

        for (int i = 0; i < 27; i++) {

            if (backpack.getItems().get(i) != null) location.getWorld().dropItem(location, backpack.getItems().get(i));

            backpack.seSlot(i, null, player);

        }

    }

}