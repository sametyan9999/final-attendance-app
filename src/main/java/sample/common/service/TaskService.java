package sample.common.service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sample.common.dao.entity.Task;
import sample.common.dao.mapper.TaskMapper;
import sample.thymeleaf.form.TaskForm;

@Service
@Transactional(readOnly = true)
public class TaskService {

	public static final int PAGE_SIZE = 10;

	private final TaskMapper taskMapper;
	private final Clock clock;

	public TaskService(TaskMapper taskMapper, Clock clock) {

		this.taskMapper = taskMapper;
		this.clock = clock;
	}

	// ログインユーザーのタスク一覧取得
	public List<Task> findByUsername(String username, int page, int size) {

		int offset = (page - 1) * size;

		return taskMapper.findByUsername(username, size, offset);
	}

	// ログインユーザーのタスク件数取得
	public int countByUsername(String username) {

		return taskMapper.countByUsername(username);
	}

	// ログインユーザー本人のタスク取得
	public Task findByIdForOwner(Integer id, String username) {

		return taskMapper.findByIdAndUsername(id, username);
	}

	// タスク登録処理
	@Transactional
	public void insert(TaskForm form, String username) {

		LocalDateTime now = LocalDateTime.now(clock);

		Task task = new Task();

		task.setTitle(form.getTitle());
		task.setContent(form.getContent());
		task.setName(form.getName());
		task.setStartDate(form.getStartDate());
		task.setEndDate(form.getEndDate());

		// セッション中のログインユーザーを登録者として設定する
		task.setUsername(username);

		// アプリ側で時刻を設定する
		task.setCreatedAt(now);
		task.setUpdatedAt(now);

		taskMapper.insert(task);
	}

	// ログインユーザー本人のタスク更新
	@Transactional
	public void updateForOwner(Integer id, TaskForm form, String username) {

		LocalDateTime now = LocalDateTime.now(clock);

		Task task = new Task();

		task.setId(id);
		task.setTitle(form.getTitle());
		task.setContent(form.getContent());
		task.setName(form.getName());
		task.setStartDate(form.getStartDate());
		task.setEndDate(form.getEndDate());

		// 他ユーザーのタスク更新を防ぐため、
		// ログインユーザー名を条件に含める
		task.setUsername(username);

		// 更新日時をアプリ側で設定する
		task.setUpdatedAt(now);

		taskMapper.updateByOwner(task, username);
	}

	// ログインユーザー本人のタスク削除
	@Transactional
	public void deleteForOwner(Integer id, String username) {

		taskMapper.deleteByOwner(id, username);
	}
}