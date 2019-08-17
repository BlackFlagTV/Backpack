package de.pvpmaster.backpack;

import de.pvpmaster.backpack.commands.BackpackCommand;
import de.pvpmaster.backpack.listener.DeathListener;
import de.pvpmaster.backpack.utilities.Backpack;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class Main extends JavaPlugin {

    private Map<Player, Inventory> inventoryMap = new HashMap<>();
    private Map<Player, Backpack> backpackMap = new HashMap<>();
    private Map<Player, Integer> taskMap = new HashMap<>();

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
        //getServer().getPluginManager().registerEvents(new InventoryClickListener(this), this);
        this.getCommand("backpack").setExecutor(new BackpackCommand(this));

        loadConfiguration();
        reloadConfig();

        System.out.println("[Backpack] Plugin loaded!");

    }

    @Override
    public void onDisable() {

        System.out.println("[Backpack] Plugin disabled!");

    }

    private void loadConfiguration() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public Map<Player, Inventory> getInventoryMap() {
        return inventoryMap;
    }

    public void addInventoryMap(Player player, Inventory inventory) {
        if (inventoryMap.containsKey(player)) {
            inventoryMap.replace(player, inventory);
        } else {
            inventoryMap.put(player, inventory);
        }
    }

    public Map<Player, Backpack> getBackpackMap() {
        return backpackMap;
    }

    public void addBackpackMap(Player player, Backpack backpack) {
        if (backpackMap.containsKey(player)) {
            backpackMap.replace(player, backpack);
        } else {
            backpackMap.put(player, backpack);
        }
    }

    public void checkInventory(Player player) {
        taskMap.put(player, Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                if (player.getOpenInventory().getTitle() == "container.crafting") updateConfig(player);
            }
        }, 0L, 2L));
    }

    private void updateConfig(Player player) {

        Bukkit.getServer().getScheduler().cancelTask(taskMap.get(player));
        Inventory inventory = inventoryMap.get(player);
        Backpack backpack = backpackMap.get(player);
        for (int i = 0; i < 27; i++) {

            //if (inventory.getItem(i) != null) {
                backpack.seSlot(i, inventory.getItem(i), player);
            //}

        }

    }
}
