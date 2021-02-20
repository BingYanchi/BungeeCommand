package cn.yistars.bungeecommand;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.logging.Logger;

import cn.yistars.command.Run;

public class main extends Plugin {
	public Logger logger = Logger.getLogger("BungeeCommand");
	
	@Override
	public void onEnable() {
		ProxyServer.getInstance().registerChannel("BungeeCommand");
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new Run());
		this.logger.info("Enabled successfully.");
	}
	
	public void onDisable() {
		ProxyServer.getInstance().getPluginManager().unregisterCommands(this);
		ProxyServer.getInstance().unregisterChannel("BungeeCommand");
		this.logger.info("Disenabled successfully.");
	}
}

