package sample.common.exception;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(Integer id) {
        super("指定されたタスクが見つかりません。ID: " + id);
    }
}