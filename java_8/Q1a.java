interface Greaterthanint{
    boolean greaterint(int a,int b);
}

interface Incremental1 {
    int increment1(int a);
}

interface Concatenation{
    String concatefun(String a,String b);
}
interface Uppercase{
    String upper(String a);
}
class Q1a{
    public static void main(String[] args) {
        Greaterthanint ob1=(a,b)->{
            return a>b;
        };
        System.out.println("Greater no by comparing first to second : "+ob1.greaterint(3,8));

        Incremental1 ob2=(a)->{
            return a+1;
        };
        System.out.println("incrementing no : "+ob2.increment1(8));

        Concatenation ob3=(a,b)->{
            return a+b;
        };
        System.out.println("concat two strings : "+ob3.concatefun("good ","morning"));

        Uppercase ob4=(a)->{
            return  a.toUpperCase();
        };
        System.out.println("changing lowercase to uppercase : "+ob4.upper("tr2 group2"));
    }
}