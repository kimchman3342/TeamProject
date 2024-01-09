package object.day8;

public class Cat extends Animal {
	
	
	public Cat(String color) {
		this.color = color;			// 부모클래스 생성자 호출. 인자를 전달합니다.
	}
	
	
	@Override
	public void sound() {
		System.out.println(this.color +" 고양이는 야옹🐱🐱하고 소리냅니다. ");

	}

	@Override
	public String toString() {
		return TYPE+"[ color = " + color + ", name = " + name + "]";
	} 
	// toString을 재정의 하지 않으면 부모 toString이 실행되고 부모의  TYPE 상수 값 출력
	// static 필드 또는 메소드는 객체의 특징이 적용되지 않습니다.
}
