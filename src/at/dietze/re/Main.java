package at.dietze.re;

import at.dietze.re.biome.BiomeExternal;
import at.dietze.re.food.ItemConsume;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getServer().getPluginManager().registerEvents(new ItemConsume(), this);
        getServer().getPluginManager().registerEvents(new BiomeExternal(), this);
        Bukkit.getConsoleSender().sendMessage("RealisticEnvironment was enabled.");
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}
