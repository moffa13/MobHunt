package me.ryandw11.mobhunt.API;

import me.ryandw11.mobhunt.core.Main;
/**
 * MobRewards
 * @author Ryandw11s
 *
 */
public class MobRewards {
	private Main plugin;
	public MobRewards(){
		this.plugin = Main.plugin;
	}
	/**
	 * Grab reward message.
	 * @param number Reward number
	 * @return Message
	 * @throws NullPointerException
	 */
	public String getRewardMessage(int number){
		if(!plugin.getConfig().contains("Rewards." + number)){
			return null;
		}
		return plugin.getConfig().getString("Rewards." + number + ".Message");
	}
	/**
	 * Grab reward kills. Note: returns -1 if that reward # does not exsist
	 * @param number Reward number
	 * @return Kills
	 */
	public int getRewardKills(int number){
		if(!plugin.getConfig().contains("Rewards." + number)){
			return -1;
		}
		return plugin.getConfig().getInt("Rewards." + number + ".Kills");
	}
	/**
	 * Grab reward money. Note: returns -1 if that reward # does not exsist
	 * @param number Reward number
	 * @return Money
	 */
	public int getRewardMoney(int number){
		if(!plugin.getConfig().contains("Rewards." + number)){
			return -1;
		}
		return plugin.getConfig().getInt("Rewards." + number + ".Money");
	}
}
