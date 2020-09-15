import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<String> orig = new ArrayList<>();
    private static List<String> test = new ArrayList<>();

    public static void main(String[] args){
        orig = PreHandle.Handle(args[0]);
        test = PreHandle.Handle(args[1]);

        double d = CosineSimilarity.getSimilarity(orig,test);
        WriteAns.writeTxtFile(args[2],d);
        System.out.println(d);
    }
}
