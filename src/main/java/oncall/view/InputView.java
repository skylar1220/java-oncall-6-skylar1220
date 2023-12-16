package oncall.view;

import oncall.common.Symbol;
import oncall.domain.EmergencyMonth;
import oncall.domain.Workers;
import oncall.util.converter.Converter;
import oncall.view.printer.Printer;
import oncall.view.reader.Reader;
import oncall.view.validator.InputValidator;

public class InputView {
    public static final String EMERGENCY_MONTH_SEPARATOR = Symbol.COMMA;
    private static final String WORKERS_SEPARATOR = Symbol.COMMA;
    private final Reader reader;
    private final Printer printer;
    private final InputValidator validator;

    private InputView(Reader reader, Printer printer, InputValidator validator) {
        this.reader = reader;
        this.printer = printer;
        this.validator = validator;
    }

    public static InputView of(Reader reader, Printer printer) {
        return new InputView(reader, printer, InputValidator.getInstance());
    }

    public EmergencyMonth inputEmergencyMonth() {
        printer.printInOneLine("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        String emergencyMonth = reader.readLine();
        validator.validateEmergencyMonth(emergencyMonth, "배정할 월과 시작 요일");
        return EmergencyMonth.of(
                Converter.convertToInt(Converter.splitValue(EMERGENCY_MONTH_SEPARATOR, 0, emergencyMonth)),
                Converter.splitValue(EMERGENCY_MONTH_SEPARATOR, 1, emergencyMonth));
    }

    public Workers inputWeekdaysWorkers() {
        printer.printInOneLine("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        String weekdaysWorkers = reader.readLine();
        validator.validateWeekdaysWorkers(weekdaysWorkers, "평일 비상 근무 사원");
        return Workers.from(Converter.splitToTrimedList(WORKERS_SEPARATOR, weekdaysWorkers));
    }

    public Workers inputWeekendsWorkers() {
        printer.printInOneLine("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        String weekendsWorkers = reader.readLine();
        validator.validateWeekdaysWorkers(weekendsWorkers, "휴일 비상 근무 사원");
        return Workers.from(Converter.splitToTrimedList(WORKERS_SEPARATOR, weekendsWorkers));
    }

//    public Template inputTemplate() {
//        printer.printLine("");
//        String template = reader.readLine();
//        validator.validate(template, "템플릿");
//        return new Template();
//    }
}
