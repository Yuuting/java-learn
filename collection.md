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
