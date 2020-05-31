package com.plexiate.listener;

import net.mmogroup.mmolib.MMOLib;
import net.mmogroup.mmolib.api.AttackResult;
import net.mmogroup.mmolib.api.player.MMOData;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class Defense implements Listener {
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void a(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof org.bukkit.entity.Player) || event.getEntity().hasMetadata("NPC"))
            return;
        MMOData data = MMOData.get((OfflinePlayer)event.getEntity());
        AttackResult res = MMOLib.plugin.getDamage().findInfo(event.getEntity());
        event.setDamage(event.getDamage() * 1);
    }
}
