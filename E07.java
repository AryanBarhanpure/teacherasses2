class UnderAgeException1 extends RuntimeException{   // Unchecked Exception
    UnderAgeException1(){          // 1 Constructor Overloading (Different Parameters)
        super("You are under age");  // Super Refers to the parent class constructor
    }
    UnderAgeException1(String message){  // 2
        super(message);    // "message" goes to "default exception handler"which is parent class
    }
}
public class E07 {
    public static void main(String[] args) {
        int age=17;
        try{
            if(age<18){
                throw new UnderAgeException1("You cannnot vote as you are below age"); //throw new UnderAgeException("") massage goes to 1
            }
            else{
                System.out.println("You can vote now....!!");
            }
        }
        catch(UnderAgeException1 e){
            e.printStackTrace();
        }
    }
}