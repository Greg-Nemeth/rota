package com.rota.commands.shift;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public enum CommonShifts {
    EIGHT_THIRTY_TILL_FOUR(1, "08:30-16:00", LocalTime.of(8, 30), LocalTime.of(16, 00)),
    EIGHT_THIRTY_TILL_THREE(2, "08:30-15:00", LocalTime.of(8, 30), LocalTime.of(15, 00)),
    TEN_TILL_THREE(3, "10:00-15:00", LocalTime.of(10, 0), LocalTime.of(15, 00)),
    TEN_TILL_FOUR(4, "10:00-16:00", LocalTime.of(10, 0), LocalTime.of(16, 00)),
    TEN_TILL_NINE(5, "10:00-21:00", LocalTime.of(10, 0), LocalTime.of(21, 00)),
    TWELVE_TILL_FOUR(6, "12:00-16:00", LocalTime.of(12, 0), LocalTime.of(16, 00)),
    TWELVE_TILL_NINE(7, "12:00-21:00", LocalTime.of(12, 0), LocalTime.of(21, 00)),
    FOUR_TILL_NINE(8, "16:00-21:00", LocalTime.of(16, 0), LocalTime.of(21, 00)),
    FIVE_TILL_NINE(9, "17:00-21:00", LocalTime.of(17, 0), LocalTime.of(21, 00)),
    ALL_DAY(0, "08:30-21:00", LocalTime.of(8, 30), LocalTime.of(21, 00));

    private static final Map<Integer, CommonShifts> BY_ID = new HashMap<>();
    private static final Map<String, CommonShifts> BY_LABEL = new HashMap<>();
    private static final Map<LocalTime, CommonShifts> BY_START_TIME = new HashMap<>();
    private static final Map<LocalTime, CommonShifts> BY_END_TIME = new HashMap<>();

    static {
        for (CommonShifts c : values()) {
            BY_ID.put(c.iD, c);
            BY_LABEL.put(c.label, c);
            BY_START_TIME.put(c.startTime, c);
            BY_END_TIME.put(c.endTime, c);
        }
    }
    public final Integer iD;
    public final String label;
    public final LocalTime startTime;
    public final LocalTime endTime;

    private CommonShifts(Integer iD, String label, LocalTime startTime, LocalTime endTime) {
        this.iD = iD;
        this.label = label;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static CommonShifts valueOfId(Integer iD) {
        return BY_ID.get(iD);
    }
    public static CommonShifts valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }

    public static CommonShifts valueOfStartTime(LocalTime startTime) {
        return BY_START_TIME.get(startTime);
    }

    public static CommonShifts valueOfEndTime(LocalTime endTime) {
        return BY_END_TIME.get(endTime);
    }

}