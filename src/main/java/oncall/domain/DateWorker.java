package oncall.domain;

import java.util.Objects;

public class DateWorker implements Comparable<DateWorker>{
    private final WorkDate workDate;
    private final Worker worker;

    public DateWorker(WorkDate workDate, Worker worker) {
        this.workDate = workDate;
        this.worker = worker;
    }

    public WorkDate getWorkDate() {
        return workDate;
    }

    public Worker getWorker() {
        return worker;
    }

    @Override
    public int compareTo(DateWorker other) {
        return this.workDate.compareTo(other.workDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DateWorker that = (DateWorker) o;
        return Objects.equals(workDate, that.workDate) && Objects.equals(worker, that.worker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workDate, worker);
    }
}
