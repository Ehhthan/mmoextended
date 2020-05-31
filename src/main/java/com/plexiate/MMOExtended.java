package com.plexiate;

import com.plexiate.listener.DefenseListener;
import com.plexiate.loader.MMOExtendedLoader;
import com.plexiate.stats.Defense;
import com.plexiate.triggers.LevelUpMultiplyEventTrigger;
import net.Indyuce.mmocore.MMOCore;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;


public final class MMOExtended extends JavaPlugin {

    public static MMOExtended plugin;

    public static FileConfiguration config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "MMOExtended by Sek successfully enabled!");
        if (Bukkit.getPluginManager().getPlugin("MMOItems") != null)
            Bukkit.getServer().getPluginManager().registerEvents((Listener) new DefenseListener(), (Plugin) this);
    }
    public void onLoad() {
        this.saveDefaultConfig();
        new MMOExtendedLoader();
        if (Bukkit.getPluginManager().getPlugin("MMOItems") != null)
            MMOItems.plugin.getStats().register(new Defense());
        if (Bukkit.getPluginManager().getPlugin("MMOCore") != null)
            MMOCore.plugin.classManager.registerEvent(new LevelUpMultiplyEventTrigger());
        config = this.getConfig();
    }
}

//