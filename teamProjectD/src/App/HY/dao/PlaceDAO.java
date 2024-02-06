package App.HY.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import App.HY.PlaceVO;
import App.HY.resource.JdbcConnection;

public class PlaceDAO {

    JdbcConnection dbconn = new JdbcConnection();

    /**
     * 기능1. 맛집 검색
     */
    public void selectPlace() {
        GoodPlaceAddressDAO goodPlaceAddressDAO = new GoodPlaceAddressDAO();
        System.out.println("-".repeat(35));
        System.out.println("1- 상호명으로 검색");
        System.out.println("2- 평점으로 검색");
        System.out.println("3- 메뉴이름으로 검색");
        System.out.println("-".repeat(35));

        System.out.println("선택 입력__");
        String opt = System.console().readLine();

        switch (opt) {
            case "1":
                goodPlaceAddressDAO.placeNameSchList();
                break;
            case "2":
                goodPlaceAddressDAO.placeRateSchList();
                break;
            case "3":
                goodPlaceAddressDAO.placeMenuNameSchList();
                break;

            default:
                break;
        }

    }

    /**
     * 기능4. 평점순(5점 만점) 으로 맛집 주소록 보기
     */
    public void selectRatePlaceList() {
        System.out.println("-".repeat(35));

        List<PlaceVO> placeList = new ArrayList<>();
        String sql = "SELECT PLACE_SEQ ,NAME ,PHONE ,RATE ,OPEN_TIME ,CLOSE_TIME ,FOOD_TYPE \n" +
                "FROM TBL_PLACE \n" +
                "ORDER BY RATE DESC";

        try (
                Connection conn = dbconn.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                placeList.add(new PlaceVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4),
                        rs.getString(5), rs.getString(6), rs.getString(7)));
            }

        } catch (Exception e) {
            System.out.println("selectPlaceList 예외 발생 : " + e.getMessage());
        }

        for (PlaceVO vo : placeList) {
            System.out.println(vo);
        }
    }

    /**
     * 맛집 주소록 전체 목록
     */
    public List<PlaceVO> selectPlaceList() {
        System.out.println("-".repeat(35));

        List<PlaceVO> placeList = new ArrayList<>();
        String sql = "SELECT PLACE_SEQ ,NAME ,PHONE ,RATE ,OPEN_TIME ,CLOSE_TIME ,FOOD_TYPE \n" +
                "FROM TBL_PLACE \n" +
                "ORDER BY PLACE_SEQ ";

        try (
                Connection conn = dbconn.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                placeList.add(new PlaceVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4),
                        rs.getString(5), rs.getString(6), rs.getString(7)));
            }

        } catch (Exception e) {
            System.out.println("selectPlaceList 예외 발생 : " + e.getMessage());
        }

        return placeList;
    }

    /**
     * 기능5. 평점 수정
     */
    public void updateRatePlace() {
        System.out.println("-".repeat(35));
        List<PlaceVO> placeList = selectPlaceList();

        for (PlaceVO vo : placeList) {
            System.out.println(vo);
        }

        Map<String, Object> map = new HashMap<>();

        System.out.println("place_seq 선택 __");
        int place_seq = Integer.parseInt(System.console().readLine());
        System.out.println("평점 입력 __");
        float rate = Float.parseFloat(System.console().readLine());

        map.put("place_seq", place_seq);
        map.put("rate", rate);

        String sql = "UPDATE tbl_place\n" +
                "SET rate = ? \n" +
                "WHERE place_seq = ?";

        try (
                Connection conn = dbconn.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setFloat(1, (Float) map.get("rate"));
            ps.setInt(2, (int) map.get("place_seq"));
            int cnt = ps.executeUpdate();

            if (cnt > 0)
                System.out.println("수정 됐어요.");

        } catch (Exception e) {
            System.out.println("updateRatePlace 예외 발생 : " + e.getMessage());
        }

    }

}
