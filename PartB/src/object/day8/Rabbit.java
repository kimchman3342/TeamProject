package object.day8;

public class Rabbit extends Animal {
	public static final String TYPE ="강아지 ";
	
	public Rabbit() {
	}
	
	public Rabbit(String name, String color) {
		super(name,color);			// 부모클래스 생성자 호출. 인자를 전달합니다.
	}
	
	
	@Override
	public void sound() {
		System.out.println(this.color +" 토끼는 그냥 🐰🐰 조용합니다. ");

	}

	@Override
	public String toString() {
		return TYPE+"[ color = " + color + ", name = " + name + "]";
	} 
	// toString을 재정의 하지 않으면 부모 toString이 실행되고 부모의  TYPE 상수 값 출력
	// static 필드 또는 메소드는 객체의 특징이 적용되지 않습니다.
}
