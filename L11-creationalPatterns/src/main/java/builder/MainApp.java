package builder;

public class MainApp {
    public static void main(String[] args) {
        User user1 = new User.UserBuilder()
                .setFirstName("Vladislav")
                .setLastName("Zhuravsky")
                .setAge(30)
                .setPhone(32333333)
                .setAddress("Moscow")
                .build();

        User user2 = new User.UserBuilder()
                .setFirstName("Olga")
                .setPhone(1111111)
                .setAddress("KLIN")
                .build();

        User user3 = new User.UserBuilder()
                .setFirstName("Kotofei")
                .setLastName("Kotofeevich")
                .build();


        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
    }
}
