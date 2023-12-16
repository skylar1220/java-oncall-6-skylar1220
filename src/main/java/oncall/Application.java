package oncall;

import oncall.controller.OncallController;
import oncall.view.InputView;
import oncall.view.OutputView;
import oncall.view.printer.ConsolePrinter;
import oncall.view.printer.Printer;
import oncall.view.reader.ConsoleReader;
import oncall.view.reader.Reader;

public class Application {
    public static void main(String[] args) {
        Reader reader = new ConsoleReader();
        Printer printer = new ConsolePrinter();

        InputView inputView = InputView.of(reader, printer);
        OutputView outputView = new OutputView(printer);

        OncallController oncallController = new OncallController(inputView, outputView);
        oncallController.run();
    }
}
