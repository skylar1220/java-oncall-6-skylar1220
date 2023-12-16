package oncall.domain;

import java.util.Objects;

public class Worker {
    private final String workerName;

    public Worker(String workerName) {
        validateLength(workerName);
        this.workerName = workerName;
    }

    private void validateLength(String workerName) {
        if (!isValidLength(workerName)) {
            throw new IllegalArgumentException("사원의 이름은 5자 이하여야 합니다.");
        }
    }

    private boolean isValidLength(String workerName) {
        return workerName.length() <= 5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Worker worker = (Worker) o;
        return Objects.equals(workerName, worker.workerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workerName);
    }
}
