package oncall.domain;

import java.util.ArrayList;
import java.util.List;

public class LegalHolidays {
    private final List<LegalHoliday> legalHolidays;

    public LegalHolidays(List<LegalHoliday> legalHolidays) {
        this.legalHolidays = legalHolidays;
    }

    public static LegalHolidays init() {
        List<LegalHoliday> legalHolidays = new ArrayList<>();
        legalHolidays.add(LegalHoliday.of(1, 1));
        legalHolidays.add(LegalHoliday.of(3, 1));
        legalHolidays.add(LegalHoliday.of(5, 5));
        legalHolidays.add(LegalHoliday.of(6, 6));
        legalHolidays.add(LegalHoliday.of(8, 15));
        legalHolidays.add(LegalHoliday.of(10, 3));
        legalHolidays.add(LegalHoliday.of(10, 9));
        legalHolidays.add(LegalHoliday.of(12, 25));
        return new LegalHolidays(legalHolidays);
    }

    public boolean contains(Month month, WorkDate workDate) {
        return legalHolidays.stream()
                .anyMatch(legalHoliday -> legalHoliday.isSameWith(month, workDate));
    }
}
