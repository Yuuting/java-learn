# 反射

1、反射机制
	
1.1、反射机制有什么用？

通过java语言中的反射机制可以操作字节码文件。有点类似于黑客。（可以读和修改字节码文件。）通过反射机制可以操作代码片段。（class文件。）

1.2、反射机制的相关类在哪个包下？

java.lang.reflect.*;

1.3、反射机制相关的重要的类有哪些？

java.lang.Class：代表整个字节码，代表一个类型，代表整个类。

java.lang.reflect.Method：代表字节码中的方法字节码。代表类中的方法。

java.lang.reflect.Constructor：代表字节码中的构造方法字节码。代表类中的构造方法

java.lang.reflect.Field：代表字节码中的属性字节码。代表类中的成员变量（静态变量+实例变量）。
```java
//java.lang.Class：
  public class User{
    // Field
    int no;

    // Constructor
    public User(){

    }
    public User(int no){
      this.no = no;
    }

    // Method
    public void setNo(int no){
      this.no = no;
    }
    public int getNo(){
      return no;
    }
```
1.4、要操作一个类的字节码，需要首先获取到这个类的字节码，怎么获取java.lang.Class实例？

三种方式

第一种：Class c = Class.forName("完整类名带包名");

Class.forName()

静态方法；
方法的参数是一个字符串；
字符串需要的是一个完整类名；
完整类名必须带有包名；java.lang包也不能省略。

第二种：Class c = 对象.getClass();

第三种：Class c = 任何类型.class;

1.5、获取到Class，能干什么？

通过Class的newInstance()方法来实例化对象。

注意：newInstance()方法内部实际上调用了无参数构造方法，必须保证无参构造存在才可以。

1.6、Class.forName()发生了什么？

记住，重点：如果你只是希望一个类的静态代码块执行，其它代码一律不执行，你可以使用：

Class.forName("完整类名");

这个方法的执行会导致类加载，类加载时，静态代码块执行。

提示：
后面JDBC技术的时候我们还需要。

1.7、验证反射机制的灵活性。

java代码写一遍，再不改变java源代码的基础之上，可以做到不同对象的实例化。非常之灵活。（符合OCP开闭原则：对扩展开放，对修改关闭。）

后期你们要学习的是高级框架，而工作过程中，也都是使用高级框架，包括： ssh ssm  Spring SpringMVC MyBatis Spring Struts Hibernate...

这些高级框架底层实现原理：都采用了反射机制。所以反射机制还是重要的。学会了反射机制有利于你理解剖析框架底层的源代码。

1.8 研究一下文件路径的问题。

怎么获取一个文件的绝对路径。以下讲解的这种方式是通用的。但前提是：文件需要在类路径下。才能用这种方式。
```java
public class AboutPath {
    public static void main(String[] args) throws Exception{
        // 这种方式的路径缺点是：移植性差，在IDEA中默认的当前路径是project的根。
        // 这个代码假设离开了IDEA，换到了其它位置，可能当前路径就不是project的根了，这时这个路径就无效了。
        //FileReader reader = new FileReader("chapter25/classinfo2.properties");

        // 接下来说一种比较通用的一种路径。即使代码换位置了，这样编写仍然是通用的。
        // 注意：使用以下通用方式的前提是：这个文件必须在类路径下。
        // 什么类路径下？方式在src下的都是类路径下。【记住它】
        // src是类的根路径。
        /*
        解释：
            Thread.currentThread() 当前线程对象
            getContextClassLoader() 是线程对象的方法，可以获取到当前线程的类加载器对象。
            getResource() 【获取资源】这是类加载器对象的方法，当前线程的类加载器默认从类的根路径下加载资源。
         */
        String path = Thread.currentThread().getContextClassLoader()
                .getResource("classinfo2.properties").getPath(); // 这种方式获取文件绝对路径是通用的。

        // 采用以上的代码可以拿到一个文件的绝对路径。
        // /C:/Users/Administrator/IdeaProjects/javase/out/production/chapter25/classinfo2.properties
        System.out.println(path);
```
1.9、直接以流的形式返回。

InputStream reader = Thread.currentThread().getContextClassLoader().getResourceAsStream("classinfo2.properties");

1.10、java.util包下提供了一个资源绑定器，便于获取属性配置文件中的内容。

使用以下这种方式的时候，属性配置文件xxx.properties必须放到类路径下。

```java
public class ResourceBundleTest {
    public static void main(String[] args) {

        // 资源绑定器，只能绑定xxx.properties文件。并且这个文件必须在类路径下。文件扩展名也必须是properties
        // 并且在写路径的时候，路径后面的扩展名不能写。
        //ResourceBundle bundle = ResourceBundle.getBundle("classinfo2");

        ResourceBundle bundle = ResourceBundle.getBundle("com/bjpowernode/java/bean/db");

        String className = bundle.getString("className");
        System.out.println(className);

    }
}
```
2、关于JDK中自带的类加载器

2.1、什么是类加载器？

专门负责加载类的命令/工具。
ClassLoader

2.2、JDK中自带了3个类加载器

启动类加载器:rt.jar

扩展类加载器:ext/*.jar

应用类加载器:classpath

2.3、假设有这样一段代码：
String s = "abc";

代码在开始执行之前，会将所需要类全部加载到JVM当中。通过类加载器加载，看到以上代码类加载器会找String.class文件，找到就加载，那么是怎么进行加载的呢？

首先通过“启动类加载器”加载。

注意：启动类加载器专门加载：C:\Program Files\Java\jdk1.8.0_101\jre\lib\rt.jar\rt.jar中都是JDK最核心的类库。

如果通过“启动类加载器”加载不到的时候，会通过"扩展类加载器"加载。

注意：扩展类加载器专门加载：C:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\*.jar


如果“扩展类加载器”没有加载到，那么会通过“应用类加载器”加载。

注意：应用类加载器专门加载：classpath中的类。

2.4、java中为了保证类加载的安全，使用了双亲委派机制。优先从启动类加载器中加载，这个称为“父”.“父”无法加载到，再从扩展类加载器中加载，这个称为“母”。双亲委派。如果都加载不到，才会考虑从应用类加载器中加载。直到加载到为止。

3、反射Student类当中所有的Field
```java
public class ReflectTest05 {
    public static void main(String[] args) throws Exception{

        // 获取整个类
        Class studentClass = Class.forName("com.bjpowernode.java.bean.Student");

        //com.bjpowernode.java.bean.Student
        String className = studentClass.getName();
        System.out.println("完整类名：" + className);

        String simpleName = studentClass.getSimpleName();
        System.out.println("简类名：" + simpleName);

        // 获取类中所有的public修饰的Field
        Field[] fields = studentClass.getFields();
        System.out.println(fields.length); // 测试数组中只有1个元素
        // 取出这个Field
        Field f = fields[0];
        // 取出这个Field它的名字
        String fieldName = f.getName();
        System.out.println(fieldName);

        // 获取所有的Field
        Field[] fs = studentClass.getDeclaredFields();
        System.out.println(fs.length); // 4

        System.out.println("==================================");
        // 遍历
        for(Field field : fs){
            // 获取属性的修饰符列表
            int i = field.getModifiers(); // 返回的修饰符是一个数字，每个数字是修饰符的代号！！！
            System.out.println(i);
            // 可以将这个“代号”数字转换成“字符串”吗？
            String modifierString = Modifier.toString(i);
            System.out.println(modifierString);
            // 获取属性的类型
            Class fieldType = field.getType();
            //String fName = fieldType.getName();
            String fName = fieldType.getSimpleName();
            System.out.println(fName);
            // 获取属性的名字
            System.out.println(field.getName());
        }
    }
}
```
4、通过反射机制，反编译一个类的属性Field
```java

public class ReflectTest06 {
    public static void main(String[] args) throws Exception{

        // 创建这个是为了拼接字符串。
        StringBuilder s = new StringBuilder();

        Class studentClass = Class.forName("java.lang.Thread");

        s.append(Modifier.toString(studentClass.getModifiers()) + " class " + studentClass.getSimpleName() + " {\n");

        Field[] fields = studentClass.getDeclaredFields();
        for(Field field : fields){
            s.append("\t");
            s.append(Modifier.toString(field.getModifiers()));
            s.append(" ");
            s.append(field.getType().getSimpleName());
            s.append(" ");
            s.append(field.getName());
            s.append(";\n");
        }

        s.append("}");
        System.out.println(s);

    }
}
```

5、必须掌握：怎么通过反射机制访问一个java对象的属性？

给属性赋值set、获取属性的值get
```java
public class ReflectTest04 {
    public static void main(String[] args) throws Exception {
        Class c=Class.forName("com.yuting.javase.reflect.Student");
        Object o=c.newInstance();
        Field field=c.getDeclaredField("no");
        field.setAccessible(true);
        field.set(o,222);
        System.out.println(field.get(o));

    }
}
```
6、反射Method、反编译Method
```java
public class ReflectTest08 {
    public static void main(String[] args) throws Exception{

        // 获取类了
        Class userServiceClass = Class.forName("com.bjpowernode.java.service.UserService");

        // 获取所有的Method（包括私有的！）
        Method[] methods = userServiceClass.getDeclaredMethods();
        //System.out.println(methods.length); // 2

        // 遍历Method
        for(Method method : methods){
            // 获取修饰符列表
            System.out.println(Modifier.toString(method.getModifiers()));
            // 获取方法的返回值类型
            System.out.println(method.getReturnType().getSimpleName());
            // 获取方法名
            System.out.println(method.getName());
            // 方法的修饰符列表（一个方法的参数可能会有多个。）
            Class[] parameterTypes = method.getParameterTypes();
            for(Class parameterType : parameterTypes){
                System.out.println(parameterType.getSimpleName());
            }
        }
    }
}
```
```java
public class ReflectTest09 {
    public static void main(String[] args) throws Exception{
        StringBuilder s = new StringBuilder();
        //Class userServiceClass = Class.forName("com.bjpowernode.java.service.UserService");
        Class userServiceClass = Class.forName("java.lang.String");
        s.append(Modifier.toString(userServiceClass.getModifiers()) + " class "+userServiceClass.getSimpleName()+" {\n");

        Method[] methods = userServiceClass.getDeclaredMethods();
        for(Method method : methods){
            //public boolean login(String name,String password){}
            s.append("\t");
            s.append(Modifier.toString(method.getModifiers()));
            s.append(" ");
            s.append(method.getReturnType().getSimpleName());
            s.append(" ");
            s.append(method.getName());
            s.append("(");
            // 参数列表
            Class[] parameterTypes = method.getParameterTypes();
            for(Class parameterType : parameterTypes){
                s.append(parameterType.getSimpleName());
                s.append(",");
            }
            // 删除指定下标位置上的字符
            s.deleteCharAt(s.length() - 1);
            s.append("){}\n");
        }

        s.append("}");
        System.out.println(s);
    }
}
```
7、重点：必须掌握，通过反射机制怎么调用一个对象的方法？

五颗星*****

反射机制，让代码很具有通用性，可变化的内容都是写到配置文件当中，

将来修改配置文件之后，创建的对象不一样了，调用的方法也不同了，

但是java代码不需要做任何改动。这就是反射机制的魅力。
```java
public class ReflectTest10 {
    public static void main(String[] args) throws Exception{
        // 不使用反射机制，怎么调用方法
        // 创建对象
        UserService userService = new UserService();
        // 调用方法
        /*
        要素分析：
            要素1：对象userService
            要素2：login方法名
            要素3：实参列表
            要素4：返回值
         */
        boolean loginSuccess = userService.login("admin","123");
        //System.out.println(loginSuccess);
        System.out.println(loginSuccess ? "登录成功" : "登录失败");

        // 使用反射机制来调用一个对象的方法该怎么做？
        Class userServiceClass = Class.forName("com.bjpowernode.java.service.UserService");
        // 创建对象
        Object obj = userServiceClass.newInstance();
        // 获取Method
        Method loginMethod = userServiceClass.getDeclaredMethod("login", String.class, String.class);
        //Method loginMethod = userServiceClass.getDeclaredMethod("login", int.class);
        // 调用方法
        // 调用方法有几个要素？ 也需要4要素。
        // 反射机制中最最最最最重要的一个方法，必须记住。
        /*
        四要素：
        loginMethod方法
        obj对象
        "admin","123" 实参
        retValue 返回值
         */
        Object retValue = loginMethod.invoke(obj, "admin","123123");
        System.out.println(retValue);
    }
}
```
8、反编译一个类的Constructor构造方法
```java
public class ReflectTest11 {
    public static void main(String[] args) throws Exception{
        StringBuilder s = new StringBuilder();
        Class vipClass = Class.forName("java.lang.String");
        s.append(Modifier.toString(vipClass.getModifiers()));
        s.append(" class ");
        s.append(vipClass.getSimpleName());
        s.append("{\n");

        // 拼接构造方法
        Constructor[] constructors = vipClass.getDeclaredConstructors();
        for(Constructor constructor : constructors){
            //public Vip(int no, String name, String birth, boolean sex) {
            s.append("\t");
            s.append(Modifier.toString(constructor.getModifiers()));
            s.append(" ");
            s.append(vipClass.getSimpleName());
            s.append("(");
            // 拼接参数
            Class[] parameterTypes = constructor.getParameterTypes();
            for(Class parameterType : parameterTypes){
                s.append(parameterType.getSimpleName());
                s.append(",");
            }
            // 删除最后下标位置上的字符
            if(parameterTypes.length > 0){
                s.deleteCharAt(s.length() - 1);
            }
            s.append("){}\n");
        }

        s.append("}");
        System.out.println(s);
    }
}
```

9、通过反射机制调用构造方法实例化java对象。（这个不是重点）
```java
public class ReflectTest12 {
    public static void main(String[] args) throws Exception{
        // 不使用反射机制怎么创建对象
        Vip v1 = new Vip();
        Vip v2 = new Vip(110, "zhangsan", "2001-10-11", true);

        // 使用反射机制怎么创建对象呢？
        Class c = Class.forName("com.bjpowernode.java.bean.Vip");
        // 调用无参数构造方法
        Object obj = c.newInstance();
        System.out.println(obj);

        // 调用有参数的构造方法怎么办？
        // 第一步：先获取到这个有参数的构造方法
        Constructor con = c.getDeclaredConstructor(int.class, String.class, String.class,boolean.class);
        // 第二步：调用构造方法new对象
        Object newObj = con.newInstance(110, "jackson", "1990-10-11", true);
        System.out.println(newObj);

        // 获取无参数构造方法
        Constructor con2 = c.getDeclaredConstructor();
        Object newObj2 = con2.newInstance();
        System.out.println(newObj2);
    }
}
```
10、重点：给你一个类，怎么获取这个类的父类，已经实现了哪些接口？
```java
public class ReflectTest13 {
    public static void main(String[] args) throws Exception{

        // String举例
        Class stringClass = Class.forName("java.lang.String");

        // 获取String的父类
        Class superClass = stringClass.getSuperclass();
        System.out.println(superClass.getName());

        // 获取String类实现的所有接口（一个类可以实现多个接口。）
        Class[] interfaces = stringClass.getInterfaces();
        for(Class in : interfaces){
            System.out.println(in.getName());
        }
    }
}
```
