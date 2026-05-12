package com.example;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
	List<Task> findByUsername(
	        @Param("username") String username,
	        @Param("limit") int limit,
	        @Param("offset") int offset
	);

	// タスク件数取得（ログインユーザーの件数）
	@Select("""
	    SELECT COUNT(*)
	    FROM tasks
	    WHERE username = #{username}
	""")
	int countByUsername(String username);

    // IDで1件取得
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
    """)
    Task findById(Integer id);

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

    // タスク更新
    @Update("""
        UPDATE tasks
        SET
            title = #{title},
            content = #{content},
            name = #{name},
            start_date = #{startDate},
            end_date = #{endDate},
            updated_at = CURRENT_TIMESTAMP
        WHERE id = #{id}
    """)
    void update(Task task);

    // タスク削除
    @Delete("""
        DELETE FROM tasks
        WHERE id = #{id}
    """)
    void delete(Integer id);
}