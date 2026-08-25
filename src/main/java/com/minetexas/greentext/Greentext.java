package com.minetexas.greentext;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.minetexas.greentext.commands.CommandBase;
import com.minetexas.greentext.util.GTLog;
import com.minetexas.greentext.util.GTSettings;

public class Greentext extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
	
		GTLog.init(this);
		GTLog.info("onEnable has been invoked!");
		GTSettings.init(this);

		this.getCommand("me").setExecutor(new CommandBase());
	    this.getCommand("me0").setExecutor(new CommandBase());
	    this.getCommand("me1").setExecutor(new CommandBase());
	    this.getCommand("me2").setExecutor(new CommandBase());
	    this.getCommand("me3").setExecutor(new CommandBase());
	    this.getCommand("me4").setExecutor(new CommandBase());
	    this.getCommand("me5").setExecutor(new CommandBase());
	    this.getCommand("me6").setExecutor(new CommandBase());
	    this.getCommand("me7").setExecutor(new CommandBase());
	    this.getCommand("me8").setExecutor(new CommandBase());
	    this.getCommand("me9").setExecutor(new CommandBase());
	    this.getCommand("mea").setExecutor(new CommandBase());
	    this.getCommand("meb").setExecutor(new CommandBase());
	    this.getCommand("mec").setExecutor(new CommandBase());
	    this.getCommand("med").setExecutor(new CommandBase());
	    this.getCommand("mee").setExecutor(new CommandBase());
	    this.getCommand("mef").setExecutor(new CommandBase());
	    if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getPluginManager().registerEvents(this, this);
	    } else {
	    	throw new RuntimeException("Could not find PlaceholderAPI!! Plugin can not work without it!");
	    } 
	}
 
	@Override
	public void onDisable() {
		GTLog.info("onDisable has been invoked!");
	}
	
	public boolean hasPlugin(String name) {
		Plugin p;
		p = getServer().getPluginManager().getPlugin(name);
		return (p != null);
	}
}
