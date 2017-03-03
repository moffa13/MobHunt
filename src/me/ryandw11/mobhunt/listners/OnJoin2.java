package me.ryandw11.mobhunt.listners;

import me.ryandw11.mobhunt.core.Main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin2 implements Listener {
	
	private Main plugin;
	public OnJoin2(Main plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onJoins(PlayerJoinEvent event){
		Player p = event.getPlayer();
		if(plugin.kill.contains(p.getUniqueId().toString() + "kills")){
			plugin.logger.info("[MobHunt] " + p.getName() + " has joined with " + plugin.kill.getInt(p.getUniqueId().toString() + "kills") + " kills!");
		}
		else{
			plugin.kill.set(p.getUniqueId().toString() + "kills", 0);
			plugin.saveFile();
		}
	}
}
