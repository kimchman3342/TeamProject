package App.mainBH;

import java.util.ArrayList;
import java.util.List;

import dao.*;
import vo.*;

public class RestaurantApp {
    private TblPlaceDao placeDao = new TblPlaceDao();
    private TblPlaceAddressDao placeAddressDao = new TblPlaceAddressDao();
    private TblAreaUnitDao AreaUnitDao = new TblAreaUnitDao();
    private TblMenuDao menuDao = new TblMenuDao();
    private MenuVo menuVo = new MenuVo(0, 0, null, 0);
    private AreaUnitVo areaUnitVo = new AreaUnitVo(0, null);

    // private PlaceVo placeVo = new PlaceVo(0, null, null, 0, null, null, null);

    /*
     * TODAY TODO
     * 
     */
    public static void main(String[] args) {
        RestaurantApp app = new RestaurantApp();
        app.start();

    }// main

    public static void joinAdressBook() {
        System.out.println(".".repeat(50));
        System.out.println("[A] 이름으로 맛집 찾기     [B]지역 별로 맛집 찾기   [C]평점 순위 보기   [D] 랜덤 맛집 뽑기 ");
        System.out.println("[E] 맛집 추가                [F]맛집 수정               [G]삭제                [H]종료");
        System.out.println(".".repeat(50));
    }// joinAdressBook

    public void start() {
        boolean run = true;
        while (run) { // 메뉴 선택 반복
            joinAdressBook();
            System.out.print("선택 >>> ");
            // int select = Integer.parseInt(System.console().readLine());
            String select = System.console().readLine();
            switch (select) {
                case "A", "a":
                    System.out.println("[A]이름으로 맛집 찾기");
                    System.out.println("찾으시는 맛집 이름을 입력해주세요__");
                    String name = System.console().readLine();
                    System.out.println(placeDao.findName(name));
                    break;

                case "B", "b":
                    System.out.println("[B] 지역 별로 맛집 찾기");
                    System.out.println("맛집 목록[1.서울 2.인천 3. 경기 4. 대구 5. 광주 6. 제주]");
                    System.out.println("찾는 맛집의 지역명을 입력해주세요__");
                    String address = System.console().readLine();
                    List<PlaceAddressVo> addressList = placeAddressDao.findArea(address);
                    for (PlaceAddressVo vo : addressList)
                        System.out.println(vo);
                    break;

                case "C", "c":
                    System.out.println("[C] 평점 순위 보기");
                    List<PlaceVo> list = placeDao.showRate(1);
                    if (list.size() > 0) {
                        for (PlaceVo vo : list) {
                            System.out.println(vo.getPlace_seq() + "\t" + vo.getName() + "\t" + vo.getRate() + "\t"
                                    + vo.getOpen_time()
                                    + "\t" + vo.getClose_time() + "\t" + vo.getAddress());
                        }
                    }
                    break;

                case "D", "d":
                    System.out.println("[D] 랜덤 맛집 뽑기");
                    System.out.println("지역을 입력해주세요 [1.서울 2.인천 3. 경기 4. 대구 5. 광주 6. 제주]");
                    String place = System.console().readLine();
                    System.out.println("리스트 몇개를 출력할지 알려주세요");
                    int time = Integer.parseInt(System.console().readLine());
                    List<PlaceVo> random = placeDao.randomRestaurant(place, time);
                    if (random.size() > 0) {

                        for (PlaceVo vo : random) {
                            System.out.println(vo.getName() + "\t" + vo.getOpen_time() + "\t" + vo.getClose_time()
                                    + "\t" + vo.getAddress());
                        }
                    }
                    break;

                case "E", "e":
                    System.out.println("[E] 맛집 추가");
                    System.out.println("맛집을 추가합니다.");
                    System.out.print("추가할 맛집이름을 입력해주세요__");
                    name = System.console().readLine();

                    break;

                case "F", "f":
                    System.out.println("가게 평점을 수정하겠습니다.");
                    System.out.println("맛집 번호를 입력해주세요__");
                    name = System.console().readLine();
                    System.out.println("수정할 평점을 입력해주세요__");
                    double newRate = Double.parseDouble(System.console().readLine());
                    if (newRate != 0) {
                        placeDao.modifyRate(name, newRate);
                        System.out.println("가게 평점이 수정되었습니다.");
                    }
                    break;

                case "G", "g":
                    System.out.println("맛집을 삭제하겠습니다.");
                    System.out.println(".".repeat(50));
                    System.out.println("주소록에서 삭제할 맛집 번호를 알려주세요");
                    System.out.print("맛집 번호 (100??? or 추가된 인덱스번호) >>> ");
                    int place_seq = Integer.parseInt(System.console().readLine());
                    if (place_seq >= 100001 && place_seq <= 100120) {
                        placeAddressDao.deletePlaceAddress(place_seq);
                        System.out.println("맛집이 추방되었습니다. 새로운 맛집을 탐방해주세요");
                    } else {
                        System.out.println("잘못된 번호입니다. 다시 입력해주세요");
                    }
                    break;

                case "H", "h":
                    System.out.println("[H] 종료");
                    System.out.println("프로그램을 종료합니다");
                    run = false;
                    break;

                default:
                    System.out.println("잘못된 알파벳 입력입니다. 다시 확인하시고 입력해주세요.");
                    break;
            }// switch
        } // while
    }// start

}
