package App.HY.vo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 기능1. 맛집찾기 (이름, 평점, 메뉴)
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class PlaceSchVO {

    private int place_seq;
    private String name;
    private String open_time;
    private String close_time;
    private float rate;
    private String address;
    private String menu_name;
    private int price;

    @Override
    public String toString() {
        // String msg = String.format("%-10s %-50s %10s~%10s %-100s %30s %-10s" ,
        // "분류번호","상호명","시작 시간", "종료 시간", "주소", "메뉴", "가격");
        String msg = String.format("%d.\t\t%6.1f점 %-28s\t%s ~ %s \t\t%-30s\t%-15s\t%,10d",
                place_seq, rate, name, open_time, close_time, address, menu_name, price);
        return msg;

    }

}
