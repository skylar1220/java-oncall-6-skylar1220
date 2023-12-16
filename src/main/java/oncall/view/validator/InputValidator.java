package oncall.view.validator;

import oncall.common.Symbol;
import oncall.util.converter.Converter;
import oncall.util.validator.GeneralValidator;
import oncall.util.validator.StringValidator;

public class InputValidator {
    private static InputValidator inputValidator;
    public static final String EMERGENCY_MONTH_SEPARATOR = Symbol.COMMA;

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

//    public static void validateNumber(String template, String target) {
//        StringValidator.validateBlank(template, target);
//        StringValidator.validateNumeric(template, target);
//        StringValidator.validateIntegerRange(template, target);
//    }
//
//    public void validatList(String template, String target) {
//        StringValidator.validateBlank(template, target);
//        GeneralValidator.validateSingleSeparator(SEPARATOR, template, target);
//        GeneralValidator.validateNotStartsWith(SEPARATOR, template, target);
//        GeneralValidator.validateNotEndsWith(SEPARATOR, template, target);
//        GeneralValidator.validateSplitCount(SEPARATOR, template, 2, target);
//    }
//
//    public void validateRawList(String raw, String target) {
//        validateFormat(SEPARATOR, raw, target);
//        List<String> list = Converter.splitToList(SEPARATOR, raw);
//        list.forEach(value -> validateEachFormat(VALUE_SEPARATOR, value, target));
//        list.forEach(value -> validateEachValue(VALUE_SEPARATOR, value, target));
//    }
}
