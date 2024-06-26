package lusiiplugin.commands;

import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.net.command.Command;
import net.minecraft.core.net.command.CommandHandler;
import net.minecraft.core.net.command.CommandSender;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.player.inventory.InventoryPlayer;

public class InvseeCommand extends Command {
	public InvseeCommand() {
		super("invsee", "openinv");
	}

	public boolean execute(CommandHandler handler, CommandSender sender, String[] args) {
		EntityPlayer player = handler.getPlayer(args[0]);
		if (player == null) {
			return false;
		}

		IInventory inventory = new InventoryPlayer(player).player.inventory;
		sender.getPlayer().displayGUIChest(inventory);
		return true;
	}


	public boolean opRequired(String[] args) {
		return true;
	}



	public void sendCommandSyntax(CommandHandler handler, CommandSender sender) {
		if (sender.isConsole() || sender.isAdmin()) {
			sender.sendMessage("§3/invsee §4<username>");
			sender.sendMessage("§5View the inventory of another player");
		}
	}
}
