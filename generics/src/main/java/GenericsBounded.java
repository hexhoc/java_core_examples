import entity.Cat;
import entity.HomeCat;
import entity.WildCat;

public class GenericsBounded <T extends Cat> {
    public static void main(String[] args) {
        GenericsBounded<Cat> gb1 = new GenericsBounded<>();
        GenericsBounded<WildCat> gb2 = new GenericsBounded<>();
        GenericsBounded<HomeCat> gb3 = new GenericsBounded<>();
    }
}
