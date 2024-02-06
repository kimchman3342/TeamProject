package App.HY;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 기능5. 평점 수정
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class PlaceVO {
    private int place_seq;
    private String name;
    private String phone;
    private float rate;
    private String open_time;
    private String close_time;
    private String food_type;

    @Override
    public String toString() {
        // String msg = String.format("%-10s %-50s %10s~%10s %-100s %30s %-10s" ,
        // "분류번호","가게","연락처", "평점", 시작 시간", "종료 시간","종류");
        String msg = String.format("%-10d %-20s(%1.2f)\t%15s\t%s ~ %s\t%-10s", place_seq, name, rate, phone, open_time,
                close_time, food_type);
        return msg;

    }

}
