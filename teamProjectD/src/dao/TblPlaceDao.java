package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.PlaceVo;

public class TblPlaceDao {
    public static final String URL = "jdbc:oracle:thin:@//localhost:1521/xe";
    public static final String USERNAME = "c##idev";
    private static final String PASSWORD = "1234";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public void deletePlace(PlaceVo pv) {
        String sql = "DELETE\r\n" + "FROM TBL_PLACE_ADDRESS tpa\r\n" + "WHERE PLACE_SEQ = ?";
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, pv.getPlace_seq());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("[place] 삭제 예외 발생: " + e.getMessage());
        }
    }// deletePlace

    public void modifyPlace(int placeSeq, double newRate) {
        String sql = "UPDATE tbl_place SET rate = ? WHERE place_seq = ?";

        try (
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);) {
            pstmt.setDouble(1, newRate);
            pstmt.setInt(2, placeSeq);
            pstmt.executeUpdate();
            System.out.println("가게 평점이 수정되었습니다.");
        } catch (SQLException e) {
            System.out.println("가게 평점 변경 실행 예외 발생: " + e.getMessage());
        }
    }// modifyPlace

    public PlaceVo randomRestorant(String place,int time) {// 이거 행으로 가져가실 거면 리스트로 가져가 주세요!!!!
        PlaceVo vo = null; // 저기 밑에 있는 오류들은 지금 Vo 잘못 가져와서 그런거죠?
        List<PlaceVo> list = new HashMa
        String sql = "SELECT * FROM(SELECT * FROM TBL_PLACE tp, TBL_PLACE_ADDRESS tpa WHERE ADDRESS like '%'||?||'%' ORDER BY DBMS_RANDOM.VALUE) WHERE ROWNUM <= ?";

        try (
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);) {

            pstmt.setString(1, place); // place_seq에 대한 바인딩이 필요하지 않다면 이 부분은 제거해도 됩니다.
            pstmt.setInt(2,time);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // ResultSet에서 데이터를 가져와서 PlaceVo 객체에 저장합니다.
                vo = new PlaceVo(0,null,null,0,null,null,null);
                vo.setPlace(rs.getInt("PLACE_ID"));
                vo.setPlace();
                vo.setPlaceName(rs.getString("PLACE_NAME"));
                // 필요한 컬럼들을 추가로 설정하시면 됩니다.
            }
        } catch (SQLException e) {
            System.out.println("randomRestorant 실행 예외 발생: " + e.getMessage());
        }

        return vo;
    }// randomRestorant

    public List<PlaceVo> findName(String name) {
        List<PlaceVo> list = new ArrayList<>();
        String sql = "SELECT  tp.place_seq\r\n" + //
                "\t  , name\r\n" + //
                "\t  , open_time \r\n" + //
                "\t  , close_time\r\n" + //
                "\t  , tpa.address\r\n" + //
                "\t  , tm.menu_name\r\n" + //
                "\t  , to_char(tm.price, '999,999,999') AS price\r\n" + //
                "FROM  tbl_place tp\r\n" + //
                "\t, tbl_menu tm\r\n" + //
                "\t, tbl_place_address tpa\r\n" + //
                "WHERE tp.place_seq = tm.place_seq\r\n" + //
                "    AND tp.place_seq = tpa.place_seq\r\n" + //
                "    AND tp.name LIKE '%' || '?'  ||'%'";
        try (Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new PlaceVo(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)));
            }

        } catch (SQLException e) {
            System.out.println("예외 발생 :" + e.getMessage());
        }

        return list;

    }// 이름으로 찾기

}
