package test;

import com.jeramtough.jtlog.facade.L;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created on 2018-09-07 17:09
 * by @author JeramTough
 */
public class SortTest implements Comparator<Student> {

    @Test
    public void test(){
        ArrayList<Student> students=new ArrayList<>();
        students.add(new Student("a",10.1f));
        students.add(new Student("b",13.4f));
        students.add(new Student("c",12.8f));

        Collections.sort(students,new SortTest());
        for (Student student:students){
            L.p(student);
        }
    }

    @Override
    public int compare(Student o1, Student o2) {
        return (int) (o1.getScore()-o2.getScore());
    }
}
class Student{
    private String name;
    private float score;

    public Student(String name, float score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
