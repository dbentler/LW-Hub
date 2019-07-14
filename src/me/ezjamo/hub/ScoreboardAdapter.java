package me.ezjamo.hub;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import io.github.thatkawaiisam.assemble.AssembleAdapter;

public class ScoreboardAdapter implements AssembleAdapter {
	
	
	@Override
	public String getTitle(Player player) {
		return ChatColor.RED.toString() + "LoneWolves";
	}

	@Override
	
	public List<String> getLines(Player player) {
		final List<String> toReturn = new ArrayList<>();
        toReturn.add(ChatColor.translateAlternateColorCodes('&',"&7&m-------------------"));
        toReturn.add(ChatColor.RED + "Online " + ChatColor.RESET + String.valueOf(Hub.playerCount) + " / 1000");
        toReturn.add("");
        toReturn.add("");
        if (player.hasPermission("rank.default"))  
        toReturn.add(ChatColor.translateAlternateColorCodes('&', "&cRank " + ChatColor.GOLD + "» "+ ChatColor.RESET + "Default"));
        else if (player.hasPermission("rank.vip"))       	
            toReturn.add(ChatColor.translateAlternateColorCodes('&', "&cRank "+ ChatColor.GOLD + "» " + ChatColor.YELLOW + "VIP"));
        else if (player.hasPermission("rank.vip+"))       	
            toReturn.add(ChatColor.translateAlternateColorCodes('&', "&cRank "+ ChatColor.GOLD + "» " + ChatColor.AQUA + "VIP+"));
        else if (player.hasPermission("rank.mvp"))       	
            toReturn.add(ChatColor.translateAlternateColorCodes('&', "&cRank "+ ChatColor.GOLD + "» " + ChatColor.RED + "MVP"));
        else if (player.hasPermission("rank.noble"))       	
            toReturn.add(ChatColor.translateAlternateColorCodes('&', "&cRank "+ ChatColor.GOLD + "» " + ChatColor.DARK_AQUA + "Noble"));
        else if (player.hasPermission("rank.mystic"))       	
            toReturn.add(ChatColor.translateAlternateColorCodes('&', "&cRank "+ ChatColor.GOLD + "» " + ChatColor.DARK_PURPLE + "Mystic"));
        else if (player.hasPermission("rank.kingpin"))       	
            toReturn.add(ChatColor.translateAlternateColorCodes('&', "&cRank "+ ChatColor.GOLD + "» " + ChatColor.DARK_GREEN + "Kingpin"));
        else if (player.hasPermission("rank.helper"))       	
            toReturn.add(ChatColor.translateAlternateColorCodes('&', "&cRank "+ ChatColor.GOLD + "» " + ChatColor.LIGHT_PURPLE + "Helper"));
        else if (player.hasPermission("rank.moderator"))       	
            toReturn.add(ChatColor.translateAlternateColorCodes('&', "&cRank "+ ChatColor.GOLD + "» " + ChatColor.BLUE + "Moderator"));
        else if (player.hasPermission("rank.admin"))      	
        toReturn.add(ChatColor.translateAlternateColorCodes('&', "&cRank "+ ChatColor.GOLD + "» " + ChatColor.DARK_RED + "Admin"));
        else if (player.hasPermission("rank.owner"))  
        toReturn.add(ChatColor.translateAlternateColorCodes('&', "&cRank "+ ChatColor.GOLD + "» " + ChatColor.DARK_RED + "Owner"));
        toReturn.add(ChatColor.translateAlternateColorCodes('&',"&7&m-------------------"));
		return toReturn;
	}
}

