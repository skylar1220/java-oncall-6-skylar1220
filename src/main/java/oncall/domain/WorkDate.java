package oncall.domain;

import java.util.Objects;

public class WorkDate implements Comparable<WorkDate>{
    private final int date;
    private DayType dayType;
    private boolean isLegalHoliday;

    public WorkDate(int date, DayType dayType, boolean isLegalHoliday) {
        this.date = date;
        this.dayType = dayType;
        this.isLegalHoliday = isLegalHoliday;
    }

    public static WorkDate of(int date, DayType dayType) {
        return new WorkDate(date, dayType, false);
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

    public int getDate() {
        return date;
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
