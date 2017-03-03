package me.ryandw11.mobhunt.API;

import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;

import me.ryandw11.mobhunt.core.Main;
import me.ryandw11.mobhunt.core.Mobs;
/**
 * All methods having to do with mobs.
 * @author Ryandw11
 *
 */
public class MobData {
	private Main plugin;
	public MobData(){
		this.plugin = Main.plugin;
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
	
	/**
	 * Grab the mob particle effect
	 * @param e The mob you want.
	 * @return Effect that was given.
	 * @throws NullPointerException
	 */
	public Effect getMobPartical(EntityType e){
		MobEffect me = new MobEffect();
		String monstertype = e.toString().toLowerCase();
		return me.effectOut(plugin.getConfig().getString(monstertype + ".Particle"));
	}
	/**
	 * Grab the mobs sound
	 * @param e The mob you want.
	 * @return Sound that was given
	 * @throws NullPointerException
	 */
	public Sound getMobSound(EntityType e){
		MobSound ms = new MobSound();
		String monstertype = e.toString().toLowerCase();
		return ms.soundEffect(plugin.getConfig().getString(monstertype + ".Sound"));
	}
	
}
