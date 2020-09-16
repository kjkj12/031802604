import Except.SimilarExcept;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CosineSimilarity {

    private static Map<String,Integer> weight;

    public static void init(){
        new Thread(() -> {
            try {
                weight = Json.parseJson();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static double getSimilarity(List<String> txt_orig, List<String> txt_test) {
        double Max = weight.get("MaxNum") ;
        Map<String,int[]> countMap=new HashMap<>();
        txt_orig.forEach(s -> {
            int[] count = countMap.get(s);
            if(count == null){
                count = new int[2];
                count[0] = 1;
                countMap.put(s,count);
            }else{
                count[0]++;
            }
        });
        txt_test.forEach(s -> {
            int[] count = countMap.get(s);
            if(count == null){
                count = new int[2];
                count[1] = 1;
                countMap.put(s,count);
            }else{
                count[1]++;
            }
        });

        double sum_orig = 0;
        double sum_test = 0;
        double sum_all = 0;

        double weight_key;

        for(Map.Entry<String,int[]> entry4:countMap.entrySet()){

            //设置权重
            String key=entry4.getKey();
            weight_key = weight.get(key)==null?1:weight.get(key)+1;
            weight_key = Math.sqrt(Max/weight_key);

            int[] ints=entry4.getValue();
            sum_orig += ints[0] * ints[0] * weight_key;
            sum_test += ints[1] * ints[1] * weight_key;
            sum_all += ints[0] * ints[1] * weight_key;

            //System.out.println(key+" "+ints[0]+" "+ints[1]+" "+weight_key);
        }

//        System.out.println(sum_orig);
//        System.out.println(sum_test);
//        System.out.println(sum_all);
        double result = sum_all / Math.sqrt(sum_orig * sum_test);
        if(result == 1){
            throw new SimilarExcept("不同的文本获得了同样的结果");
        }
        return result;
    }
}