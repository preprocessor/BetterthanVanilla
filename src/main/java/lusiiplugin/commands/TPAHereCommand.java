package lusiiplugin.commands;

import lusiiplugin.LusiiPlugin;
import lusiiplugin.utils.PlayerData;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.net.command.Command;
import net.minecraft.core.net.command.CommandHandler;
import net.minecraft.core.net.command.CommandSender;

import java.util.Objects;

public class TPAHereCommand extends Command {
	public TPAHereCommand() {
		super("tpahere", "tphere", "tph");
	}

	public boolean opRequired(String[] args) {
		return !LusiiPlugin.TPACommand;
	}

	public void sendCommandSyntax(CommandHandler handler, CommandSender sender) {
		sender.sendMessage("§3/tpahere §4<username>");
		sender.sendMessage("§5Request a player to teleport to you");
	}

	public boolean execute(CommandHandler handler, CommandSender sender, String[] args) {
		if (sender.isConsole()) return true;

		EntityPlayer p = sender.getPlayer();
		PlayerData.TPInfo ptpInfo = PlayerData.get(p).tpInfo();
		if (p.score < LusiiPlugin.TPACost) {
			sender.sendMessage("§4You do not have enough points to use this command! You need §1" + (LusiiPlugin.TPACost - p.score) + "§4 more points!");
			return true;
		}
		if (p.dimension != 0) {
			sender.sendMessage("§4You may only use this in the overworld!");
			return true;
		}
		if (args.length == 0) {
			sender.sendMessage("§4You must specify a player!");
			return true;
		}
		if (!ptpInfo.canTP() && !sender.isAdmin()) {
			int waitTime = ptpInfo.cooldown();
			sender.sendMessage("§4Teleport available in §1" + waitTime + "§4 seconds.");
			return true;
		}

		String target = args[0];
		if (Objects.equals(target, "wyspr")) target = "wyspr_"; // ;)

		EntityPlayer targetPlayer = handler.getPlayer(target);
		if (targetPlayer == null) {
			sender.sendMessage("§4Player not found!");
			return true;
		}

		PlayerData.TPInfo targettpInfo = PlayerData.get(targetPlayer).tpInfo();

		boolean isOnlyRequest = targettpInfo.sendRequest(p.username, PlayerData.TPInfo.RequestType.TPAHERE);
		if (isOnlyRequest) {
			sender.sendMessage("§4Sent a request to " + targetPlayer.getDisplayName());
			targetPlayer.addChatMessage("§4" + p.username + "§1 has sent you a request to teleport to them.");
			targetPlayer.addChatMessage("§5/tpyes §1to accept, §e/tpno §1to deny.");
		} else {
			sender.sendMessage("§4You already have a pending request for " + target);
		}

		return true;
	}
}
