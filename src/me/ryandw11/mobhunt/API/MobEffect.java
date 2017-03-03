package me.ryandw11.mobhunt.API;

import org.bukkit.Effect;

import me.ryandw11.mobhunt.core.Main;

public class MobEffect {
	private Main plugin;
	public MobEffect(){
		this.plugin = Main.plugin;
	}
	/**
	 * Grabs particle through the particle system the plugin uses.
	 * @param particle
	 * @return Effect.
	 * @throws NullPointerException
	 * @deprecated Will be replaced soon
	 */
	public Effect effectOut(String particle){
		Effect eff = null;
		switch(particle){
		
		case "bow_fire":
			eff = Effect.BOW_FIRE;
			break;
		case "mobspawner_flames":
			eff = Effect.MOBSPAWNER_FLAMES;
			break;
		case "cloud":
			eff = Effect.CLOUD;
			break;
		case "colored_dust":
			eff = Effect.COLOURED_DUST;
			break;
		case "crit":
			eff = Effect.CRIT;
			break;
		case "ender_signal":
			eff = Effect.ENDER_SIGNAL;
			break;
		case "explosion":
			eff = Effect.EXPLOSION;
			break;
		case "explosion_huge":
			eff = Effect.EXPLOSION_HUGE;
			break;
		case "explosion_large":
			eff = Effect.EXPLOSION_LARGE;
			break;
		case "firework_spark":
			eff = Effect.FIREWORKS_SPARK;
			break;
		case "flame":
			eff = Effect.FLAME;
			break;
		case "footstep":
			eff = Effect.FOOTSTEP;
			break;
		case "happy_villager":
			eff = Effect.HAPPY_VILLAGER;
			break;
		case "heart":
			eff = Effect.HEART;
			break;
		case "large_smoke":
			eff = Effect.LARGE_SMOKE;
			break;
		case "lava_pop":
			eff = Effect.LAVA_POP;
			break;
		case "magic_crit":
			eff = Effect.MAGIC_CRIT;
			break;
		case "note":
			eff = Effect.NOTE;
			break;
		case "particle_smoke":
			eff = Effect.PARTICLE_SMOKE;
			break;
		case "small_smoke":
			eff = Effect.SMALL_SMOKE;
			break;
		case "smoke":
			eff = Effect.SMOKE;
			break;
		case "thunder_cloud":
			eff = Effect.VILLAGER_THUNDERCLOUD;
			break;
		case "spell":
			eff = Effect.SPELL;
			break;
		case "dust":
			eff = Effect.TILE_DUST;
			break;
		case "none":
			eff = null;
			break;
		}
		return eff;
	}
}
