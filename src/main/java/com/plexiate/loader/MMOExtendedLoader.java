package com.plexiate.loader;

import com.plexiate.triggers.StackableItemTrigger;
import net.Indyuce.mmocore.MMOCore;
import net.Indyuce.mmocore.api.load.MMOLoader;
import net.Indyuce.mmocore.api.quest.trigger.Trigger;
import net.mmogroup.mmolib.api.MMOLineConfig;
import org.bukkit.Bukkit;

public class MMOExtendedLoader extends MMOLoader {
    public MMOExtendedLoader() {
        MMOCore.plugin.loadManager.registerLoader(this);
    }
    @Override
    public Trigger loadTrigger(MMOLineConfig config) {
        if (Bukkit.getPluginManager().getPlugin("MMOCore") != null && Bukkit.getPluginManager().getPlugin("MMOItems") != null && Bukkit.getPluginManager().getPlugin("StackableItems") != null)
            if (config.getKey().equals("stackableitem"))
                return new StackableItemTrigger(config);
        return null;
    }

}