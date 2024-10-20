package code.net.ttfl.residenceFlyFix;

import code.net.ttfl.residenceFlyFix.listener.PlayerCommandEvent;
import code.net.ttfl.residenceFlyFix.listener.PlayerFlyEvent;
import code.net.ttfl.residenceFlyFix.listener.PlayerMoveEvent;
import code.net.ttfl.residenceFlyFix.listener.PlayerTeleportEvent;
import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.containers.Flags;
import com.bekvon.bukkit.residence.containers.ResidencePlayer;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import com.bekvon.bukkit.residence.protection.PlayerManager;
import com.bekvon.bukkit.residence.protection.ResidenceManager;
import com.bekvon.bukkit.residence.protection.ResidencePermissions;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemorySection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class ResidenceFlyFix extends JavaPlugin {
    private PlayerManager playerManager = null;
    private ResidenceManager residenceManager = null;

    @Override
    public void onEnable() {
        if(getServer().getPluginManager().isPluginEnabled("Residence")){
            playerManager = Residence.getInstance().getPlayerManager();
            residenceManager = Residence.getInstance().getResidenceManager();
        }
        else{
            getServer().getLogger().severe("Can not find Residence! ResidenceFlyFix will be disabled now!");
            getServer().getPluginManager().disablePlugin(this);
        }

        saveDefaultConfig();
        reloadConfig();
        registerEventListeners();

        CommandHandler commandHandler = new CommandHandler(this);
        getCommand("residenceflyfix").setExecutor(commandHandler);
        getCommand("rff").setExecutor(commandHandler);
        getCommand("residenceflyfix").setTabCompleter(commandHandler);
        getCommand("rff").setTabCompleter(commandHandler);

        ConfigurationSection section = this.getConfig().getConfigurationSection("message");
        System.out.println(section);
        System.out.println(section.getString("off"));
    }

    @Override
    public void onDisable() {

    }

    public void registerEventListeners(){
        getServer().getPluginManager().registerEvents(new PlayerMoveEvent(this), this);
        getServer().getPluginManager().registerEvents(new PlayerCommandEvent(this), this);
        getServer().getPluginManager().registerEvents(new PlayerFlyEvent(this), this);
    }

    public boolean canFly(Player player){
        ClaimedResidence res = residenceManager.getByLoc(player.getLocation());
        if(res == null){
            return false;
        }
        ResidencePermissions perms = res.getPermissions();
        return perms.playerHas(player, Flags.fly, true);
    }

    public void sendMessage(String key, Player player){
        ConfigurationSection section = this.getConfig().getConfigurationSection("message");
        System.out.println(section);
        System.out.println(section.getString("off"));
//        System.out.println(this.getConfig());
//        System.out.println(key);
//        String message = getConfig().getString("message." + key);
//        System.out.println(message);
//
//        if(message.contains("&")){
//            message = ChatColor.translateAlternateColorCodes('&', message);
//        }
//
//        player.sendMessage(message);
    }
}
