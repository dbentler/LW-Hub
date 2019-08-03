package me.ezjamo.hub.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.md_5.bungee.api.ChatColor;

public class HubListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = (Player) e.getPlayer();
		e.getPlayer().getInventory().clear();
		ItemStack compass = new ItemStack(Material.COMPASS, 1);
		ItemMeta compassMeta = compass.getItemMeta();
		compassMeta.setDisplayName(ChatColor.DARK_AQUA + "Server Selector");
		compass.setItemMeta(compassMeta);
		e.getPlayer().getInventory().setItem(4, compass);
		e.getPlayer().teleport(new Location(Bukkit.getWorld("world"), 0.5, 101.0, 0.5));
		e.setJoinMessage(null);
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9000000, 5));
		p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 9000000, 3));
	}
	
	@EventHandler 
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		if (!e.getPlayer().hasPermission("mcauthenticator.use")) {
		e.setCancelled(true);
		}	
		else
			e.setCancelled(false);
		e.setFormat(ChatColor.RED + "[Staff] " + e.getPlayer().getName() + ": " + ChatColor.RESET + e.getMessage());
	}
	
	@EventHandler
	public void onChunkunloadEvent(ChunkUnloadEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onWeather(WeatherChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onEntityDamage(EntityDamageByBlockEvent e) {
		e.setCancelled(true); 
		if (e.getCause().equals(DamageCause.VOID)) {
			e.getEntity().teleport(new Location(Bukkit.getWorld("world"), 0, 101, 0.5));
		}
	}
	@EventHandler
	public void onDrowning(EntityDamageEvent e) {
		e.setCancelled(true); 
		if (e.getCause().equals(DamageCause.DROWNING)) {
			e.getEntity().teleport(new Location(Bukkit.getWorld("world"), 0.5, 101, 0.5));
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		e.getPlayer().getInventory().clear();
		e.setQuitMessage(null);
	}
}
