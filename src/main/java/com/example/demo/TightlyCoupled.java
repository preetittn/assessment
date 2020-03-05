
package com.example.demo;

public class TightlyCoupled {
    DemoService demo = new DemoService();

    public void tightlyDemo(){
        demo.createDemo();
    }

}

