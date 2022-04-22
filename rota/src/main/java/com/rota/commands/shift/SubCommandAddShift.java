package com.rota.commands.shift;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.rota.entity.Chef;
import com.rota.entity.Shift;
import com.rota.repository.ChefRepository;
import com.rota.repository.ShiftRepository;

import jakarta.inject.Singleton;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
@Singleton
@Command(name = "add",
  description = "add a shift to the system",
  mixinStandardHelpOptions = true)
public class SubCommandAddShift implements Runnable {
    @Option(names = {"-t","--this-week"},description="select a date from the current week to add a shift to it")
    boolean forThisWeek;

    @Option(names = {"-n","--next-week"},description="select a date from next week to add a shift to it")
    boolean forNextWeek;

    // @Parameters(paramLabel = "specify a date in the form 'dd/mm/yyyy'",arity= "0..1")
    // String dateToAddShiftTo;

    ShiftRepository shiftRepository;
    ChefRepository chefRepository;
    ShowDate showDate = new ShowDate();
    public SubCommandAddShift(ShiftRepository shiftRepository,
                              ChefRepository chefRepository) {
        this.shiftRepository = shiftRepository;
        this.chefRepository = chefRepository;
    }

    public void theMethod(List<LocalDate> givenByShowDate) {
        
        boolean addAnotherOne = true;
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("eeee, dd MMM uuuu");
        System.out.println("\n\n\n\ntoday is: "+showDate.getTodayDate().format(fmt)+"\n\n\n");
        List<String> dayStringList = givenByShowDate.stream()
            .map(x -> {int indexPlusOne = givenByShowDate.indexOf(x)+1;
                       String ret = Integer.toString(indexPlusOne).concat(":  " + x.format(fmt));
                       return ret;})
            .collect(Collectors.toList());
        
        List<Chef> chef = new ArrayList<>();
        chefRepository.findAll().forEach(chef::add);
        List<String> chefDisplay = chef.stream()
            .map(c -> {String name = c.getF_name()+" "+c.getL_name();
                       int indexPlusOne = chef.indexOf(c)+1;
                       String toReturn = Integer.toString(indexPlusOne).concat(": "+name);
                       return toReturn;})
            .collect(Collectors.toList());
        
        while (addAnotherOne) {
            dayStringList.forEach(System.out::println);
            System.out.print("\n Select a day to add a shift to it: ");
            Integer dayAnswer = sc.nextInt();
            LocalDate shiftDate =  givenByShowDate.get(dayAnswer-1);
            
            for (CommonShifts c : CommonShifts.values()) {
                System.out.println(c.iD + ": "+c.label);
            }
            System.out.print("\nSelect a shift to assign: ");
            Integer shiftSelected = sc.nextInt();
            CommonShifts shiftEnum = CommonShifts.valueOfId(shiftSelected);
            
            chefDisplay.forEach(System.out::println);
            System.out.print("\nSelect a chef to assign this shift to: ");
            Integer chefSelected = sc.nextInt();
            Chef selectedChefEnt = chef.get(chefSelected-1);

            System.out.print("\nBreak length forecast for this shift [in hours e.g. 0.5]: ");
            Double breakDuration = sc.nextDouble();

            Shift shift = new Shift();
            shift.setDateOf(shiftDate);
            shift.setStart_time(shiftEnum.startTime);
            shift.setEnd_time(shiftEnum.endTime);
            shift.setBreak_duration_h(breakDuration);
            shift.setChef(selectedChefEnt);

            Shift s1 = shiftRepository.save(shift);
            System.out.println(DisplayShift.display(s1));
            
            System.out.print("Would you like to add another shift? [y/n]");
            String answer = sc.next();
            switch (answer) {
                case "y" -> {
                }
                case "n" -> addAnotherOne = false;
                }
        }
    }

    @Override
    public void run() {
        if (forThisWeek) {
            List<LocalDate> thisWeek = showDate.getDaysOfThisWeek();
            theMethod(thisWeek);
        }
        if (forNextWeek) {
            List<LocalDate> nextWeek = showDate.getDaysOfNextWeek();
            theMethod(nextWeek);
        }

    }

}