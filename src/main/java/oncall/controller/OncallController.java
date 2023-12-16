package oncall.controller;

import java.util.function.Function;
import java.util.function.Supplier;
import oncall.domain.AllWorkers;
import oncall.domain.EmergencyMonth;
import oncall.domain.LegalHolidays;
import oncall.domain.Workers;
import oncall.view.InputView;
import oncall.view.OutputView;

public class OncallController {
    private final InputView inputView;
    private final OutputView outputView;

    public OncallController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        LegalHolidays legalHolidays = LegalHolidays.init();
        EmergencyMonth emergencyMonth = readWithRetry(inputView::inputEmergencyMonth);
        emergencyMonth.applyLegalHolidays(legalHolidays);

        AllWorkers allWorkers = readWithRetry(this::getWorkers);
    }

    private AllWorkers getWorkers() {
        Workers weekdaysWorkers = inputView.inputWeekdaysWorkers();
        Workers weekendsWorkers = inputView.inputWeekendsWorkers();
        return AllWorkers.of(weekdaysWorkers, weekendsWorkers);
    }

    private <T> T readWithRetry(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return readWithRetry(supplier);
        }
    }

    private <T, R> R readWithRetry(Function<T, R> function, T input) {
        try {
            return function.apply(input);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return readWithRetry(function, input);
        }
    }
}
