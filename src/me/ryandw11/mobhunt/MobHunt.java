package me.ryandw11.mobhunt;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import me.ryandw11.mobhunt.commands.MobHuntTabCompleter;
import me.ryandw11.mobhunt.commands.MobHuntCmd;
import me.ryandw11.mobhunt.listners.EnitityMetaData;
import me.ryandw11.mobhunt.listners.ScoreBoard;
import me.ryandw11.mobhunt.listners.Join;
import me.ryandw11.mobhunt.util.ActionBar;
import me.ryandw11.mobhunt.util.ActionBar_1_13_R1;
import me.ryandw11.mobhunt.util.ActionBar_1_13_R2;
import me.ryandw11.mobhunt.util.ActionBar_1_14_R1;
import me.ryandw11.mobhunt.util.ActionBar_1_15_R1;
import me.ryandw11.mobhunt.listners.mobkill.MobKill;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class MobHunt extends JavaPlugin{
		public final Logger logger = Logger.getLogger("Minecraft");
		public static MobHunt plugin;
		public String Prefix;
		public String NoPerm;
		public static ArrayList<Player> debug;
		public ActionBar ab;
		
		
		public static Economy econ = null;
		
		public File killfile = new File(getDataFolder() + "/data/kills.yml");
		public FileConfiguration kill = YamlConfiguration.loadConfiguration(killfile);
		
		public File scoreboardfile = new File(getDataFolder() + "/data/scoreboard.yml");
		public FileConfiguration scoreboard = YamlConfiguration.loadConfiguration(scoreboardfile);
		
		
		@Override
		public void onEnable(){
			plugin = this;
			if (setupPlug()) {
				logger.info(String.format("[%s]MobHunt for has enabled and running fine! V: %s", getDescription().getName(), getDescription().getVersion())); // prints into the log
				loadMethod();
				debug = new ArrayList<Player>();
				if (!setupEconomy() ) {
		            logger.severe(String.format("[%s] - Disabled due to no Vault or Economy plugin!", getDescription().getName()));
		            Bukkit.getPluginManager().disablePlugin(this);
		            return;
		        }

				
				loadFile();
				loadBoard();
				registerConfig();
				scoreBoardSetup();

	        } else {

	            getLogger().severe("Failed to load MobHunt!");
	            getLogger().severe("Your server version is not compatible with this plugin!");
	            getLogger().severe("Usable Versions: 1.13, 1.14, 1.15");
	            getLogger().severe("Use a different version of this plugin for 1.10-1.12 support.");

	            Bukkit.getPluginManager().disablePlugin(this);
	        }
			
		}
		
		@Override
		public void onDisable(){
			logger.info("MobHunt for 1.13+ has been disabled correctly!"); // same thing
			logger.info("Saving the file: kills.yml");
			saveFile();
			saveBoard();

		}
		
		//VAULT SETUP
		private boolean setupEconomy() {
	        if (getServer().getPluginManager().getPlugin("Vault") == null) {
	            return false;
	        }
	        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
	        if (rsp == null) {
	            return false;
	        }
	        econ = rsp.getProvider();
	        return econ != null;
	    }
//===================		
		public void scoreBoardSetup(){
			if(!(scoreboard.contains("ScoreBoard"))){
				scoreboard.set("ScoreBoard.1.Player", "none");
				scoreboard.set("ScoreBoard.1.Kills", "none");
				scoreboard.set("ScoreBoard.2.Player", "none");
				scoreboard.set("ScoreBoard.2.Kills", "none");
				scoreboard.set("ScoreBoard.3.Player", "none");
				scoreboard.set("ScoreBoard.3.Kills", "none");
				scoreboard.set("ScoreBoard.4.Player", "none");
				scoreboard.set("ScoreBoard.4.Kills", "none");
				scoreboard.set("ScoreBoard.5.Player", "none");
				scoreboard.set("ScoreBoard.5.Kills", "none");
				plugin.saveBoard();
			}
		}
//========================================================================================================================================
//=                                               File Save and Load Area                                                                =
//========================================================================================================================================


	public void saveFile(){
		try{
			kill.save(killfile);
		}catch(IOException e){
			e.printStackTrace();
			
		}	
	}
		public void saveBoard(){
		try{
			scoreboard.save(scoreboardfile);
		}catch(IOException e){
			e.printStackTrace();
			
		}	
	}
	
	public void loadFile(){
		if(killfile.exists()){
			try {
				kill.load(killfile);
				
			} catch (IOException | InvalidConfigurationException e) {

				e.printStackTrace();
			}
		}
		else{
			try {
				kill.save(killfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void loadBoard(){
		if(scoreboardfile.exists()){
			try {
				scoreboard.load(scoreboardfile);
				
			} catch (IOException | InvalidConfigurationException e) {

				e.printStackTrace();
			}
		}
		else{
			try {
				scoreboard.save(scoreboardfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

//=================================		
		private void registerConfig() {
			getConfig().options().copyDefaults(true);
			saveConfig();
		}
//========================================		
		public void loadMethod(){
			getCommand("mobhunt").setExecutor(new MobHuntCmd(this));
			getCommand("mobhunt").setTabCompleter(new MobHuntTabCompleter());
			Bukkit.getServer().getPluginManager().registerEvents(new ScoreBoard(this), this);
			Bukkit.getServer().getPluginManager().registerEvents(new Join(this), this);
			Bukkit.getServer().getPluginManager().registerEvents(new EnitityMetaData(this), this);
			
			Prefix = ChatColor.translateAlternateColorCodes('&', getConfig().getString("Prefix"));
			NoPerm = ChatColor.translateAlternateColorCodes('&', getConfig().getString("NoPermission"));
		}
//============================		
		private boolean setupPlug() {
	        String version;

	        try {

	            version = Bukkit.getServer().getClass().getPackage().getName().replace(".",  ",").split(",")[3];

	        } catch (ArrayIndexOutOfBoundsException w0w) {
	            return false;
	        }

	        getLogger().info("Your server is running version " + version);
	        if (version.equals("v1_13_R1")) {
	        	ab = new ActionBar_1_13_R1();
				Bukkit.getServer().getPluginManager().registerEvents(new MobKill(this), this);
	        }
	        else if (version.equals("v1_13_R2")) {
	        	ab = new ActionBar_1_13_R2();
				Bukkit.getServer().getPluginManager().registerEvents(new MobKill(this), this);
	        }
	        else if (version.equals("v1_14_R1")) {
	        	ab = new ActionBar_1_14_R1();
				Bukkit.getServer().getPluginManager().registerEvents(new MobKill(this), this);
	        }
	        else if (version.equals("v1_15_R1")) {
	        	ab = new ActionBar_1_15_R1();
				Bukkit.getServer().getPluginManager().registerEvents(new MobKill(this), this);
	        }
	        
	        return version.equals("v1_13_R1") || version.equals("v1_13_R2") || version.equals("v1_14_R1") || version.equals("v1_15_R1");
	    }

	}