package com.ezen.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.spring.dao.BoardDAO;
import com.ezen.spring.dao.CommentDAO;
import com.ezen.spring.dao.FileDAO;
import com.ezen.spring.domain.BoardDTO;
import com.ezen.spring.domain.BoardVO;
import com.ezen.spring.domain.CommentVO;
import com.ezen.spring.domain.FileVO;
import com.ezen.spring.domain.PagingVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class BoardServiceImpl implements BoardService{
	
	private final BoardDAO bdao;
	private final FileDAO fdao;
	private final CommentDAO cdao;

//	@Override
//	public int insert(BoardVO bvo) {
//		// TODO Auto-generated method stub
//		return bdao.insert(bvo);
//	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.getList(pgvo);
	}

//	@Override
//	public BoardVO getDetail(int bno) {
//		// TODO Auto-generated method stub
//		return bdao.getDetail(bno);
//	}

//	@Override
//	public int update(BoardVO bvo) {
//		// TODO Auto-generated method stub
//		return bdao.update(bvo);
//	}

	@Override
	public int delete(int bno) {
		// TODO Auto-generated method stub
		return bdao.delete(bno);
	}

	@Override
	public int getTotal(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.getTotal(pgvo);
	}

	@Override
	public int readCount(int bno) {
		// TODO Auto-generated method stub
		return bdao.readCount(bno);
	}

	@Transactional // insert를 하는동안은 락을 걸어서 다른 접근을 못하게 막음
	@Override
	public int insert(BoardDTO bdto) {
		// bvo + file
		// bvo 먼저 insert 하고 난 후 bno를 DB에서 빼와야 함. > fvo를 DB에 저장
		int isOk = bdao.insert(bdto.getBvo());
		if(bdto.getFlist() == null) { // 첨부파일이 없을 경우
			return isOk;
		}
				
		// 첨부파일이 있는 케이스 
		if(isOk > 0 && bdto.getFlist().size() > 0) {
			// bno setting
			long bno = bdao.getOneBno(); // 가장 마지막에 저장된 bno
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bno);
				isOk *= fdao.insertFile(fvo);
			}
		}
		
		return isOk;
	}
	
	@Transactional
	@Override
	public BoardDTO getDetail(int bno) {
		 
		BoardVO bvo = bdao.getDetail(bno);
		List<FileVO> flist = fdao.getList(bno);
		
		BoardDTO bdto = new BoardDTO(bvo, flist);
		
		return bdto;
	}
	
	@Override
	public int fileDelete(String uuid) {
		// TODO Auto-generated method stub
		return fdao.fileDelete(uuid);
	}

	@Override
	public int update(BoardDTO boardDTO) {
		// 
		int isOk = bdao.update(boardDTO.getBvo());
		if(boardDTO.getFlist() == null) {
			return isOk;
		}
		if(isOk > 0 && boardDTO.getFlist().size() > 0) {
			for(FileVO fvo : boardDTO.getFlist()) {
				fvo.setBno(boardDTO.getBvo().getBno());
				isOk *= fdao.insertFile(fvo);
			}
		}
		return isOk;
	}

	@Override
	public int updateCmtCount(long bno) {
		// TODO Auto-generated method stub
		return bdao.updateCmtCount(bno);
	}

	@Override
	public int hasFile(long bno) {
		// TODO Auto-generated method stub
		return bdao.hasFile(bno);
	}

	@Override
	public int hasFileDelete(String uuid) {
		
		FileVO fvo = fdao.getFvo(uuid);		
		int isOk = bdao.hasFileDelete(fvo.getBno());
		
		return isOk;
	}

	@Override
	public int cmtQtyDelete(long cno) {
		
		CommentVO cvo = cdao.getCvo(cno);
		
		int isOk = bdao.cmtQtyDelete(cvo.getBno());
		return isOk;
	}


	
}
