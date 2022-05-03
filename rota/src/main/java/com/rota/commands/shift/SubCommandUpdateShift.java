package com.rota.commands.shift;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import com.rota.entity.Chef;
import com.rota.entity.Shift;
import com.rota.repository.ChefRepository;
import com.rota.repository.ShiftRepository;

import jakarta.inject.Singleton;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Singleton
@Command(name = "update",
  description = "update shifts' details",
  mixinStandardHelpOptions = true)
public class SubCommandUpdateShift implements Runnable{


    @Parameters(paramLabel="specify id of shift to be updated")
    Long shift_id;

    ChefRepository chefRepository;

    ShiftRepository shiftRepository;

    public SubCommandUpdateShift(ShiftRepository shiftRepository, ChefRepository chefRepository) {
        this.shiftRepository = shiftRepository;
        this.chefRepository = chefRepository;
    }
    @Transactional
    @Override
    public void run() {
        DateTimeFormatter fmtDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter fmtTime = DateTimeFormatter.ofPattern("HH:mm");
        Shift shift = shiftRepository.findById(shift_id).get();
        Scanner sc = new Scanner(System.in);
        System.out.println(DisplayShift.display(shift));
        List<String> attributes = Arrays.asList("1: date", "2: start",  "3: finish","4: break", "5: chef");
        System.out.println("Which detail would you like to update? use the numbers to select an option!\n");
        attributes.forEach(System.out::println);
        System.out.print("select option:");
        Integer opt = sc.nextInt();
        switch (opt) {
            case 1 -> { System.out.print("\nPlease enter date ['dd-MM-yyyy']: ");
                        String date = sc.next();
                        shift.setDateOf(LocalDate.parse(date, fmtDate));
                        shiftRepository.update(shift);
                        System.out.println(DisplayShift.display(shift)); 
            }
            case 2 -> { System.out.print("\nPlease enter new start time ['HH:mm']: ");
                        String startTime = sc.next();
                        shift.setStartTime(LocalTime.parse(startTime, fmtTime));
                        shiftRepository.update(shift);
                        System.out.println(DisplayShift.display(shift));   
            }
            case 3 -> { System.out.print("\nPlease enter new finishing time ['HH:mm']: ");
                        String endTime = sc.next();
                        shift.setStartTime(LocalTime.parse(endTime, fmtTime));
                        shiftRepository.update(shift);
                        System.out.println(DisplayShift.display(shift)); 
            }
            case 4 -> { System.out.print("\nPlease enter new break duration: ");
                        Double breakDuration = sc.nextDouble();
                        shift.setBreakDurationInHours(breakDuration);
                        shiftRepository.update(shift);
                        System.out.println(DisplayShift.display(shift));  
            }
            case 5 -> { Iterable<Chef> chefs = chefRepository.findAll();
                        chefs.forEach( c -> {
                            System.out.println("id :"+c.getChef_id()+"\t"+c.getF_name()+" "+c.getL_name());
                        }
                        );
                        System.out.print("\nPlease select a new chef: ");
                        Long chef_id = sc.nextLong();
                        Chef chef = StreamSupport.stream(chefs.spliterator(), false)
                            .filter(c -> c.getChef_id().equals(chef_id))
                            .findFirst().get();
                        shift.setChef(chef);
                        shiftRepository.update(shift);
                        System.out.println(DisplayShift.display(shift));
            }
            default -> System.out.println("Please try again and select a valid option");
        }
        

    }
}