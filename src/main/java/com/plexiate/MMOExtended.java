package com.plexiate;

import com.plexiate.loader.MMOExtendedLoader;
import com.plexiate.stats.Defense;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.stat.type.ItemStat;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

public final class MMOExtended extends JavaPlugin {

    //public static final ItemStat DEFENSE = ;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "MMOExtended by Sek successfully enabled!");
        MMOItems.plugin.getStats().register((ItemStat)new Defense());
    }
    public void onLoad() {
        new MMOExtendedLoader();
    }
}