package com.rota.commands.shift;

import java.util.Scanner;

import com.rota.entity.Shift;
import com.rota.repository.ShiftRepository;

import jakarta.inject.Singleton;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Singleton
@Command(name = "delete",
  description = "use this command to delete a shift from the database after acquiring its id via 'rota shift show...'",
  mixinStandardHelpOptions = true)
public class SubCommandDeleteShift implements Runnable {

    @Parameters(paramLabel="specifiy id of shift to be deleted")
    Long shift_id;

    ShiftRepository shiftRepository;

    public SubCommandDeleteShift(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    @Override
    public void run() {

        Scanner sc = new Scanner(System.in);
        Shift shift = shiftRepository.findById(shift_id).get();
        System.out.println(DisplayShift.display(shift));
        String shiftInfo = shift.getDateOf()+" "+shift.getStartTime()+"-"+shift.getEndTime()+" "+shift.getChef().getF_name();
        System.out.println("Are you sure you want to delete "+shiftInfo+" from the system?  [y/n]");
        String answer = sc.next();
        switch (answer) {
            case "y" -> {
                System.out.println("\n\n\n\n\n\n");
                shiftRepository.deleteById(shift_id);
                System.out.println(shiftInfo+ " has been removed from the system");
            }
            default -> System.out.println("Deletion cancelled, shift still in database");
        }
    }
}