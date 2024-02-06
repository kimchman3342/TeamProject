package App.HY.main;

import App.HY.dao.GoodPlaceAddressDAO;
import App.HY.dao.PlaceDAO;

public class GoodPlaceAddressMain {
    public static void main(String[] args) {
        PlaceDAO placeDAO = new PlaceDAO();
        GoodPlaceAddressDAO miniDAO = new GoodPlaceAddressDAO();

        System.out.println("-".repeat(50));
        System.out.println("맛집 주소록 관리 시스템에 오신 것을 환영해요.");
        System.out.println("-".repeat(50));

        boolean run = true;
        while (run) {
            showMenu();
            System.out.println("선택 입력 __");
            String opt = System.console().readLine();

            switch (opt) {
                case "1":
                    placeDAO.selectPlace();
                    break;
                case "2":
                    miniDAO.selectRandomPlaceList();
                    break;
                case "3":
                    miniDAO.selectAreaPlaceList();
                    break;
                case "4":
                    placeDAO.selectRatePlaceList();
                    break;
                case "5":
                    placeDAO.updateRatePlace();
                    break;
                case "X", "x":
                    run = false;
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 메뉴 출력
     */
    public static void showMenu() {
        System.out.println("가능한 기능들을 보여드릴게요.");
        System.out.println("1- 맛집 검색");
        System.out.println("2- 지역별 맛집 3곳 뽑기");
        System.out.println("3- 지역별 맛집 전체 보기");
        System.out.println("4- 평점 높은 순으로 맛집 보기");

        System.out.println("5- 맛집 평점 수정하기");
        System.out.println("x- 나가기");
        System.out.println("-".repeat(35));
    }

}
