
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static com.alibaba.fastjson.JSON.parse;

public class Json {

    //读取json文件
    public static Map<String,Integer> parseJson(String filepath) throws IOException {
        BufferedReader reader ;
        StringBuilder laststr = new StringBuilder();
        FileInputStream fileInputStream = new FileInputStream(filepath);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
        reader = new BufferedReader(inputStreamReader);
        String tempString ;
        while ((tempString = reader.readLine()) != null) laststr.append(tempString);
        reader.close();
        return (Map<String, Integer>) parse(laststr.toString());
    }
}