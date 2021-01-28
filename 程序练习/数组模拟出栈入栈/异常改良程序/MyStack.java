package com.bjpowernode.javase.exception;
/*
	编写程序，使用一维数组，模拟栈数据结构。
	要求：
		1、这个栈可以存储java中的任何引用类型的数据。
		2、在栈中提供push方法模拟压栈。（栈满了，要有提示信息。）
		3、在栈中提供pop方法模拟弹栈。（栈空了，也有有提示信息。）
		4、编写测试程序，new栈对象，调用push pop方法来模拟压栈弹栈的动作。
		5、假设栈的默认初始化容量是10.（请注意无参数构造方法的编写方式。）
 */
public class MyStack {
    // 向栈当中存储元素，我们这里使用一维数组模拟。存到栈中，就表示存储到数组中。
    // 因为数组是我们学习java的第一个容器。
    // 为什么选择Object类型数组？因为这个栈可以存储java中的任何引用类型的数据
    // new Animal()对象可以放进去，new Person()对象也可以放进去。因为Animal和Person的超级父类就是Object。
    // 包括String也可以存储进去。因为String父类也是Object。
    private Object[] elements;

    // 栈帧，永远指向栈顶部元素
    // 那么这个默认初始值应该是多少。注意：最初的栈是空的，一个元素都没有。
    //private int index = 0; // 如果index采用0，表示栈帧指向了顶部元素的上方。
    //private int index = -1; // 如果index采用-1，表示栈帧指向了顶部元素。
    private int index;

    /**
     * 无参数构造方法。默认初始化栈容量10.
     */
    public MyStack() {
        // 一维数组动态初始化
        // 默认初始化容量是10.
        this.elements = new Object[10];
        // 给index初始化
        this.index = -1;
    }

    /**
     * 压栈的方法
     * @param obj 被压入的元素
     */
    public void push(Object obj) throws MyStackOperationException {
        if(index >= elements.length - 1){
            // 改良之前
            //System.out.println("压栈失败，栈已满！");
            //return;

            // 创建异常对象
            //MyStackOperationException e = new MyStackOperationException("压栈失败，栈已满！");
            // 手动将异常抛出去！
            //throw e; //这里捕捉没有意义，自己new一个异常，自己捉，没有意义。栈已满这个信息你需要传递出去。

            // 合并（手动抛出异常！）
            throw new MyStackOperationException("压栈失败，栈已满！");
        }
        // 程序能够走到这里，说明栈没满
        // 向栈中加1个元素，栈帧向上移动一个位置。
        index++;
        elements[index] = obj;
        // 在声明一次：所有的System.out.println()方法执行时，如果输出引用的话，自动调用引用的toString()方法。
        System.out.println("压栈" + obj + "元素成功，栈帧指向" + index);
    }

    /**
     * 弹栈的方法，从数组中往外取元素。每取出一个元素，栈帧向下移动一位。
     * @return
     */
    public void pop() throws MyStackOperationException {
        if(index < 0){
            //System.out.println("弹栈失败，栈已空！");
            //return;
            throw new MyStackOperationException("弹栈失败，栈已空！");
        }
        // 程序能够执行到此处说明栈没有空。
        System.out.print("弹栈" + elements[index] + "元素成功，");
        // 栈帧向下移动一位。
        index--;
        System.out.println("栈帧指向" + index);
    }

    // set和get也许用不上，但是你必须写上，这是规矩。你使用IDEA生成就行了。
    // 封装：第一步：属性私有化，第二步：对外提供set和get方法。
    public Object[] getElements() {
        return elements;
    }

    public void setElements(Object[] elements) {
        this.elements = elements;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
