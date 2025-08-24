package org.iti;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello from Java App 2!");
        System.out.println("Running on JDK 17");
        
        if (args.length > 0) {
            System.out.println("Arguments received: " + String.join(", ", args));
        }
    }
    
    public String getMessage() {
        return "Hello from Java App 2!";
    }
}
