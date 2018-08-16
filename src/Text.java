import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Text {

    public static int  ranNumber = (int)(Math.random() * 5000);
    public static String filename = ranNumber + "out.txt";

    public static String read(String entry){
        String result = "debug--" + entry.toLowerCase();
        String toTest = entry.toLowerCase();
        String location = Main.getLocation();


        switch (toTest) {
            case "n" : Room.roomCheck(location + "n"); break;

            case "e" : Room.roomCheck(location + "e"); break;

            case "s" : Room.roomCheck(location + "s"); break;

            case "w" : Room.roomCheck(location + "w"); break;


            default:
                if(Main.debugOn) {System.out.println("debug: default reached |" + toTest + "|" );}



                //common commands to any area
                if(toTest.contains("help") || toTest.equals("guide") || toTest.contains("?")) {
                    System.out.println("You are playing a text game. \n To move use n s e w, to move north, south ... \n Otherwise use command like look, take, pickup, use x on y to solve the puzzles around you."); break; }

                //inventory
                if(toTest.equals("inventory")  || toTest.equals("show inventory") || toTest.equals("inv")) {System.out.println(Room.invCheck()); break; }
                if(toTest.contains("coat") && Room.heavyCoat || toTest.contains("drop") || toTest.contains("discard") || toTest.contains("take off")) {
                    Room.heavyCoat = false;
                    System.out.println("You discard the coat onto the floor, felling lighter & cooler."); break; }
                if(toTest.equals("look") ||  ( toTest.contains("look") && toTest.contains("around") || toTest.contains("room")  || toTest.contains("room") || toTest.contains("surroundings")) ) {
                    if(Main.debugOn) { System.out.println("debug:look"); }
                    Room.roomCheck(location + "enter"); break;
                }

                //examine inventory items
                if(Room.textCheckExamine(toTest)) {
                    if(invCheck(toTest)){ break; }
                    //no break as initial trigger words may be used in rooms
                }

                if(toTest.contains("read")){
                    //not taken, in room
                    if(!Room.note1 && location == "0205" && toTest.contains("note") || toTest.contains("paper")) {

                    }
                    //notes in inventory
                    if(Room.notesHeld == 1 && toTest.equals("read note")){  //only holding 1 note
                        if(Room.note1) {System.out.println(Room.note1text); break;}
                        if(Room.note2) {System.out.println(Room.note2text); break;}
                        if(Room.note3) {System.out.println(Room.note3text); break;}
                    }
                    if(Room.notesHeld > 1){  //holding more than 1
                        if(Room.note1 && toTest.contains("first") || toTest.contains("1") || toTest.contains("bloody")) {   //bloody note -1-
                            System.out.println(Room.note1text); break;
                        } else if(Room.note2 && toTest.contains("second") || toTest.contains("2") || toTest.contains("torn")) { //torn note -2-
                            System.out.println(Room.note2text); break;
                        } else if(Room.note3 && toTest.contains("third") || toTest.contains("3") || toTest.contains("hidden")) { // hidden note -3-
                            System.out.println(Room.note3text); break;
                        }
                    }
                    System.out.println("You are unsure what you want to read."); break;
                }
                if(toTest.contains("feel")) { System.out.println("Feeling tells you little about the room"); break; }
                if(toTest.contains("eyes")) {
                    if(Room.candleTaken){
                        System.out.println("You eyes struggle under the thin light of the candle," +
                                "\nthey provide little additional information.");
                    } else {
                        System.out.println("Your eyes cannot see well in the darkness.");
                    }
                }

                //last check is room other then, default nothing found
                //-
                //-
                if(Room.roomCheck(location + toTest)) {
                    System.out.println("Your actions are odd and have no effect!");
                    log(toTest);

                }
                //nothing below here!!!!
        }


        System.out.print("\n==]");
        return result;

    }

    public static void log(String msg){

        try {
            FileWriter writer = new FileWriter(filename, true);
            PrintWriter output = new PrintWriter(writer, true);
            if(Main.debugOn) {
                System.out.println("msg written to file.");
            }
            output.write(msg + ",   ");
            output.close();
        }catch (IOException e) {
            System.out.println("error : File creation failed.");
        }


    }

    public static boolean invCheck(String msg) {

        if(msg.contains("coat") && Room.heavyCoat){
            if(Room.note1) { System.out.println(Room.descHeavyCoat); return true;
            } else { System.out.println(Room.descHeavyCoatHasNote);
                Room.note1 = true; return true; }
        } else if(msg.contains("candle") && Room.candleTaken ){
            System.out.println(Room.descCandle); return true;

        } else if(Room.hallKeySmall && (msg.contains("key") && msg.contains("small")) )  {
            System.out.println(Room.descHallKey); return true;
        } else if(Room.frontKey && (msg.contains("key") && msg.contains("large")) ) {
            System.out.println(Room.descFrontKey); return true;
        } else {
            return false;
        }
    }


}
