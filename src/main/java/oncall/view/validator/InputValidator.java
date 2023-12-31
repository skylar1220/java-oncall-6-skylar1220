package oncall.view.validator;

import oncall.common.Symbol;
import oncall.util.converter.Converter;
import oncall.util.validator.GeneralValidator;
import oncall.util.validator.StringValidator;

public class InputValidator {
    private static InputValidator inputValidator;
    public static final String EMERGENCY_MONTH_SEPARATOR = Symbol.COMMA;
    private static final String WORKERS_SEPARATOR = Symbol.COMMA;

    private InputValidator() {
    }

    public static InputValidator getInstance() {
        if (inputValidator == null) {
            return new InputValidator();
        }
        return inputValidator;
    }

    public void validateEmergencyMonth(String rawEmergencyMonth, String target) {
        validateFormat(EMERGENCY_MONTH_SEPARATOR, rawEmergencyMonth, target);
        validateMonth(Converter.splitValue(EMERGENCY_MONTH_SEPARATOR, 0, rawEmergencyMonth), "배정월");
    }

    private void validateMonth(String month, String target) {
        StringValidator.validateBlank(month, target);
        StringValidator.validateNumeric(month, target);
        StringValidator.validateIntegerRange(month, target);
    }

    private void validateFormat(String separator, String emergencyMonth, String target) {
        StringValidator.validateBlank(emergencyMonth, target);
        GeneralValidator.validateSingleSeparator(separator, emergencyMonth, target);
        GeneralValidator.validateNotStartsWith(separator, emergencyMonth, target);
        GeneralValidator.validateNotEndsWith(separator, emergencyMonth, target);
        GeneralValidator.validateSplitCount(separator, emergencyMonth, 2, target);
    }

    public void validateWeekdaysWorkers(String weekdaysWorkers, String target) {
        StringValidator.validateBlank(weekdaysWorkers, target);
        GeneralValidator.validateSingleSeparator(WORKERS_SEPARATOR, weekdaysWorkers, target);
        GeneralValidator.validateNotStartsWith(WORKERS_SEPARATOR, weekdaysWorkers, target);
        GeneralValidator.validateNotEndsWith(WORKERS_SEPARATOR, weekdaysWorkers, target);
    }
}
