
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static com.alibaba.fastjson.JSON.parse;

public class Json {

    //读取json文件
    public static Map<String,Integer> parseJson() throws IOException {
        BufferedReader reader ;
        StringBuilder lastStr = new StringBuilder();
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("\\TfIdfJson\\TfIdf.json");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        reader = new BufferedReader(inputStreamReader);
        String tempString ;
        while ((tempString = reader.readLine()) != null) lastStr.append(tempString);
        reader.close();
        return (Map<String, Integer>) parse(lastStr.toString());
    }
}