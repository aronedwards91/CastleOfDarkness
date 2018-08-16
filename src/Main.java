import java.util.Scanner;

public class Main {

    private static int Xpos;
    private static int Ypos;
    private static Scanner scanner = new Scanner(System.in);
    public static boolean debugOn = false;

    public static void main(String[] args) {
        //testing
        Xpos = 3;
        Ypos = 4;

        printMainStart();
        createItems();
        while(true){
            String entry = scanner.nextLine();
            if(entry.toLowerCase().equals("quit")) { break; }
            if(entry.toLowerCase().contains("debug")) { turnOnDebug(); }
            Text.read(entry);
        }
        System.exit(0);

    }

    public static String getLocation(){

        //ensures always 03 never 3
        String X;
        String Y;
        if (Xpos < 10)  { X = "0" + Xpos;}
        else            { X = "" + Xpos;}
        if (Ypos < 10)  { Y = "0" + Ypos;}
        else            { Y = "" + Ypos;}

        return X + Y;
    }

    public static int getX(){
        return Xpos;
    }

    public static int getY(){
        return Ypos;
    }

    public static void updatePos(int X, int Y){
        Xpos += X;
        Ypos += Y;
    }

    public static void printMainStart() {
        System.out.println("                                                                                  ");
        System.out.println("                                                                                  ");
        System.out.println("                                                        _______________           ");
        System.out.println("                                                      /----------------\\         ");
        System.out.println("                                                   _/--------------------\\_        ");
        System.out.println("          The                                    _/------------------------\\_    ");
        System.out.println("              Curse                             /_____________________________\\  ");
        System.out.println("                     of                         |    ___               ___     |  ");
        System.out.println("                        Darkhill                |   |+|+|             |+|+|    |  ");
        System.out.println("                                House           |                              |  ");
        System.out.println("                                                |    ___      / \\      ___     |  ");
        System.out.println("                                                |   |+|+|    |   |    |+|+|    |  ");
        System.out.println("                                                |            |   |             |  ");
        System.out.println("                                                                                  ");
        System.out.println("                                                                                  ");
        System.out.println("   Deep below old darkhill,                       |     |          |    |    |    ");
        System.out.println("   a dark essence lies,                               |      |           |  |     ");
        System.out.println("   fed by death, corruption,                    ____|____|_________|________|___  ");
        System.out.println("   and soul-scarring cries.                                                        ");
        System.out.println("                                                                                  ");
        System.out.println("                                                                                  ");
        System.out.println("You awaken from a deep slumber, your is memory hazy, and your is vision blank,");
        System.out.println("slowly you struggle onto your feet and feel a damp floor below you,");
        System.out.println("a thin shaft of moonlight pierces through the doorway to your north.");
        System.out.println("..                                                                        ");
        System.out.println("..                                                                       ");

    }

    public static void turnOnDebug() {
        debugOn = true;
    }

    public static void createItems(){

    }






}
