package me.ryandw11.mobhunt.listners;

import me.ryandw11.mobhunt.core.Main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.metadata.FixedMetadataValue;

public class EnitityMetaData implements Listener{
	private Main plugin;
	public EnitityMetaData(Main plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void SpawnEvent(CreatureSpawnEvent e){
		Entity mob = e.getEntity();
		if(mob instanceof Player) 
			return;
		SpawnReason sp = e.getSpawnReason();
		if(!plugin.getConfig().getBoolean("Limit_Money_Mob_Spawn")) 
			return;
		//Checks to see if the mob is from a spawner or spawn egg.
		if(sp.equals(SpawnReason.SPAWNER) || sp.equals(SpawnReason.SPAWNER_EGG)){
			mob.setMetadata("SR", new FixedMetadataValue(plugin, "MobHunt By: Ryandw11")); //adding a metadata if so.
		}

	}
}
