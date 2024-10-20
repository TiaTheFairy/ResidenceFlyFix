package code.net.ttfl.residenceFlyFix;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler implements CommandExecutor, TabCompleter {
    private ResidenceFlyFix plugin;

    public CommandHandler(ResidenceFlyFix plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        /*
         *
         * 指令头不算长度！！ args里没有指令头！！
         *
         * */

        if (!label.equalsIgnoreCase("residenceflyfix") && !label.equalsIgnoreCase("rff")) {
            return false;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("reload") && sender.hasPermission("residenceflyfix.admin")) {
            plugin.reloadConfig();
            return true;
        }

        else{
            sender.sendMessage("Command usage:");
            sender.sendMessage("/rff reload --- Reload configuration");
        }
        return true;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();
        if(args.length == 1){
            list.add("reload");
            return list;
        }
        return null;
    }
}
