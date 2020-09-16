import Except.FileIsNullExcept;
import Except.SimilarExcept;
import com.huaban.analysis.jieba.JiebaSegmenter;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class test {

    private static final String filePath = "src\\main\\resources\\";

    private static final String IN_PATH = filePath+"example";

    @Test(timeout = 5000)
    public void Test(){
        String[] str = {IN_PATH+"\\time1.txt",IN_PATH+"\\time2.txt",filePath+"\\result\\ans_time.txt"};
        Main.main(str);
    }

    @Test
    public void Test2(){
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
                Main.main(str);
                System.out.println("===================================================");
                cnt++;
            }
        }
    }

    @Test(expected = FileIsNullExcept.class)
    public void test3FileNullExcept() {
        String[] str = {filePath+"test\\null.txt",IN_PATH+"\\time2.txt",filePath+"\\result\\ans_time.txt"};
        Main.main(str);
    }

    @Test(expected = SimilarExcept.class)
    public void Test4SimilarExcept(){
        String[] str = {IN_PATH+"\\time2.txt",IN_PATH+"\\time2.txt",filePath+"\\result\\ans_time.txt"};
        Main.main(str);
    }

    /**
     * 测试对json的读取
     */
    @Test()
    public void Test5Json(){
        Map<String,Integer> map = null;
        try {
            map = Json.parseJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(map.get("的"));
    }

    /**
     * 测试分词
     */
    @Test
    public void Test6Jieba(){
        String s = "博客园是一个面向开发者的知识分享社区。自创建以来,博客园一直致力并专注于为开发者打造一个纯净的技术交流社区,推动并帮助开发者通过互联网分享知识,从而让更多.";
        JiebaSegmenter segmenter = new JiebaSegmenter();
        List<String> strings = segmenter.sentenceProcess(s);
        strings.forEach(s1 -> {
            System.out.println(s1);
        });
    }

    @Test
    public void Test7PreHandle(){
        List<String> strings = PreHandle.Handle(IN_PATH+"\\time2.txt");
        strings.forEach(s1 -> {
            System.out.println(s1);
        });
    }

    /**
     * 测试不相似的情况
     */
    @Test
    public void Test8(){
        File f = new File(IN_PATH);
        String[] files = f.list();
        int cnt = 1;
        if (files == null){
            throw new NullPointerException();
        }
        for(String file : files){
            if(!file.equals("time1.txt")){
                System.out.println("开始处理"+file);
                String[] str = {IN_PATH+"time1.txt",IN_PATH+file,filePath+"\\result\\ans_no_similar"+cnt+".txt"};
                Main.main(str);
                System.out.println("===================================================");
                cnt++;
            }
        }
    }

    @Test
    public void Test9TfIdf(){
        String[] args = {};
        try {
            TfIdf.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test10Write(){
        WriteAns.writeTxtFile(filePath+"result\\test.txt",1);
    }



}
