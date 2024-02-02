package vo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class MenuVo {
    private int menu_seq;
    private int place_seq;
    private String menu_name;
    private int price;

    @Override
    public String toString() {
        return String.format("%8d \t %-%8d %50s",
                place_seq, address_seq, address);
    }
}
