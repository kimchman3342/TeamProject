package basic.day4;

// 문자열은 문자의 집합. 따라서 문자열 구성하는 문자 하나씩 가져와 배열에 저장해 봅니다.
public class A28CharArray {

    public static void main(String[] args) {
        String message = "Hello~ World~ 자바는 맛있다.";

        // 문자열의 길이 만큼 배열의 크기를 정합니다.
        char[] messageArray = new char[message.length()];

        for(int i = 0; i < message.length(); i++ ) {
            char temp = message.charAt(i);
            System.out.println(temp);
            messageArray[i] = temp;
        }

        
    }
    
}
