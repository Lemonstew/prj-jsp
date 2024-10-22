package com.example.prjjsp.service;

import com.example.prjjsp.dto.Board;
import com.example.prjjsp.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper mapper;

    public void add(Board board) {
        mapper.insert(board);
    }
}
