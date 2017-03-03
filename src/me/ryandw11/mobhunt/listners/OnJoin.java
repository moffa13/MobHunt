package me.ryandw11.mobhunt.listners;

import me.ryandw11.mobhunt.core.Main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin implements Listener {

	
	private Main plugin;
	public OnJoin(Main plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void joinEvent(PlayerJoinEvent event){
		int i = 1;
		boolean found = true;
		Player p = event.getPlayer();
		find(p, found, i);
		

		
	}//----------------End Of On Join---------------
	
	public void find(Player p, boolean found, int i){
		do{
			if(plugin.scoreboard.getString("ScoreBoard." + i + ".Player").equalsIgnoreCase("none")){
				plugin.scoreboard.set("ScoreBoard." + i + ".Player", p.getName());
				plugin.scoreboard.set("ScoreBoard." + i + ".Kills", plugin.kill.getInt(p.getUniqueId() + "kills"));
				plugin.saveBoard();
				found = false;
				
			}
			else{
				if(plugin.scoreboard.getInt("ScoreBoard." + i + ".Kills") < plugin.kill.getInt(p.getUniqueId() + "kills")){
					plugin.scoreboard.set("ScoreBoard." + i + ".Player", p.getName());
					plugin.scoreboard.set("ScoreBoard." + i + ".Kills", plugin.kill.getInt(p.getUniqueId() + "kills"));
					plugin.saveBoard();
					found = false;
					
				}
			}
			
			if(i <= 5) found = false;
			i++;
		}while(found);
	}
	
	
	
	
}
