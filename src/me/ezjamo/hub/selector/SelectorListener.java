package me.ezjamo.hub.selector;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.ezjamo.hub.Hub;
import me.ezjamo.hub.Utils;
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

public class SelectorListener extends Utils implements Listener {

	// Server Selector
	public static void selector(Player p) {
		Inventory inv = Bukkit.getServer().createInventory(null, 45, color("&9Server Selector"));
		createItem(inv, 276, 1, 21, "&aFactions",
				"", "&fJoin our &cfactions &fserver!", "", "&b" + Hub.playerFactions + " &fplayers online", "");
		createItem(inv, 373, 1, 23, "&9Events",
				"", "&fJoin our &cevents &fserver!", "", "&b" + Hub.playerEvents + " &fplayers online", "");
		if (p.hasPermission("2fa.use")) {
			createItem(inv, 69, 1, 44, "&5Build &7(Whitelisted)", "");
		}
		p.openInventory(inv);
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (event.getAction() == Action.PHYSICAL || event.getItem() == null || event.getItem().getType() == Material.AIR || (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)))
			return;
		ItemStack compass = new ItemStack(Material.COMPASS, 1);
		ItemMeta compassMeta = compass.getItemMeta();
		compassMeta.setDisplayName(ChatColor.DARK_AQUA + "Server Selector");
		compass.setItemMeta(compassMeta);
		if (event.getItem() != null && event.getItem().isSimilar(compass)) {
			event.setCancelled(true);
			selector(event.getPlayer());
		}
	}

	@EventHandler
	public void onInventoryClickB(InventoryClickEvent event) {
		if (event.getCurrentItem() == null) {
			return;
		}
		Player p = (Player) event.getWhoClicked();
		event.setCancelled(true);
		if (event.getCurrentItem().getType() == Material.POTION) {
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("Connect");
			out.writeUTF("events");
			p.sendPluginMessage(Hub.instance, "BungeeCord", out.toByteArray());
		} else {
			if (event.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
				ByteArrayDataOutput out2 = ByteStreams.newDataOutput();
				out2.writeUTF("Connect");
				out2.writeUTF("factions");
				p.sendPluginMessage(Hub.instance, "BungeeCord", out2.toByteArray());
			} else {
				if (event.getCurrentItem().getType() == Material.LEVER) {
					ByteArrayDataOutput out3 = ByteStreams.newDataOutput();
					out3.writeUTF("Connect");
					out3.writeUTF("build");
					p.sendPluginMessage(Hub.instance, "BungeeCord", out3.toByteArray());
				}
			}
		}
	}
}
