package com.ezen.spring.dao;

import java.util.List;

import com.ezen.spring.domain.BoardVO;
import com.ezen.spring.domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	BoardVO getDetail(int bno);

	int update(BoardVO bvo);

	int delete(int bno);

	int getTotal(PagingVO pgvo);

	int readCount(int bno);

	long getOneBno();

	int updateCmtCount(long bno);

	int hasFile(long bno);

	int hasFileDelete(long bno);

	int cmtQtyDelete(long bno);


}
