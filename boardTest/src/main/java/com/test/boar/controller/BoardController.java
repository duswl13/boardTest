package com.test.boar.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.HttpResource;

import com.test.boar.service.BoardService;
import com.test.boar.model.BoardList;
import com.test.boar.model.BoardTest;
import com.test.boar.model.BoardUpdate;
import com.test.boar.model.BoardWrite;

@Controller
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Value("${savefoldername}")
	private String saveFolder;

	@Autowired
	BoardService boardService;

	@GetMapping("/main")
	public ModelAndView main(ModelAndView mv) {

		int limit = 10;
		int page = 1;

		List<BoardList> list = boardService.getAllList(limit, page);
		mv.setViewName("boardList");
		mv.addObject("list", list);

		return mv;
	}

	@PostMapping("/boardUpdate")
	public void boardUpdate(BoardUpdate board, @RequestParam(value = "originFile", required = true) String originFile, HttpServletResponse resp) throws IOException {

		MultipartFile uploadfile = board.getUploadfile();

		if (!uploadfile.isEmpty()) {

			// 이전 파일이 있을 경우 휴지통으로 넣고 시작하기
			if(originFile != null)
			boardService.insertDeleteFile(originFile);

			String fileName = uploadfile.getOriginalFilename(); // 원래 파일 명
			board.setFileNameOrigin(fileName); // 원래 파일명 저장

			// 새로운 폴더 이름 : 오늘 년, 월, 일
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int date = c.get(Calendar.DATE);

			String homedir = saveFolder + year + "-" + month + "-" + date;

			System.out.println(homedir);

			File path1 = new File(homedir);
			if (!(path1.exists())) { // 이 파일의 경로가 존재하는지 확인
				path1.mkdir(); // 없을 경우 경로 만들기
			}

			// 난수를 구합니다. 사용자가 올린 파일의 이름이 중복되면 안되니까
			Random r = new Random();
			int random = r.nextInt(100000000);

			/* 확장자 구하기 시작 //// 원래 파일의 형식이 .png / .jpg / 같은 형식일거니까 */
			int index = fileName.lastIndexOf(".");
			/*
			 * 문자열에서 특정 문자열의 위치 값을 반환한다. indexOf가 처음 발견되는 문자열에 대한 index를 반환하는 반면,
			 * lastIndexOf는 마지막으로 발견되는 문자열의 index를 반환합니다. (파일명에 점이 여러개 있을 경우 맨 마지막에 발견되는
			 * 문자열의 위치를 리턴합니다.)
			 */

			/*
			 * 문자열에서 특정 문자열의 위치 값을 반환한다. indexOf가 처음 발견되는 문자열에 대한 index를 반환하는 반면, lastIndex는
			 * 마지막으로 발견되는 문자열ㅇ릐 index를 반환함
			 */

			System.out.println("index = " + index);

			String fileExtension = fileName.substring(index + 1); // 확장자만 따로 뻄
			System.out.println("fileExtension = " + fileExtension);
			/* 확장자 구하기 끝 */

			// 새로운 파일명
			String refileName = "bbs" + year + month + date + random + "." + fileExtension;

			System.out.println("refileName = " + refileName);

			// 오라클 디비에 저장될 파일명
			String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
			System.out.println("fileDbName = " + fileDBName);

			// transferTo(File path) : 업로한 파일의 매개변수의 경로에 저장합니다.
			uploadfile.transferTo(new File(saveFolder + fileDBName));

			// 바뀐 파일명으로 저장
			board.setFileName(fileDBName);
		}

		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();

		int result = boardService.boardUpdate(board);

		out.print("<script>");

		if (result == 1) {
			out.print("alert('수정되었습니다.');");
		} else {
			out.print("alert('수정되지 않았습니다.');");
		}

		out.print("location.href='boardDetail?boardKey=" + board.getBoardKey() + "';");
		out.print("</script>");
		out.close();

	}

	@GetMapping("/boardWrite")
	public String boardWrite() {
		return "boardWrite";
	}

	@ResponseBody
	@PostMapping("/deleteBoard")
	public int boardDelete(int boardKey) {

		return boardService.boardDelete(boardKey);
	}

	@PostMapping("/boardSave")
	public void boardSave(BoardWrite boardWrite, HttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException {
		logger.info("check >>>> " + boardWrite.toString());

		MultipartFile uploadfile = boardWrite.getUploadfile();

		if (!uploadfile.isEmpty()) {

			String fileName = uploadfile.getOriginalFilename(); // 원래 파일 명
			boardWrite.setFileNameOrigin(fileName); // 원래 파일명 저장

			// 새로운 폴더 이름 : 오늘 년, 월, 일
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int date = c.get(Calendar.DATE);

			String homedir = saveFolder + year + "-" + month + "-" + date;

			System.out.println(homedir);

			File path1 = new File(homedir);
			if (!(path1.exists())) { // 이 파일의 경로가 존재하는지 확인
				path1.mkdir(); // 없을 경우 경로 만들기
			}

			// 난수를 구합니다. 사용자가 올린 파일의 이름이 중복되면 안되니까
			Random r = new Random();
			int random = r.nextInt(100000000);

			/* 확장자 구하기 시작 //// 원래 파일의 형식이 .png / .jpg / 같은 형식일거니까 */
			int index = fileName.lastIndexOf(".");
			/*
			 * 문자열에서 특정 문자열의 위치 값을 반환한다. indexOf가 처음 발견되는 문자열에 대한 index를 반환하는 반면,
			 * lastIndexOf는 마지막으로 발견되는 문자열의 index를 반환합니다. (파일명에 점이 여러개 있을 경우 맨 마지막에 발견되는
			 * 문자열의 위치를 리턴합니다.)
			 */

			/*
			 * 문자열에서 특정 문자열의 위치 값을 반환한다. indexOf가 처음 발견되는 문자열에 대한 index를 반환하는 반면, lastIndex는
			 * 마지막으로 발견되는 문자열ㅇ릐 index를 반환함
			 */

			System.out.println("index = " + index);

			String fileExtension = fileName.substring(index + 1); // 확장자만 따로 뻄
			System.out.println("fileExtension = " + fileExtension);
			/* 확장자 구하기 끝 */

			// 새로운 파일명
			String refileName = "bbs" + year + month + date + random + "." + fileExtension;

			System.out.println("refileName = " + refileName);

			// 오라클 디비에 저장될 파일명
			String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
			System.out.println("fileDbName = " + fileDBName);

			// transferTo(File path) : 업로한 파일의 매개변수의 경로에 저장합니다.
			uploadfile.transferTo(new File(saveFolder + fileDBName));

			// 바뀐 파일명으로 저장
			boardWrite.setFileName(fileDBName);
		}

		int result = boardService.insertBoard(boardWrite); // 저장 메서드 호출

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");

		if (result == 1) {
			out.println("alert('추가되었습니다.');");
			out.println("location.href='main';");
		} else {
			out.println("alert('추가되지 않았습니다.');");
			out.println("history.back();");
		}

		out.println("</script>");

	}

	@GetMapping("/boardDetail")
	public ModelAndView boardDetail(int boardKey, ModelAndView mv) {

		// 조회수 올리기
		boardService.addReadCount(boardKey);

		// 글 상세정보 가져오기
		BoardTest boardTest = boardService.getBoardDetail(boardKey);

		// 댓글 가져오기

		mv.addObject("item", boardTest);
		mv.setViewName("boardDetail");
		return mv;
	}

	@GetMapping("BoardFileDown")
	public void BoardFileDown(String fileName, HttpServletRequest request, String fileNameOrigin,
			HttpServletResponse response) throws Exception {

		ServletContext context = request.getSession().getServletContext();

		String sFilePath = saveFolder + "/" + fileName;
		logger.info("file 경로를 확인합니다. : ", sFilePath);

		byte b[] = new byte[4096];
		// sFilePath에 있는 파일의 MimeType을 구해옵니다
		String sMimeType = context.getMimeType(sFilePath);
		logger.info("sMimeType>>>" + sMimeType);

		if (sMimeType == null)
			sMimeType = "application/octet-stream";

		response.setContentType(sMimeType);

		// 이부분이 한글 파일명이 깨지는 것을 방지해줍니다
		String sEncoding = new String(fileNameOrigin.getBytes("utf-8"), "ISO-8859-1");
		logger.info(sEncoding);

		/*
		 * 
		 * Content-Disposition: attachemnt : 브라우저는 해당 Content를 처리하지않습니다.
		 * 
		 */

		response.setHeader("Content-Disposition", "attachment;filename =" + sEncoding);

		// 프로젝트 속성 - Project - facets에서 자바버전 1.8로수정
		try (
				// 웹브라우저로 출력 스트림을 생성합니다
				BufferedOutputStream out2 = new BufferedOutputStream(response.getOutputStream());
				// sFilePath로 지정한 파일에 대한 입력 스트림을 생성합니다
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(sFilePath));) {
			int numRead;
			// read(b,0,b.length) : 바이트 배열 B의 0 부터 렝스
			// 크기만큼
			while ((numRead = in.read(b, 0, b.length)) != -1) {// 읽을 데이터가 존재하면 //바이트 배열 b의 0번부터 numRead크기만 큼 브라우저로 출력
				// 바이트 배열 b의 0번부터 num Read크기만큼 브라우저로 출력
				out2.write(b, 0, numRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
