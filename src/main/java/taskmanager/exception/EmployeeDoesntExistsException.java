package taskmanager.exception;

public class EmployeeDoesntExistsException extends Exception {
    public EmployeeDoesntExistsException(String message) {
        super(message);
    }
}
