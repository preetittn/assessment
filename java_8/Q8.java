
interface Left
{
    default void m1()
    {
        System.out.println("left interface");
    }
}
interface Right
{
    default void m1()
    {
        System.out.println("right interface");
    }
}
public class Q8 implements Left,Right
{
    public void m1() {
//        System.out.println("main method definition");

    Left.super.m1();
    Right.super.m1();
    }
    public static void main(String[] args)
    {
        Q8 q=new Q8();
        q.m1();
    }
}
