# String类

关于Java JDK中内置的一个类：java.lang.String

1、String表示字符串类型，属于引用数据类型，不属于基本数据类型。

2、在java中随便使用双引号括起来的都是String对象。例如："abc"，"def"，"hello world!"，这是3个String对象。

3、java中规定，双引号括起来的字符串，是不可变的，也就是说"abc"自出生到最终死亡，不可变，不能变成"abcd"，也不能变成"ab"

4、在JDK当中双引号括起来的字符串，例如："abc" "def"都是直接存储在“方法区”的“字符串常量池”当中的。

为什么SUN公司把字符串存储在一个“字符串常量池”当中呢。因为字符串在实际的开发中使用太频繁。为了执行效率，所以把字符串放到了方法区的字符串常量池当中。

5、字符串对象之间的比较不能使用“==”，"=="不保险。应该调用String类的equals方法。

6、关于String类中的构造方法。
 *  第一个：String s = new String("");
 *  第二个：String s = ""; 最常用
 *  第三个：String s = new String(char数组);
 *  第四个：String s = new String(char数组,起始下标,长度);
 *  第五个：String s = new String(byte数组);
 *  第六个：String s = new String(byte数组,起始下标,长度)

7、常用方法：
```java
public class StringTest05 {
    public static void main(String[] args) {

        // String类当中常用方法。
        //1（掌握）.char charAt(int index)
        char c = "中国人".charAt(1); // "中国人"是一个字符串String对象。只要是对象就能“点.”
        System.out.println(c); // 国

        // 2（了解）.int compareTo(String anotherString)
        // 字符串之间比较大小不能直接使用 > < ，需要使用compareTo方法。
        int result = "abc".compareTo("abc");
        System.out.println(result); //0（等于0） 前后一致  10 - 10 = 0

        int result2 = "abcd".compareTo("abce");
        System.out.println(result2); //-1（小于0） 前小后大 8 - 9 = -1

        int result3 = "abce".compareTo("abcd");
        System.out.println(result3); // 1（大于0） 前大后小 9 - 8 = 1

        // 拿着字符串第一个字母和后面字符串的第一个字母比较。能分胜负就不再比较了。
        System.out.println("xyz".compareTo("yxz")); // -1

        // 3（掌握）.boolean contains(CharSequence s)
        // 判断前面的字符串中是否包含后面的子字符串。
        System.out.println("HelloWorld.java".contains(".java")); // true
        System.out.println("http://www.baidu.com".contains("https://")); // false

        // 4（掌握）. boolean endsWith(String suffix)
        // 判断当前字符串是否以某个子字符串结尾。
        System.out.println("test.txt".endsWith(".java")); // false
        System.out.println("test.txt".endsWith(".txt")); // true
        System.out.println("fdsajklfhdkjlsahfjkdsahjklfdss".endsWith("ss")); // true

        // 5（掌握）.boolean equals(Object anObject)
        // 比较两个字符串必须使用equals方法，不能使用“==”
        // equals方法有没有调用compareTo方法？ 老版本可以看一下。JDK13中并没有调用compareTo()方法。
        // equals只能看出相等不相等。
        // compareTo方法可以看出是否相等，并且同时还可以看出谁大谁小。
        System.out.println("abc".equals("abc")); // true

        // 6（掌握）.boolean equalsIgnoreCase(String anotherString)
        // 判断两个字符串是否相等，并且同时忽略大小写。
        System.out.println("ABc".equalsIgnoreCase("abC")); // true

        // 7（掌握）.byte[] getBytes()
        // 将字符串对象转换成字节数组
        byte[] bytes = "abcdef".getBytes();
        for(int i = 0; i < bytes.length; i++){
            System.out.println(bytes[i]);
        }

        // 8（掌握）.int indexOf(String str)
        // 判断某个子字符串在当前字符串中第一次出现处的索引（下标）。
        System.out.println("oraclejavac++.netc#phppythonjavaoraclec++".indexOf("java")); // 6

        // 9（掌握）.boolean isEmpty()
        // 判断某个字符串是否为“空字符串”。底层源代码调用的应该是字符串的length()方法。
        //String s = "";
        String s = "a";
        System.out.println(s.isEmpty());

        // 10（掌握）. int length()
        // 面试题：判断数组长度和判断字符串长度不一样
        // 判断数组长度是length属性，判断字符串长度是length()方法。
        System.out.println("abc".length()); // 3

        System.out.println("".length()); // 0

        // 11（掌握）.int lastIndexOf(String str)
        // 判断某个子字符串在当前字符串中最后一次出现的索引（下标）
        System.out.println("oraclejavac++javac#phpjavapython".lastIndexOf("java")); //22

        // 12（掌握）. String replace(CharSequence target, CharSequence replacement)
        // 替换。
        // String的父接口就是：CharSequence
        String newString = "http://www.baidu.com".replace("http://", "https://");
        System.out.println(newString); //https://www.baidu.com
        // 把以下字符串中的“=”替换成“:”
        String newString2 = "name=zhangsan&password=123&age=20".replace("=", ":");
        System.out.println(newString2); //name:zhangsan&password:123&age:20

        // 13（掌握）.String[] split(String regex)
        // 拆分字符串
        String[] ymd = "1980-10-11".split("-"); //"1980-10-11"以"-"分隔符进行拆分。
        for(int i = 0; i < ymd.length; i++){
            System.out.println(ymd[i]);
        }
        String param = "name=zhangsan&password=123&age=20";
        String[] params = param.split("&");
        for(int i = 0; i <params.length; i++){
            System.out.println(params[i]);
            // 可以继续向下拆分，可以通过“=”拆分。
        }

        // 14（掌握）、boolean startsWith(String prefix)
        // 判断某个字符串是否以某个子字符串开始。
        System.out.println("http://www.baidu.com".startsWith("http")); // true
        System.out.println("http://www.baidu.com".startsWith("https")); // false

        // 15（掌握）、 String substring(int beginIndex) 参数是起始下标。
        // 截取字符串
        System.out.println("http://www.baidu.com".substring(7)); //www.baidu.com

        // 16（掌握）、String substring(int beginIndex, int endIndex)
        // beginIndex起始位置（包括）
        // endIndex结束位置（不包括）
        System.out.println("http://www.baidu.com".substring(7, 10)); //www

        // 17(掌握)、char[] toCharArray()
        // 将字符串转换成char数组
        char[] chars = "我是中国人".toCharArray();
        for(int i = 0; i < chars.length; i++){
            System.out.println(chars[i]);
        }

        // 18（掌握）、String toLowerCase()
        // 转换为小写。
        System.out.println("ABCDefKXyz".toLowerCase());

        // 19（掌握）、String toUpperCase();
        System.out.println("ABCDefKXyz".toUpperCase());

        // 20（掌握）. String trim();
        // 去除字符串前后空白
        System.out.println("           hello      world             ".trim());

        // 21（掌握）. String中只有一个方法是静态的，不需要new对象
        // 这个方法叫做valueOf
        // 作用：将“非字符串”转换成“字符串”
        //String s1 = String.valueOf(true);
        //String s1 = String.valueOf(100);
        //String s1 = String.valueOf(3.14);

        // 这个静态的valueOf()方法，参数是一个对象的时候，会自动调用该对象的toString()方法吗？
        String s1 = String.valueOf(new Customer());
        //System.out.println(s1); // 没有重写toString()方法之前是对象内存地址 com.bjpowernode.javase.string.Customer@10f87f48
        System.out.println(s1); //我是一个VIP客户！！！！

        // 我们是不是可以研究一下println()方法的源代码了？
        System.out.println(100);
        System.out.println(3.14);
        System.out.println(true);

        Object obj = new Object();
        // 通过源代码可以看出：为什么输出一个引用的时候，会调用toString()方法!!!!
        //　本质上System.out.println()这个方法在输出任何数据的时候都是先转换成字符串，再输出。
        System.out.println(obj);

        System.out.println(new Customer());
    }
}

class Customer {
    // 重写toString()方法

    @Override
    public String toString() {
        return "我是一个VIP客户！！！！";
    }
}
```
# StringBuffer、StringBuilder
1、我们在实际的开发中，如果需要进行字符串的频繁拼接，会有什么问题？

因为java中的字符串是不可变的，每一次拼接都会产生新字符串。这样会占用大量的方法区内存。造成内存空间的浪费。

String s = "abc";

s += "hello";

就以上两行代码，就导致在方法区字符串常量池当中创建了3个对象。

2、如果以后需要进行大量字符串的拼接操作，建议使用JDK中自带的：

java.lang.StringBuffer
java.lang.StringBuilder

如何优化StringBuffer的性能？
在创建StringBuffer的时候尽可能给定一个初始化容量。最好减少底层数组的扩容次数。预估计一下，给一个大一些初始化容量。

关键点：给一个合适的初始化容量。可以提高程序的执行效率。

3、StringBuffer和StringBuilder的区别？
StringBuffer中的方法都有：synchronized关键字修饰。表示StringBuffer在多线程环境下运行是安全的。
StringBuilder中的方法都没有：synchronized关键字修饰，表示StringBuilder在多线程环境下运行是不安全的。

StringBuffer是线程安全的。
StringBuilder是非线程安全的。

4、面试题：String为什么是不可变的？

我看过源代码，String类中有一个byte[]数组，这个byte[]数组采用了final修饰，因为数组一旦创建长度不可变。并且被final修饰的引用一旦指向某个对象之后，不可再指向其它对象，所以String是不可变的！"abc" 无法变成 "abcd"

StringBuilder/StringBuffer为什么是可变的呢？

我看过源代码，StringBuffer/StringBuilder内部实际上是一个byte[]数组，
这个byte[]数组没有被final修饰，StringBuffer/StringBuilder的初始化
容量我记得应该是16，当存满之后会进行扩容，底层调用了数组拷贝的方法
System.arraycopy()...是这样扩容的。所以StringBuilder/StringBuffer
适合于使用字符串的频繁拼接操作。
