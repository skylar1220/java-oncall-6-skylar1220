package oncall.view;

import java.util.List;
import oncall.domain.DateWorker;
import oncall.domain.EmergencyMonth;
import oncall.domain.EmergencyScheduler;
import oncall.view.printer.Printer;

public class OutputView {
    private static final String ERROR_MESSAGE_FORMAT = "[ERROR] ";
    private final Printer printer;

    public OutputView(Printer printer) {
        this.printer = printer;
    }

    public void printEmergencySchedule(EmergencyMonth emergencyMonth, EmergencyScheduler emergencyScheduler) {
        List<DateWorker> totalWorkers = emergencyScheduler.getTotalWorkers();
        int month = emergencyMonth.getMonth().getName();
        totalWorkers.forEach(dateWorker -> printDateWorker(month, dateWorker));
    }

    private void printDateWorker(int month, DateWorker dateWorker) {
        int date = dateWorker.getWorkDate().getDate();
        String dayOfTheWeek = dateWorker.getWorkDate().getDayOfTheWeek().getKoreanName();
        if (dateWorker.getWorkDate().isLegalHoliday() && dateWorker.getWorkDate().isWeekday()) {
            dayOfTheWeek = dayOfTheWeek + "(휴일)";
        }
        String worker = dateWorker.getWorker().getWorkerName();
        printer.printLine("%d월 %d일 %s %s", month, date, dayOfTheWeek, worker);
    }

    public void printExceptionMessage(String message) {
        printer.printLine(ERROR_MESSAGE_FORMAT + message);
        printer.printEmptyLine();
    }
}
