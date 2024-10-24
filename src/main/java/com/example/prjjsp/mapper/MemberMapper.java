package com.example.prjjsp.mapper;

import com.example.prjjsp.dto.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    @Insert("""
            INSERT INTO member
            (id, password, nick_name, description)
            VAlUES (#{id}, #{password}, #{nickname}, #{description})
            """)
    int insert(Member member);
}
