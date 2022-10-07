package com.kenneth.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kenneth.dao.ProductDao;
import com.kenneth.dto.ProductVo;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/writeProduct.do")
public class WriteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("product/writeProduct.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // post 방식 한글 설정
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		ProductVo pVo = new ProductVo();
		ProductDao pDao = ProductDao.getInstance();

		int result = -1;
		String savePath = "upload";
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		;
		System.out.println("저장파일 서버 경로 : " + uploadFilePath);
		int uploadFileSizeLimit = 5 * 1024 * 1024;
		String encType = "UTF-8";

		try {
			MultipartRequest multi = new MultipartRequest(request, // request
					uploadFilePath, // 서버상의 실제 디렉토리
					uploadFileSizeLimit, // 최대업로드 파일 크기
					encType, // 인코딩 방식
					new DefaultFileRenamePolicy()); // 정책 : 파일이름 중복 시 새로운 이름 부여
//			int code = Integer.parseInt(multi.getParameter("code"));
			String name = multi.getParameter("name");
			int price = Integer.parseInt(multi.getParameter("price"));
			String description = multi.getParameter("description");
			String pictureurl = multi.getFilesystemName("pictureurl");
			Date reg_date = Date.valueOf(multi.getParameter("reg_date"));

//			System.out.println(code);
//			System.out.println(name);
//			System.out.println(price);
//			System.out.println(description);
//			System.out.println(reg_date);
//			System.out.println(pictureurl);
			System.out.println(pVo);

//			pVo.setCode(code); // 입력된 상품 정보 pVo 저장
			pVo.setPrice(price);
			pVo.setName(name);
			pVo.setDescription(description);
			pVo.setPictureurl(pictureurl);
			pVo.setReg_date(reg_date);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("파일 업로드간 예외 발생");
		}
		System.out.println(pVo);			// 입력된 상품 정보 디버깅
		result = pDao.insertProduct(pVo); // 입력된 상품정보 삽입

		// 상품 등록 완료 시, 메세지 출력
		if (result == 1) {
			System.out.println("등록 완료");
			request.setAttribute("message", "상품 등록에 성공했습니다.");
		} else {
			System.out.println("등록 실패");
			request.setAttribute("message", "상품 등록에 실패했습니다.");
		}
		response.sendRedirect("productList.do");
	}

}
