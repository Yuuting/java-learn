# 异常
1、什么是异常，java提供异常处理机制有什么用？

以下程序执行过程中发生了不正常的情况，而这种不正常的情况叫做：异常

java语言是很完善的语言，提供了异常的处理方式，以下程序执行过程中出现了不正常情况，java把该异常信息打印输出到控制台，供程序员参考。程序员看到异常信息之后，可以对程序进行修改，让程序更加的健壮。

什么是异常：程序执行过程中的不正常情况。

异常的作用：增强程序的健壮性。

2、异常在java中以类的形式存在，每一个异常类都可以创建异常对象。

// 通过“异常类”实例化“异常对象”

NumberFormatException nfe = new NumberFormatException("数字格式化异常！");

3.java的异常处理机制

	3.1、异常在java中以类和对象的形式存在。那么异常的继承结构是怎样的？
		Object
		Object下有Throwable（可抛出的）
		Throwable下有两个分支：Error（不可处理，直接退出JVM）和Exception（可处理的）
		Exception下有两个分支：
			Exception的直接子类：编译时异常（要求程序员在编写程序阶段必须预先对这些异常进行处理，如果不处理编译器报错，因此得名编译时异常。）。
			RuntimeException：运行时异常。（在编写程序阶段程序员可以预先处理，也可以不管，都行。）
	
	3.2、编译时异常和运行时异常，都是发生在运行阶段。编译阶段异常是不会发生的。
	编译时异常因为什么而得名？
		因为编译时异常必须在编译(编写)阶段预先处理，如果不处理编译器报错，因此得名。
		所有异常都是在运行阶段发生的。因为只有程序运行阶段才可以new对象。
		因为异常的发生就是new异常对象。
	
	3.3、编译时异常和运行时异常的区别？

		编译时异常一般发生的概率比较高。
			举个例子：
				你看到外面下雨了，倾盆大雨的。
				你出门之前会预料到：如果不打伞，我可能会生病（生病是一种异常）。
				而且这个异常发生的概率很高，所以我们出门之前要拿一把伞。
				“拿一把伞”就是对“生病异常”发生之前的一种处理方式。

				对于一些发生概率较高的异常，需要在运行之前对其进行预处理。

		运行时异常一般发生的概率比较低。
			举个例子：
				小明走在大街上，可能会被天上的飞机轮子砸到。
				被飞机轮子砸到也算一种异常。
				但是这种异常发生概率较低。
				在出门之前你没必要提前对这种发生概率较低的异常进行预处理。
				如果你预处理这种异常，你将活的很累。
		
		假设你在出门之前，你把能够发生的异常都预先处理，你这个人会更加
		的安全，但是你这个人活的很累。
		
		假设java中没有对异常进行划分，没有分为：编译时异常和运行时异常，
		所有的异常都需要在编写程序阶段对其进行预处理，将是怎样的效果呢？
			首先，如果这样的话，程序肯定是绝对的安全的。
			但是程序员编写程序太累，代码到处都是处理异常
			的代码。
	
	3.4、编译时异常还有其他名字：
		受检异常：CheckedException
		受控异常
	
	3.5、运行时异常还有其它名字：
		未受检异常：UnCheckedException
		非受控异常
	
	3.6、再次强调：所有异常都是发生在运行阶段的。

	3.7、Java语言中对异常的处理包括两种方式：

		第一种方式：在方法声明的位置上，使用throws关键字，抛给上一级。
			谁调用我，我就抛给谁。抛给上一级。

		第二种方式：使用try..catch语句进行异常的捕捉。
			这件事发生了，谁也不知道，因为我给抓住了。

		举个例子：
			我是某集团的一个销售员，因为我的失误，导致公司损失了1000元，
			“损失1000元”这可以看做是一个异常发生了。我有两种处理方式，
			第一种方式：我把这件事告诉我的领导【异常上抛】
			第二种方式：我自己掏腰包把这个钱补上。【异常的捕捉】
			
			张三 --> 李四 ---> 王五 --> CEO
		
		思考：
			异常发生之后，如果我选择了上抛，抛给了我的调用者，调用者需要
			对这个异常继续处理，那么调用者处理这个异常同样有两种处理方式。

	3.8、注意：Java中异常发生之后如果一直上抛，最终抛给了main方法，main方法继续
	向上抛，抛给了调用者JVM，JVM知道这个异常发生，只有一个结果。终止java程序的执行。
  
4、 只要异常没有捕捉，采用上报的方式，此方法的后续代码不会执行。
另外需要注意，try语句块中的某一行出现异常，该行后面的代码不会执行。
try..catch捕捉异常之后，后续代码可以执行。

在以后的开发中，处理编译时异常，应该上报还是捕捉呢，怎么选？
如果希望调用者来处理，选择throws上报。其它情况使用捕捉的方式。

5、异常对象有两个非常重要的方法：

获取异常简单的描述信息：String msg = exception.getMessage();

打印异常追踪的堆栈信息：exception.printStackTrace();

6、关于try..catch中的finally子句：

1、在finally子句中的代码是最后执行的，并且是一定会执行的，即使try语句块中的代码出现了异常。finally子句必须和try一起出现，不能单独编写。

2、finally语句通常使用在哪些情况下呢？
通常在finally语句块中完成资源的释放/关闭。因为finally中的代码比较有保障。即使try语句块中的代码出现异常，finally中代码也会正常执行。

7、特例
```java
/*
finally面试题
 */
public class ExceptionTest13 {
    public static void main(String[] args) {
        int result = m();
        System.out.println(result); //100
    }

    /*
    java语法规则（有一些规则是不能破坏的，一旦这么说了，就必须这么做！）：
        java中有一条这样的规则：
            方法体中的代码必须遵循自上而下顺序依次逐行执行（亘古不变的语法！）
        java中海油一条语法规则：
            return语句一旦执行，整个方法必须结束（亘古不变的语法！）
     */
    public static int m(){
        int i = 100;
        try {
            // 这行代码出现在int i = 100;的下面，所以最终结果必须是返回100
            // return语句还必须保证是最后执行的。一旦执行，整个方法结束。
            return i;
        } finally {
            i++;
        }
    }
}

/*
反编译之后的效果
public static int m(){
    int i = 100;
    int j = i;
    i++;
    return j;
}
 */
 ```
8、final finally finalize有什么区别？

final 关键字

final修饰的类无法继承，final修饰的方法无法覆盖，final修饰的变量不能重新赋值。

finally 关键字

和try一起联合使用。finally语句块中的代码是必须执行的。

finalize 标识符

是一个Object类中的方法名。这个方法是由垃圾回收器GC负责调用的。

9、自定义异常：

9.1、SUN提供的JDK内置的异常肯定是不够的用的。在实际的开发中，有很多业务，这些业务出现异常之后，JDK中都是没有的。和业务挂钩的。那么异常类我们程序员可以自己定义吗？
    
可以。

9.2、Java中怎么自定义异常呢？

两步：

第一步：编写一个类继承Exception或者RuntimeException.

第二步：提供两个构造方法，一个无参数的，一个带有String参数的。
```java
*/
public class MyException extends Exception{ // 编译时异常
    public MyException(){

    }
    public MyException(String s){
        super(s);
    }
}

/*
public class MyException extends RuntimeException{ // 运行时异常

}
```
10、异常在开发中的作用：见栈改良程序

11、之前在讲解方法覆盖的时候，当时遗留了一个问题？

重写之后的方法不能比重写之前的方法抛出更多（更宽泛）的异常，可以更少。
```java
class Animal {
    public void doSome(){

    }

    public void doOther() throws Exception{

    }
}

class Cat extends Animal {

    // 编译正常。
    public void doSome() throws RuntimeException{

    }

    // 编译报错。
    /*public void doSome() throws Exception{

    }*/

    // 编译正常。
    /*public void doOther() {

    }*/

    // 编译正常。
    /*public void doOther() throws Exception{

    }*/

    // 编译正常。
    public void doOther() throws NullPointerException{

    }
}

/*
总结异常中的关键字：
    异常捕捉：
        try
        catch
        finally

    throws 在方法声明位置上使用，表示上报异常信息给调用者。
    throw 手动抛出异常！
```
