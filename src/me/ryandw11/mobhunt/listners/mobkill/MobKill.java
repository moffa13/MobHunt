package me.ryandw11.mobhunt.listners.mobkill;

import java.util.List;

import me.ryandw11.mobhunt.MobHunt;
import me.ryandw11.mobhunt.util.Mobs;
import me.ryandw11.mobhunt.util.Utilities;
import net.milkbowl.vault.economy.EconomyResponse;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class MobKill implements Listener{
	private int am; // used for stuff
	private String amount; // used for the global amount in the methods!
	
	private MobHunt plugin;
	public MobKill(MobHunt plugin){
		this.plugin = plugin;
	}
	

	
	@EventHandler
	public void OnDeath(EntityDeathEvent event){
		Utilities u = new Utilities();
		Entity e = event.getEntity();
		if(e instanceof Player) 
			return;    
		
			Entity monsterEnt = (Entity) e;
			String monstertype = e.getType().toString().toLowerCase();
			
			if(!(((LivingEntity) monsterEnt).getKiller() instanceof Player)) 
				return;
			
			Player p = ((LivingEntity) monsterEnt).getKiller();
			if(MobHunt.debug.contains(p)){
				p.sendMessage( "[MH Debug] "+ monsterEnt.getType().toString());
			}
			
			if(plugin.getConfig().getBoolean("GameMode_Limit.Enabled") == true) 
				if(p.getGameMode() == GameMode.CREATIVE) return;
				
				Boolean check = checkWorld(p); //Checks to see if world is disabled.
				
				
				//If mob has meta data return
				if(grabMetaData(monsterEnt)) 
					return;
				//if world is disabled.
				if(check) 
					return;
				
				if(!plugin.getConfig().contains(monstertype)) return;
			

						if(p.hasPermission("mobhunt.pay")){
		
							this.paymentArea(monstertype, p);
							
							//==================================Sounds==========================
									if(!(plugin.getConfig().getString(monstertype + ".Sound").equalsIgnoreCase("none"))){

										u.playerSound(p, plugin.getConfig().getString(monstertype + ".Sound"));
									}
							//===============================Efects=============================
									if(!(plugin.getConfig().getString(monstertype + ".Particle").equalsIgnoreCase("none"))){
										int Effnum = plugin.getConfig().getInt("Particle.Effect_Number");
										Effect eff = u.effectOut(plugin.getConfig().getString(monstertype + ".Particle"));
										Location loc = e.getLocation();
										e.getWorld().playEffect(loc, eff, Effnum);
									}
							
							//----------------------------------------------------
							//---------------Announcements------------------------
							//----------------------------------------------------
							
							if(plugin.getConfig().contains("Announcements." + e.getType().toString().toLowerCase())){
								Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Prefix") + plugin.getConfig().getString("Announcements." + e.getType().toString().toLowerCase()).replace("{player}", p.getDisplayName())));
							}
							
							int kills = plugin.kill.getInt(p.getUniqueId().toString() + "kills");
							kills += 1;
							plugin.kill.set(p.getUniqueId() + "kills", kills);
							plugin.saveFile();
							
							//---------------------------------------Rewards---------------------------------------------
							if(p.hasPermission("mobhunt.rewards")){
								EconomyResponse moneykill = null;
								int i = 1;
								int numberRewards = plugin.getConfig().getInt("Rewards.Number");
								
								do{
									if(plugin.kill.getInt(p.getUniqueId().toString() + "kills") == plugin.getConfig().getInt("Rewards." + i + ".Kills")){
										moneykill = MobHunt.econ.depositPlayer(p, plugin.getConfig().getInt("Rewards." + i +".Money"));
										if(moneykill.transactionSuccess()) {//----------Vault-------------
											p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Rewards." + i +".Message")));
										} else {
											p.sendMessage(String.format("An error occured: %s", moneykill.errorMessage));
											plugin.logger.warning("[MobHunt] Error- The config: config.yml. Is not defined correctly.");
										}
									}
									i++;
								}while(i <= numberRewards);
								
								
								}//perm
								//-------------------------------------------[Title]-----------------------------------
								
								
							}
							//-------------------------------------------[Title]------------------------------

							

						
					}// end

					
				
				
				
			//------------{End of Death Event}------------------------------	
	
	
	
	private boolean checkWorld(Player p){
		World w = p.getWorld();
		List<String> worlds = plugin.getConfig().getStringList("Worlds_Not_Allowed");
		for(String s : worlds){
			if(w.getName().equals(s)){
				return true;
			}
		}
		return false;
	}
	private void vault(EconomyResponse er, Player p, String monstertype){
		if(er.transactionSuccess()) {//----------Vault-------------
			if(plugin.getConfig().getString("Messages.Mode").equalsIgnoreCase("Chat")){
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Mob_Kill_Message").replace("{mob}", monstertype).replace("{money}", amount)));
			}
			else if(plugin.getConfig().getString("Messages.Mode").equalsIgnoreCase("Title")){
				if(plugin.getConfig().get(monstertype + ".Money") instanceof Integer && plugin.getConfig().getInt(monstertype + ".Money") != 0){
					String message = plugin.getConfig().getString("Title.Message").replace("{mob}", monstertype).replace("{money}", amount).replace("&", "§");
					plugin.ab.actionBar(p, message);
				}
			}
			else{
				plugin.logger.warning("[MobHunt] Error: An error has occured in the config! Please check Messages.Mode!");
				p.sendMessage("[MobHunt] §cError: §aAn error has occured. Please contact the server admin!");
			}
		} else {
			p.sendMessage(String.format("An error occured: %s", er.errorMessage));
		}
	}
	
	private boolean grabMetaData(Entity e){
		if(e.hasMetadata("SR")){
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * Used to clean up code.
	 */
	private void paymentArea(String monstertype, Player p){
		EconomyResponse er = null;
		//If it is a string / has a range
		if(plugin.getConfig().isString(monstertype + ".Money")){// new area
			if(Mobs.splitFirst(plugin.getConfig().getString(monstertype + ".Money")) == -1){
				p.sendMessage("[MobHunt] §cError: §6Mob.Money §a- String is not vailed. Contact an admin!");
			}
			am = Mobs.genRandom(//Grabs a random number
				Mobs.splitFirst(plugin.getConfig().getString(monstertype + ".Money")), //Get's number 1
				Mobs.splitSecond(plugin.getConfig().getString(monstertype + ".Money")) // Get's number 2
				);
			amount = String.valueOf(am);
			er = MobHunt.econ.depositPlayer(p, am);
			vault(er, p, monstertype);
		}
		else if(plugin.getConfig().isInt(monstertype + ".Money")){ //else if it is an int
			if(plugin.getConfig().getInt(monstertype + ".Money") == 0) return;
			amount = String.valueOf(plugin.getConfig().getInt(monstertype + ".Money"));
			int mon = plugin.getConfig().getInt(monstertype + ".Money");
			er = MobHunt.econ.depositPlayer(p, mon);
			vault(er, p, monstertype);
		}
		else{ //if it is none of the above
			plugin.logger.warning("[MobHunt] Error: An error has occured in the config! Please check " + monstertype + ".Money!");
			return;
		}//-----------Vault----------------
		
	}


}
