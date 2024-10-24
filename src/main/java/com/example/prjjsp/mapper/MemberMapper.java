package com.example.prjjsp.mapper;

import com.example.prjjsp.dto.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemberMapper {

    @Insert("""
            INSERT INTO member
            (id, password, nick_name, description)
            VAlUES (#{id}, #{password}, #{nickName}, #{description})
            """)
    int insert(Member member);

    @Select("""
            SELECT *
            FROM member
            ORDER BY id
            """)
    List<Member> selectAll();

    @Select("""
            SELECT * FROM member
            WHERE id = #{id}
            """)
    Member selectById(String id);
}
