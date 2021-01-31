# IO
1、IO流，什么是IO？

I : Input

O : Output

通过IO可以完成硬盘文件的读和写。

2、IO流的分类？

	有多种分类方式：

		一种方式是按照流的方向进行分类：
			以内存作为参照物，
				往内存中去，叫做输入(Input)。或者叫做读(Read)。
				从内存中出来，叫做输出(Output)。或者叫做写(Write)。

		另一种方式是按照读取数据方式不同进行分类：
			有的流是按照字节的方式读取数据，一次读取1个字节byte，等同于一次读取8个二进制位。
			这种流是万能的，什么类型的文件都可以读取。包括：文本文件，图片，声音文件，视频文件等....
				假设文件file1.txt，采用字节流的话是这样读的：
					a中国bc张三fe
					第一次读：一个字节，正好读到'a'
					第二次读：一个字节，正好读到'中'字符的一半。
					第三次读：一个字节，正好读到'中'字符的另外一半。

			有的流是按照字符的方式读取数据的，一次读取一个字符，这种流是为了方便读取
			普通文本文件而存在的，这种流不能读取：图片、声音、视频等文件。只能读取纯
			文本文件，连word文件都无法读取。
				假设文件file1.txt，采用字符流的话是这样读的：
					a中国bc张三fe
					第一次读：'a'字符（'a'字符在windows系统中占用1个字节。）
					第二次读：'中'字符（'中'字符在windows系统中占用2个字节。）
	
	综上所述：流的分类
		输入流、输出流
		字节流、字符流
3、java中所有的流都是在：java.io.\*;下。

java中主要还是研究：
怎么new流对象。
调用流对象的哪个方法是读，哪个方法是写。

4、java IO流这块有四大家族：

四大家族的首领：

java.io.InputStream  字节输入流

java.io.OutputStream 字节输出流

java.io.Reader		字符输入流

java.io.Writer		字符输出流

四大家族的首领都是抽象类。(abstract class)

所有的流都实现了：
java.io.Closeable接口，都是可关闭的，都有close()方法。流毕竟是一个管道，这个是内存和硬盘之间的通道，用完之后一定要关闭，不然会耗费(占用)很多资源。养成好习惯，用完流一定要关闭。

所有的输出流都实现了：
java.io.Flushable接口，都是可刷新的，都有flush()方法。养成一个好习惯，输出流在最终输出之后，一定要记得flush()刷新一下。这个刷新表示将通道/管道当中剩余未输出的数据强行输出完（清空管道！）刷新的作用就是清空管道。

注意：如果没有flush()可能会导致丢失数据。


注意：在java中只要“类名”以Stream结尾的都是字节流。以“Reader/Writer”结尾的都是字符流。

5、java.io包下需要掌握的流有16个：
	
	文件专属：
		java.io.FileInputStream（掌握）
		java.io.FileOutputStream（掌握）
		java.io.FileReader
		java.io.FileWriter

	转换流：（将字节流转换成字符流）
		java.io.InputStreamReader
		java.io.OutputStreamWriter

	缓冲流专属：
		java.io.BufferedReader
		java.io.BufferedWriter
		java.io.BufferedInputStream
		java.io.BufferedOutputStream

	数据流专属：
		java.io.DataInputStream
		java.io.DataOutputStream

	标准输出流：
		java.io.PrintWriter
		java.io.PrintStream（掌握）

	对象专属流：
		java.io.ObjectInputStream（掌握）
		java.io.ObjectOutputStream（掌握）
## java.io.FileInputStream

1、文件字节输入流，万能的，任何类型的文件都可以采用这个流来读。

2、字节的方式，完成输入的操作，完成读的操作（硬盘---> 内存）

```java
package com.yuting.javase.IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class InputStreamTest01 {
    public static void main(String[] args) {
        FileInputStream fileInputStream=null;
        try {
            fileInputStream=new FileInputStream("C:\\Users\\fengyuting\\Desktop\\003-JavaSE课堂源码\\javase\\chapter23\\src\\com\\bjpowernode\\java\\io\\tempfile4");
            /*读单个字符
            int readData=0;
            while ((readData=fileInputStream.read())!=-1){
                System.out.println(readData);
            }
            */
            //读整个文件内容
            byte[] bytes=new byte[4];
            int readCount = 0;
            while ((readCount=fileInputStream.read(bytes))!=-1){
                System.out.print(new String(bytes,0,readCount));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileInputStream!=null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

```
3、FileInputStream类的其它常用方法：

int available()：返回流当中剩余的没有读到的字节数量

long skip(long n)：跳过几个字节不读。
```java
byte[] bytes = new byte[fis.available()]; // 这种方式不太适合太大的文件，因为byte[]数组不能太大。
// 不需要循环了。
// 直接读一次就行了。
int readCount = fis.read(bytes); // 6
System.out.println(new String(bytes)); // abcdef

// skip跳过几个字节不读取，这个方法也可能以后会用！
fis.skip(3);
System.out.println(fis.read());
```
## java.io.FileOutputStream

1、 文件字节输出流，负责写。从内存到硬盘。
```java
package com.bjpowernode.java.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件字节输出流，负责写。
 * 从内存到硬盘。
 */
public class FileOutputStreamTest01 {
    public static void main(String[] args) {
        FileOutputStream fos = null;
        try {
            // myfile文件不存在的时候会自动新建！
            // 这种方式谨慎使用，这种方式会先将原文件清空，然后重新写入。
            //fos = new FileOutputStream("myfile");
            //fos = new FileOutputStream("chapter23/src/tempfile3");

            // 以追加的方式在文件末尾写入。不会清空原文件内容。
            fos = new FileOutputStream("chapter23/src/tempfile3", true);
            // 开始写。
            byte[] bytes = {97, 98, 99, 100};
            // 将byte数组全部写出！
            fos.write(bytes); // abcd
            // 将byte数组的一部分写出！
            fos.write(bytes, 0, 2); // 再写出ab

            // 字符串
            String s = "我是一个中国人，我骄傲！！！";
            // 将字符串转换成byte数组。
            byte[] bs = s.getBytes();
            // 写
            fos.write(bs);

            // 写完之后，最后一定要刷新
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

```
2、文件复制
```java
package com.bjpowernode.java.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
使用FileInputStream + FileOutputStream完成文件的拷贝。
拷贝的过程应该是一边读，一边写。
使用以上的字节流拷贝文件的时候，文件类型随意，万能的。什么样的文件都能拷贝。
 */
public class Copy01 {
    public static void main(String[] args) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            // 创建一个输入流对象
            fis = new FileInputStream("D:\\course\\02-JavaSE\\video\\chapter01\\动力节点-JavaSE-杜聚宾-001-文件扩展名的显示.avi");
            // 创建一个输出流对象
            fos = new FileOutputStream("C:\\动力节点-JavaSE-杜聚宾-001-文件扩展名的显示.avi");

            // 最核心的：一边读，一边写
            byte[] bytes = new byte[1024 * 1024]; // 1MB（一次最多拷贝1MB。）
            int readCount = 0;
            while((readCount = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, readCount);
            }

            // 刷新，输出流最后要刷新
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 分开try，不要一起try。
            // 一起try的时候，其中一个出现异常，可能会影响到另一个流的关闭。
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```
## FileReader
文件字符输入流，只能读取普通文本。读取文本内容时，比较方便，快捷。将bytes数组换成char数组。
```java
char[] chars = new char[4]; // 一次读取4个字符
int readCount = 0;
while((readCount = reader.read(chars)) != -1) {
System.out.print(new String(chars,0,readCount));
}
```
## FileWriter
文件字符输出流。写。只能输出普通文本。既能写入char数组又能直接写入string数组。
```java
char[] chars = {'我','是','中','国','人'};
out.write(chars);
out.write(chars, 2, 3);

out.write("我是一名java软件工程师！");
```
## BufferedReader
带有缓冲区的字符输入流。
使用这个流的时候不需要自定义char数组，或者说不需要自定义byte数组。自带缓冲。
```java
public class BufferedReaderTest01 {
    public static void main(String[] args) throws Exception{

        FileReader reader = new FileReader("Copy02.java");
        // 当一个流的构造方法中需要一个流的时候，这个被传进来的流叫做：节点流。
        // 外部负责包装的这个流，叫做：包装流，还有一个名字叫做：处理流。
        // 像当前这个程序来说：FileReader就是一个节点流。BufferedReader就是包装流/处理流。
        BufferedReader br = new BufferedReader(reader);

        // 读一行
        /*String firstLine = br.readLine();
        System.out.println(firstLine);

        String secondLine = br.readLine();
        System.out.println(secondLine);

        String line3 = br.readLine();
        System.out.println(line3);*/

        // br.readLine()方法读取一个文本行，但不带换行符。
        String s = null;
        while((s = br.readLine()) != null){
            System.out.print(s);
        }

        // 关闭流
        // 对于包装流来说，只需要关闭最外层流就行，里面的节点流会自动关闭。（可以看源代码。）
        br.close();
    }
}

```
```java
//字节流转换为字符流
/*// 字节流
        FileInputStream in = new FileInputStream("Copy02.java");

        // 通过转换流转换（InputStreamReader将字节流转换成字符流。）
        // in是节点流。reader是包装流。
        InputStreamReader reader = new InputStreamReader(in);

        // 这个构造方法只能传一个字符流。不能传字节流。
        // reader是节点流。br是包装流。
        BufferedReader br = new BufferedReader(reader);*/

        // 合并
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Copy02.java")));
```
## BufferedWriter
直接write()就行。
```java
BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("copy", true)));
        // 开始写。
        out.write("hello world!");
        out.write("\n");
        out.write("hello kitty!");
        // 刷新
        out.flush();
        // 关闭最外层
        out.close();
```
## DataInputStream
DataInputStream:数据字节输入流。
## DataOutputStream
DataOutputStream写的文件，只能使用DataInputStream去读。并且读的时候你需要提前知道写入的顺序。读的顺序需要和写的顺序一致。才可以正常取出数据。
```java
public class DataOutputStreamTest {
    public static void main(String[] args) throws Exception{
        // 创建数据专属的字节输出流
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("data"));
        // 写数据
        byte b = 100;
        short s = 200;
        int i = 300;
        long l = 400L;
        float f = 3.0F;
        double d = 3.14;
        boolean sex = false;
        char c = 'a';
        // 写
        dos.writeByte(b); // 把数据以及数据的类型一并写入到文件当中。
        dos.writeShort(s);
        dos.writeInt(i);
        dos.writeLong(l);
        dos.writeFloat(f);
        dos.writeDouble(d);
        dos.writeBoolean(sex);
        dos.writeChar(c);

        // 刷新
        dos.flush();
        // 关闭最外层
        dos.close();
    }
}
```
```java
public class DataInputStreamTest01 {
    public static void main(String[] args) throws Exception{
        DataInputStream dis = new DataInputStream(new FileInputStream("data"));
        // 开始读
        byte b = dis.readByte();
        short s = dis.readShort();
        int i = dis.readInt();
        long l = dis.readLong();
        float f = dis.readFloat();
        double d = dis.readDouble();
        boolean sex = dis.readBoolean();
        char c = dis.readChar();

        System.out.println(b);
        System.out.println(s);
        System.out.println(i + 1000);
        System.out.println(l);
        System.out.println(f);
        System.out.println(d);
        System.out.println(sex);
        System.out.println(c);

        dis.close();
    }
}
```
