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
	    this.getCommand("me2").setExecutor(new CommandBase());
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
