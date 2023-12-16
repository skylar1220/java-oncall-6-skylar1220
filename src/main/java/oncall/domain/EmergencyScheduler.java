package oncall.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmergencyScheduler {
    private final List<DateWorker> totalWorkers;

    public EmergencyScheduler(List<DateWorker> totalWorkers) {
        this.totalWorkers = totalWorkers;
    }

    public static EmergencyScheduler of(EmergencyMonth emergencyMonth, AllWorkers allWorkers) {
        List<Worker> rawWeekdaysWorkers = allWorkers.getWeekdaysWorkers().getWorkers();
        List<Worker> rawWeekendsWorkers = allWorkers.getWeekendsWorkers().getWorkers();

        List<DateWorker> weekdaysWorkers = new ArrayList<>();
        List<DateWorker> weekendsWorkers = new ArrayList<>();
        List<DateWorker> totalWorkers = new ArrayList<>();

        int weekdayWorkersIndex = 0;
        int weekendWorkersIndex = 0;

        List<WorkDate> workDates = emergencyMonth.getWorkDates();
        for (int dayIndex = 0; dayIndex < workDates.size(); dayIndex++) {
            WorkDate workDate = workDates.get(dayIndex);
            if (workDate.isWeekday()) {
                Worker worker = rawWeekdaysWorkers.get(weekdayWorkersIndex);
                if (!isContinousWorker(totalWorkers, workDate, worker)) {
                    weekdaysWorkers.add(new DateWorker(workDate, worker));
                }
                weekdayWorkersIndex++;

                if (isContinousWorker(totalWorkers, workDate, worker)) {
                    Worker anotherWorker = getAnotherWorker(rawWeekdaysWorkers, weekdayWorkersIndex);
                    weekdaysWorkers.add(new DateWorker(workDate, anotherWorker));
                    weekdaysWorkers.add(new DateWorker(workDate, worker));
                    weekdayWorkersIndex++;
                }

            }
            if (!workDate.isWeekday()) {
                Worker worker = rawWeekendsWorkers.get(weekendWorkersIndex);
                if (!isContinousWorker(totalWorkers, workDate, worker)) {
                    weekdaysWorkers.add(new DateWorker(workDate, worker));
                }
                weekendWorkersIndex++;
                if (isContinousWorker(totalWorkers, workDate, worker)) {
                    Worker anotherWorker = getAnotherWorker(rawWeekendsWorkers, weekendWorkersIndex);
                    weekendsWorkers.add(new DateWorker(workDate, anotherWorker));
                    weekendsWorkers.add(new DateWorker(workDate, worker));
                    weekendWorkersIndex++;
                }

            }
            if (weekdayWorkersIndex >= rawWeekdaysWorkers.size()) {
                weekdayWorkersIndex = weekdayWorkersIndex - rawWeekdaysWorkers.size();
            }
            if (weekendWorkersIndex >= rawWeekendsWorkers.size()) {
                weekendWorkersIndex = weekendWorkersIndex - rawWeekendsWorkers.size();
            }
            totalWorkers = sortDateWorkers(weekdaysWorkers, weekendsWorkers);
        }
        return new EmergencyScheduler(totalWorkers);
    }

    private static boolean isContinousWorker(List<DateWorker> totalWorkers, WorkDate workDate, Worker worker) {
        List<DateWorker> newTotalWorkers = new ArrayList<>(totalWorkers);
        newTotalWorkers.add(new DateWorker(workDate, worker));
        // 중복된 DateWorker 확인
        for (int i = 0; i < newTotalWorkers.size() - 1; i++) {
            DateWorker current = newTotalWorkers.get(i);
            DateWorker next = newTotalWorkers.get(i + 1);

            if (current.getWorker().equals(next.getWorker())) {
                return true; // 중복된 DateWorker가 있음
            }
        }

        return false; // 중복된 DateWorker가 없음
    }

    private static List<DateWorker> sortDateWorkers(List<DateWorker> weekdaysWorkers,
                                                    List<DateWorker> weekendsWorkers) {
        List<DateWorker> totalWorkers = new ArrayList<>(weekdaysWorkers);
        totalWorkers.addAll(weekendsWorkers);
        Collections.sort(totalWorkers);
        return totalWorkers;
    }

    private static Worker getAnotherWorker(List<Worker> rawWeekdaysWorkers, int weekdayWorkersIndex) {
        return rawWeekdaysWorkers.get(weekdayWorkersIndex);
    }

    public List<DateWorker> getTotalWorkers() {
        return totalWorkers;
    }
}
