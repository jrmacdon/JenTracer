/**
 * Created by kevin on 7/15/15.
 */
public class StringTest {

    public static void main(String[] args) {

        String someString = new String("Kevin is great");
        modify(someString);
        System.out.println(someString);
        someString = "Kevin is bad";

        Person p = new Person("Kevin", 29);
        System.out.println(p);

        modifyPerson(p);
        System.out.println(p);
    }

    public static void modifyPerson(Person person) {
        person.setAge(30);
    }

    public static void modify(String s) {
        s = "Hello";

    }
}
