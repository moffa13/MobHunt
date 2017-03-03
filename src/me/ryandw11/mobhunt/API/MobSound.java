package me.ryandw11.mobhunt.API;

import org.bukkit.Sound;

import me.ryandw11.mobhunt.core.Main;

public class MobSound {
	private Main plugin;
	public MobSound(){
		this.plugin = Main.plugin;
	}
	
	/**
	 * Grab the sound effect.
	 * @param sounds Plugin sound name.
	 * @return Sound enum.
	 * @throws NullPointerException
	 */
	public Sound soundEffect(String sounds){
		Sound sound;
		switch(sounds){
		case "Bat_Death":
			sound = Sound.ENTITY_BAT_DEATH;
			break;
		case "Blaze_Death":
			sound = Sound.ENTITY_BLAZE_DEATH;
			break;
		case "Blaze_Shoot":
			sound = Sound.ENTITY_BLAZE_SHOOT;
			break;
		case "Cat_Death":
			sound = Sound.ENTITY_CAT_DEATH;
			break;
		case "Cat_Purr":
			sound = Sound.ENTITY_CAT_PURR;
			break;
		case "Cat_Hiss":
			sound = Sound.ENTITY_CAT_HISS;
			break;
		case "Chicken_Death":
			sound = Sound.ENTITY_CHICKEN_DEATH;
			break;
		case "Chicken_Hurt":
			sound = Sound.ENTITY_CHICKEN_HURT;
			break;
		case "Cow_Death":
			sound = Sound.ENTITY_COW_DEATH;
			break;
		case "Cow_Hurt":
			sound = Sound.ENTITY_COW_HURT;
			break;
		case "Creeper_Death":
			sound = Sound.ENTITY_CREEPER_DEATH;
			break;
		case "Creeper_Hurt":
			sound = Sound.ENTITY_CREEPER_HURT;
			break;
		case "Elder_Guardian_Curse":
			sound = Sound.ENTITY_ELDER_GUARDIAN_CURSE;
			break;
		case "Elder_Guardian_Death":
			sound = Sound.ENTITY_ELDER_GUARDIAN_DEATH;
			break;
		case "Ender_Guardian_Hurt":
			sound = Sound.ENTITY_ELDER_GUARDIAN_HURT;
			break;
		case "Enderdragon_Growl":
			sound = Sound.ENTITY_ENDERDRAGON_GROWL;
			break;
		case "Enderdragon_Flap":
			sound = Sound.ENTITY_ENDERDRAGON_FLAP;
			break;
		case "Enderman_Death":
			sound = Sound.ENTITY_ENDERMEN_DEATH;
			break;
		case "Enderman_Hurt":
			sound = Sound.ENTITY_ENDERMEN_HURT;
			break;
		case "Explosion":
			sound = Sound.ENTITY_GENERIC_EXPLODE;
			break;
		case "Ghast_Death":
			sound = Sound.ENTITY_GHAST_DEATH;
			break;
		case "Ghast_Hurt":
			sound = Sound.ENTITY_GHAST_HURT;
			break;
		case "Ghast_Scream":
			sound = Sound.ENTITY_GHAST_SCREAM;
			break;
		case "Ghast_Warn":
			sound = Sound.ENTITY_GHAST_WARN;
			break;
		case "Horse_Hurt":
			sound = Sound.ENTITY_HORSE_HURT;
			break;
		case "Horse_Death":
			sound = Sound.ENTITY_HORSE_DEATH;
			break;
		case "IronGolem_Death":
			sound = Sound.ENTITY_IRONGOLEM_DEATH;
			break;
		case "Pig_Death":
			sound = Sound.ENTITY_PIG_DEATH;
			break;
		case "Rabbit_Death":
			sound = Sound.ENTITY_RABBIT_DEATH;
			break;
		case "Shulker_Death":
			sound = Sound.ENTITY_SHULKER_DEATH;
			break;
		case "Slime_Death":
			sound = Sound.ENTITY_SLIME_DEATH;
			break;
		case "Slime_Hurt":
			sound = Sound.ENTITY_SLIME_HURT;
			break;
		case "Snowman_Death":
			sound = Sound.ENTITY_SNOWMAN_DEATH;
			break;
		case "Villager_Death":
			sound = Sound.ENTITY_VILLAGER_DEATH;
			break;
		case "Wolf_Death":
			sound = Sound.ENTITY_WOLF_DEATH;
			break;
		case "Zombie_Hurt":
			sound = Sound.ENTITY_ZOMBIE_HURT;
			break;
		case "Husk":
			sound = Sound.ENTITY_HUSK_AMBIENT;
			break;
		case "Husk_Step":
			sound = Sound.ENTITY_HUSK_STEP;
			break;
		case "Stray":
			sound = Sound.ENTITY_STRAY_AMBIENT;
			break;
		case "Stray_hurt":
			sound = Sound.ENTITY_STRAY_HURT;
			break;
		case "polarbear":
			sound = Sound.ENTITY_POLAR_BEAR_AMBIENT;
			break;
		case "polarbear_warning":
			sound = Sound.ENTITY_POLAR_BEAR_WARNING;
			break;
		case "polarbear_death":
			sound = Sound.ENTITY_POLAR_BEAR_DEATH;
			break;
		case "none":
			sound = null;
			break;
		default:
			sound = null;
			break;
		}
		return sound;
	}
}
