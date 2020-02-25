import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Q5 {
    public static void main(String[] args) {
        System.out.println("Function********");
        Function<Integer,Integer>f=i->i*i;
        System.out.println("square of 4 is "+f.apply(4));
        System.out.println("square of 5 is "+f.apply(5));

        System.out.println("Predicate********");
        Predicate<Integer> p=i->i%2==0;
        System.out.println(p.test(4));
        System.out.println(p.test(5));

        System.out.println("Consumer**********");
        Consumer<String> c=s-> System.out.println(s);
        c.accept("hello");
        c.accept("preeti");

        System.out.println("Supplier************");
        Supplier<String> d=()->{
            String[] d1={"aa","bb","cc","dd"};
            int x=(int)(Math.random()*3+1);
            return d1[x];
        };
        System.out.println(d.get());
        System.out.println(d.get());
        System.out.println(d.get());
        System.out.println(d.get());

    }
}
