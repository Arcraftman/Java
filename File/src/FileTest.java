import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class FileTest {

    @Test
    public void test_instance_01(){
//        1.创建 File 对象
        File file1 = new File("example");
        System.out.println("文件名" + file1.getName());
        System.out.println("绝对路径" + file1.getAbsolutePath());
        System.out.println("Parent dir " + file1.getParent());
//        2.判断文件是否存在
        if (file1.exists()){
            System.out.println("Exist");
        } else {
            System.out.println("Not Exist");
        }

//        3.创建文件或目录
        try {
            if (file1.createNewFile()){
                System.out.println("file created successfully" + file1.getName());
            } else {
                System.out.println("file already exists");
            }
        } catch (IOException e) {
            System.out.println("Something went Wrong :" + e.getMessage());
        }


    }
}
