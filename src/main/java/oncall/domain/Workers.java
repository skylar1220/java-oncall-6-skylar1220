package oncall.domain;

import java.util.List;
import java.util.stream.Collectors;
import oncall.util.validator.GeneralValidator;

public class Workers {
    private final List<Worker> workers;

    public Workers(List<Worker> workers) {
        this.workers = workers;
    }

    public static Workers from(List<String> workers) {
        validateWorkers(workers);
        return new Workers(convertToWorkers(workers));
    }

    private static List<Worker> convertToWorkers(List<String> workers) {
        return workers.stream()
                .map(worker -> new Worker(worker))
                .collect(Collectors.toList());
    }

    private static void validateWorkers(List<String> workers) {
        validateDuplicates(workers);
        validateCount(workers);
    }

    private static void validateDuplicates(List<String> workers) {
        GeneralValidator.validateListForDuplicates(workers, "비상근무자는 중복될 수 없습니다.");
    }

    private static void validateCount(List<String> workers) {
        if (!isValidCount(workers)) {
            throw new IllegalArgumentException("비상 근무자는 5명 이상, 35명 이하여야 합니다.");
        }
    }

    private static boolean isValidCount(List<String> workers) {
        return workers.size() >= 5 && workers.size() <= 35;
    }

    public List<Worker> getWorkers() {
        return workers;
    }
}
