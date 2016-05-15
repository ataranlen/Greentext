package com.minetexas.greentext;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.minetexas.greentext.commands.CommandBase;
import com.minetexas.greentext.util.GTLog;
import com.minetexas.greentext.util.GTSettings;

public class Greentext extends JavaPlugin {
	@Override
	public void onEnable() {
		

		GTLog.init(this);
		GTLog.info("onEnable has been invoked!");
		GTSettings.init(this);


		this.getCommand("me").setExecutor(new CommandBase());
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
