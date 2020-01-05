package me.ryandw11.mobhunt.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ryandw11.mobhunt.MobHunt;

public class MobHuntCmd implements CommandExecutor {
	
	private MobHunt plugin;
	public MobHuntCmd(MobHunt plugin){
		this.plugin = plugin;
	}
	
	

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		if(!(sender instanceof Player )) {
			sender.sendMessage(ChatColor.RED + "This command is for players only!");
			return true;
		}
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("MobHunt")){
			
			if(args.length == 0){
				if(p.hasPermission("mobhunt.help")){
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8---------[&aMobHunt&8]---------"));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Plugin Developed by: &aRyandw11"));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("&7Version: &a %s", plugin.getDescription().getVersion())));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7For the list of commands do &a/mb help&7."));
				}
				else{
					p.sendMessage(plugin.Prefix + plugin.NoPerm);
				}
				
			}//end of help command
			else if(args.length == 1 && args[0].equalsIgnoreCase("help")){
				if(p.hasPermission("mobhunt.help")){
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8---------[&aMobHunt Help&8]---------"));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/MobHunt help &7 Get the help page."));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/MobHunt kills &7 Get your kills."));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/MobHunt rewards &7 Check the rewards."));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/MobHunt leaderboard &7 List of the top 5 players!"));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cAdmin: &a/MobHunt check {player} &7 See a players kills."));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cAdmin: &a/MobHunt reload &7 Reload the config files."));
				}
				else{
					p.sendMessage(plugin.Prefix + plugin.NoPerm);
				}
			}
			else if(args.length == 1 && args[0].equalsIgnoreCase("kills")){
				if(p.hasPermission("mobhunt.kills")){
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.Prefix + "&aYou have&e " + plugin.kill.getInt(p.getUniqueId() + "kills") + " &akills."));
				}
				else{
					p.sendMessage(plugin.Prefix + plugin.NoPerm);
				}
			}else if(args.length == 1 && args[0].equalsIgnoreCase("debug")){
				if(p.hasPermission("mobhunt.debug")){
					if(!MobHunt.debug.contains(p)){
						p.sendMessage(ChatColor.GRAY + "Debug mode enabled!");
						MobHunt.debug.add(p);
					}else if(MobHunt.debug.contains(p)){
						p.sendMessage(ChatColor.GRAY + "Debug mode disabled!");
						MobHunt.debug.remove(p);
					}else{
						p.sendMessage(ChatColor.RED + "A fatal error has happened! Error code: MHCMD_Ln_62-73 Array not found.");
					}
				}
				else{
					p.sendMessage(plugin.Prefix + plugin.NoPerm);
				}
			}
			else if(args.length == 1 && args [0].equalsIgnoreCase("rewards")){
				if(p.hasPermission("mobhunt.rewards.view")){
					int i = 1;
					int numberRewards = plugin.getConfig().getInt("Rewards.Number");
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8---------[&aMobHunt Rewards&8]---------"));
					do{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Rewards.Reward_Format")
								.replace("{kills}",  plugin.getConfig().get("Rewards." + i + ".Kills").toString())
								.replace("{money}",  plugin.getConfig().get("Rewards." + i + ".Money").toString())));
						i++;
					}while(i <= numberRewards);
					
				}
				else{
					p.sendMessage(plugin.Prefix + plugin.NoPerm);
				}
			}
			else if(args.length == 2 && args[0].equalsIgnoreCase("check")){
				if(p.hasPermission("mobhunt.check")){
					
					if(Bukkit.getServer().getOfflinePlayer(args[1]).isOnline()){
						Player p2 = (Player) Bukkit.getServer().getPlayer(args[1]);
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.Prefix + "&aThat player has&e " + plugin.kill.getInt(p2.getUniqueId() + "kills") + " &akills."));
					}
					else{
						p.sendMessage(plugin.Prefix + ChatColor.RED + "That player is not currently online!");
					}
				}
				else{
					p.sendMessage(plugin.Prefix + plugin.NoPerm);
				}
			}
			else if(args.length == 1 && args[0].equalsIgnoreCase("reload")){
				if(p.hasPermission("mobhunt.reload")){
					plugin.reloadConfig();
					p.sendMessage(plugin.Prefix + ChatColor.GREEN + "The config files were reloaded!");
					plugin.logger.info("[MobHunt] The config files were reloaded!");
				}
				else{
					p.sendMessage(plugin.Prefix + plugin.NoPerm);
				}
			}
			
		
		else if(args.length == 1 && args[0].equalsIgnoreCase("scoreboard") ||
				args.length == 1 && args[0].equalsIgnoreCase("leaderboard")){
			
			if(p.hasPermission("mobhunt.leaderboard")){
				int i = 1;
				String x = "1";
				
				
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8---------[&aMobHunt LeaderBoard&8]---------"));
				do{
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("LeaderBoard_Format")
							.replace("{kills}", String.valueOf( plugin.scoreboard.getInt("ScoreBoard." + i + ".Kills")))
							.replace("{player}",  plugin.scoreboard.getString("ScoreBoard." + i + ".Player"))
							.replace("{rank}", x)
							));
					i++;
					x = Integer.toString(i);
				}while(i <= 5);
			
			}
			else{
				p.sendMessage(plugin.Prefix + plugin.NoPerm);
			}
		}
		else{
			p.sendMessage(plugin.Prefix + ChatColor.RED + " Error: That is not a command. Do /mb help for a list of commands.");
		}
		
		}
		return false;
	}
	
	

}

