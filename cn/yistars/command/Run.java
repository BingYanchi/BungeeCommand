package cn.yistars.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

public class Run
extends Command
implements TabExecutor {

    public Run() {
         super("bungeecommand");
    }

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length >= 2) {
			if (sender.hasPermission("bungeecommand.admin") == false) {
				sender.sendMessage(new ComponentBuilder ("无权操作").color(ChatColor.RED).create());
				return;
			}
			
			ProxiedPlayer player = ProxyServer.getInstance().getPlayer(args[0]);
			if (player == null) {
				sender.sendMessage(new ComponentBuilder ("无效玩家").color(ChatColor.RED).create());
				return;
			}
			
			String command= "";
			int x = 0;
			while (x < (args.length - 1)) {
				command += args[x + 1];
				command += " ";
				x++;
			}
			command = command.substring(0,command.length()-1);
			
			if (command == "") {
				sender.sendMessage(new ComponentBuilder ("无效指令").color(ChatColor.RED).create());
				return;
			}
			ProxyServer.getInstance().getPluginManager().dispatchCommand(player, command);
		}
	}
	
	public Iterable<String> onTabComplete(final CommandSender sender, final String[] args) {
		if(args.length == 1) {
			final List<String> list = new ArrayList<String>();
			for(final ProxiedPlayer player : BungeeCord.getInstance().getPlayers()) {
				list.add(player.getName());
			}
			return list;
		}
		return Collections.emptyList();
	}
}
