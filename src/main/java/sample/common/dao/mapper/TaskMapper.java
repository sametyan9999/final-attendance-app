package sample.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import sample.common.dao.entity.Task;

@Mapper
public interface TaskMapper {

	// タスク一覧取得（ログインユーザーのタスクを10件ずつ）
	@Select("""
			    SELECT
			        id,
			        username,
			        title,
			        content,
			        name,
			        start_date AS startDate,
			        end_date AS endDate,
			        created_at AS createdAt,
			        updated_at AS updatedAt
			    FROM tasks
			    WHERE username = #{username}
			    ORDER BY id DESC
			    LIMIT #{limit}
			    OFFSET #{offset}
			""")
	List<Task> findByUsername(@Param("username") String username, @Param("limit") int limit,
			@Param("offset") int offset);

	// タスク件数取得（ログインユーザーの件数）
	@Select("""
			    SELECT COUNT(*)
			    FROM tasks
			    WHERE username = #{username}
			""")
	int countByUsername(String username);

	// IDとユーザー名で1件取得
	@Select("""
			    SELECT
			        id,
			        username,
			        title,
			        content,
			        name,
			        start_date AS startDate,
			        end_date AS endDate,
			        created_at AS createdAt,
			        updated_at AS updatedAt
			    FROM tasks
			    WHERE id = #{id}
			      AND username = #{username}
			""")
	Task findByIdAndUsername(@Param("id") Integer id, @Param("username") String username);

	// タスク登録
	@Insert("""
			    INSERT INTO tasks
			    (
			        username,
			        title,
			        content,
			        name,
			        start_date,
			        end_date,
			        created_at,
			        updated_at
			    )
			    VALUES
			    (
			        #{username},
			        #{title},
			        #{content},
			        #{name},
			        #{startDate},
			        #{endDate},
			        CURRENT_TIMESTAMP,
			        CURRENT_TIMESTAMP
			    )
			""")
	void insert(Task task);

	// タスク更新（ログインユーザー本人のタスクだけ更新）
	@Update("""
			    UPDATE tasks
			    SET
			        title = #{task.title},
			        content = #{task.content},
			        name = #{task.name},
			        start_date = #{task.startDate},
			        end_date = #{task.endDate},
			        updated_at = CURRENT_TIMESTAMP
			    WHERE id = #{task.id}
			      AND username = #{username}
			""")
	int updateByOwner(@Param("task") Task task, @Param("username") String username);

	// タスク削除（ログインユーザー本人のタスクだけ削除）
	@Delete("""
			    DELETE FROM tasks
			    WHERE id = #{id}
			      AND username = #{username}
			""")
	int deleteByOwner(@Param("id") Integer id, @Param("username") String username);
}