/**
 * Created by kevin on 7/15/15.
 */
public class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return name + " is " + age + " years old";
    }
}
