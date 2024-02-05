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
        return String.format("%8d, menu_seq : %8d \t menu_name : %-50s %,10d",
                place_seq, menu_seq, menu_name, price);
    }

    public MenuVo(int menu_seq, String menu_name2, int place_seq2, int price2) {
        // TODO Auto-generated constructor stub
    }

}
