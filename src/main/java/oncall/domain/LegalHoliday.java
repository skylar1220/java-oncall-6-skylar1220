package oncall.domain;

public class LegalHoliday {
    private final Month month;
    private final int day;

    public LegalHoliday(Month month, int day) {
        this.month = month;
        this.day = day;
    }

    public static LegalHoliday of(int month, int day) {
        return new LegalHoliday(Month.from(month), day);
    }
}
