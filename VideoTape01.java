//import java.util.Scanner;
abstract class VideoTape01 {
    String title;
    String length;
    String Avail;
    abstract void show();
}
class movie extends VideoTape01{
    //	this.title = "jawan";
//	this.length = "Three Hour";
//	this.Avail = "Yes";
    String Mo,Director,Rating;
    void Store(String M,String D,String R) {
        Mo = M;
        Director = D;
        Rating = R;
    }
    void Display() {
//		System.out.println("The Movie Name is : "+Mo);
        System.out.println("The Director is : "+Director);
        System.out.println("The Rating is :  "+Rating);
    }
    void show() {
        System.out.println("The Title of movie is "+title+" having Length of "+length+"! And It is Directed by "+Director+" And It has "+Rating+" Star Rating, Avability : "+Avail);
        System.out.println();
    }
}
class MusicVideo extends VideoTape01{
    //	this.title = "Shape of You";
//	this.length = "Two min";
//	this.Avail = "Yes";
    String Mv,MusicArtist,Category;
    void Store(String Mv,String MA,String C) {
        this.Mv = Mv;
        MusicArtist = MA;
        Category = C;
    }
    void Display() {
//		System.out.println("The Name of MusicVideo is = : "+Mv);
        System.out.println("The Artist of Song is : "+MusicArtist);
        System.out.println("The Rating is "+Category);
    }
    void show() {
        System.out.println("The Title of movie is "+title+" having Length of "+length+" ! And The Artist is "+MusicArtist+" And It has "+Category+" Star Rating, Avability : "+Avail);
        System.out.println();
    }
    public static void main(String[] args) {

        movie m1 = new movie();
        m1.title = "jawan";
        m1.length = "Three Hour";
        m1.Avail = "Yes";
        m1.Store("Jawan","Atlee","Four");
        m1.Display();
        m1.show();

        MusicVideo m2 = new MusicVideo();
        m2.title = "Shape of You";
        m2.length = "Two min";
        m2.Avail = "Yes";
        m2.Store("Shape of You","Ed Sherran","Five");
        m2.Display();
        m2.show();
    }

}

