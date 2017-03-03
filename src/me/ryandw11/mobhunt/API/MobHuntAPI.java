package me.ryandw11.mobhunt.API;


import org.bukkit.entity.Player;

import me.ryandw11.mobhunt.core.Main;
/**
 * 
 * @author Ryandw11
 * @version 1.5.1
 *
 */
public class MobHuntAPI {
	private Main plugin;
	public MobHuntAPI(Main plugin){
		this.plugin = plugin;
	}
	/**
	 * Grab the main plugin.
	 * @return plugin
	 */
	public static Main getPlugin(){
		return Main.plugin;
	}
	/**
	 * Get the mob hunt verson 
	 * Example: 1.5.2
	 * @return MobHunt Version
	 */
	public String getVersion(){
		return plugin.getDescription().getVersion();
	}
	/**
	 * Grabs a player from the scoreboard.
	 * @param number
	 * @return # kills.
	 */
	public String getScoreBoardPlayer(int number){
		return plugin.scoreboard.getString("ScoreBoard." + number + ".Player");
	}
	/**
	 * Grab the kills from the scoreboard.
	 * @param number
	 * @return number of kills
	 */
	public int getScoreBoardKills(int number){
		return plugin.scoreboard.getInt("ScoreBoard." + number + ".Kills");
	}
	/**
	 * Get a players kills.
	 * @param p
	 * @return kills
	 */
	public int getPlayerKills(Player p){
		return plugin.kill.getInt(p.getUniqueId().toString() + "kills");
	}
}
