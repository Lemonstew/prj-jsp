package com.example.prjjsp.mapper;

import com.example.prjjsp.dto.Board;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {

    @Insert("""
            INSERT INTO board
            (title, content, writer)
            VALUES (#{title}, #{content}, #{writer})
            """)
    int insert(Board board);
}
