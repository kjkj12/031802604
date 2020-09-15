import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteAns {

    public static void writeTxtFile(String filepath, double result) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filepath));
            out.write(String.format("%.2f", result));
            out.close();
            //System.out.println("文件创建成功！");
        } catch (IOException e) {
            System.out.println("创建文件内容出错");
            e.printStackTrace();
        }
    }

}
