package com.example.prjjsp.service;

import com.example.prjjsp.dto.Member;
import com.example.prjjsp.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberMapper mapper;

    public void addMember(Member member) {
        mapper.insert(member);
    }

    public List<Member> list() {
        return mapper.selectAll();
    }

    public Member info(String id) {
        return mapper.selectById(id);
    }

    public boolean remove(String id, String password) {
        int cnt = mapper.deleteByIdAndPassword(id, password);
        return cnt == 1;
    }
}