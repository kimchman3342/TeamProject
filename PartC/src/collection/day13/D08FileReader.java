package collection.day13;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class D08FileReader {

    public static void main(String[] args) {

        long start = System.currentTimeMillis(); //1000분의 1초
        // readByChar();
        // readByCharBuffer();
        readByScanner();
        long end = System.currentTimeMillis();
        System.out.println(String.format("실행 소요 시간 : %,d ms",(end-start)));
       

    }

    
    public static void readByChar(){
        // String filePath = "단어장.txt";
        String filePath = "C:\\Windows\\PFRO.log";
        //문자열 저장하고 변경할 수 있는 가변 객체
        StringBuilder content = new StringBuilder();
        int charValue;
        int count=0;

       try( FileReader fr = new FileReader(filePath)){

         while ((charValue = fr.read()) != -1) {    //read는 문자단위 입력. 문자를 1개씩 읽음(입력)
             // int 값을 char로 변환하여 StringBuilder에 추가
             System.out.print((char)charValue);       //문자단위이므로 표준출력가능
             //파일에서 줄바꿈 2바이트 \r\n (10과 13)
             //int 값을 char로 변환하여 StringBuilder에 추가
             content.append((char)charValue);
             count++;
        }   
    } catch (Exception e) { }
        // FileReader 닫기
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        // 파일에서 읽은 내용 출력
        System.out.println(content.toString());
        System.out.println("읽은 문자수 : "+count);
    }

    public static void readByCharArray(){
        //String filePath = "단어장.txt";
        String filePath = "C:\\Windows\\PFRO.log";
        StringBuilder content = new StringBuilder();
        char[] charValues = new char[256];
        int count=0; int charCount=0;
        int b;

       try( FileReader fr = new FileReader(filePath)){
        
         while ((b = fr.read(charValues,0,256)) != -1) {        //read(배열이름,시작인덱스,최대문자길이)
            //읽은 문자의 개수는 리턴.b에 저장.
            System.out.println("읽은 내용 : " + new String(charValues,0,b));
                                                //String 생성자 : 배열이름, 시작위치, 길이 를 인자로 문자열만듭니다. 
            // int 값을 char로 변환하여 StringBuilder에 추가
             content.append(charValues,0,b); 
             charCount+=b;      //문자의 개수를 세는 수식
             count++;           //while 반복 횟수 수식
        }   
    } catch (Exception e) { }
        // FileReader 닫기

        // 파일에서 읽은 내용 출력
        System.out.println("~~~~~~~~~~~~~~~~~~~~");
        System.out.println("읽은 문자수 : " + charCount);
        System.out.println("반복 횟수 : " + count);
    }

    public static void readByCharBuffer(){
        String filePath = "C:\\Windows\\PFRO.log";   
        //한 줄이 256문자보다 많이 적으면 위의 char 배열보다 시간은 더 소요될수 있습니다.      
       // char[] charValues = new char[256];
        int count=0;       //읽은 라인수. 문자기반 버퍼스트림은 라인(줄) 단위로 입출력 기능이 있습니다.
        int b;
        try(
           BufferedReader br = new BufferedReader(new FileReader(filePath))
           ){
               String line;  //라인 단위로 읽어서 저장할 변수
               while ((line = br.readLine())!=null) {          //readLine 은 String 리턴
                   System.out.println(line);
                   count++;
                   
               }

            }catch(IOException e){
               System.out.println("파일 입력 예외 : " + e.getMessage());
            }
            System.out.println("읽어온 라인 수 : " + count);
   }

            public static void readByScanner(){
                String filePath = "단어장.txt";
                int count=0;

                // Scanner 클래스 : 한줄씩 읽어오는 nextLine() 메소드, 2) 구분기호로 분리해서 읽어오는 방법
                try( Scanner fc = new Scanner(new FileReader(filePath))){

                    fc.useDelimiter(",|\\n");   // 구분기호(delimiter)를 , 또는 (|) 엔터

                    // 파일의 끝까지 반복
                    while (fc.hasNext()) {      // 구분기호로 분리된 데이터가
                        String token = fc.next();   // next 메소드로 가져오기
                        System.out.println(token.trim());
                        count++;
                    }

            } catch (Exception e) { }

                System.out.println("읽은 단어수 : "+count);
            }
}