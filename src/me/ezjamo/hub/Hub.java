package me.ezjamo.hub;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import me.ezjamo.hub.listeners.ToggleVisibilityListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import io.github.thatkawaiisam.assemble.Assemble;
import io.github.thatkawaiisam.assemble.AssembleStyle;
import me.ezjamo.hub.listeners.DoubleJumpListener;
import me.ezjamo.hub.listeners.HubListener;
import me.ezjamo.hub.listeners.BlockedCommandsListener;
import me.ezjamo.hub.selector.SelectorListener;

public class Hub extends JavaPlugin implements PluginMessageListener {
	public static Hub instance;
	public static int playerCount = 0;
	public static int playerFactions = 0;
	public static int playerEvents = 0;

	public void onEnable() {
		instance = this;
		start();
	}

	public void onDisable() {
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
		}
	}

	public void start() {
		Assemble assemble = new Assemble(this, new ScoreboardAdapter());
		assemble.setTicks(19); // <-- don't set this above 20 -jamo
		assemble.setAssembleStyle(AssembleStyle.LONEWOLVES);         
        Bukkit.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		getServer().getPluginManager().registerEvents(new HubListener(), this);
		getServer().getPluginManager().registerEvents(new DoubleJumpListener(), this);
		getServer().getPluginManager().registerEvents(new SelectorListener(), this);
		getServer().getPluginManager().registerEvents(new BlockedCommandsListener(), this);
		getServer().getPluginManager().registerEvents(new ToggleVisibilityListener(), this);
		Bukkit.getServer().getWorld("world").setPVP(false);
		getPlayersCount();
		}

	public void getPlayersCount() {
		new BukkitRunnable() {
			@Override
			public void run() {
				getCount("ALL");
				getCount("factions");
				getCount("events");
			}
		}.runTaskTimerAsynchronously(this, 22L , 22L);
	}

    public void onPluginMessageReceived(String channel, Player player, byte[] bytemessage) {
        if (!channel.equals("BungeeCord"))
            return;

        try {
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytemessage));
            String subchannel = in.readUTF();
            String server = in.readUTF();
            
            if (subchannel.equals("PlayerCount")) {
                int playerCountServer = in.readInt();

                if (server.equalsIgnoreCase("ALL")) {
                    playerCount = playerCountServer;
                    }
                 if (server.equalsIgnoreCase("factions")) {
                	 playerFactions = playerCountServer;
                }
                 if (server.equalsIgnoreCase("events")) {
                	 playerEvents = playerCountServer;
                 }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getCount(String server) {
        try {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();

            out.writeUTF("PlayerCount");
            out.writeUTF(server);
            Bukkit.getServer().sendPluginMessage(this, "BungeeCord", out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
