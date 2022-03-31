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

    @Parameters(paramLabel="specify id of chef to show", arity="0..1")
    Long chef_id;

    @Option(names = {"-l", "--list"}, description = "list all chefs name and id")
    private boolean listingRequired;

    @Override
    public void run() {
        if (listingRequired) {
            Iterable<Chef> chefs = chefRepository.findAll();
            System.out.println("\n\n\n\n\n");
            for (Chef chef : chefs) {
                System.out.println();
                System.out.println(chef.getChef_id()+": "+chef.getF_name()+" "+chef.getL_name());
                System.out.println("____________________________________");
            }
        }
        else {
            Optional<Chef> chefyChef = chefRepository.findById(chef_id);
            if (chefyChef.isPresent()) {
                System.out.println(Display.display(chefyChef.get()));
            }
            else {
                System.out.println("No chef with such id found, try listing them with --list to get an id");
            }
        }
    }
}