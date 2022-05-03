package com.rota.commands.shift;


import picocli.CommandLine.Command;

@Command(name = "shift",
   description= "manage individual shifts here",
   mixinStandardHelpOptions = true,
   subcommands={SubCommandAddShift.class,
                SubCommandShowShift.class,
                SubCommandDeleteShift.class,
                /*SubCommandUpdateShift.class*/})
public class ShiftCommand implements Runnable {
   @Override
   public void run() {
       System.out.println("Please specify a subcommand: add|update|delete|show");
   } 
}