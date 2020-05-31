package com.plexiate;

import com.plexiate.loader.MMOExtendedLoader;
import com.plexiate.stats.Defense;
import com.plexiate.triggers.LevelEveryEventTrigger;
import net.Indyuce.mmocore.MMOCore;
import net.Indyuce.mmoitems.MMOItems;
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
        this.saveDefaultConfig();
        new MMOExtendedLoader();
        if (Bukkit.getPluginManager().getPlugin("MMOItems") != null)
            MMOItems.plugin.getStats().register(new Defense());
        if (Bukkit.getPluginManager().getPlugin("MMOCore") != null)
            MMOCore.plugin.classManager.registerEvent(new LevelEveryEventTrigger());
    }
}

//