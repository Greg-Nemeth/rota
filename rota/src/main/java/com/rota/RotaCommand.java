package com.rota;


import com.rota.commands.chef.ChefCommand;
import io.micronaut.configuration.picocli.PicocliRunner;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "rota", 
  header = {
   "________          ________          _________        ________ ",    
   "|\\   __  \\        |\\   __  \\        |\\___   ___\\     |\\   __  \\ ",   
   " \\ \\  \\|\\  \\       \\ \\  \\|\\  \\       \\|___ \\  \\_|     \\ \\  \\|\\  \\ ",  
   "  \\ \\   _  _\\       \\ \\  \\\\\\  \\           \\ \\  \\       \\ \\   __  \\  ",
   "   \\ \\  \\\\  \\|       \\ \\  \\\\\\  \\           \\ \\  \\       \\ \\  \\ \\  \\ ",
   "    \\ \\__\\\\ _\\        \\ \\_______\\           \\ \\__\\       \\ \\__\\ \\__\\",
   "     \\|__|\\|__|        \\|_______|            \\|__|        \\|__|\\|__|"
  },
  description = "...",
  mixinStandardHelpOptions = true,
   subcommands=ChefCommand.class)
public class RotaCommand implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(RotaCommand.class, args);
    }

    @Override
    public void run() {
        // business logic here
        if (verbose) {
            System.out.println("Hi!");
        }
        
    }
}
