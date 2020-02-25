interface Inter
    {
        public int operation(int a,int b);
    }
public class Q2
{
    public static void main(String[] args)
    {
        Inter i=(a,b)-> {
            return b;
        };
        System.out.println("returned :"+i.operation(3,2));
    }
}
