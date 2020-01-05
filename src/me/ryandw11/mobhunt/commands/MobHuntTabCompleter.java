package me.ryandw11.mobhunt.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class MobHuntTabCompleter implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) {
		if(cmd.getName().equalsIgnoreCase("mobhunt")){
			ArrayList<String> completions = new ArrayList<>();
			if(args.length == 1){
				completions = new ArrayList<>( Arrays.asList("help", "kills", "debug", "rewards", "check", "scoreboard", "leaderboard"));
				completions = getAppliableTabCompleters(args.length == 1 ? args[0] : "", completions);
			}else{
				return null;
			}
			Collections.sort(completions);
			return completions;
		}
		
		return null;
	}
	
	public ArrayList<String> getAppliableTabCompleters(String arg, ArrayList<String> completions) {
	       if (arg == null || arg.equalsIgnoreCase("")) {
	           return completions;
	       }
	       ArrayList<String> valid = new ArrayList<>();
	       for (String posib : completions) {
	           if (posib.startsWith(arg)) {
	               valid.add(posib);
	           }
	       }
	       return valid;
	   }

}
