# Contents  
- [集合](#集合)   
  - [List](#List) 
    - [ArrayList](#ArrayList)
    - [LinkedList](#LinkedList)
    - [Vector](#Vector)
  - [Set](#Set) 
    - [HashSet](#HashSet)
    - [TreeSet](#TreeSet)
- [map](#map)   
  - [HashMap](#HashMap) 
  - [TreeMap](#TreeMap)
  - [HashTable](#HashTable)

  
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

## Set
### HashSet

1、HashSet集合：无序不可重复。
```java
        // 演示一下HashSet集合特点
        Set<String> strs = new HashSet<>();

        // 添加元素
        strs.add("hello3");
        strs.add("hello4");
        strs.add("hello1");
        strs.add("hello2");
        strs.add("hello3");
        strs.add("hello3");
        strs.add("hello3");
        strs.add("hello3");

        // 遍历
        /*
        hello1
        hello4
        hello2
        hello3
        1、存储时顺序和取出的顺序不同。
        2、不可重复。
        3、放到HashSet集合中的元素实际上是放到HashMap集合的key部分了。
         */
        for(String s : strs){
            System.out.println(s);
```
### TreeSet
TreeSet集合存储元素特点：

1、无序不可重复的，但是存储的元素可以自动按照大小顺序排序！称为：可排序集合。

2、无序：这里的无序指的是存进去的顺序和取出来的顺序不同。并且没有下标。

# map

1、java.util.Map接口中常用的方法：

1.1、Map和Collection没有继承关系。

1.2、Map集合以key和value的方式存储数据：键值对

key和value都是引用数据类型。

key和value都是存储对象的内存地址。

key起到主导的地位，value是key的一个附属品。

1.3、Map接口中常用方法：

V put(K key, V value) 向Map集合中添加键值对

V get(Object key) 通过key获取value

void clear()    清空Map集合

boolean containsKey(Object key) 判断Map中是否包含某个key

boolean containsValue(Object value) 判断Map中是否包含某个value

boolean isEmpty()   判断Map集合中元素个数是否为0

V remove(Object key) 通过key删除键值对

int size() 获取Map集合中键值对的个数。

Collection<V> values() 获取Map集合中所有的value，返回一个Collection

Set<K> keySet() 获取Map集合所有的key（所有的键是一个set集合）

Set<Map.Entry<K,V>> entrySet()
将Map集合转换成Set集合
```
假设现在有一个Map集合，如下所示：
map1集合对象
key             value
----------------------------
1               zhangsan
2               lisi
3               wangwu
4               zhaoliu

Set set = map1.entrySet();
set集合对象
1=zhangsan 【注意：Map集合通过entrySet()方法转换成的这个Set集合，Set集合中元素的类型是 Map.Entry<K,V>】
2=lisi     【Map.Entry和String一样，都是一种类型的名字，只不过：Map.Entry是静态内部类，是Map中的静态内部类】
3=wangwu
4=zhaoliu ---> 这个东西是个什么？Map.Entry
```
2、Map集合的遍历。【非常重要】
```java
public class MapTest02 {
    public static void main(String[] args) {

        // 第一种方式：获取所有的key，通过遍历key，来遍历value
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "zhangsan");
        map.put(2, "lisi");
        map.put(3, "wangwu");
        map.put(4, "zhaoliu");
        // 遍历Map集合
        // 获取所有的key，所有的key是一个Set集合
        Set<Integer> keys = map.keySet();
        // 遍历key，通过key获取value
        // 迭代器可以
        /*Iterator<Integer> it = keys.iterator();
        while(it.hasNext()){
            // 取出其中一个key
            Integer key = it.next();
            // 通过key获取value
            String value = map.get(key);
            System.out.println(key + "=" + value);
        }*/
        // foreach也可以
        for(Integer key : keys){
            System.out.println(key + "=" + map.get(key));
        }

        // 第二种方式：Set<Map.Entry<K,V>> entrySet()
        // 以上这个方法是把Map集合直接全部转换成Set集合。
        // Set集合中元素的类型是：Map.Entry
        Set<Map.Entry<Integer,String>> set = map.entrySet();
        // 遍历Set集合，每一次取出一个Node
        // 迭代器
        /*Iterator<Map.Entry<Integer,String>> it2 = set.iterator();
        while(it2.hasNext()){
            Map.Entry<Integer,String> node = it2.next();
            Integer key = node.getKey();
            String value = node.getValue();
            System.out.println(key + "=" + value);
        }*/

        // foreach
        // 这种方式效率比较高，因为获取key和value都是直接从node对象中获取的属性值。
        // 这种方式比较适合于大数据量。
        for(Map.Entry<Integer,String> node : set){
            System.out.println(node.getKey() + "--->" + node.getValue());
        }
    }
}
```
## HashMap

1、HashMap集合底层是哈希表/散列表的数据结构。

2、哈希表是一个怎样的数据结构呢？

哈希表是一个数组和单向链表的结合体。
数组：在查询方面效率很高，随机增删方面效率很低。
单向链表：在随机增删方面效率较高，在查询方面效率很低。
哈希表将以上的两种数据结构融合在一起，充分发挥它们各自的优点。

![哈希表或者散列表数据结构](/src/05-集合/008-哈希表或者散列表数据结构.png)

3、HashMap集合底层的源代码：
public class HashMap{
// HashMap底层实际上就是一个数组。（一维数组）
Node<K,V>[] table;
// 静态的内部类HashMap.Node
static class Node<K,V> {
    final int hash; // 哈希值（哈希值是key的hashCode()方法的执行结果。hash值通过哈希函数/算法，可以转换存储成数组的下标。）
    final K key; // 存储到Map集合中的那个key
    V value; // 存储到Map集合中的那个value
    Node<K,V> next; // 下一个节点的内存地址。
}
}
哈希表/散列表：一维数组，这个数组中每一个元素是一个单向链表。（数组和链表的结合体。）
4、最主要掌握的是：
map.put(k,v)
v = map.get(k)
以上这两个方法的实现原理，是必须掌握的。
5、HashMap集合的key部分特点：
无序，不可重复。
为什么无序？ 因为不一定挂到哪个单向链表上。
不可重复是怎么保证的？ equals方法来保证HashMap集合的key不可重复。
如果key重复了，value会覆盖。

放在HashMap集合key部分的元素其实就是放到HashSet集合中了。
所以HashSet集合中的元素也需要同时重写hashCode()+equals()方法。

6、哈希表HashMap使用不当时无法发挥性能！
假设将所有的hashCode()方法返回值固定为某个值，那么会导致底层哈希表变成了
纯单向链表。这种情况我们成为：散列分布不均匀。
什么是散列分布均匀？
假设有100个元素，10个单向链表，那么每个单向链表上有10个节点，这是最好的，
是散列分布均匀的。
假设将所有的hashCode()方法返回值都设定为不一样的值，可以吗，有什么问题？
不行，因为这样的话导致底层哈希表就成为一维数组了，没有链表的概念了。
也是散列分布不均匀。
散列分布均匀需要你重写hashCode()方法时有一定的技巧。
7、重点：放在HashMap集合key部分的元素，以及放在HashSet集合中的元素，需要同时重写hashCode和equals方法。
8、HashMap集合的默认初始化容量是16，默认加载因子是0.75
这个默认加载因子是当HashMap集合底层数组的容量达到75%的时候，数组开始扩容。

重点，记住：HashMap集合初始化容量必须是2的倍数，这也是官方推荐的，
这是因为达到散列均匀，为了提高HashMap集合的存取效率，所必须的。
## TreeMap
## HashTable
