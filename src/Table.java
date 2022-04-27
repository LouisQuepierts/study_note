import java.util.ArrayList;
import java.util.List;

/*
一个新的名为 Table 的类
我们希望可以在他上面放置物品
所以我们可以通过数组来进行储存
*/
public class Table {
    private String name;

    /*
    因为我们无法确定一个桌子能放多少个物品
    所以使用 ArrayList 来储存 Item 对象
    */
    private List<Item> itemList = new ArrayList<>();

    /*
    桌面放置的物品所占用的空间
    用于判断能否往桌面再放置一个物品
    */
    private float usedSpace = 0;

    private final float sizeX;
    private final float sizeY;

    /*
    构造器
    与 [Item] 类大致相同
    */
    public Table() {
        this.sizeX = 0.0F;
        this.sizeY = 0.0F;
    }

    public Table(String name) {
        this.name = name;

        this.sizeX = 0.0F;
        this.sizeY = 0.0F;
    }

    public Table(String name, float sizeX, float sizeY) {
        this.name = name;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    // 往桌上放物品
    public void putItem(Item item) {
        /*
        首先, 咱假设我们的餐桌礼仪上说
        不同的物品不能堆积在一起
        除去排列组合的因素
        那么我们在往桌子上放 [Item] 对象时就必须考虑能否放得下
        即 <usedSpace> 加上物品的大小要不大于 size 的乘积
        */

        /*
         判定大小
         如果 当前使用了的面积 加上 物品的面积 不大于 桌面面积的话,
         就添加物品
        */
        if (this.usedSpace + (item.getSizeX() * item.getSizeY()) <= this.sizeX * this.sizeY) {
            this.usedSpace += (item.getSizeX() * item.getSizeY());
            this.itemList.add(item);
        } else {
            System.out.println("There no enough space to contain this item");
        }
    }

    public void putItems(Item... items) {
        /*
        利用 Object... objects 作为参数
        可直接输入多个同种类参数
        */

        /*
        由于是输入了一个 [Item] 的数组
        所以我们需要先统计一下所需的空间
        然后在判断能否放置
        */
        float requireSpace = 0;
        // 遍历数组
        for (Item item : items) {
            // 将每个物品的面积加入统计
            requireSpace += item.getSizeX() * item.getSizeY();
        }

        if (this.usedSpace + requireSpace <= this.sizeX * this.sizeY) {
            this.usedSpace += requireSpace;
            /*
            List<>.addAll()
            用于一次性将一整个数组的对象放入List中
            */;
            this.itemList.addAll(List.of(items));
        } else {
            System.out.println("There no enough space to contain these items");
        }
    }

    // 拿走桌面上的物品
    public void pickUpItem(String name) {
        // 遍历数组
        for (int i = 0; i < this.itemList.size(); i++) {
            // 判定名称
            if (this.itemList.get(i).getName().equals(name)) {
                // 移除 List 中指定位置的对象
                this.itemList.remove(i);
                return;
            }
        }

        System.out.println("There's not such a Item on table");
    }

    // 寻找桌面上物品
    public Item findItem(String name) {
        for (int i = 0; i < this.itemList.size(); i++) {
            // 判定名称
            if (this.itemList.get(i).getName().equals(name)) {
                return this.itemList.get(i);
            }
        }

        return null;
    }

    public String getName() {
        return name;
    }

    public float getUsedSpace() {
        return usedSpace;
    }

    public float getSizeX() {
        return sizeX;
    }

    public float getSizeY() {
        return sizeY;
    }

    public int getItemAmount() {
        return this.itemList.size();
    }

    public void printInfo() {
        /*  获取每个物品的名字 */
        List<String> items = new ArrayList<>();
        for (Item item : this.itemList) {
            items.add(item.getName());
        }

        // printf 不会自动换行, 需要在结尾手动添加 '\n'
        System.out.printf("Table Name: %s, Size: (%s, %s), Used Space: %s\nItems: %s\n", this.name, this.sizeX, this.sizeY, this.usedSpace, items);
    }
}
