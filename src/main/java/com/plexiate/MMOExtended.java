package com.plexiate;

import com.plexiate.commands.MainCommand;
import com.plexiate.files.CustomConfig;
import com.plexiate.listener.DefenseListener;
import com.plexiate.loader.MMOExtendedLoader;
import com.plexiate.stats.Defense;
import com.plexiate.triggers.LevelUpMultiplyEventTrigger;
import com.plexiate.util.MMOExtendedExpansion;
import net.Indyuce.mmocore.MMOCore;
import net.Indyuce.mmoitems.MMOItems;
import org.bstats.bukkit.Metrics;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;


public final class MMOExtended extends JavaPlugin {
    public JavaPlugin plugin = this;

    @Override
    public void onEnable() {
        CustomConfig.setup();
        CustomConfig.get().options().copyDefaults(true);
        System.out.println(ChatColor.GREEN + "MMOExtended by Sek successfully enabled!");
        this.getCommand("mmoextended").setExecutor(new MainCommand());
        new Metrics(plugin, 7708);
        if (Bukkit.getPluginManager().getPlugin("MMOItems") != null)
            Bukkit.getServer().getPluginManager().registerEvents(new DefenseListener(), plugin);
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new MMOExtendedExpansion(plugin).register();
        }
    }
    @Override
    public void onLoad() {
        new MMOExtendedLoader();
        if (Bukkit.getPluginManager().getPlugin("MMOItems") != null)
            MMOItems.plugin.getStats().register(new Defense());
        if (Bukkit.getPluginManager().getPlugin("MMOCore") != null)
            MMOCore.plugin.classManager.registerEvent(new LevelUpMultiplyEventTrigger());
    }
}