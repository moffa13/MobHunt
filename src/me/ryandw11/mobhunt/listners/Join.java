package me.ryandw11.mobhunt.listners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.ryandw11.mobhunt.MobHunt;

public class Join implements Listener {
	
	private MobHunt plugin;
	public Join(MobHunt plugin){
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
