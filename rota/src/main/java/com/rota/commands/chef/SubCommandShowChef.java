package com.rota.commands.chef;

import java.util.Optional;

import com.rota.entity.Chef;
import com.rota.repository.ChefRepository;

import jakarta.inject.Singleton;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Singleton
@Command(name = "show",
  description = "display information about chefs",
  mixinStandardHelpOptions = true)
public class SubCommandShowChef implements Runnable {

    ChefRepository chefRepository;

    public SubCommandShowChef(ChefRepository chefRepository) {
        this.chefRepository = chefRepository;
    }

    @Parameters
    Long chef_id;

    @Option(names = {"-l", "--list"}, description = "list all chefs name and id")
    private boolean listingRequired;

    @Override
    public void Run() {
        if (listingRequired) {
            Iterable<Chef> chefs = chefRepository.findAll();
            for (Chef chef : chefs) {
                System.out.println(chef.getChef_id()+": "+chef.getF_name()+" "+chef.getL_name());
                System.out.println("____________________________________");
            }
        }
        else {
            Optional<Chef> chefyChef = chefRepository.findById(chef_id);
            if (chefyChef.isPresent()) {
                Display.display(chefyChef.get());
            }
            else {
                System.out.println("No chef with such id found, try listing them with --list to get an id");
            }
        }
    }
}