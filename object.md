JDK类库的根类：Object

2.1、这个老祖宗类中的方法我们需要先研究一下，因为这些方法都是所有子类通用的。
任何一个类默认继承Object。就算没有直接继承，最终也会间接继承。

2.2、Object类当中有哪些常用的方法？
  我们去哪里找这些方法呢？
    第一种方法：去源代码当中。（但是这种方式比较麻烦，源代码也比较难）
    第二种方法：去查阅java的类库的帮助文档。

  什么是API？
    应用程序编程接口。（Application Program Interface）
    整个JDK的类库就是一个javase的API。
    每一个API都会配置一套API帮助文档。
    SUN公司提前写好的这套类库就是API。（一般每一份API都对应一份API帮助文档。）

    protected Object clone()   // 负责对象克隆的。
    int hashCode()	// 获取对象哈希值的一个方法。
    boolean equals(Object obj)  // 判断两个对象是否相等
    String toString()  // 将对象转换成字符串形式
    protected void finalize()  // 垃圾回收器负责调用的方法，java8后已经无这一方法。

2.3、toString()方法
  以后所有类的toString()方法是需要重写的。
  重写规则，越简单越明了就好。

  System.out.println(引用); 这里会自动调用“引用”的toString()方法。

  String类是SUN写的，toString方法已经重写了。

2.4、equals()方法
  以后所有类的equals方法也需要重写，因为Object中的equals方法比较
  的是两个对象的内存地址，我们应该比较内容，所以需要重写。

  重写规则：自己定，主要看是什么和什么相等时表示两个对象相等。

  基本数据类型比较实用：==
  对象和对象比较：调用equals方法

  String类是SUN编写的，所以String类的equals方法重写了。
  以后判断两个字符串是否相等，最好不要使用==，要调用字符串对象的equals方法。

  注意：重写equals方法的时候要彻底。

2.5、finalize()方法。
  这个方法是protected修饰的，在Object类中这个方法的源代码是？
    protected void finalize() throws Throwable { }

```java
//重写toString方法
class MyTime{
	int year;
	int month;
	int day;

	public MyTime(){
	
	}

	public MyTime(int year, int month, int day){
		this.year = year;
		this.month = month;
		this.day = day;
	}

	// 重写toString()方法
	// 这个toString()方法怎么重写呢？
	// 越简洁越好，可读性越强越好。
	// 向简洁的、详实的、易阅读的方向发展
	public String toString(){
		//return this.year + "年" + this.month + "月" + this.day + "日";
		return this.year + "/" + this.month + "/" + this.day;
	}
}
```

```java
//重写equals方法
class Student{
	// 学号
	int no; //基本数据类型，比较时使用：==
	// 所在学校
	String school; //引用数据类型，比较时使用：equals方法。

	public Student(){}
	public Student(int no,String school){
		this.no = no;
		this.school = school;
	}

	// 重写toString方法
	public String toString(){
		return "学号" + no + "，所在学校名称" + school;
	}

	// 重写equals方法
	// 需求：当一个学生的学号相等，并且学校相同时，表示同一个学生。
	// 思考：这个equals该怎么重写呢？
	// equals方法的编写模式都是固定的。架子差不多。
	public boolean equals(Object obj){
		if(obj == null || !(obj instanceof Student)) return false;
		if(this == obj) return true;
		Student s = (Student)obj;
		return this.no == s.no && this.school.equals(s.school);

		//字符串用双等号比较可以吗？
		// 不可以
		//return this.no == s.no && this.school == s.school;
	}
}
```
