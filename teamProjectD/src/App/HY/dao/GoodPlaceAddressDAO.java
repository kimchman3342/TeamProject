package App.HY.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import App.HY.AreaSchPlaceVO;
import App.HY.PlaceSchVO;
import App.HY.RandomSchPlaceVO;
import App.HY.resource.JdbcConnection;

/**
 * DB 데이터 접근 클래스
 */
public class GoodPlaceAddressDAO {

    JdbcConnection dbconn = new JdbcConnection();

    /**
     * 기능1. 맛집 찾기 ( 이름으로, 평점으로, 메뉴로 )
     */
    public void placeNameSchList() {
        System.out.println("상호명 입력__");
        String param = System.console().readLine();

        List<PlaceSchVO> placeList = new ArrayList<>();
        String sql = "SELECT  tp.place_seq, tp.name, open_time, close_time, tp.rate, tpa.address, tm.menu_name, tm.price\n"
                +
                "FROM  tbl_place tp, tbl_menu tm, tbl_place_address tpa \n" +
                "WHERE tp.place_seq = tm.place_seq(+) \n" +
                " AND tp.place_seq = tpa.place_seq(+) \n" +
                " AND tp.name LIKE '%'|| ? ||'%'";

        try (
                Connection conn = dbconn.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, param);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                placeList.add(new PlaceSchVO(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getFloat(5),
                        rs.getString(6), rs.getString(7),
                        rs.getInt(8)));
            }

        } catch (Exception e) {
            System.out.println("placeNameSchList 예외 발생 : " + e.getMessage());
        }

        if (placeList.size() == 0) {
            System.out.println("조회 결과가 없습니다.");
            return;
        }

        System.out.println("=".repeat(20));
        System.out.println(param + "을 검색한 결과를 보여줄게요");
        for (PlaceSchVO vo : placeList) {
            System.out.println(vo);
        }
        System.out.println("=".repeat(20));

    }

    public void placeRateSchList() {
        System.out.println("평점 입력 __");
        float param = Float.parseFloat(System.console().readLine());

        List<PlaceSchVO> placeList = new ArrayList<>();
        String sql = "SELECT  tp.place_seq, tp.name, open_time, close_time, tp.rate, tpa.address, tm.menu_name, tm.price\n"
                +
                "FROM  tbl_place tp, tbl_menu tm, tbl_place_address tpa \n" +
                "WHERE tp.place_seq = tm.place_seq \n" +
                "AND tp.place_seq = tpa.place_seq \n" +
                "AND tp.rate >= ? \n" +
                "ORDER BY rate DESC, place_seq";

        try (
                Connection conn = dbconn.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setFloat(1, param);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                placeList.add(new PlaceSchVO(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getFloat(5),
                        rs.getString(6), rs.getString(7),
                        rs.getInt(8)));
            }

        } catch (Exception e) {
            System.out.println("placeNameSchList 예외 발생 : " + e.getMessage());
        }

        if (placeList.size() == 0) {
            System.out.println("조회 결과가 없습니다.");
            return;
        }

        System.out.println("-".repeat(35));
        System.out.println("평점 " + param + "점 이상인 맛집 검색 결과");
        System.out.println(String.format("%-8s\t%5s %-28s\t\t%10s\t%30s\t\t%20s\t\t%8s",
                "분류번호", "평점", "상호명", "운영 시간", "주소", "메뉴", "가격"));
        for (PlaceSchVO vo : placeList) {
            System.out.println(vo);
        }
        System.out.println("-".repeat(35));
    }

    public void placeMenuNameSchList() {
        System.out.println("메뉴이름 입력__");
        String param = System.console().readLine();

        List<PlaceSchVO> placeList = new ArrayList<>();
        String sql = "SELECT tp.place_seq, tp.name, open_time, close_time, tp.rate, tpa.address, tm.menu_name, tm.price\n"
                +
                "FROM  tbl_place tp, tbl_menu tm, tbl_place_address tpa \n" +
                "WHERE tp.place_seq = tm.place_seq \n" +
                "AND tp.place_seq = tpa.place_seq \n" +
                "AND tm.menu_name LIKE '%'|| ? ||'%'";

        try (
                Connection conn = dbconn.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, param);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                placeList.add(new PlaceSchVO(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getFloat(5),
                        rs.getString(6), rs.getString(7),
                        rs.getInt(8)));
            }

        } catch (Exception e) {
            System.out.println("placeMenuNameSchList 예외 발생 : " + e.getMessage());
        }

        if (placeList.size() == 0) {
            System.out.println("조회 결과가 없습니다.");
            return;
        }
        System.out.println("=".repeat(20));
        System.out.println(param + "메뉴 검색 결과를 보여줄게요");
        for (PlaceSchVO vo : placeList) {
            System.out.println(vo);
        }
        System.out.println("=".repeat(20));
    }

    /**
     * 기능2. 지역별로 랜덤 맛집 하나만 보여주기
     */
    public void selectRandomPlaceList() {
        System.out.println("지역을 입력하면 가게 3곳을 랜덤하게 보여줄게요.");
        System.out.println("서울\t인천\t부산\t대구\t광주\t제주");
        System.out.println("지역 입력__");
        String param = System.console().readLine();

        List<RandomSchPlaceVO> placeList = new ArrayList<>();
        String sql = "SELECT *\r\n" + //
                "FROM (\r\n" + //
                "\tSELECT   tp.place_seq, tp.name, open_time , close_time, tpa.address, tp.rate\r\n" + //
                "\tFROM  tbl_place tp, tbl_place_address tpa, tbl_area_unit au\r\n" + //
                "\tWHERE tp.place_seq = tpa.place_seq\r\n" + //
                "\t\tAND substr(tpa.address,0,2) = au.unit_name\r\n" + //
                "\t\tAND au.unit_name = ? \r\n" + //
                "\tORDER BY DBMS_RANDOM.VALUE\r\n" + //
                ")\r\n" +
                "WHERE ROWNUM <= 3";

        try (
                Connection conn = dbconn.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, param);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                placeList.add(new RandomSchPlaceVO(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getFloat(6)));
            }

        } catch (Exception e) {
            System.out.println("selectRandomPlaceList 예외 발생 : " + e.getMessage());
        }

        if (placeList.size() == 0) {
            System.out.println("조회 결과가 없습니다.");
            return;
        }

        System.out.println("=".repeat(20));
        System.out.println(param + "지역 랜덤 뽑기 결과 ");
        for (RandomSchPlaceVO vo : placeList) {
            System.out.println(vo);
        }
        System.out.println("=".repeat(20));

    }

    /**
     * 기능3. 지역별로 가게 찾기
     */
    public void selectAreaPlaceList() {
        System.out.println("지역별 가게 목록을 보여줄게요.");
        System.out.println("서울\t인천\t부산\t대구\t광주\t제주");
        System.out.println("지역 입력__");
        String param = System.console().readLine();

        List<AreaSchPlaceVO> placeList = new ArrayList<>();
        String sql = "SELECT   tp.place_seq\r\n" + //
                "\t\t,tp.name\r\n" + //
                "\t\t, open_time \r\n" + //
                "\t\t, close_time\r\n" + //
                "\t\t, tpa.address\r\n" + //
                "FROM  tbl_place tp\r\n" + //
                "\t, tbl_place_address tpa\r\n" + //
                "\t, tbl_area_unit au\r\n" + //
                "WHERE tp.place_seq = tpa.place_seq\r\n" + //
                "  AND substr(tpa.address,0,2) = au.unit_name\r\n" + //
                "  AND au.unit_name =?";

        try (
                Connection conn = dbconn.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, param);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                placeList.add(new AreaSchPlaceVO(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5)));
            }

        } catch (Exception e) {
            System.out.println("selectAreaPlaceList 예외 발생 : " + e.getMessage());
        }

        if (placeList.size() == 0) {
            System.out.println("조회 결과가 없습니다.");
            return;
        }

        System.out.println("=".repeat(20));
        System.out.println(param + " 맛집 검색 결과 ");
        for (AreaSchPlaceVO vo : placeList) {
            System.out.println(vo);
        }
        System.out.println("=".repeat(20));

    }

}
