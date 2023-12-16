package oncall.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Month {
    JANUARY(1, daysWith31()),
    FEBRUARY(2, daysWith28()),
    MARCH(3, daysWith31()),
    APRIL(4, daysWith30()),
    MAY(5, daysWith31()),
    JUNE(6, daysWith30()),
    JULY(7, daysWith31()),
    AUGUST(8, daysWith31()),
    SEPTEMBER(9, daysWith30()),
    OCTOBER(10, daysWith31()),
    NOVEMBER(11, daysWith30()),
    DECEMBER(12, daysWith31());

    private final int name;
    private final List<Integer> days;

    Month(int name, List<Integer> days) {
        this.name = name;
        this.days = days;
    }

    private static List<Integer> daysWith30() {
        List<Integer> days = new ArrayList<>();
        for (int date = 1; date <= 30; date++) {
            days.add(date);
        }
        return days;
    }

    private static List<Integer> daysWith31() {
        List<Integer> days = new ArrayList<>();
        for (int date = 1; date <= 31; date++) {
            days.add(date);
        }
        return days;
    }

    private static List<Integer> daysWith28() {
        List<Integer> days = new ArrayList<>();
        for (int date = 1; date <= 28; date++) {
            days.add(date);
        }
        return days;
    }

    public static Month from(int input) {
        return Arrays.stream(values())
                .filter(month -> month.name == input)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 달입니다."));
    }
}
