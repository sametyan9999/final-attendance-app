package sample.thymeleaf.form;

import java.time.LocalDate;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TaskForm {

    @NotBlank(message = "タイトルを入力してください")
    @Size(max = 255, message = "タイトルは255文字以内で入力してください")
    private String title;

    @NotBlank(message = "内容を入力してください")
    @Size(max = 2000, message = "内容は2000文字以内で入力してください")
    private String content;

    @NotBlank(message = "登録者を入力してください")
    @Size(max = 100, message = "登録者は100文字以内で入力してください")
    private String name;

    @NotNull(message = "開始日を入力してください")
    private LocalDate startDate;

    @NotNull(message = "終了日を入力してください")
    private LocalDate endDate;

    // 開始日より前の終了日は入力できないようにする
    @AssertTrue(message = "終了日は開始日以降を入力してください")
    public boolean isValidDateRange() {

        if (startDate == null || endDate == null) {
            return true;
        }

        return !startDate.isAfter(endDate);
    }

    // getter setter

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}