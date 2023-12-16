package oncall.domain;

import java.util.ArrayList;
import java.util.List;

public class EmergencyMonth {
    private final List<WorkDate> workDates;
    private final Month month;

    public EmergencyMonth(Month month, List<WorkDate> workDates) {
        this.month = month;
        this.workDates = workDates;
    }

    public static EmergencyMonth of(int rawMonth, String rawStartDay) {
        Month month = Month.from(rawMonth);
        DayOfTheWeek startDay = DayOfTheWeek.from(rawStartDay);
        List<Integer> monthDays = month.getDays();
        List<WorkDate> workDates = new ArrayList<>();

        for (int index = 0; index < monthDays.size(); index++) {
            int offsetValue = index + startDay.getOffset() / 7;
            DayOfTheWeek dayOfTheWeek = DayOfTheWeek.fromOffset(offsetValue);

            if (isWeekdays(offsetValue)) {
                WorkDate workDate = WorkDate.of(index + 1, dayOfTheWeek,  DayType.WEEKDAY);
                workDates.add(workDate);
            }
            if (!isWeekdays(offsetValue)) { // 여기 수정 주말 오프셋
                WorkDate workDate = WorkDate.of(index + 1,dayOfTheWeek,  DayType.WEEKEND);
                workDates.add(workDate);
            }
        }
        return new EmergencyMonth(month, workDates);
    }

    private static boolean isWeekdays(int offsetValue) {
        return offsetValue >= 0 && offsetValue <= 4;
    }

    public void applyLegalHolidays(LegalHolidays legalHolidays) {
        workDates.stream()
                .filter(workDate -> legalHolidays.contains(month, workDate))
                .forEach(WorkDate::applyHoliday);
    }

    public List<WorkDate> getWorkDates() {
        return workDates;
    }

    public Month getMonth() {
        return month;
    }
}
