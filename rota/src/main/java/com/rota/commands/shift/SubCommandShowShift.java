package com.rota.commands.shift;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.rota.entity.Chef;
import com.rota.entity.Shift;
import com.rota.repository.ChefRepository;
import com.rota.repository.ShiftRepository;

import jakarta.inject.Singleton;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Singleton
@Command(name = "show",
  description = "display information about shifts",
  mixinStandardHelpOptions = true)
public class SubCommandShowShift implements Runnable {

    @Parameters(paramLabel = "specify id of shift to show", arity="0..1")
    Long shift_id;

    @Option(names = {"-t","--this-week"}, description = "Show this week's shifts")
    private boolean showThisWeek;

    @Option(names = {"-n","--next-week"}, description = "Show next week's shifts")
    private boolean showNextWeek;
    
    ShiftRepository shiftRepository;
    ChefRepository chefRepository;
    ShowDate showDate;
    
    public SubCommandShowShift(ShiftRepository shiftRepository, ChefRepository chefRepository) {
        this.shiftRepository = shiftRepository;
        this.chefRepository = chefRepository;
    }
    
    
   
    @Override
    public void run() {
        List<Shift> shiftList = new ArrayList<>();
        shiftRepository.findAll().forEach(shiftList::add);
        List<Chef> chefList = new ArrayList<>();
        chefRepository.findAll().forEach(chefList::add);
       
        if (showThisWeek) {
            List<LocalDate> thisWeek = showDate.getDaysOfThisWeek();
            System.out.println(DisplayWeek.displayWeeklyRota(thisWeek, chefList, shiftList));
        }
        if (showNextWeek) {
            List<LocalDate> nextWeek = showDate.getDaysOfNextWeek();
            System.out.println(DisplayWeek.displayWeeklyRota(nextWeek, chefList, shiftList));
        }
        else {
            Shift shift = shiftRepository.findById(shift_id).get();
            Chef chef = chefRepository.findById(shift.getChef()).get();
            DisplayShift.display(shift, chef);
        }
        
    }
    
}
