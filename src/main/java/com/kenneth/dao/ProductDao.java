package com.kenneth.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kenneth.dto.ProductVo;
import com.kenneth.util.DBManager;

public class ProductDao {
	
	// **싱글톤
	// 1. 생성자 private : 객체를 외부에서 만들지 못하도록
	// 2. 객체 생성 private static :  자신이 객체를 생성
	// 3. 객체 제공 기능 getInstance() : 자신의 객체(단지 1개)를 사용할 수 있도록 제공
	private ProductDao() {
		
	}
	private static ProductDao instance = new ProductDao();
	public static ProductDao getInstance() {
		return instance;
	}
	// 상품 등록 코드
	// 입력값 : 전체 상품 정보
	// 반환값 : 쿼리 수행 결과
	public int insertProduct(ProductVo pVo) {
		int result = -1;
		Connection conn = null;
		// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할때, 매개변수가 많아서 쿼리문 정리 필요
		PreparedStatement pstmt = null;		// 동적 쿼리
		
		String sql_insert = "insert into product values(product_seq.nextval, ?, ?, ?, ?, ?)";
		
		
		try {
			conn = DBManager.getConnection();
			
			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql_insert);
			
//			pstmt.setInt(1, pVo.getCode());				// 정수형
			pstmt.setString(1, pVo.getName());
			pstmt.setInt(2, pVo.getPrice());			// 정수형
			pstmt.setString(3, pVo.getPictureurl());
			pstmt.setString(4, pVo.getDescription());			// 문자형
			pstmt.setDate(5, pVo.getReg_date());			// 날짜형

			
			// (4 단계) SQL문 실행 및 결과 처리
			// executeUpdate : 삽입(insert/update/delete)

			result = pstmt.executeUpdate();				// 쿼리 수행
		} catch(Exception e) {
			e.printStackTrace();			
		} finally {
			//(5 단계) 사용한 리소스 해제
			DBManager.close(conn, pstmt);
		}
		return result;
	}


	// 전체 상품 조회
	public List<ProductVo> selectAllProducts() {	// 리스트를 리턴할꺼기때문에 리턴타입을 저렇게 지정
		
		String sql = "select * from product order by code";
		List<ProductVo> list = new ArrayList<ProductVo>();
		
		ProductVo pVo = null;		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			
			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// (4 단계) SQL문 실행 및 결과 처리 => executeQuery : 조회(select)
			rs = pstmt.executeQuery();

			// rs.next() : 다음 행(row)을 확인, rs.getString("컬럼명")
			while(rs.next()){
				
				// 디비로부터 가져온 상품 정보를 pVo 객체에 저장
				
				pVo = new ProductVo();
				
				pVo.setCode(rs.getInt("code"));
				pVo.setName(rs.getString("name"));
				pVo.setPrice(rs.getInt("price"));
				pVo.setPictureurl(rs.getString("pictureurl"));
				pVo.setDescription(rs.getString("description"));
				pVo.setReg_date(rs.getDate("reg_date"));

//				System.out.println(pVo);
				list.add(pVo);	// List 객체에 데이터 추가
				
			} 
//			System.out.println("rs : " + rs);
//			System.out.println("list : "+ list);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
		
		
	// 단일 상품 조회 (상품코드) => DB에 정상 반영 여부 반환
	public ProductVo selectProductByCode(String code) {
		String sql = "select * from product where code=?";
		ProductVo pVo = null;		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			
			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);				// 정수형
			// (4 단계) SQL문 실행 및 결과 처리 => executeQuery : 조회(select)
			rs = pstmt.executeQuery();
			
			// rs.next() : 다음 행(row)을 확인, rs.getString("컬럼명")
			while(rs.next()){
				
				// 디비로부터 가져온 상품 정보를 pVo 객체에 저장
				
				pVo = new ProductVo();
				
				pVo.setCode(rs.getInt("code"));
				pVo.setName(rs.getString("name"));
				pVo.setPrice(rs.getInt("price"));
				pVo.setPictureurl(rs.getString("pictureurl"));
				pVo.setDescription(rs.getString("description"));
				pVo.setReg_date(rs.getDate("reg_date"));
			} 
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return pVo;
	}
		

	// 상품 수정 (수정정보) => DB에 정상 반영 여부 반환
	public int updateProduct(ProductVo pVo) {
		int result=-1;
		Connection conn = null;
		// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할때, 매개변수가 많아서 쿼리문 정리 필요
		PreparedStatement pstmt = null;		// 동적 쿼리
		
		String sql = "update product set name=?, price=?, pictureurl=?, description=?, reg_date=? where code=?";
		
		
		try {
			conn = DBManager.getConnection();
			
			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pVo.getName());
			pstmt.setInt(2, pVo.getPrice());			// 정수형
			pstmt.setString(3, pVo.getPictureurl());
			pstmt.setString(4, pVo.getDescription());			// 문자형
			pstmt.setDate(5, pVo.getReg_date());
			pstmt.setInt(6, pVo.getCode());	
			
			// (4 단계) SQL문 실행 및 결과 처리
			// executeUpdate : 삽입(insert/update/delete)

			pstmt.executeUpdate();				// 쿼리 수행
		} catch(Exception e) {
			e.printStackTrace();			
		} finally {
			//(5 단계) 사용한 리소스 해제
			DBManager.close(conn, pstmt);
		}
		return result;
	}
	
	// 상품 삭제
	public void deleteProduct(String code) {
		
		Connection conn = null;
		// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할때, 매개변수가 많아서 쿼리문 정리 필요
		PreparedStatement pstmt = null;		// 동적 쿼리
		
		String sql = "delete from product where code=?";
		
		
		try {
			conn = DBManager.getConnection();
			
			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);	

			
			// (4 단계) SQL문 실행 및 결과 처리
			// executeUpdate : 삽입(insert/update/delete)

			pstmt.executeUpdate();				// 쿼리 수행
		} catch(Exception e) {
			e.printStackTrace();			
		} finally {
			//(5 단계) 사용한 리소스 해제
			DBManager.close(conn, pstmt);
		}
		
	}

	// 상품 검색
	// 입력값: column: 검색 대상(분야), keyword: 검색어
	// 반환값: 검색 결과 리스트
	public List<ProductVo> searchProduct(String column, String keyword) {
		String sql = "select * from product where "+column+" like ? order by code";
		
		List<ProductVo> list = new ArrayList<ProductVo>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			// (4 단계) SQL문 실행 및 결과 처리 => executeQuery : 조회(select)
			rs = pstmt.executeQuery();
			// rs.next() : 다음 행(row)을 확인, rs.getString("컬럼명")
			while(rs.next()){
				// 디비로부터 가져온 상품 정보를 pVo 객체에 저장
				ProductVo pVo = new ProductVo();
				pVo.setCode(rs.getInt("code"));
				pVo.setName(rs.getString("name"));
				pVo.setPrice(rs.getInt("price"));
				pVo.setPictureurl(rs.getString("pictureurl"));
				pVo.setDescription(rs.getString("description"));
				pVo.setReg_date(rs.getDate("reg_date"));
				list.add(pVo);	// List 객체에 데이터 추가
			} 
//			System.out.println("rs : " + rs);
//			System.out.println("list : "+ list);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
}



