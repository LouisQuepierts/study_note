import java.util.*;

public class ExampleClass {
    /*
    基础类声明
    [类名] 名称 = 值
    例如
    String a = "something i want to say"
    float b = 1.5F;
    double c = 2.8;
    int d = 200;

    变量/对象声明
    [类名] 名称 = new [构造器]
    例如
    List<String> stringList = new ArrayList<>();
    */
    /*
    数组:
    用 Object[] 来声明一个 对象 的数组
    在进行访问时, 索引应从 0 开始计算, 而非 1

    数组在第一次赋值时将会把长度固定
    如下:
    ... = new Object[5]
      └ 一个长度为 5 的空数组
    ... = new Object[]{a, b, c};
      └ 一个长度为 3 并带内容的数组

    通常来说, 如果不想在数组刚声明的时候就赋值
    即可用 = new Object[length] 进行创建
    */
    public static Object[] objectsA = new Object[5];
    public static Object[] objectsB = new Object[]{1, 2, 3, 4};

    /*
    List<Object>
    列表是继承了 Collection 类的一个类
    通常来说, 如果是要储存对象
    建议是用 ArrayList<>();
    操作方式相对数组来说要方便很多

    - List<Object> objectList
        └ 添加对象 - add(xxx);
        └ 删除对象 - remove(xxx);
        └ 往指定位添加对象 - set(index, xxx);
        └ 删除指定位置对象 - remove(index);
        └ 获取指定位置对象 - get(index);
        └ 判断是否含有指定对象 - contains(xxx);
    当然, List<Object> 是可以和 数组 相互转换的

    List<Object> = List.of(Object[]);
    Object[] = List<Object>.tpArray(new Object[0]);
    */

    public static List<Object> objectList = new ArrayList<>();

    /*
    java的main方法
    可以直接调用
    也可通过其他函数调用
    */

    public static void main(String[] args) {
        // method 内可嵌套其他 method
        aMethod();
        say0To9();

        // 一条只是为了好看的分割线
        System.out.println("------------");


        /*
        声明一个 [Scanner] 对象
        并让其捕获指令栏输入
        */

        Scanner scanner = new Scanner(System.in);

        /*
        利用 scanner 获取下面数据
          └ next() - 以字符串返回输入的值
          └ nextFloat() - 返回输入的浮点数
          ... 以此类推
        */
        System.out.println("Please type in the name for the table");
        String nameTable = scanner.next();
        System.out.println("Please type in the size of the table (x & y)");
        float x = scanner.nextFloat();
        float y = scanner.nextFloat();

        /*
        例 输入
        table
        8.0
        12.0
        */

        /*
        声明一个名为 "dinnerTable" 的 [Table] 对象
        并在构造器上输入名称与大小 (参数)
        这里的参数来源于 scanner 捕获到的输入
        */
        Table dinnerTable = new Table(nameTable, x, y);

        /*
        声明一些 [Item] 对象
        并在构造器上输入名称与大小 (参数)
        */
        Item vanillaCake = new Item("Vanilla Cake", 2.0F, 2.5F);
        Item cookedTurkey = new Item("Cooked Turkey", 6.5F, 8.2F);
        Item plate = new Item("Plate", 3.0F, 3.0F);
        Item salt = new Item("Salt", 0.1F, 0.1F);

        /*
        这部分可能有一丢丢超纲
        不过应该很快就会学的了
        声明一个 [StorageBox] 对象
        因为 [StorageBox] 继承了 [Item]
        所以也可以被放入 [Table] 对象
        */
        StorageBox saltBox = new StorageBox("Salt Box", 2.0F, 2.0F, 4);

        /* 通过 [Item] 对象调用函数进行操作 */
        vanillaCake.setCount(2);
        plate.setCount(6);
        salt.setCount(12);

        plate.printInfo();
        // Item Name: Vanilla Cake; Count: 2; Size: (2.0, 2.5)

        saltBox.addItem(salt);
        saltBox.printInfo();

        /*
        对于 Object... objects 的补充
        当一个 method 的参数位 Object... objects 时
        每个输入的 Object 只需用逗号经行分割即可
        */
        dinnerTable.putItems(vanillaCake, cookedTurkey, plate, saltBox);
        dinnerTable.printInfo();

        /*
        print
        Table Name: table, Size: (8.0, 12.0), Used Space: 71.3
        Items: [Vanilla Cake, Cooked Turkey, Plate, SaltBox]
        */

        /*
        假设,我们需要从 dinnerTable 上拿一些盐
        那么我们需要先从 dinnerTable 上获取 saltBox
        然后再从 saltBox 里获取盐
        但是因为 [Table] 内的 findItem() 只能返回 [Item]
        所以我们需要一点小方法来获取盐罐
        */

        // 先获取 Salt Box 的 [Item] 对象
        Item itemOnTable = dinnerTable.findItem("Salt Box");
        // 判断这个对象是否是 [StorageBox]
        if (itemOnTable instanceof StorageBox) {
            // 若是, 转换成 [StorageBox]
            /*
            如果在情况允许的时候
            可以通过 (子类) 父类对象 来转换
            */
            StorageBox boxIFound = (StorageBox) itemOnTable;

            /*
            然后我们通过转换过后的 Salt Box 来获取 Salt
            并拿走一份盐
            因为我们设定了 [Item] 类的 addCount() 可以返回 int
            所以我们可以获取他的数并且打印出来
            */
            int saltRemain = boxIFound.findItem("Salt").addCount(-1);

            // printf 不会自动换行, 需要在结尾手动添加 '\n'
            System.out.printf("Remain Salt: %s\n", saltRemain);
        }
    }

    /*
    单独以一个 method 的方式来剖析一下 main
    public - 标识该方法可以在项目内的任何地方进行访问, 不过需 import 该文件
      └ 补充:
      └ private - 仅该类内可访问
      └ protect - 仅该类和 extend 了该类的类可访问
    static - 标识该方法为静态
      └ 如果 method 所在的类是可以作为一个对象进行声明
      └ 标识了 static 的 method 可以直接调用
      └ 但是标识的只能通过声明一个对象来调用
    void - 标识该方法不需要返回任何值
    String[] args - 该方法的输入变量
    *
    标识符也可用于类内变量的声明
    如:
    public String name = new String();
    public static Entity aEntity = ...;
    */

    // 声明一个仅该类可访问的 method
    private static void aMethod() {
        System.out.println("this method only ExampleClass.java can use");
    }

    // 声明一个任何类都可访问的 method
    public static void say0To9() {
        /*
        for() 循环:
        在有的时候, 我们需要处理大量信息
        那么 for 循环可以节省一定的代码量

        for循环有两种格式
        for (初始化; 判断式; 更新)
          └ 初始化 - 一般用于第一次进入循环时进行赋值
          └ 判断式 - 用于判断是否进入下一轮循环
          └ 更新 - 在每轮循环过后会运行
              └ 更新中也可以输入别的 method 来进行调用
        for (Object object : objectList)
          └ 一般用于不需要其他额外条件的遍历
          └ 左右的数据类型需要一样
          └ 右方需要是一个数组或列表
          └ 如 - String line : String[]

        在实际使用下, 经常会出现for循环的嵌套
        比如遍历一片矩形区域的每一个坐标
       */

        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }

        /*
        while() 循环跟 for() 循环道理差不多
        只不过括号内可填的只有判断式
        若第一次尝试运行时不满足 while() 循环的判断式
        则不会运行

        do...while() 循环
        与while()循环不同
        do...while() 循环无论第一次执行时判断式是否符合
        都会执行一次 do{} 内的函数
        */

        /*
        其他的遍历方式
        Iterator<Object>
        一般也用于遍历列表, 方法如下

        List<Object> objectList;
        Iterator<Object> iterator = objectList.iterator();

        while(iterator.hasNext()) {
            Object object = iterator.next;
            ...
        }

        */
    }
}
