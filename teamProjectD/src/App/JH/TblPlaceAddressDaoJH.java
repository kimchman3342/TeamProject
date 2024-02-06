package App.JH;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.PlaceAddressVo;

public class TblPlaceAddressDaoJH {
    public static final String URL = "jdbc:oracle:thin:@//localhost:1521/xe";
    public static final String USERNAME = "c##idev";
    private static final String PASSWORD = "1234";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public List<PlaceAddressVo> findArea(String address) {
        List<PlaceAddressVo> list = new ArrayList<>();
        String sql = "SELECT   tp.place_seq\r\n" + //
                "      ,tp.name\r\n" + //
                "      , open_time \r\n" + //
                "        , close_time\r\n" + //
                "        , tpa.address\r\n" + //
                "FROM  tbl_place tp\r\n" + //
                "   , tbl_place_address tpa\r\n" + //
                "   , tbl_area_unit au\r\n" + //
                "WHERE tp.place_seq = tpa.place_seq\r\n" + //
                "  AND substr(tpa.address,0,2) = au.unit_name\r\n" + //
                "  AND au.unit_name = ?";
        try (Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);) {
            pstmt.setString(1, address);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new PlaceAddressVo(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)));
            }
        } catch (SQLException e) {
            System.out.println("예외 발생 :" + e.getMessage());

        }
        return list;
    }

    public void deletePlaceAddress(int PlaceVo) {
        String sql = "DELETE\r\n" + "FROM TBL_PLACE_ADDRESS tpa\r\n" + "WHERE PLACE_SEQ = ?";
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, PlaceVo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("삭제 예외 발생" + e.getMessage());

        }

    }

}
