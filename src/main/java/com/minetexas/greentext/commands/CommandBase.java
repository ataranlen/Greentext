package com.minetexas.greentext.commands;

import java.util.Collection;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.minetexas.greentext.exception.GTException;
import com.minetexas.greentext.util.GTColor;
import com.minetexas.greentext.util.GTLog;
import com.minetexas.greentext.util.GTSettings;
import com.dthielke.herochat.Chatter;
import com.dthielke.herochat.ChatterManager;
import com.dthielke.herochat.Herochat;

public class CommandBase implements CommandExecutor {

	protected HashMap<String, String> commands = new HashMap<String, String>();
	protected String[] args;
	protected CommandSender sender;
	
	
	protected String command = "FIXME";
	protected String displayName = "FIXME";
	protected boolean sendUnknownToDefault = false;
	
	public void init() {
		command = "/me";
		displayName = "Throwdown, right meow!";
	}
 
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		init();
		
		this.args = args;
		this.sender = sender;
		
		if (!permissionCheck(GTSettings.PERMISSION_BASE))
		{
			sendMessage(sender, GTColor.Rose+"You'd better check yourself before you wreck yourself!");
			return false;
		}

		if (args.length == 0) {
			sendMessage(sender, GTColor.LightPurple+"You didn't meme hard enough!");
			return false;
		} else {
			StringBuilder builder = new StringBuilder();
			if (!permissionCheck(GTSettings.PERMISSION_ANON)){
				builder.append(sender.getName()+" ");
			}
			builder.append(">");
			
			for(String s : args) {
				s = s.replace("@p", GTColor.LightBlue+sender.getName()+GTColor.LightGreen);
				if(sender instanceof Player){
					 //Then we cast the sender to player,which means now we can handle him through 'player' variable like any other players
//					   Location loc = player.getLocation();
					   
						s = s.replace("@l", GTColor.Gold+"XYZ: 1337 250 1337"+GTColor.LightGreen);
					}
			    builder.append(" "+s);
			}
			String meme = builder.toString();
			GTLog.info(sender.getName()+meme);

			Collection <? extends Player> players = Bukkit.getOnlinePlayers();//.forEach(p -> p.sendMessage(GTColor.LightGreen+meme));


			Boolean useHerochat = false;
			if (GTSettings.hasHerochat == true) {
				Herochat hc = Herochat.getPlugin();
				useHerochat = hc.isEnabled();

				GTLog.debug("Herochat usable? "+useHerochat);
			}
			for(Player p : players) {
				if (useHerochat) {
					ChatterManager cm = Herochat.getChatterManager();
					Player player=(Player) sender;
					Chatter chatter = cm.getChatter(player);
					Chatter chattee = cm.getChatter(p);
					if (chattee.isIgnoring(chatter)) {
						continue;
					}
				}
				p.sendMessage(GTColor.LightGreen+meme);
			}
			
			

			return true;
		}
	}
	
	public Boolean permissionCheck(String permission) {
		Player player;
		try {
			player = getPlayer();
		} catch (GTException e) {
			e.printStackTrace();
			return false;
		}
		
		if (!player.isOp() && !player.hasPermission(permission)) {
			return false;
		}
		return true;
	}
	
	public Player getPlayer() throws GTException {
		if (sender instanceof Player) {
			return (Player)sender;
		}
		throw new GTException("You must be a player to execute this command");
	}
	
	public static void sendMessage(Object sender, String line) {
		if ((sender instanceof Player)) {
			((Player) sender).sendMessage(line);
		} else if (sender instanceof CommandSender) {
			((CommandSender) sender).sendMessage(line);
		}
	}
	public static void sendMessage(Object sender, String[] lines) {
		boolean isPlayer = false;
		if (sender instanceof Player)
			isPlayer = true;

		for (String line : lines) {
			if (isPlayer) {
				((Player) sender).sendMessage(line);
			} else {
				((CommandSender) sender).sendMessage(line);
			}
		}
	}
	
	public static String buildTitle(String title) {
		String line =   "-------------------------------------------------";
		String titleBracket = "[ " + GTColor.Yellow + title + GTColor.LightBlue + " ]";
		
		if (titleBracket.length() > line.length()) {
			return GTColor.LightBlue+"-"+titleBracket+"-";
		}
		
		int min = (line.length() / 2) - titleBracket.length() / 2;
		int max = (line.length() / 2) + titleBracket.length() / 2;
		
		String out = GTColor.LightBlue + line.substring(0, Math.max(0, min));
		out += titleBracket + line.substring(max);
		
		return out;
	}
	
	public static void sendHeading(CommandSender sender, String title) {	
		sendMessage(sender, buildTitle(title));
	}
	
	public static void sendError(Object sender, String line) {		
		sendMessage(sender, GTColor.Rose+line);
	}


}
