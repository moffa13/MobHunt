package me.ryandw11.mobhunt.util;

import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import me.ryandw11.mobhunt.MobHunt;

public class Utilities {
	private MobHunt plugin;
	public Utilities(){
		plugin = MobHunt.plugin;
	}
	public void playerSound(Player p, String sound){
		p.playSound(p.getLocation(), soundEffect(sound), 3, 0);
	}
	public Sound soundEffect(String sounds){
		Sound sound = null;
		if(sounds == "none") return sound;
		try{
			sound = Sound.valueOf(sounds.toUpperCase());
		}catch (Exception e){
			plugin.getLogger().warning("A sound in the config is invalid.");
			sound = Sound.BLOCK_ANVIL_BREAK;
		}
		return sound;
	}
	
	public Effect effectOut(String particle){
		Effect eff = null;
		if(particle == "none") return eff;
		try{
			eff = Effect.valueOf(particle.toUpperCase());
		}catch(Exception e){
			plugin.getLogger().warning("An effect is invaild in the config!");
			eff = Effect.DRAGON_BREATH;
		}
		
		
		return eff;
	}
	
	
}
