import java.util.Arrays;
import java.util.Comparator;

/**
 * ClassName: ComparatorTest
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author 毕研政
 * @Create 2023/4/28 15:29
 * @Version 1.0
 */
public class ComparatorTest {
    public static void main(String[] args) {
        Student1[] arr = new Student1[4];
        arr[0] = new Student1("a",2);
        arr[1] = new Student1("b",1);
        arr[2] = new Student1("c",4);
        arr[3] = new Student1("d",3);
        Arrays.sort(arr,new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Student1 && o2 instanceof Student1){
                    Student1 s1 = (Student1) o1;
                    Student1 s2 = (Student1) o2;
                    return s1.getScore()-s2.getScore();
                }
                throw new RuntimeException("类型不匹配");
            }
        });
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
class Student1{
    private String name;
    private int score;
    public Student1(){}

    public Student1(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student1{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
