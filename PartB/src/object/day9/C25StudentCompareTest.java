package object.day9;

import java.util.Arrays;
import java.util.Comparator;

// Arrays.sort 메소드에 두 번째 인자로 Comparator 인터페이스 타입 객체를 전달하면
//             비교 기준과 정렬 방법을 실행시점에서 결정됩니다.

public class C25StudentCompareTest {
    
    public static void main(String[] args) {

        Student[] students = new Student[4];
        students[0] = new Student("장모모",23);
        students[1] = new Student("박나연",22);
        students[2] = new Student("김다현",24);
        students[3] = new Student("사나",21);

        System.out.println("초기 students 배열 : " + Arrays.toString(students) + "\n");
        // Arrays.sort(students);  // 오류 : Student 객체를 Comparable 이 아닙니다.
        
        
        // 1. ascending : 오름차순
        Comparator<Student> ageAscending = new Comparator<Student>() {
            
            @Override
            public int compare(Student o1, Student o2) {
                
                return o1.getAge() - o2.getAge();
            }

        };
        System.out.println("----------------------------------");
        Arrays.sort(students,ageAscending);
        System.out.println("나이 오름차순 정렬 후 students 배열 : " + Arrays.toString(students));
        
    //    o1(왼쪽), o2(오른쪽) 이며 오름/내림 상관없이 교환을 안해도 되는 경우에는 음수 값으로 리턴합니다.
    //    sort 메소드 정렬 알고리즘은 비교결과 "음수 리턴하면 교환하지 않고 양수 리턴하면 교환합니다."
       
       
       
        // 2. ageDecending
        // 한 번만 사용할 Comparator는 변수 선언하지 않고 바로 인자로 전달할 수 있습니다.
        Comparator<Student> ageDecending = new Comparator<Student>() {
            
            @Override
            public int compare(Student o1, Student o2) {
                
                return o2.getAge() - o1.getAge();       // 내림차순 o1 > o2이 되어야합니다.
                                                        // o2 - o1 < 0 이면 교환.
            }

        };
        
       
        Arrays.sort(students,ageDecending);
        System.out.println("나이 내림차순 정렬 후 students 배열 : " + Arrays.toString(students));

       
       
       
       
        // 3.nameAscending
    
        Arrays.sort(students,(o1,o2) -> {   // sort(Araays, Comparator)
             return o1.getName().compareTo(o2.getName());

            });
        // 위의 람다식은 Comparator 구현 클래스 정의 : 추상메소드 1개는 compare
        // 메소드의 인자는 Student 타입 o1, o2
        // 메소드 실행과 리턴은 {몸통}에 작성.

        System.out.println("이름 오름차순 정렬 후 students 배열 : " + Arrays.toString(students));
        
        // 4.nameDecending
       
        Arrays.sort(students, new Comparator<Student>() { 
            
            @Override
            public int compare(Student o1, Student o2) {
                
                return o2.getName().compareTo(o1.getName());
            }

        });
        System.out.println("이름 내림차순 정렬 후 students 배열 : " + Arrays.toString(students));
        System.out.println(" ");

        // C21번에 int 배열 오름차순은 그냥하면 됩니다. 내림차순 완성하러 갑니다~~~
    }
    
}
