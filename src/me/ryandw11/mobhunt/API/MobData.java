package me.ryandw11.mobhunt.API;

import org.bukkit.entity.EntityType;

import me.ryandw11.mobhunt.MobHunt;
import me.ryandw11.mobhunt.util.Mobs;
/**
 * All methods having to do with mobs.
 * API Note: MobEffect has been removed due to the 1.13 update.
 * API Note: MobSound has been removed due to the 1.13 update.
 * @author Ryandw11
 *
 */
public class MobData {
	private MobHunt plugin;
	public MobData(){
		this.plugin = MobHunt.plugin;
	}
	/**
	 * Grabs the amount of money the player gets for killing a mob.
	 * Note: returns -1 if value is invaild.
	 * @param e The mob you want.
	 * @return Amount of money
	 */
	public int getMobMoney(EntityType e){
		String monstertype = e.toString().toLowerCase();
		if(plugin.getConfig().isInt(monstertype + ".Money")){
			return plugin.getConfig().getInt(monstertype + ".Money");
		}
		else if(plugin.getConfig().isString(monstertype + ".Money")){
			int am;
			am = Mobs.genRandom(
					Mobs.splitFirst(plugin.getConfig().getString(monstertype + ".Money")), 
					Mobs.splitSecond(plugin.getConfig().getString(monstertype + ".Money"))
					);
			if(Mobs.splitFirst(plugin.getConfig().getString(monstertype + ".Money"))!= -1){
				return am;
			}
			else{
				return -1;
			}
		}
		else{
			return -1;
		}
	}
	
}
