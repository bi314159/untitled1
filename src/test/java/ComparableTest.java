import java.util.Arrays;

/**
 * ClassName: ComparableTest
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author 毕研政
 * @Create 2023/4/28 15:02
 * @Version 1.0
 */
public class ComparableTest {
    public static void main(String[] args) {
        Student[] arr = new Student[4];
        arr[0] = new Student("a",2);
        arr[1] = new Student("b",1);
        arr[2] = new Student("c",4);
        arr[3] = new Student("d",3);
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
class Student implements Comparable{
    private String name;
    private int score;
    public Student(){}

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Student){
            Student obj = (Student) o;
            return this.score- obj.score;
        }
        throw new RuntimeException("类型异常");
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
