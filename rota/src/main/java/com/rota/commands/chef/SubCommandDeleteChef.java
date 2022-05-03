package com.rota.commands.chef;


import java.util.Scanner;

import com.rota.entity.Chef;
import com.rota.repository.ChefRepository;

import jakarta.inject.Singleton;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Singleton
@Command(name = "delete",
  description = "use this command to delete a chef from the database after acquiring its id via 'rota chef show...'",
  mixinStandardHelpOptions = true)
public class SubCommandDeleteChef implements Runnable {

    @Parameters(paramLabel="specifiy id of chef to be deleted")
    Long chef_id;

    ChefRepository chefRepository;
    public SubCommandDeleteChef(ChefRepository chefRepository) {
        this.chefRepository = chefRepository;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        Chef chef = chefRepository.findById(chef_id).get();
        String chefName = chef.getF_name()+" "+chef.getL_name();
        System.out.println("Are you sure you want to delete "+chefName+" from the system?  [y/n]");
        String answer = sc.next();
        switch (answer) {
            case "y" -> {
                System.out.println("\n\n\n\n\n\n");
                chefRepository.deleteById(chef_id);
                System.out.println(chefName+ " has been removed from the system");
            }
            default -> System.out.println("That lucky bastard lives to see another shift in this hellhole :)");
        }
        

    }
}