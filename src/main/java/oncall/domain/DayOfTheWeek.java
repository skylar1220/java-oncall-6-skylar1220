package oncall.domain;

import java.util.Arrays;

public enum DayOfTheWeek {
    MONDAY("월", 0),
    TUESDAY("화", 1),
    WEDNESDAY("수", 2),
    THURSDAY("목", 3),
    FRIDAY("금", 4),
    SATURDAY("토", 0),
    SUNDAY("일", 1);

    private final String koreanName;
    private final int offset;

    DayOfTheWeek(String koreanName, int offset) {
        this.koreanName = koreanName;
        this.offset = offset;
    }

    public static DayOfTheWeek from(String input) {
        return Arrays.stream(values())
                .filter(dayOfTheWeek -> dayOfTheWeek.koreanName.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 요일입니다."));
    }

    public static DayOfTheWeek fromOffset(int input) {
        return Arrays.stream(values())
                .filter(dayOfTheWeek -> dayOfTheWeek.offset == input)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 요일입니다."));
    }

    public String getKoreanName() {
        return koreanName;
    }

    public int getOffset() {
        return offset;
    }
}
