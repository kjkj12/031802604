import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<String> orig = new ArrayList<>();
    private static List<String> test = new ArrayList<>();

    public static void main(String[] args){
        long now = System.currentTimeMillis();
        orig = PreHandle.Handle(args[0]);
        test = PreHandle.Handle(args[1]);
        WriteAns.writeTxtFile(args[2],CosineSimilarity.getSimilarity(orig,test));
        System.out.println(String.format("花费时间：%d", System.currentTimeMillis()-now));
    }
}
