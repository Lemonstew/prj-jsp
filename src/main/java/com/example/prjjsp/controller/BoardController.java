package com.example.prjjsp.controller;

import com.example.prjjsp.dto.Board;
import com.example.prjjsp.dto.Member;
import com.example.prjjsp.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService service;

    // 게시물 CRUD

    //  /board/new
    @GetMapping("new")
    public String newBoard(@SessionAttribute(value = "loggedInMember",
            required = false) Member member, RedirectAttributes rttr) {

        if (member == null) {
            // 로그인 안한 상태
            rttr.addFlashAttribute("message", Map.of("type", "warning",
                    "text", "로그인한 회원만 글 작성이 가능합니다."));
            return "redirect:/member/login";
        } else {
            // 로그인 한 상태
            // /WEB-INF/view/board/new.jsp
            return "board/new";
        }


    }

    @PostMapping("new")
    public String newBoard(Board board,
                           RedirectAttributes rttr,
                           @SessionAttribute("loggedInMember") Member member) {

        System.out.println(board);
        service.add(board, member);

        rttr.addFlashAttribute("message",
                Map.of("type", "success",
                        "text", "새 게시물이 등록되었습니다."));
        rttr.addAttribute("id", board.getId());

        return "redirect:/board/view";
    }

    // /board/list
    // /board/list?page=1
    @GetMapping("list")
    public void listBoard(@RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(required = false) String searchTarget,
                          @RequestParam(defaultValue = "false") String keyword,
                          Model model) {
        // 한 페이지에 10개의 게시물

        Map<String, Object> result = service.list(page, searchTarget, keyword);
        model.addAllAttributes(result);

    }

    @GetMapping("view")
    public void viewBoard(Integer id, Model model) {
        Board board = service.get(id);
        model.addAttribute("board", board);
    }

    @PostMapping("delete")
    public String deleteBoard(Integer id,
                              RedirectAttributes rttr,
                              @SessionAttribute("loggedInMember") Member member) {
        try {
            service.remove(id, member);

            rttr.addFlashAttribute("message",
                    Map.of("type", "warning",
                            "text", id + "번 게시물이 삭제되었습니다."));
            return "redirect:/board/list";
        } catch (RuntimeException e) {
            rttr.addFlashAttribute("message",
                    Map.of("type", "danger",
                            "text", id + "번 게시물이 삭제 중 문제가 발생하였습니다."));
            rttr.addAttribute("id", id);
            return "redirect:/board/view";
        }
    }

    @GetMapping("edit")
    public void editBoard(Integer id, Model model) {
        Board board = service.get(id);
        model.addAttribute("board", board);
    }

    @PostMapping("edit")
    public String editBoard(Board board, RedirectAttributes rttr) {

        System.out.println(board);

        service.update(board);

        rttr.addFlashAttribute("message",
                Map.of("type", "success",
                        "text", board.getId() + "번 게시물이 수정되었습니다."));
        rttr.addAttribute("id", board.getId());

        return "redirect:/board/view";
    }
}

