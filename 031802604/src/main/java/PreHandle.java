import Except.FileIsNullExcept;
import com.huaban.analysis.jieba.JiebaSegmenter;

import java.io.*;
import java.util.List;

public class PreHandle {

    private static JiebaSegmenter segmenter;

    static Thread t;

    static {
        t = new Thread(() -> {
            segmenter = new JiebaSegmenter();
        });
        t.start();
        CosineSimilarity.init();
    }

    public static List<String> Handle(String fileName){
        String text = readToString(fileName);
        text = removePunctuation(text);
        return split(text);
    }


    /**
     * 去标点
     */
    private static String removePunctuation(String text){
        return text.replaceAll("，","").replaceAll("。","")
                .replaceAll("：","").replaceAll("！","")
                .replaceAll("？","").replaceAll("“","")
                .replaceAll("\n","").replaceAll("；","")
                .replaceAll("　","").replaceAll(" ","");
    }

    /**
     * 分词
     */
    private static List<String> split(String text){
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return segmenter.sentenceProcess(text);
    }

    /**
     * 读取文件
     */
    private static String readToString(String fileName) {
        File file = new File(fileName);
        long length = file.length();
        if(length == 0){
            throw new FileIsNullExcept(fileName+"文件不能为空");
        }
        byte[] content = new byte[(int) length];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(content);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(content);
    }



}
