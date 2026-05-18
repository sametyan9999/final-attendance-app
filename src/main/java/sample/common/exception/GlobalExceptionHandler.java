package sample.common.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // タスク未検出エラー
    @ExceptionHandler(TaskNotFoundException.class)
    public String handleTaskNotFound(
            TaskNotFoundException e,
            Model model) {

        model.addAttribute("message", e.getMessage());

        return "error/404";
    }
    
 // パラメータ不正エラー
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatch(
            MethodArgumentTypeMismatchException e,
            Model model) {

        model.addAttribute(
                "message",
                "不正なリクエストです"
        );

        return "error/400";
    }

    // その他エラー
    @ExceptionHandler(Exception.class)
    public String handleException(
            Exception e,
            Model model) {

        model.addAttribute(
                "message",
                "予期しないエラーが発生しました"
        );

        return "error/500";
    }
}