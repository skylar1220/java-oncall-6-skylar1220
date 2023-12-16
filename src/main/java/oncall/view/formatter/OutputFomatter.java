package oncall.view.formatter;

import oncall.common.Symbol;

public class OutputFomatter {
    public static final String SEPARATOR = Symbol.COMMA;

    public static String formatMoney(int money) {
        return String.format("%,d", money);
    }
}
