package oncall.domain;

import java.util.Objects;

public class WorkDate implements Comparable<WorkDate> {
    private final int date;
    private final DayOfTheWeek dayOfTheWeek;
    private DayType dayType;
    private final DayType originDayType;
    private boolean isLegalHoliday;

    public WorkDate(int date, DayOfTheWeek dayOfTheWeek, DayType dayType, boolean isLegalHoliday) {
        this.date = date;
        this.dayOfTheWeek = dayOfTheWeek;
        this.dayType = dayType;
        this.originDayType = dayType;
        this.isLegalHoliday = isLegalHoliday;
    }

    public static WorkDate of(int date, DayOfTheWeek dayOfTheWeek, DayType dayType) {
        return new WorkDate(date, dayOfTheWeek, dayType, false);
    }

    public boolean isSameDate(int day) {
        return this.date == day;
    }

    public void applyHoliday() {
        dayType = DayType.WEEKEND;
        isLegalHoliday = true;
    }

    public boolean isWeekday() {
        return dayType == DayType.WEEKDAY;
    }

    public boolean isOriginWeekday() {
        return originDayType == DayType.WEEKDAY;
    }

    public int getDate() {
        return date;
    }

    public DayOfTheWeek getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public boolean isLegalHoliday() {
        return isLegalHoliday;
    }

    public DayType getOriginDayType() {
        return originDayType;
    }

    @Override
    public int compareTo(WorkDate other) {
        return Integer.compare(this.date, other.date);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WorkDate workDate = (WorkDate) o;
        return date == workDate.date && isLegalHoliday == workDate.isLegalHoliday && dayType == workDate.dayType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, dayType, isLegalHoliday);
    }
}
