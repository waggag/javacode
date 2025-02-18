package cn.waggag.reflect;

public class User {
	private int id;
	private int age;
	private String name;
	//javabean必须要有无参构造,否则用开源框架会出错
	public User() {
		
	}
	public User(int id, int age, String name) {
		super();
		this.id = id;
		this.age = age;
		this.name = name;
	}
	
	public User(int id, int age) {
		super();
		this.id = id;
		this.age = age;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
