package com.plexiate;

import com.plexiate.loader.MMOExtendedLoader;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

public final class MMOExtended extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "MMOExtended by Sek successfully enabled!");

    }

    public void onLoad() {
        new MMOExtendedLoader();
    }
}