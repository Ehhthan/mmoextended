package com.plexiate.triggers;

import net.Indyuce.mmocore.api.event.PlayerLevelUpEvent;
import net.Indyuce.mmocore.api.player.PlayerData;
import net.Indyuce.mmocore.api.player.profess.PlayerClass;
import net.Indyuce.mmocore.api.player.profess.event.EventTriggerHandler;
import net.Indyuce.mmocore.api.quest.trigger.Trigger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

public class LevelUpMultiplyEventTrigger implements EventTriggerHandler {
    public boolean handles(String event) {
        return event.startsWith("level-up-multiple");
    }
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void a(PlayerLevelUpEvent event) {
        PlayerData player = event.getData();
        PlayerClass profess = player.getProfess();
        int level = event.getNewLevel();
        for (String t : profess.getEventTriggers()){
            if (t.startsWith("level-up-multiple")) {
                String[] split = t.split("-");
                int multiple = Integer.parseInt(split[split.length-1]);
                double result = (double)level/multiple;
                if (result % 1 == 0) {
                    if (event.hasProfession()) {
                        String prof = event.getProfession().getId().toLowerCase();
                        processTrigger(player, profess, "level-up-multiple" + prof + "-" + multiple);
                    } else {
                        processTrigger(player, profess, "level-up-multiple-" + multiple);
                    }
                }
            }
        }

    }
    public void processTrigger(PlayerData player, PlayerClass profess, String trigger) {
        if (profess.hasEventTriggers(trigger)) {
            for (Trigger t : profess.getEventTriggers(trigger).getTriggers()) {
                t.apply(player);
            }
        }
    }
}
