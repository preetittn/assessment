public class Employee {
    String name;
    int age;
    String city;
    Employee(String name,int age,String city){
        this.name=name;
        this.age=age;
        this.city=city;
    }

    protected void show(){

        System.out.println("This is employee"+" :Name-> "+name+" :City->"+age+" :City->"+city+" ");
    }

    public static void main(String[] args) {
        Employee emp=new Employee ("Gunja",23,"Nainital");
        emp.show();
    }
}
interface constrefinterface{
    Employee auto (String name,int age,String city);
}