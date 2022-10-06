package me.crimp.claudius.mod.command.commands;

import me.crimp.claudius.Claudius;
import me.crimp.claudius.mod.command.Command;
import me.crimp.claudius.utils.ClientUtil;
 //ALL CREDIT GOES TO ORGINAL CREATOR OF FSYNC IIRC ITS THC FREE
public class FriendSyncCommand extends Command {
    public FriendSyncCommand() {
        super("fsync", new String[]{"<add | del | sync>","<name>","<Alais>"});
    }
    @Override
    public void execute(String[] commands) {
        switch (commands[0]){
            case "add":{
                if(commands.length == 3){
                    if(ClientUtil.addFriend(commands[1],commands[2],null)){
                        Claudius.friendManager.addFriend(commands[1]);
                        Command.sendMessage("Player " + commands[1] + " has been added to your friends list");
                    }else{
                        Command.sendMessage("Player " + commands[1] + " already exists on your friends list");
                    }
                }else if(commands.length == 2){
                    if(ClientUtil.addFriend(commands[1],commands[1],null)){
                        Command.sendMessage("Player " + commands[1] + " has been added to your friends list");
                    }else{
                        Command.sendMessage("Player " + commands[1] + " already exists on your friends list");
                    }
                }else{
                    Command.sendMessage("Wrong usage, correct syntax: fsync <add | del | sync> <user>");
                    break;
                }
            }

            case "del":{
                if(commands.length == 2){
                    if(ClientUtil.delFriend(commands[1])){
                        Claudius.friendManager.removeFriend(commands[1]);
                        Command.sendMessage("Player " + commands[1] + " has been deleted from your friends list");
                    }else{
                        Command.sendMessage("Player " + commands[1] + " doesn't exist on your friends list");
                    }
                }
                break;
            }

            case "sync":{
                ClientUtil.read();
                ClientUtil.write();
                Command.sendMessage("Friend lists have been synced");
                break;
            }

            default:{
            Command.sendMessage("Wrong usage, correct syntax: fsync <add | del | sync> <user>");
            }
        }

    }
}
