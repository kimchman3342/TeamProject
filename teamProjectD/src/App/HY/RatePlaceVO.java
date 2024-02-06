package App.HY;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 기능4. 평점순(5점 만점)으로 맛집 보여주기
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class RatePlaceVO {
    private int place_seq;
    private String name;
    private String open_time;
    private String close_time;
    private String address;
    private float rate;

    @Override
    public String toString() {
        // String msg = String.format("%-10s %-50s %10s %10s~%10s %-100s %30s %-10s" ,
        // "분류번호","상호명", "평점", "시작 시간", "종료 시간", "주소");
        String msg = String.format("%8d%15.2s(%s~%s)\t %1.2f점 \t %2.30s", place_seq, name, open_time, close_time, rate,
                address);
        return msg;

    }

}
