package vo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class PlaceVo {
        private int place_seq;
        private String name;
        private String phone;
        private int rate;
        private String open_time;
        private String close_time;
        private String food_type;
        private String address;

        public PlaceVo(int place_seq, String name, String open_time, String close_time, String address, int rate) {

                this.place_seq = place_seq;
                this.name = name;
                this.open_time = open_time;
                this.close_time = close_time;
                this.rate = rate;
                this.address = address;
        }

        public PlaceVo(int place_seq, String name, String phone, int rate, String open_time, String close_time,
                        String food_type) {
                this.place_seq = place_seq;
                this.name = (name == null) ? "" : name;
                this.phone = (phone == null) ? "" : phone;
                this.rate = rate;
                this.open_time = (open_time == null) ? "" : open_time;
                this.close_time = (close_time == null) ? "" : close_time;
                this.food_type = food_type;
        }

        public PlaceVo(int place_seq, String name, String open_time, String close_time, String address) {
                this.place_seq = place_seq;
                this.name = name;
                this.open_time = open_time;
                this.close_time = close_time;
                this.address = address;
                // 나머지 필드는 기본값 또는 null로 설정
                this.phone = null;
                this.rate = 0;
                this.food_type = null;
        }

        @Override
        public String toString() {
                return place_seq + ", " + name + ", " + phone + ", " + rate
                                + ", " + open_time + ", " + close_time + ", " + food_type;
        }

}