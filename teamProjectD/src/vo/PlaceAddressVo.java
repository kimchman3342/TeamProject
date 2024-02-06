package vo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class PlaceAddressVo {
    // private int address_seq;
    private String address;
    private String name;
    private String open_time;
    private String close_time;
    private String food_type;

    @Override
    public String toString() {
        String formattedString = String.format("%-20s%-35s%-20s%-20s%-20s", name, "주소 : " + address,
                "오픈 : " + open_time, "마감 : " + close_time, "가게 유형 : " + food_type);
        return formattedString;
    }

}
