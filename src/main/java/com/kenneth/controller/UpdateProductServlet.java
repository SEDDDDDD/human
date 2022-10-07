package com.kenneth.controller;

import java.io.IOException;
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


@WebServlet("/updateProduct.do")
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 쿼리 스트링으로 전달받은 code 를 획득
		String code = request.getParameter("code");
		
		// 상품 삭제 링크 클릭시 삭제할 상품 정보를 표시
		ProductDao pDao = ProductDao.getInstance();
		ProductVo pVo = new ProductVo();
		
		// 데이터 베이스에서 삭제할 데이터 정보 확인
		pVo = pDao.selectProductByCode(code);
		
		request.setAttribute("product", pVo);

		
		// 페이지 이동 : 삭제 페이지
		RequestDispatcher dispatcher = request.getRequestDispatcher("product/updateProduct.jsp");
		dispatcher.forward(request, response);		// 페이지 이동	
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상품 수정 코드 : 데이터베이스에서 상품 수정
		ProductDao pDao = ProductDao.getInstance();
		ProductVo pVo = new ProductVo();
		
				
		// 파일 업로드 코드 작성
		int result = 1;
		String savePath = "upload";
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		;
		System.out.println("저장파일 서버 경로 : " + uploadFilePath);
		int uploadFileSizeLimit = 5 * 1024 * 1024;		// 용량 최대 5MB
		String encType = "UTF-8"; 	// 인코딩 타입

		try {
			MultipartRequest multi = new MultipartRequest(request, // request
					uploadFilePath, // 서버상의 실제 디렉토리
					uploadFileSizeLimit, // 최대업로드 파일 크기
					encType, // 인코딩 방식
					new DefaultFileRenamePolicy()); // 정책 : 파일이름 중복 시 새로운 이름 부여
			int code = Integer.parseInt(multi.getParameter("code"));
			String name = multi.getParameter("name");
			int price = Integer.parseInt(multi.getParameter("price"));
			String description = multi.getParameter("description");
			String pictureurl = multi.getFilesystemName("pictureurl");
			Date reg_date = Date.valueOf(multi.getParameter("reg_date"));

			pVo.setCode(code); // 입력된 상품 정보 pVo 저장
			pVo.setPrice(price);
			pVo.setName(name);
			pVo.setDescription(description);
			pVo.setPictureurl(pictureurl);
			pVo.setReg_date(reg_date);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("파일 업로드간 예외 발생");
		}
		

		// 데이터베이스로부터 해당 코드의 정보를 수정
		pDao.updateProduct(pVo);
		
		// 상품 등록 완료 시, 메세지 출력
				if (result == 1) {
					System.out.println("상품 수정 성공");
					request.setAttribute("message", "상품 수정에 성공했습니다.");
				} else {
					System.out.println("상품 수정 실패");
					request.setAttribute("message", "상품 수정에 실패했습니다.");
				}
		
		// 삭제 후 목록 페이지로 이동
		
				
		response.sendRedirect("productList.do");
	}

}
