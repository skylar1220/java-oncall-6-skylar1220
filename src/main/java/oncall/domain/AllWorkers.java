package oncall.domain;

public class AllWorkers {
    private final Workers weekdaysWorkers;
    private final Workers weekendsWorkers;

    public AllWorkers(Workers weekdaysWorkers, Workers weekendsWorkers) {
        this.weekdaysWorkers =weekdaysWorkers;
        this.weekendsWorkers = weekendsWorkers;
    }

    public static AllWorkers of(Workers weekdaysWorkers, Workers weekendsWorkers) {
        return new AllWorkers(weekdaysWorkers, weekendsWorkers);
    }

    public Workers getWeekdaysWorkers() {
        return weekdaysWorkers;
    }

    public Workers getWeekendsWorkers() {
        return weekendsWorkers;
    }
}
