package com.example.prjjsp.service;

import com.example.prjjsp.dto.Board;
import com.example.prjjsp.dto.Member;
import com.example.prjjsp.mapper.BoardMapper;
import com.example.prjjsp.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper mapper;
    private final MemberMapper memberMapper;

    public void add(Board board, Member member) {
        mapper.insert(board, member);
    }

    public Map<String, Object> list(Integer page, String searchTarget, String keyword) {
        // 한 페이지에 10개

        Integer offset = (page - 1) * 10;

//        List<Board> list = mapper.selectAll();
        List<Board> list = mapper.selectAllPaging(offset, searchTarget, keyword);

        // Controller 에게넘겨 줄 정보들을 담을 map
        Map<String, Object> map = new HashMap<>();
        // 페이지 관련 정보들
        Integer countAll = mapper.countAll(searchTarget, keyword);
        Integer lastPageNumber = (countAll - 1) / 10 + 1; // 마지막 페이지 번호
        Integer rightPageNumber = ((page - 1) / 10 + 1) * 10; // 현재 페이지 기준 오른쪽 끝 페이지 번호
        Integer leftPageNumber = rightPageNumber - 9; // 현재 페이지 기준 왼쪽 끝 페이지 번호
        Integer nextPageNumber = rightPageNumber + 1; // 다음 버튼 클릭 시 이동하는 페이지
        Integer prevPageNumber = leftPageNumber - 1; // 이전 버튼 클릭 시 이동하는 페이지

        Boolean hasNextPage = nextPageNumber < lastPageNumber; // 다음 버튼 유무
        Boolean hasPrevPage = prevPageNumber > 0; // 이전 버튼 유무

        // 오른쪽 끝페이지는 마지막 페이지보다 클 수 없음
        rightPageNumber = Math.min(rightPageNumber, lastPageNumber);

        Map<String, Object> pageInfo = new HashMap<>();

        pageInfo.put("hasPrevPage", hasPrevPage);
        pageInfo.put("hasNextPage", hasNextPage);
        pageInfo.put("nextPageNumber", nextPageNumber);
        pageInfo.put("prevPageNumber", prevPageNumber);
        pageInfo.put("leftPageNumber", leftPageNumber);
        pageInfo.put("rightPageNumber", rightPageNumber);
        pageInfo.put("lastPageNumber", lastPageNumber);
        pageInfo.put("currentPageNumber", page);

        map.put("pageInfo", pageInfo);
        map.put("boardList", list);

        return map;
    }

    public Board get(Integer id) {
        Board board = mapper.selectById(id);
        return board;
    }

    public void remove(Integer id, Member member) {
        Board board = mapper.selectById(id);
        if (board.getWriter().equals(member.getId())) {
            mapper.deleteById(id);
        } else {
            throw new RuntimeException("삭제 권한이 없습니다.");
        }
    }

    public void update(Board board) {
        mapper.update(board);
    }
}
