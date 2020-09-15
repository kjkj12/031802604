import com.huaban.analysis.jieba.JiebaSegmenter;

import java.io.*;
import java.util.List;

public class PreHandle {

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
        JiebaSegmenter segmenter = new JiebaSegmenter();
        return segmenter.sentenceProcess(text);
    }

    /**
     * 读取文件
     */
    private static String readToString(String fileName) {
        File file = new File(fileName);
        long length = file.length();
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
