import java.util.Arrays;
import java.util.List;

public class Q11{
    public static void main(String[] args) {
        List<Integer>list=Arrays.asList(1,2,3,4,5,7,9,6);
        System.out.println(
                list
                        .stream()
                        .filter(e->e>2)
                        .map(e->e*2)
                        .mapToDouble(e->e)
                        .average()
        );

    }
}
