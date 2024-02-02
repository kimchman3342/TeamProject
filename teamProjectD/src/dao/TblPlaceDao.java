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

    public void randomRestorant(int place_seq){
    PlaceVo vo = null;
    
    String sql = "SELECT * FROM(SELECT *FROM TBL_PLACE tp WHERE RATE >= 4.5 ORDER BY DBMS_RANDOM.VALUE) WHERE ROWNUM <=3";

    try(
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(sql);
    ) {
        pstmt.setInt(1, place_seq);
        ResultSet rs = pstmt.executeQuery();
        vo =
    } catch (SQLException e) {
      System.out.println("randomRestorant 실행 예외 발생 : " + e.getMessage());
    }
    return vo;
    }// randomRestorant

    public List<PlaceVo> nameSearchList(String name) {
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
