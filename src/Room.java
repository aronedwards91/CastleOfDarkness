import java.util.ArrayList;
import java.util.Arrays;

public class Room {

    //NoPrinting!! - take over Text class?

    public static ArrayList<Item> Inventory = new ArrayList<>();
    public static void addToInventory(Item item) {
        Inventory.add(item);
    }


    public static String oddAction = "This doesn't seem to accomplish anything";

    //logic vars
    public static String dropArrayXY[][] = new String[5][7];
    public static boolean frontDoorUnlocked = false;
    public static boolean coatTaken = false;
    public static boolean hallDoorOpen = false;
    public static boolean bedroomCOpen = false;

    //-----inventory vars-----
    //starting room
    //dining room - chair, candle, kitchen door, cutlery
    private static Item Candle = new Item("candle","A lonely candle flickers, illuminating a slowly rotting meal set out haphazardly upon the table","The candle is small and gives off only a small light.");
    private static Item Chair = new Item("chair", "a well crafted wooden chair sits facing the fireplace","The chair is comfortable to sit in.",true);
    private static Item Cutlery = new Item("cutlery", "rusty old cutlery is spread across the table","The cutlery is too rusty to use.",true);
    private static Item KitchenDoor = new Item("door", "a door leads north toward the kitchen", "The door is blocked from the other side",true);


    //Area A
    public static boolean candleTaken = false;
        public static String descCandle = "The candle is small and gives off only a small light.";
    public static boolean hallKeySmall = false;  // small key , hidden behind harold's portrait
        public static String descHallKey = "It is a small victorian era room key.";
    public static boolean heavyCoat = false;
        public static String descHeavyCoat = "It is a heavy winter coat.";
        public static String descHeavyCoatHasNote = "It is a heavy winter coat. In the pocket is a torn note.\n You take the note and place it in your pocket.";
        //useless items
    public static boolean pinsTaken = false;
        public static String descPins = "The pins are small but the end is ornate, they are not very sharp";

    //Area B
    public static boolean frontKey = false; // large key
        public static String descFrontKey = "The key is heavy, old and well ornamented, it has held up well.";

    //
    public static boolean bedroomKey = false; //ornate key
        public static String descBedroomKey = "The key is very ornate, with elegant detail along it's length.";

    //notes
    public static int notesHeld = 0;
    public static boolean note1 = false; // bloody note
    public static String note1text = "Why why why?!, I cannot stand it, it seems I will die soon." +
            "\nfor all my sins I am so very sorry." +
            "\n.." +
            "\nwhether dangerous or not I intend to step outside for " +
            "\nair. Let them have me if that is their will." +
            "\n.." +
            "\nMartin.";
    public static boolean note2 = false; // torn note
    public static String note2text = "..coming for me!!, I will be in the dining room, I don't know" +
            "\nhow much paper I have left, nor how much wits. I will " +
            "\nleave you my notes beneath the windowsill." +
            "\n.." +
            "\nMartin.";
    public static boolean note3 = false; // hidden note
    public static String note3text = "I am now very afraid, whatever has been chasing me is getting closer," +
            "\nI fear it will nto be long before I am caught, if you can" +
            "\ntake the key I've hidden from behind the fat gentleman's " +
            "\nportrait, please, rescue me, your all the hope I have" +
            "\nleft now!" +
            "\n.." +
            "\nMartin.";

    //Room object creation

    private static RoomClass DiningRoom = new RoomClass(false,false,false,true,"You are in a grimy and messy dining room, it has not been cleaned in an age, paint falls from the wall, most of the cutlery is spread around aimlessly.",new ArrayList<>(Arrays.asList(Candle,Chair,KitchenDoor,Cutlery)));
    //Add new items to below invCheck

    public static String invCheck(){
        String result = "";
        int items = 0;
        if(pinsTaken) {result = result + "Pins, "; items++; }
        if(hallKeySmall) {result = result + "Ornate key, "; items++; }
        if(heavyCoat) {result = result + "Heavy Coat, "; items++; }
        if(note1) {result = result + "Bloody Note, "; items++; }
        if(note2) {result = result + "Torn Note, "; items++; }
        if(note3) {result = result + "Hidden Note, "; items++; }
        //area b
        if(frontKey) {result = result + "Large Key, "; items++; }

        for(Item item : Inventory) {
            result = result + item.name + ", ";
            items++;
        }


        if(items == 0) {result = "Nothing in inventory";}
        return result;
    }


    public static boolean roomCheck(String entry){
        String test = entry.toLowerCase();
        boolean returnVal = true;  // true means nothing found to match
        if(Main.debugOn) { System.out.println("debugRoom: roomCheck entry: " + entry); }
        String testWord = test.substring(4,test.length());

        switch (test.substring(0,4)) {

            case "0304" : // starting room

                switch (test) {
                    case "0304n":
                        Main.updatePos(0, 1);
                        System.out.println("You move north");
                        roomCheck(Main.getLocation() + "enter");
                        returnVal = false; break;
                    case "0304e":
                        Main.updatePos(1, 0);
                        System.out.println("You move east");
                        roomCheck(Main.getLocation() + "enter");
                        returnVal = false; break;
                    case "0304w":
                        Main.updatePos(-1, 0);
                        System.out.println("You move west");
                        roomCheck(Main.getLocation() + "enter");
                        returnVal = false; break;
                    case "0304s":
                        Main.updatePos(0, -1);
                        System.out.println("You move south");
                        roomCheck(Main.getLocation() + "enter");
                        returnVal = false; break;
                    case "0304enter":
                        if (candleTaken) {
                            System.out.println("You enter an Entranceway. \n The are a few dusty seats and some heavily worn out clothes, upon the floor is a red streak leading east");
                        } else {
                            System.out.println("You enter a dark room. You can only make out a rough idea of the room");
                        } lookFloor(Main.getX(),Main.getY());
                        break;
                    case "0304look":

                }
                //key interactions: -blood stain, -worn clothes
                //<editor-fold desc="interactions-fold">
                if(test.contains("clothes") && textCheckExamine(test) ) {
                    System.out.println("The clothes are moth eaten, and fall apart when touched."); returnVal = false; break;
                }
                if(test.contains("blood") || test.contains("stain") && textCheckExamine(test) ) {
                    System.out.println("the streak is somewhat fresh, it leads east"); returnVal = false; break;
                }
                if(test.contains("clothes") || test.contains("blood")) {
                    returnVal = oddActionPrint(test);
                }

                //</editor-fold>
                break;

            //example roomclass  ||||   case "0305" : DiningRoom.checkEntry(test-minuslocal)  -- object.check(input)


            case "0305" :  //dining room

                if(!DiningRoom.checkInputPrint(testWord)) {returnVal = false; break;}


            case "0303" :  //foyer

                switch (test) {
                    case "0303n":
                        Main.updatePos(0, 1);
                        System.out.println("You move north");
                        roomCheck(Main.getLocation() + "enter");
                        returnVal = false;
                        break;
                    case "0303e":
                        System.out.println("You cannot move east");
                        returnVal = false;
                        break;
                    case "0303w":
                        System.out.println("You cannot move west");
                        returnVal = false;
                        break;
                    case "0303s":
                        if (frontKey == true) {
                            Main.updatePos(0, -1);
                            System.out.println("You move south");
                            roomCheck(Main.getLocation() + "enter");
                            returnVal = false;
                            break;
                        } else {
                            System.out.println("The front door does not respond to any pushes or movements of the huge iron door handle.");
                            returnVal = false;
                            break;
                        }

                    case "0303enter":
                        if(coatTaken){
                            System.out.println("You are in the foyer, moonlight streams in through the victorian iron windows, " +
                                    "\n A huge Oak door remains shut before you, a wild smell of woodland passes from under it." +
                                    "\n to the sides remains some old moth eaten clothes.");
                        } else {
                            System.out.println("You are in the foyer, moonlight streams in through the victorian iron windows, " +
                                    "\n A huge Oak door remains shut before you, a wild smell of woodland passes from under it." +
                                    "\n to the sides remains some old moth eaten clothes, except for one newer looking, heavy winter coat.");
                        } lookFloor(Main.getX(),Main.getY());
                        break;

                }
                //key interactions: -coat'torn'note -&enter, -door, -window
                //<editor-fold desc="interactions-fold">
                if(test.contains("coat") && heavyCoat == false) {
                    returnVal = false;
                    if(takeObjectCheck(test) || test.contains("wear") || test.contains("equip") || test.contains("put on")) {
                        heavyCoat = true; coatTaken = true;
                        System.out.println("You put the coat over to keep you warm, it makes you somewhat hot in the well heated house.");returnVal = false; break;
                    } else if(textCheckExamine(test)){
                        if(Room.note1) { System.out.println(Room.descHeavyCoat);
                        } else { System.out.println(Room.descHeavyCoatHasNote);
                            Room.note1 = true; break;
                        }
                    } else {
                        returnVal = oddActionPrint(test);
                    }
                    break;
                }
                if(test.contains("door") && !frontKey && !frontDoorUnlocked && (test.contains("open") || test.contains("pull") || test.contains("push")  || test.contains("charge") || test.contains("punch") || test.contains("look") || test.contains("examine") ) ) {
                    returnVal = false;
                    System.out.println("The heavy oak door steadfastly ignores your attempt's to force it.");break;
                } else if(test.contains("door") && frontKey && !frontDoorUnlocked && test.contains("open") || test.contains("unlock") || test.contains("use key on")){
                    frontDoorUnlocked = true;
                    System.out.println("The heavy door creaks open, exposing the cold outside," +
                            "\nbefore you is a dark woodland with a thin path. A horrid wild" +
                            "\nsmell assaults your sense."); returnVal = false; break;
                } else if(test.contains("door") && frontDoorUnlocked) {
                    System.out.println("The door lies open, exposing the cold outdoors."); returnVal = false; break;
                }
                if(test.contains("window") && test.contains("smash") || test.contains("break") || textCheckExamine(test) ) {
                    if(test.contains("through") || test.contains("outside")) {
                        returnVal = windowActionPrint(true); break;
                    } else {
                        returnVal = windowActionPrint(false); break;
                    }
                }
                if(test.contains("door") || test.contains("window")) {
                    returnVal = oddActionPrint(test); break;
                }
                //</editor-fold>
                break;

            case "0404" : // lounge

                switch (test) {
                    case "0404n":
                        if(hallKeySmall){
                            if(hallDoorOpen) {
                                Main.updatePos(0, 1);
                                System.out.println("You move north, through the hallway door.");
                                roomCheck(Main.getLocation() + "enter");
                                returnVal = false;
                                break;
                            } else {
                                System.out.println("You test the small key on the door, it opens up." +
                                        "You move north, through the hallway door.");
                                Main.updatePos(0, 1);
                                roomCheck(Main.getLocation() + "enter");
                                returnVal = false;
                                break;
                            }
                        } else {
                            System.out.println("The door ahead is locked and will not budge.");
                        }
                    case "0404e":
                        System.out.println("You cannot move east");
                        returnVal = false;
                        break;
                    case "0404w":
                        Main.updatePos(-1, 0);
                        System.out.println("You move west");
                        roomCheck(Main.getLocation() + "enter");
                        returnVal = false;
                        break;
                    case "0404s":
                        System.out.println("You cannot move south");
                        returnVal = false;
                        break;

                    case "0404enter":
                        if(candleTaken){
                            System.out.println("You are in a lounge area," +
                                    "sofas and armchairs litter the room, with a bookcase on the east side." +
                                    "On the floor is a blood stain leading through the north door.");
                        } else {
                            System.out.println("You enter a dark room, you fumble around and notice some soft furniture.");
                        }lookFloor(Main.getX(),Main.getY());
                        break;

                }
                //key interactions: -hall door, -armchair-sofa-furniture, -bookcase, -keyhole
                //<editor-fold desc="interactions-fold">
                if((test.contains("Sofa") || test.contains("armchair") || test.contains("furniture") || test.contains("chair")) ) {
                    if (textCheckExamine(test)) {
                        System.out.println("The furniture is somewhat well worn, nothing of use can be found on it."); returnVal = false; break;
                    } else {
                        returnVal = oddActionPrint(test); break;
                    }
                }
                if(test.contains("book") || test.contains("writing")) {
                        if(textCheckExamine(test)){
                            System.out.println("The bookshelf contains many old books, all equally tattered & worn."); returnVal = false; break;
                        } else if (test.contains("read")) {
                            System.out.println("The books are too fragile & specialist to be read," +
                                    "\n mostly dealing with human anatomy & medicine." +
                                    "\n You examine a few titles: Words Medical anatomy," +
                                    "\n Grey's complete guide to skin." +
                                    "\n Williams simple Amputation.");returnVal = false; break;
                        } else {
                            returnVal = oddActionPrint(test); break; }
                }
                if(test.contains("door") || test.contains("north")) {
                    if(textCheckExamine(test)) {
                        if(hallKeySmall){
                            System.out.println("Examining the door you test the small key, it's a easy" +
                                    "\nfit, turning the key the door opens, revealing a hallway before you.");
                            hallDoorOpen = true; returnVal = false; break;
                        }
                        if(hallDoorOpen) {
                            System.out.println("The door before you lies open.");returnVal = false; break;
                        }
                        System.out.println("The door is old but strong, it rattle when struck," +
                                "\n but will not break, there is a small keyhole below the handle.");returnVal = false; break;
                    } else {
                        returnVal = oddActionPrint(test); break;
                    }
                }
                if(test.contains("use") &&  hallKeySmall && test.contains("key") && (test.contains("door") || test.contains("lock")) ) {
                    if(hallDoorOpen) {
                        System.out.println("The door is already open.");returnVal = false; break;
                    } else {
                        System.out.println("You unlock the hallway door, it opens with a creak.");
                        hallDoorOpen = true;
                        returnVal = false; break;
                    }
                }
                if(test.contains("Keyhole")) {
                    if(textCheckExamine(test) || test.contains("peer")) {
                        System.out.println("Peering through the keyhole you see only darkness."); returnVal = false; break;
                    } else {
                        returnVal = oddActionPrint(test); break;
                    }
                }
                //</editor-fold>

                break;

            case "0204" : //hallway west 1

                switch (test) {
                    case "0204n":
                        Main.updatePos(0, 1);
                        System.out.println("You move north");
                        roomCheck(Main.getLocation() + "enter");
                        returnVal = false;
                        break;
                    case "0204e":
                        Main.updatePos(1, 0);
                        System.out.println("You move east");
                        roomCheck(Main.getLocation() + "enter");
                        returnVal = false;
                        break;
                    case "0204w":
                        System.out.println("You cannot move west");
                        returnVal = false;
                        break;
                    case "0204s":
                        System.out.println("You cannot move south");
                        returnVal = false;
                        break;

                    case "0204enter":
                        if(candleTaken){
                            System.out.println("You are in a narrow hallway, you see a number of" +
                                    "\nold portraits, each one has been slashed with a knife," +
                                    "\nother marks of unleashed rage litter the hallway." +
                                    "\nThe hallway leads north.");
                        } else {
                            System.out.println("You enter a dark room, fumbling, you find it seems like a narrow hallway.");
                        }lookFloor(Main.getX(),Main.getY());
                        break;

                }
                //key interactions: -paintings.
                //<editor-fold desc="interactions">
                if(test.contains("painting") || test.contains("portrait") || test.contains("picture") || test.contains("art")) {
                    if (textCheckExamine(test) && candleTaken) {
                        System.out.println("The paintings are old oil paintings, the are covered in many" +
                                "slashes, likely made by a sharp knife.");returnVal = false; break;
                    } else {
                        returnVal = oddActionPrint(test); break;
                    }
                }
                //</editor-fold>
                break;

            case "0205" : //hallway west 2

                switch (test) {
                    case "0205n":
                        System.out.println("You cannot move north");
                        returnVal = false;
                        break;
                    case "0205e":
                        System.out.println("You cannot move east");
                        returnVal = false;
                        break;
                    case "0205w":
                        if(bedroomCOpen) {
                            Main.updatePos(-1, 0);
                            System.out.println("You move west");
                            roomCheck(Main.getLocation() + "enter");
                            returnVal = false;
                            break;
                        } else if(bedroomKey) {
                            System.out.println("You try the ornate key on the door, it opens.");
                            bedroomCOpen = true;
                            Main.updatePos(-1, 0);
                            System.out.println("You move west");
                            roomCheck(Main.getLocation() + "enter");
                            returnVal = false;
                            break;
                        } else {
                            System.out.println("You cannot move west as the door is closed.");
                            returnVal = false;
                            break;
                        }
                    case "0205s":
                        Main.updatePos(0, -1);
                        System.out.println("You move south");
                        returnVal = false;
                        break;

                    case "0205enter":
                        if(candleTaken){
                            if(note1){

                            } else {
                                System.out.println("You are in a narrow hallway, there are more damaged" +
                                        "\n portraits on the wall. on the north a portrait, heavily slashed" +
                                        "\n has a note stuck on it, held by multiple pins.");
                            }
                        } else {
                            System.out.println("You enter a dark room, fumbling, you find it seems like a narrow hallway.");
                        }lookFloor(Main.getX(),Main.getY());
                        break;

                }
                //item interaction; -portraits, -note, -pins, -bedroom door
                //<editor-fold desc="Interactions">
                if(test.contains("door") || test.contains("west") ) {
                    if(bedroomKey && (test.contains("key") || textCheckExamine(test)) ){
                        System.out.println("You try the ornate key on the door, it opens.");
                        returnVal = false; break;
                    }
                    if(textCheckExamine(test)) {
                        if(bedroomCOpen){
                            System.out.println("The door is open, it is ornate and undamaged except" +
                                    "\nfor the wear of time."); returnVal = false; break;
                        } else {
                            System.out.println("The door is ornate and seems less damaged than others." +
                                    "\nIt resists any attempts to be force open"); returnVal = false; break;
                        }
                    } else {
                        returnVal = oddActionPrint(test);
                    }
                }
                if(test.contains("note") || test.contains("paper")) {
                    if(takeObjectCheck(test)) {
                        System.out.println("You put the note in your pocket.");
                        note1 = true;
                        returnVal = false; break;
                    }
                    if(textCheckExamine(test)) {
                        System.out.println("You read the note pinned to the wall.");
                        System.out.println(note1text);
                        returnVal = false; break;
                    }
                }
                if(test.contains("painting") || test.contains("portrait") || test.contains("picture") || test.contains("art"))
                    if(textCheckExamine(test) && candleTaken){
                        System.out.println("The paintings are old oil paintings, the are covered in many" +
                                "slashes, likely made by a sharp knife."); returnVal = false; break;
                    } else if( (test.contains("behind") || textCheckExamine(test) || test.contains("lift") || test.contains("underneath") || test.contains("back")) && (test.contains("harold") || test.contains("rotund") || test.contains("fat")  )) {
                        hallKeySmall = true;
                        System.out.println("You search behind the portrait of the rotund gentleman and find an ornate key.");
                        returnVal = false; break;
                    } else{
                        returnVal = oddActionPrint(test);
                    }
                if(test.contains("pin")) {
                        if(takeObjectCheck(test)){
                            System.out.println("You pocket the pins.");
                            pinsTaken = true; returnVal = false;
                            if(!note1) {
                                System.out.println("You also take the note for good measure.");
                                note1 = true;
                                returnVal = false; break;
                            }
                            break;
                        }else if(textCheckExamine(test)) {
                            System.out.println(descPins); returnVal = false; break;
                        } else {
                            returnVal = oddActionPrint(test);
                        }
                        break;
                }
                //</editor-fold>
                break;

            //Area B

            case "0405" : //hallway east 1

                switch (test) {
                    case "0405n":
                        Main.updatePos(0, 1);
                        System.out.println("You move north");
                        returnVal = false;
                        break;
                    case "0405e":
                        Main.updatePos(1, 0);
                        System.out.println("You move east");
                        returnVal = false;
                        break;
                    case "0405w":
                        System.out.println("You cannot move west");
                        returnVal = false;
                        break;
                    case "0405s":
                        Main.updatePos(0, -1);
                        System.out.println("You move south");
                        returnVal = false;
                        break;

                    case "0405enter":
                        System.out.println("You enter a small poorly decorated & dilapidated corridor" +
                                        "\na shabby painting sits on the west wall, to the north the" +
                                        "\ncorridor continues, and to the east a door leads to a small" +
                                        "\nbedroom." );
                        lookFloor(Main.getX(),Main.getY());
                        returnVal = false;
                        break;

                }
                //item interaction; portrait
                if(test.contains("painting") || test.contains("portrait") || test.contains("picture") || test.contains("art")) {
                    if (textCheckExamine(test) && candleTaken) {
                        System.out.println("The painting is faded image of an angry looking old lady,.");returnVal = false; break;
                    } else {
                        returnVal = oddActionPrint(test); break;
                    }
                }
                break;

            case "0406" : //hallway east 2

            case "0505" : //Bedroom A

            case "0306" : //kitchen

            //Area C

        }

        return returnVal;
    }

 public static boolean textCheckExamine(String text){
        if(text.contains("examine") || text.contains("look") || text.contains("check") || text.contains("look") || text.contains("touch") || text.contains("inspect") || text.contains("study") || text.contains("view") || text.contains("investigate") ) {
            return true;
        } else {
            return false;
        }
 }

 public static boolean textCheckItemUse(String text) {
     if(text.contains("use")) {
         return true;
     }else {
         return false;
     }

 }


 public static boolean takeObjectCheck(String text) {
     if(text.contains("take") || text.contains("pick") || text.contains("steal")  || text.contains("pick") || text.contains("use")  || text.contains("pocket")  || text.contains("collect")  || text.contains("grab") || text.contains("obtain") || text.contains("carry")  || text.contains("gather")) {
         return true;
     } else {
         return false;
     }

 }

 public static void lookFloor(int x, int y) {
     //print items on floor
     if (!(dropArrayXY[x][y] == null)){
         System.out.println("On the floor lies " + dropArrayXY);
     }
 }

 public static boolean oddActionPrint(String msg){
     System.out.println(oddAction);
     Text.log(msg);
     return false;
 }

 public static boolean windowActionPrint(boolean lookThroughTrue){
     if(lookThroughTrue){
         System.out.println("You gaze through the window, you can see" +
                 "\nonly darkness, and maybe the odd tree.");
     } else {
         System.out.println("The window is crosshatched with iron bands, it is impassable.");
     }
     return false;
 }


}
