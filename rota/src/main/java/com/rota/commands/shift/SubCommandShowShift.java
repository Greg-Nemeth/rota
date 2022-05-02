package com.rota.commands.shift;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.rota.entity.Shift;
import com.rota.repository.ShiftRepository;

import jakarta.inject.Inject;
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
    @Inject ShowDate showDate;

    
    public SubCommandShowShift(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;

    }
    
   
    @Override
    public void run() {
        
        if (showThisWeek) {
            List<LocalDate> thisWeek = showDate.getDaysOfThisWeek();
            List<Shift> shifts = shiftRepository.findAllByDateOfBetween(thisWeek.get(0), thisWeek.get(thisWeek.size()-1));
            List<String> lines = DisplayWeek.displayWeeklyRota(shifts, thisWeek);
            lines.forEach(System.out::println);            
        }
        else if (showNextWeek) {
            List<LocalDate> nextWeek = showDate.getDaysOfNextWeek();
            List<Shift> shifts = shiftRepository.findAllByDateOfBetween(nextWeek.get(0), nextWeek.get(nextWeek.size()-1));
            List<String> lines = DisplayWeek.displayWeeklyRota(shifts, nextWeek);
            lines.forEach(System.out::println);  
        }
        else {
            
            Optional<Shift> shift = shiftRepository.findById(shift_id); 
            shift.ifPresentOrElse(
                (value) -> System.out.println(DisplayShift.display(value)),
                () -> System.out.println("something went wrong"));
            }
        
    }
    
}
