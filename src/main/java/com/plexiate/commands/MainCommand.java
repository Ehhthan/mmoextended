package com.plexiate.commands;

import com.plexiate.files.CustomConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;


public class MainCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (command.getName().equalsIgnoreCase("mmoextended"))
        {
            if(args.length == 1 && args[0].equalsIgnoreCase("reload"))
            {
                if (sender.hasPermission("mmoextended.admin"))
                {
                    CustomConfig.reload();
                    sender.sendMessage(ChatColor.GREEN + "Reloading plugin.");
                    return true;
                }
                else
                {
                    sender.sendMessage(ChatColor.RED + "No permission.");
                    return true;
                }
            }
            else
            {
                return true;
            }

        }
        return true;

    }
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("mmoextended")) {
            if (!sender.hasPermission("mmoextended.admin")) {
                return null;
            }
            List<String> l = new ArrayList<>();
            if (args.length == 1) {
                l.add("reload");
                return l;
            }
        }
        return null;
    }
}

