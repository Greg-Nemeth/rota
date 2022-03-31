package com.rota.commands.chef;

import picocli.CommandLine.Command;

@Command(name = "chef",
   description= "manage chefs",
   mixinStandardHelpOptions = true,
   subcommands={SubCommandAddChef.class,
                SubCommandShowChef.class,
                SubCommandDeleteChef.class})
public class ChefCommand implements Runnable {
   @Override
   public void run() {
       System.out.println("Please specify a subcommand: add|update|delete|show");
   } 
}