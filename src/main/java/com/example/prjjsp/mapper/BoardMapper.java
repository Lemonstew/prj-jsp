package com.example.prjjsp.mapper;

import com.example.prjjsp.dto.Board;
import com.example.prjjsp.dto.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Insert("""
            INSERT INTO board
            (title, content, writer)
            VALUES (#{board.title}, #{board.content}, #{member.id})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "board.id")
    int insert(Board board, Member member);

    @Select("""
            SELECT * FROM board
            ORDER BY id DESC
            """)
    List<Board> selectAll();

    @Select("""
            SELECT b.id,
                    b.title,
                    b.content,
                    b.inserted,
                    b.writer,
                    m.nick_name writerNickName
            FROM board b JOIN member m
                    ON b.writer = m.id
            WHERE b.id = #{id}
            """)
    Board selectById(Integer id);

    @Delete("""
            DELETE FROM board
            WHERE id = #{id}
            """)
    int deleteById(Integer id);

    @Update("""
            UPDATE board
            SET title=#{title}, content=#{content}, writer=#{writer}
            WHERE id = #{id}
            """)
    int update(Board board);

    @Select("""
            <script>
                SELECT * FROM board
                <trim prefix="WHERE" prefixOverrides="OR">
                    <if test="searchTarget == 'all' or searchTarget == 'title'">
                        title LIKE CONCAT('%', #{keyword}, '%')
                    </if>
                    <if test="searchTarget == 'all' or searchTarget == 'content'">
                        OR content LIKE CONCAT('%', #{keyword}, '%')
                    </if>
                    <if test="searchTarget == 'all' or searchTarget == 'writer'">
                        OR writer LIKE CONCAT('%', #{keyword}, '%')
                    </if>
                </trim>
                ORDER BY id DESC
                LIMIT #{offset}, 10
            </script>
            """)
    List<Board> selectAllPaging(Integer offset, String searchTarget, String keyword);

    @Select("""
            <script>
            SELECT COUNT(id) FROM board
                <trim prefix="WHERE" prefixOverrides="OR">
                    <if test="searchTarget == 'all' or searchTarget == 'title'">
                        title LIKE CONCAT('%', #{keyword}, '%')
                    </if>
                    <if test="searchTarget == 'all' or searchTarget == 'content'">
                        OR content LIKE CONCAT('%', #{keyword}, '%')
                    </if>
                    <if test="searchTarget == 'all' or searchTarget == 'writer'">
                        OR writer LIKE CONCAT('%', #{keyword}, '%')
                    </if>
                </trim>
            </script>
            """)
    Integer countAll(String searchTarget, String keyword);
}
