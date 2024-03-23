package lusiiplugin.commands;

import net.minecraft.core.net.command.Command;
import net.minecraft.core.net.command.CommandHandler;
import net.minecraft.core.net.command.CommandSender;

public class PingCommand extends Command {
	public PingCommand() {
		super("ping");
	}

	public boolean execute(CommandHandler handler, CommandSender sender, String[] args) {
		sender.sendMessage("Pong!");
		return true;
	}


	public boolean opRequired(String[] args) {
		return false;
	}

	public void sendCommandSyntax(CommandHandler handler, CommandSender sender) {

	}
}
