# 集合

1.集合继承结构图

![集合继承结构图](/src/集合继承结构图.png)

![map继承结构图](/src/map继承结构图.png)

2.关于java.util.Collection接口中常用的方法。

2.1、Collection中能存放什么元素？

没有使用“泛型”之前，Collection中可以存储Object的所有子类型。使用了“泛型”之后，Collection中只能存储某个具体的类型。集合后期我们会学习“泛型”语法。目前先不用管。Collection中什么都能存，只要是Object的子类型就行。（集合中不能直接存储基本数据类型，也不能存java对象，只是存储java对象的内存地址。）
    
2.2、Collection中的常用方法

boolean add(Object e) 向集合中添加元素

int size()  获取集合中元素的个数

void clear() 清空集合

boolean contains(Object o) 判断当前集合中是否包含元素o，包含返回true，不包含返回false

boolean remove(Object o) 删除集合中的某个元素。

boolean isEmpty()  判断该集合中元素的个数是否为0

Object[] toArray()  调用这个方法可以把集合转换成数组。【作为了解，使用不多。】

3、对集合Collection进行遍历/迭代

第一步：获取集合对象的迭代器对象Iterator

Iterator it = c.iterator();

第二步：通过以上获取的迭代器对象开始迭代/遍历集合。

以下两个方法是迭代器对象Iterator中的方法：

boolean hasNext()如果仍有元素可以迭代，则返回 true。

Object next() 返回迭代的下一个元素。

4、ArrayList集合：有序可重复； HashSet集合：无序不可重复

5、深入Collection集合的contains方法：boolean contains(Object o)判断集合中是否包含某个对象o；如果包含返回true， 如果不包含返回false。

contains方法是用来判断集合中是否包含某个元素的方法，那么它在底层是怎么判断集合中是否包含某个元素的呢？

调用了equals方法进行比对。equals方法返回true，就表示包含这个元素。

6、测试remove方法。结论：存放在一个集合中的类型，一定要重写equals方法。

重点：当集合的结构发生改变时，迭代器必须重新获取，如果还是用以前老的迭代器，会出现异常：java.util.ConcurrentModificationException

重点：在迭代集合元素的过程中，不能调用集合对象的remove方法，删除元素：c.remove(o); 迭代过程中不能这样。会出现：java.util.ConcurrentModificationException

重点：在迭代元素的过程当中，一定要使用迭代器Iterator的remove方法，删除元素，不要使用集合自带的remove方法删除元素。
```java
        Iterator it2 = c2.iterator();
        while(it2.hasNext()){
            Object o = it2.next();
            // 删除元素
            // 删除元素之后，集合的结构发生了变化，应该重新去获取迭代器
            // 但是，循环下一次的时候并没有重新获取迭代器，所以会出现异常：java.util.ConcurrentModificationException
            // 出异常根本原因是：集合中元素删除了，但是没有更新迭代器（迭代器不知道集合变化了）
            //c2.remove(o); // 直接通过集合去删除元素，没有通知迭代器。（导致迭代器的快照和原集合状态不同。）
            // 使用迭代器来删除可以吗？
            // 迭代器去删除时，会自动更新迭代器，并且更新集合（删除集合中的元素）。
            it2.remove(); // 删除的一定是迭代器指向的当前元素。
            System.out.println(o);
```
## List
### ArrayList

1.测试List接口中常用方法

List集合存储元素特点：有序可重复

有序：List集合中的元素有下标。从0开始，以1递增。

可重复：存储一个1，还可以再存储1.

2、List既然是Collection接口的子接口，那么肯定List接口有自己“特色”的方法：以下只列出List接口特有的常用的方法：
```java
            void add(int index, Object element)
            Object set(int index, Object element)
            Object get(int index)
            int indexOf(Object o)
            int lastIndexOf(Object o)
            Object remove(int index)
```
3、增删改查这几个单词要知道：

增：add、save、new

删：delete、drop、remove

改：update、set、modify

查：find、get、query、select

4、集合ArrayList的构造方法
```java
        // 默认初始化容量10
        List myList1 = new ArrayList();

        // 指定初始化容量100
        List myList2 = new ArrayList(100);

        // 创建一个HashSet集合
        Collection c = new HashSet();
        // 添加元素到Set集合
        c.add(100);
        c.add(200);
        c.add(900);
        c.add(50);

        // 通过这个构造方法就可以将HashSet集合转换成List集合。
        List myList3 = new ArrayList(c);
        for(int i = 0; i < myList3.size(); i++){
            System.out.println(myList3.get(i));
```
5、ArrayList集合：

5.1、默认初始化容量10（底层先创建了一个长度为0的数组，当添加第一个元素的时候，初始化容量10。）

5.2、集合底层是一个Object[]数组。

5.3、构造方法：

new ArrayList();
new ArrayList(20);

5.4、ArrayList集合的扩容：
增长到原容量的1.5倍。

ArrayList集合底层是数组，怎么优化？

尽可能少的扩容。因为数组扩容效率比较低，建议在使用ArrayList集合的时候预估计元素的个数，给定一个初始化容量。

5.5、数组优点：

检索效率比较高。（每个元素占用空间大小相同，内存地址是连续的，知道首元素内存地址，然后知道下标，通过数学表达式计算出元素的内存地址，所以检索效率最高。）

5.6、数组缺点：

随机增删元素效率比较低。

另外数组无法存储大数据量。（很难找到一块非常巨大的连续的内存空间。）

5.7、向数组末尾添加元素，效率很高，不受影响。

5.8、面试官经常问的一个问题？

这么多的集合中，你用哪个集合最多？

答：ArrayList集合。因为往数组末尾添加元素，效率不受影响。另外，我们检索/查找某个元素的操作比较多。

5.9、ArrayList集合是非线程安全的。（不是线程安全的集合。）

### LinkedList

1、链表的优点：
由于链表上的元素在空间存储上内存地址不连续。
所以随机增删元素的时候不会有大量元素位移，因此随机增删效率较高。在以后的开发中，如果遇到随机增删集合中元素的业务比较多时，建议使用LinkedList。

链表的缺点：
不能通过数学表达式计算被查找元素的内存地址，每一次查找都是从头节点开始遍历，直到找到为止。所以LinkedList集合检索/查找的效率较低。

ArrayList：把检索发挥到极致。（末尾添加元素效率还是很高的。）

LinkedList：把随机增删发挥到极致。

加元素都是往末尾添加，所以ArrayList用的比LinkedList多。

2、LinkedList集合有初始化容量吗？没有。

最初这个链表中没有任何元素。first和last引用都是null。

![单向链表](/src/05-集合/005-链表（单向链表）.png)
![双向链表](/src/05-集合/006-双向链表.png)

### Vector

1、底层也是一个数组。

2、初始化容量：10

3、怎么扩容的？
扩容之后是原容量的2倍。
10--> 20 --> 40 --> 80

4、ArrayList集合扩容特点：
ArrayList集合扩容是原容量1.5倍。

5、Vector中所有的方法都是线程同步的，都带有synchronized关键字，是线程安全的。效率比较低，使用较少了。

6、怎么将一个线程不安全的ArrayList集合转换成线程安全的呢？

使用集合工具类：
java.util.Collections;

java.util.Collection 是集合接口。
java.util.Collections 是集合工具类。

7、
List myList = new ArrayList(); // 非线程安全的。

变成线程安全的

Collections.synchronizedList(myList);
