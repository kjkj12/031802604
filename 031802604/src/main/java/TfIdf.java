import com.fasterxml.jackson.databind.ObjectMapper;
import com.huaban.analysis.jieba.JiebaSegmenter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TfIdf {

    private static final String filePath = "src/main/resources/";

    private static final String IN_PATH = filePath+"TfIdfResource"; // 语料库路径

    private static final String OUT_PATH = filePath+"TfIdfJson/TfIdf.json";

    private static final JiebaSegmenter segmenter = new JiebaSegmenter();

    public static void main(String[] args) throws Exception {

        Map<String,Integer> map = computeTFIDF(IN_PATH);

        writeJson(map,OUT_PATH);

    }

    private static void writeJson(Map map,String path){
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File(path), map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Integer> computeTFIDF(String path) throws Exception {

        File fileDir = new File(path);
        File[] files = fileDir.listFiles();

        int max = 0;

        Map<String, Integer> map = new HashMap<>();

        for (File f : Objects.requireNonNull(files)) {

            InputStream in = new FileInputStream(f);

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(in, StandardCharsets.UTF_8));
            String s;

            while ((s = br.readLine()) != null) {
                s=s.replaceAll("，","").replaceAll("。","")
                        .replaceAll("：","").replaceAll("！","")
                        .replaceAll("？","").replaceAll("“","")
                        .replaceAll("\n","").replaceAll("；","")
                        .replaceAll("　","").replaceAll(" ","");
                List<String> resultStr = segmenter.sentenceProcess(s);
                for(String s1:resultStr){
                    Integer i = map.get(s1);
                    if(i==null){
                        i=1;
                    }else{
                        i++;
                    }
                    map.put(s1,i);

                    if(i>max)  max = i;

                }
            }
        }
        map.put("MaxNum",max);

        return map;
    }
}