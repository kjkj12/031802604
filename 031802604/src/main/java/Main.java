import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<String> orig = new ArrayList<>();
    private static List<String> test = new ArrayList<>();

    public static void main(String[] args){
        long now = System.currentTimeMillis();
        orig = PreHandle.Handle(args[0]);
        System.out.println(String.format("%s 文件预处理成功",args[0]));
        test = PreHandle.Handle(args[1]);
        System.out.println(String.format("%s 文件预处理成功",args[1]));
        double result = CosineSimilarity.getSimilarity(orig,test);
        WriteAns.writeTxtFile(args[2],result);
        System.out.println(String.format("相识度为 %.2f", result));
        System.out.println(String.format("花费时间：%d ms", System.currentTimeMillis()-now));
    }
}
