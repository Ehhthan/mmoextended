package com.plexiate.util;

import com.plexiate.listener.DefenseListener;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.Indyuce.mmocore.api.player.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MMOExtendedExpansion extends PlaceholderExpansion {
    private JavaPlugin plugin;
    public MMOExtendedExpansion(JavaPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean persist() {
        return true;
    }
    @Override
    public boolean canRegister(){
        return true;
    }
    @Override
    public String getAuthor(){
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public String getIdentifier(){
        return "mmoextended";
    }

    @Override
    public String getVersion(){
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier){

        if(player == null){
            return "";
        }
        if(identifier.equals("stat_defense")){
            PlayerData data = PlayerData.get(player);
            double defense = data.getStats().getMap().getStat("DEFENSE");
            return String.valueOf(defense);
        }
        if(identifier.equals("stat_defense_percent")){
            PlayerData data = PlayerData.get(player);
            double defense = (DefenseListener.calculateDefenseCoefficient(data)) * 100;
            return defense + "%";
        }
        return null;
    }
}

