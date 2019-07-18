package me.ezjamo.hub.selector;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import me.ezjamo.hub.Hub;



public class SelectorListener implements Listener {
	
	
	
	// Server Selector
	
	public static void selector(Player p) {
	
	 Inventory selector = Bukkit.getServer().createInventory(null, 45, ChatColor.BLUE + "Server Selector");
	 ItemStack factions = new ItemStack(Material.DIAMOND_SWORD);
	 ItemStack events = new ItemStack(Material.POTION);
	 ItemStack build = new ItemStack(Material.LEVER);
	 ItemMeta factionsmeta = factions.getItemMeta();
	 ItemMeta eventsmeta = events.getItemMeta();
	 ItemMeta buildMeta = build.getItemMeta();
	 factionsmeta.setDisplayName(ChatColor.GREEN + "Factions");
	 ArrayList<String> factionslore = new ArrayList<String>();
	 factionslore.add(ChatColor.RED + "");
	 factionslore.add(ChatColor.RED + "Join our factions server!");
	 factionslore.add(ChatColor.RED + "");
	 factionslore.add(ChatColor.AQUA + String.valueOf(Hub.playerFactions) + ChatColor.RESET + " players online");
	 factionslore.add(ChatColor.RED + "");
	 factionsmeta.setLore(factionslore);
	 eventsmeta.setDisplayName(ChatColor.BLUE + "Events");
	 ArrayList<String> eventslore = new ArrayList<String>();
	 eventslore.add(ChatColor.RED + "");
	 eventslore.add(ChatColor.RED + "Join our events server!");
	 eventslore.add(ChatColor.RED + "");
	 eventslore.add(ChatColor.AQUA + String.valueOf(Hub.playerEvents) + ChatColor.RESET + " players online");
	 eventslore.add(ChatColor.RED + "");
	 eventsmeta.setLore(eventslore);
	 buildMeta.setDisplayName(ChatColor.DARK_PURPLE + "Build "+ ChatColor.GRAY + "(Whitelisted) ");
	 factions.setItemMeta(factionsmeta);
	 events.setItemMeta(eventsmeta);
	 build.setItemMeta(buildMeta);
	 selector.setItem(21, factions);
	 selector.setItem(23, events);
	 if (p.hasPermission("2fa.use")) {
		 selector.setItem(44, build);
	 }

	 p.getPlayer().openInventory(selector);
	 
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		
	    if (event.getAction() == Action.PHYSICAL || event.getItem() == null || event.getItem().getType() == Material.AIR|| (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK))) 
	    	
	    	return;

	     ItemStack compass = new ItemStack(Material.COMPASS, 1);
		 ItemMeta compassMeta = compass.getItemMeta();
		 compassMeta.setDisplayName(ChatColor.DARK_AQUA + "Server Selector");
		 compass.setItemMeta(compassMeta);
		if (event.getItem() != null 
				&& event.getItem().isSimilar(compass)) {
				event.setCancelled(true);
				selector(event.getPlayer()); 
				}
			}

	
@EventHandler
public void onInventoryClickB(InventoryClickEvent event) {
	if (!event.getInventory().getName().equalsIgnoreCase(ChatColor.BLUE + "Server Selector")) {
		
		return;
	}
	
	Player p = (Player) event.getWhoClicked();
	event.setCancelled(true);
	switch(event.getCurrentItem().getType())
        {
        case POTION:
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF("events");
            p.sendPluginMessage(Hub.instance, "BungeeCord", out.toByteArray());
            break;
        default: 
    		switch(event.getCurrentItem().getType())
    		{
    		case DIAMOND_SWORD:
                ByteArrayDataOutput out2 = ByteStreams.newDataOutput();
                out2.writeUTF("Connect");
                out2.writeUTF("factions");
                p.sendPluginMessage(Hub.instance, "BungeeCord", out2.toByteArray());
    			break;
    		default:
    			switch(event.getCurrentItem().getType())
    	            {
    	            case LEVER:
    	                ByteArrayDataOutput out3 = ByteStreams.newDataOutput();
    	                out3.writeUTF("Connect");
    	                out3.writeUTF("build");
    	                p.sendPluginMessage(Hub.instance, "BungeeCord", out3.toByteArray());
    	    			break;
    	            default: 
    	                break; 
    	         }
    		   }
    		}
		}
	}