package test.file.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.file.dto.FileDto;
import test.util.DbcpBean;

public class FileDao {
	// static 필드
	private static FileDao dao;

	// 외부에서 객체 생성하지 못하도록 private 접근 지정자를 가지고 있는 생성자
	private FileDao() {
	}

	// 자신의 참조값을 리턴해주는 static 메소드
	public static FileDao getInstance() {
		if (dao == null) {
			dao = new FileDao();
		}
		return dao;
	}

	// 전체 글의 갯수를 리턴하는 메소드
	public int getCount() {
		// 글의 갯수를 담을 지역변수
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			// 실행할 sql 문
			String sql = "SELECT MAX(ROWNUM) AS count" + " FROM board_file";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩할 내용이 있으면 여기서 한다.

			// query 문 수행하고 결과(ResultSet) 얻어내기
			rs = pstmt.executeQuery();
			// 만일 select 된 row 가 있다면
			if (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close(); // Connection 객체의 close() 메소드를 호출하면 Pool 에 반납된다.
			} catch (Exception e) {
			}
		}
		return count;
	}

	// 업로드된 파일의 정보를 DB 에 저장하는 메소드
	public boolean insert(FileDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			// 실행할 sql 문
			String sql = "INSERT INTO board_file"
					+ " (num, writer, title, orgFileName, saveFileName, fileSize, regdate)"
					+ " VALUES(board_file_seq.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩 할 내용이 있으면 바인딩
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getOrgFileName());
			pstmt.setString(4, dto.getSaveFileName());
			pstmt.setLong(5, dto.getFileSize());
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		if (rowCount > 0) {
			return true;
		} else {
			return false;
		}
	}

	// 파일 목록을 리턴해주는 메소드
	public List<FileDto> getList(int start, int end) {
		List<FileDto> list = new ArrayList<FileDto>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			// 실행할 sql 문
			String sql = "SELECT *" + " FROM" + "	(SELECT result1.*, ROWNUM AS rnum" + "	FROM"
					+ "		(SELECT num, writer, title, orgFileName, fileSize, regdate " + "		FROM board_file"
					+ "		ORDER BY num DESC) result1)" + " WHERE rnum BETWEEN ? AND ?";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩할 내용이 있으면 여기서 한다.
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			// query 문 수행하고 결과(ResultSet) 얻어내기
			rs = pstmt.executeQuery();
			// 반복문 돌면서
			while (rs.next()) {
				// SELECT 된 정보를 FileDto 에 담아서
				FileDto dto = new FileDto();
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setOrgFileName(rs.getString("orgFileName"));
				dto.setFileSize(rs.getLong("fileSize"));
				dto.setRegdate(rs.getString("regdate"));
				// ArrayList 객체에 누적 시키기
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close(); // Connection 객체의 close() 메소드를 호출하면 Pool 에 반납된다.
			} catch (Exception e) {
			}
		}
		return list;
	}

	// 파일 전체 목록을 리턴해주는 메소드
	public List<FileDto> getList() {
		List<FileDto> list = new ArrayList<FileDto>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			// 실행할 sql 문
			String sql = "SELECT num, writer, title, orgFileName, fileSize, regdate" + " FROM board_file"
					+ " ORDER BY num DESC";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩할 내용이 있으면 여기서 한다.

			// query 문 수행하고 결과(ResultSet) 얻어내기
			rs = pstmt.executeQuery();
			// 반복문 돌면서
			while (rs.next()) {
				// SELECT 된 정보를 FileDto 에 담아서
				FileDto dto = new FileDto();
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setOrgFileName(rs.getString("orgFileName"));
				dto.setFileSize(rs.getLong("fileSize"));
				dto.setRegdate(rs.getString("regdate"));
				// ArrayList 객체에 누적 시키기
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close(); // Connection 객체의 close() 메소드를 호출하면 Pool 에 반납된다.
			} catch (Exception e) {
			}
		}
		return list;
	}

	// 파일 하나의 정보를 리턴해주는 메소드
	public FileDto getData(int num) {
		FileDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			// 실행할 sql 문
			String sql = "SELECT writer, title, orgFileName, saveFileName, fileSize, regdate" + " FROM board_file"
					+ " WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩할 내용이 있으면 여기서 한다.
			pstmt.setInt(1, num);
			// query 문 수행하고 결과(ResultSet) 얻어내기
			rs = pstmt.executeQuery();
			// 만일 select 된 row 가 있다면
			if (rs.next()) {
				// FileDto 객체를 생성해서 select 된 row 에 있는 정보를 넣어준다.
				dto = new FileDto();
				dto.setNum(num);
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setOrgFileName(rs.getString("orgFileName"));
				dto.setSaveFileName(rs.getString("saveFileName"));
				dto.setFileSize(rs.getLong("fileSize"));
				dto.setRegdate(rs.getString("regdate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close(); // Connection 객체의 close() 메소드를 호출하면 Pool 에 반납된다.
			} catch (Exception e) {
			}
		}
		return dto;
	}

	// 파일 하나의 정보를 DB 에서 삭제하는 메소드
	public boolean delete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			// 실행할 sql 문
			String sql = "DELETE FROM board_file" + " WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩 할 내용이 있으면 바인딩
			pstmt.setInt(1, num);
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		if (rowCount > 0) {
			return true;
		} else {
			return false;
		}
	}
}
