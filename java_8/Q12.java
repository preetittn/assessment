import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Q12 {
    public static void main(String[] args) {
//        List<Integer> l= Arrays.asList(1,2,3,4,5,6);

 ArrayList<Integer> l=new ArrayList<Integer>();
 l.add(0);
 l.add(5);
 l.add(10);
 l.add(6);
        System.out.println(l);
        List<Integer> l1=l.stream().filter(I->I%2==0).collect(Collectors.toList());
        System.out.println(l1);

    }
}
