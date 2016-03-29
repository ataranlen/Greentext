package com.minetexas.greentext.util;

import com.minetexas.greentext.Greentext;

public class GTSettings {

	public static Greentext plugin;
	public static final String PERMISSION_BASE = "greentext.use";
	public static final String PERMISSION_ANON = "greentext.anon";
	
	public static void init(Greentext plugin) {
		GTSettings.plugin = plugin;
	}
	
	
}
