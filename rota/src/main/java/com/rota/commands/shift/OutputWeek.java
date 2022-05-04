package com.rota.commands.shift;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static java.util.stream.IntStream.range;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.rota.entity.Chef;
import com.rota.entity.Shift;


public class OutputWeek {
    
    private static final String userHome = System.getProperty("user.home");
    

    public static void writeToFile(List<LocalDate> week, List<Shift> shifts) {

        DateTimeFormatter fmtDay = DateTimeFormatter.ofPattern("eeee");
        DateTimeFormatter fmtDate = DateTimeFormatter.ofPattern("dd MMM uuuu");
        DateTimeFormatter fmtTime = DateTimeFormatter.ofPattern("HH:mm");

        Collections.sort(week);

        List<String> datesAsString = week.stream()
            .map(x -> x.format(fmtDate))
            .collect(Collectors.toList());
        List<String> daysList = week.stream()
            .map(y -> y.format(fmtDay))
            .collect(Collectors.toList());

        XSSFWorkbook workbook = new XSSFWorkbook();
        
        XSSFSheet sheet = workbook.createSheet(week.get(0).format(fmtDate)+"-"+week.get(6).format(fmtDate));

        Row dates = sheet.createRow(0);
        XSSFCellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        dateCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
        range(0, 9)
            .forEach(
                i -> {Cell dateCell = dates.createCell(i);
                      dateCell.setCellStyle(dateCellStyle);
                      if (i>0) {
                        dateCell.setCellValue(daysList.get(i-1)+"\n"+datesAsString.get(i-1));
                        }
                }
            );
            
        Map<Chef, List<Shift>> shiftsByChef = shifts.stream()
            .collect(Collectors.groupingBy(
                Shift::getChef
                ));

        XSSFCellStyle cheFCellStyle = workbook.createCellStyle();
        cheFCellStyle.setFillForegroundColor(IndexedColors.CORAL.getIndex());
        cheFCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        List<List<String>> content = shiftsByChef.entrySet()
            .stream()
            .map(e -> {
                List<String> chefsWeek = new ArrayList<>(8);
                chefsWeek.add(0, e.getKey().getF_name()+"\n"+e.getKey().getL_name());
                range(1, 8)
                    .boxed()
                    .forEach(i -> {
                        LocalDate target = week.get(i-1);
                        e.getValue().stream()
                        .filter(s -> s.getDateOf().equals(target))
                        .findFirst()
                        .ifPresentOrElse(
                            (value) -> {String timeToString = value.getStartTime().format(fmtTime)
                                                    + " - " + value.getEndTime().format(fmtTime);
                                        chefsWeek.add(i, timeToString);}, 
                            () -> {chefsWeek.add(i, "off");}
                            );
                    });
                return chefsWeek;})
                .collect(Collectors.toList());

        range(1, content.size()+1)
            .forEach(i -> {
                Row chefsRow = sheet.createRow(i);
                range(0, 8)
                    .forEach(j -> {
                        String filling = content.get(i).get(j);
                        Cell chefCell = chefsRow.createCell(j);
                        chefCell.setCellValue(filling);
                        if (j==0) {
                            chefCell.setCellStyle(cheFCellStyle);
                        }
                    });
            });

        String filePath = userHome + "/Documents/Rotas";
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(filePath);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
        
    }
    
}