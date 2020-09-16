import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class test {

    private static final String filePath = "src\\main\\resources\\";

    private static final String IN_PATH = filePath+"example\\";

    @Test
    public void Test(){
        File f = new File(IN_PATH);
        String[] files = f.list();
        int cnt = 1;
        if (files == null){
            throw new NullPointerException();
        }
        for(String file : files){
            if(!file.equals("orig.txt")){
                System.out.println("开始处理"+file);
                String[] str = {IN_PATH+"orig.txt",IN_PATH+file,filePath+"\\result\\ans"+cnt+".txt"};
                long current = System.currentTimeMillis();
                Main.main(str);
                System.out.println("===================================================");
                cnt++;
            }
        }
    }

    @Test(timeout = 5000)
    public void Test2(){
        String[] str = {IN_PATH+"\\time1.txt",IN_PATH+"\\time2.txt",filePath+"\\result\\ans_time.txt"};
        Main.main(str);
    }

    @Test()
    public void Test3(){
        Map<String,Integer> map = new HashMap<>();
        try {
            map = Json.parseJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(map.get("我"));
    }
}
