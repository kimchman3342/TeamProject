package vo;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class AreaUnitVo {
    private int area_unit_code;
    private String unit_name;

    @Override
    public String toString() {
        return "AreaUnitVo [area_unit_code=" + area_unit_code + ", unit_name=" + unit_name + "]";
    }

}
