package com.minetexas.greentext.util;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;

import com.minetexas.greentext.Greentext;

public class GTSettings {

	public static Greentext plugin;
	public static final String PERMISSION_BASE = "greentext.use";
	public static final String PERMISSION_ANON = "greentext.anon";
	
	public static FileConfiguration badgeConfig; /* badges.yml */
	public static Map<String, ConfigBadges> badges = new HashMap<String, ConfigBadges>();
	
	public static void init(Greentext plugin) {
		GTSettings.plugin = plugin;
	}
	
	
}
