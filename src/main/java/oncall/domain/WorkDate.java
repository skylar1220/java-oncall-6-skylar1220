package oncall.domain;

public class WorkDate {
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
}
