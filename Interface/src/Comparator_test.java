import java.lang.comparable;
import java.util.comparator;

public class Comparator_use {
    
    public static void main(String[] args){

        List<student> students = new ArrayList<>();

        students.add(new student("Tom",12,91));
        students.add(new student("hom",14,89));
        students.add(new student("uom",14,87));
        
        

    }
}

class student {

    private String name;

    private int score;

    private int age;

    public student(){};

    public student(String name ,int score ,int age){
        this.name = name;
       this.score = score;
      this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getAge() {
        return age;
    }
    
    @Override
    public String toString() {
        return "Student{name='" + name + "', score=" + score + ", age=" + age + "}";
    }

}
