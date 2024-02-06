package App.HY.vo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 기능2. 지역이름으로 검색해서 그날의 지역 맛집 랜덤으로 3개만 보여주기
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class RandomSchPlaceVO {

    private int place_seq;
    private String name;
    private String open_time;
    private String close_time;
    private String address;
    private float rate;

    @Override
    public String toString() {
        // String msg = String.format("%-10s %-50s %10s~%10s %-100s %30s %-10s" ,
        // "분류번호","상호명","시작 시간", "종료 시간", "주소",);
        String msg = String.format("%d- %10.1f %15.2s(%s~%s)\t%2.30s", place_seq, rate, name, open_time, close_time,
                address);
        return msg;

    }

}
