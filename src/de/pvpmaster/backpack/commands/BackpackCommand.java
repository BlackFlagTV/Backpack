package de.pvpmaster.backpack.commands;

import de.pvpmaster.backpack.Main;
import de.pvpmaster.backpack.utilities.Backpack;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class BackpackCommand implements CommandExecutor {

    private final Main plugin;

    public BackpackCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {

            Inventory myInventory = Bukkit.createInventory(null, 27, "Backpack from " + ((Player) sender).getDisplayName());
            plugin.addInventoryMap((Player) sender, myInventory);

            Backpack backpack = getFromConfig((Player) sender);

            if (backpack == null) {
                plugin.getConfig().set(((Player) sender).getUniqueId().toString(), new Backpack(plugin));
                plugin.saveConfig();
                backpack = getFromConfig((Player) sender);
            }

            backpack.setPlayer((Player) sender);
            plugin.addBackpackMap((Player) sender, backpack);

            Map<Integer, ItemStack> itemStackMap = backpack.getItems();

            for (Map.Entry<Integer, ItemStack> bpItem : itemStackMap.entrySet()) {

                myInventory.setItem(bpItem.getKey(), bpItem.getValue());

            }

            ((Player) sender).openInventory(myInventory);
            plugin.checkInventory((Player) sender);


        }


        return true;
    }

    private Backpack getFromConfig(Player player) {
        Backpack backpack = new Backpack(plugin);
        for (int i = 0; i < 27; i++) {
            if (plugin.getConfig().get(player.getUniqueId().toString() + "." +  i) != null) {
                backpack.seSlot(i, (ItemStack) plugin.getConfig().get(player.getUniqueId().toString() + "." +  i), player);
            }
        }
        return backpack;
    }

}