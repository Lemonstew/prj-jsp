package com.example.prjjsp.controller;

import com.example.prjjsp.dto.Board;
import com.example.prjjsp.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService service;

    // 게시물 CRUD

    //  /board/new
    @GetMapping("new")
    public void newBoard() {

        // /WEB-INF/view/board/new.jsp


    }

    @PostMapping("new")
    public String newBoard(Board board) {

        System.out.println(board);
        service.add(board);

        return "redirect:/board/new";
    }
}

