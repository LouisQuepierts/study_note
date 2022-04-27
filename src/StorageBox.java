/*
一个名为 StorageBox 的类
他继承了 Item 类

子类可以调用父类 public 和 protected 的 method
也可以对其进行覆写
*/
public class StorageBox extends Item {
    /*
    我们希望这个物品可以存储别的物品
    所以我们给他添加一个 Item数组 来储存
    在现实世界中我们没办法在一个容器里储存无穷多的物品
    不过我们想要自定义他的最大存储量
    所以我们可以在构造器中再给 Item数组 初始化
    并且用一个 [int] 进行统计
    */
    private Item[] storage;
    private int storageCount;

    // 构造器
    public StorageBox(String name, float sizeX, float sizeY, int maxStorageAmount) {
        /*
        super 是用于调用父类的 method
        这边调用的是父类的构造器*/
        super(name, sizeX, sizeY);

        // 根据输入的最大存储量来创建数组
        this.storage = new Item[maxStorageAmount];
    }

    /*
    @Override 标识
    用来复写 父类 的 method
    */

    @Override
    public void printInfo() {
        // 调用父类的 printInfo()
        super.printInfo();
        // 添加我们想要的
        //                                                                   获取当前统计的 物品数量  获取 <storage> 的长度
        System.out.printf("Stored Item Count = %s, Max Storage Amount = %s\n", this.storageCount, this.storage.length);
    }

    // 一个 method, 往 List 中放置物品
    public void addItem(Item item) {
        /*
        因为为了防止某些不科学的事情发生
        我们要限制一下放进去的物品的 种类 和 大小
        */

        /*
         如果输入的物品是 StorageBox, 大小比 该对象 要大, 已经储存的数量超过了上限
         就不输入进去
         可以给表达式进行分行, 这样看着好看一丢丢

         虽然说这样还是存在一些不科学的地方
         但是好过一个6*6的物品放进了一个4*4的盒子里要好
        */
        if (
                item instanceof StorageBox /*判断输入值是不是 StorageBox 类*/
                || item.getSizeX() >= this.getSizeX() /*判断输入值的 X 是否大于或等于该对象的 X*/
                || item.getSizeY() >= this.getSizeY() /*判断输入值的 Y 是否大于或等于该对象的 Y*/
                || this.storageCount == this.storage.length
        ) /*判断能否再放入新的物品*/ {
            System.out.println("You cannot put this item into the box");

        } else {
            // 遍历存储的物品
            for (int i = 0; i < storage.length; i++) {
                // 判断是否是空位
                if (storage[i] == null) {
                    // 赋值 并 返回
                    storage[i] = item;
                    // 计数器加一
                    ++ storageCount;
                    return;
                }
            }
        }
    }

    // 通过名字寻找储存的物品
    public Item findItem(String name) {
        // 遍历储存的物品
        for (Item item : storage) {
            // 若找到就返回
            if (item.getName().equals(name)) {
                return item;
            }
        }

        // 没找到就返回 null
        return null;
    }

    // 通过名字拿走物品
    public void removeItemByIndex(int index) {
        // 如果数组中这一位为空, 则直接返回
        if (this.storage[index] == null) {
            return;
        }
        // 替换下一位到该位直至到 数组末 或者 无法修改
        while (index + 1 < this.storage.length) {
            // 若已经是 null, 则跳出循环
            if (this.storage[index] == null) {
                break;
            }
            // 若数组末, 则替换成 null 并 跳出循环
            if (index + 1 == this.storage.length) {
                this.storage[index] = null;
                break;
            }
            // 将该位替换为下一位
            this.storage[index] = this.storage[index + 1];
            ++ index;
        }

        // 计数器减一
        -- this.storageCount;
    }
}
