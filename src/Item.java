// 一个新的名叫 Item 的类
public class Item {
    //声明该类的变量

    private String name;
    private int count = 1;

    /*
    声明常量
    常量不可被修改
    如果在声明的时候没给常量赋值
    可在构造器里进行赋值

    这里是拿来标识 Item 对象的 x, y 大小
    所以用 final
    */

    private final float sizeX;
    private final float sizeY;

    /*
    构造器
    用来构建该类的对象
    如:
    public Item cake = new Item();
    */

    public Item() {
        /*
        因为变量 <sizeX> 和 <sizeY> 为常量
        所以必须在构造器中给他们赋值
        */
        this.sizeX = 0.0F;
        this.sizeY = 0.0F;
    }

    /*
    一个带参数的构造器
    因为构造器本身可以运行参数和函数
    所以在构造器的括号中放入 [参数]
    即可实现初始化
    */
    public Item(String name) {
        this.name = name;

        this.sizeX = 0.0F;
        this.sizeY = 0.0F;
    }

    public Item(String name, float sizeX, float sizeY) {
        this.name = name;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    /*
    一个 [公共] 的 [非静态] 返回 [String] 的 method
    表示这个 method 需要在有该类的 [对象] 的使用调用
    且可以获得一个 [String] 的返回值
    例如:
    Item cake = new Item("Yummy Cake})
    System.out.println(cake.getName())
      └ print: Yummy Cake
    */
    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public float getSizeX() {
        return sizeX;
    }

    public float getSizeY() {
        return sizeY;
    }

    /*
        一个 [公共] 的 [非静态] 无返回值 的 method
        且带有输入参数
        代表了我们可以通过往 method 的参数中输如 参数所需的对象
        比如说这个 method 需要 [String]:
        cake.setName("Chocolate Cake")
        System.out.println(cake.getName())
          └ print: Chocolate cake
        */
    public void setName(String name) {
        /*
        method 调用时运算的 函数
        这里设置对应的 [Item对象] 的名字为 [输入的参数]
        */
        this.name = name;
    }


    public void setCount(int count) {
        /*
        设置 [Item] 对象的数量
        因为现实世界中物品不存在负数的
        所以我们可以限制 [Item] 对象的 <Count> 变量保持在正数
        */


        //判断输入的数值是否 [大于 1]
        if (count > 1) {

            // 若 是, 则 <Count> 变量赋值 输入参数
            this.count = count;
        } else {
            // 若 否, 则 <Count> 变量赋值 1
            this.count = 1;
        }
    }

    // 这里我们希望可以添加数量后返回该 [Item] 对象的 <Count> 变量
    public int addCount(int count) {

        // 判断 <Count> 变量与 输入参数的 和 是否为正数
        if (this.count + count > 1) {

            // 若 是, 则 <Count> 添加上 输入参数
            this.count += count;
        }
        // 若 否, 则不进行操作

        // 返回 [Item] 对象的 <Count> 变量
        return this.count;
    }

    public void printInfo() {
        /*
        因为 printf 不会自动换行
        所以要在句末加上 '\n'
        */
        System.out.printf("Item Name: %s, Count: %s, Size: (%s, %s)\n", this.name, this.count, this.sizeX, this.sizeY);
    }
}
