import java.util.ArrayList;

public class RoomClass {

    //external knowledge - location
    //input - words searched : print response & provide boolean
    //factors to consider - exit nsew, description, item list

    private boolean north = false;
    private boolean west = false;
    private boolean east = false;
    private boolean south = false;
    private boolean darkRoom = false;

    private String description;
    private ArrayList<Item> itemsInRoom;
    private ArrayList<String> doorsInRoom;  //eg doorNClosed, doorNOpen doorWOpen, ....

    public RoomClass(boolean n, boolean w, boolean e, boolean s, String describeEnter) {
        north = n;
        west = w;
        east = e;
        south = s;
        description = describeEnter;
    }

    public RoomClass(boolean n, boolean w, boolean e, boolean s, String describeEnter, ArrayList<Item> itemsInRoom) {
        this(n,w,e,s,describeEnter);
        this.itemsInRoom = itemsInRoom;
    }

    public RoomClass(boolean darkRoom, boolean n, boolean w, boolean e, boolean s, String describeEnter, ArrayList<Item> itemsInRoom){
        this(n,w,e,s,describeEnter,itemsInRoom);
        this.darkRoom = darkRoom;
    }


    public boolean checkInputPrint(String input) {

        if(Main.debugOn) {
            System.out.println("Debug - RoomClass: input: " + input);
        }

        boolean returnVal = false;

        if(north && (input.equals("n") || input.contains("north")) ){
            //req door check
            if(doorsInRoom.contains("doorNClosed")){
                System.out.println("A closed door blocks your way.");
            }
            Main.updatePos(0, 1);
            System.out.println("You move north.");
            Room.roomCheck(Main.getLocation() + "enter");
            returnVal = true;

        } else if(input.equals("n")){
            System.out.println("You cannot go north.");
            returnVal = true;
        }

        else if(west && (input.equals("w") || input.contains("west")) ){
            Main.updatePos(-1, 0);
            System.out.println("You move west.");
            Room.roomCheck(Main.getLocation() + "enter");
            returnVal = true;

        } else if(input.equals("w")){
            System.out.println("You cannot go west.");
            returnVal = true;
        }

        else if(east && (input.equals("e") || input.contains("east")) ){
            Main.updatePos(1, 0);
            System.out.println("You move east.");
            Room.roomCheck(Main.getLocation() + "enter");
            returnVal = true;

        } else if(input.equals("e")){
            System.out.println("You cannot go east.");
            returnVal = true;
        }

        else if(south && (input.equals("s") || input.contains("south")) ){
            Main.updatePos(0, -1);
            System.out.println("You move south.");
            Room.roomCheck(Main.getLocation() + "enter");
            returnVal = true;

        } else if(input.equals("s")){
            System.out.println("You cannot go south.");
            returnVal = true;
        }

        //room description - checks if candle required.
        else if(input.equals("enter")) {
            if(darkRoom && !Room.invCheck().contains("candle")) {
                System.out.println("The room is very dark.");
            } else {
                System.out.println(description);
                for(Item item : itemsInRoom) {
                    item.printDescriptionPlace();
                    System.out.println(", ");
                }
                System.out.println(".");
                Room.lookFloor(Main.getX(),Main.getY());
                returnVal = true;
            }
        }

        //examine items in room
        else if(Room.textCheckExamine(input)) {
            if(itemsInRoom.size() > 0){
                for(Item item : itemsInRoom) {

                    if(input.contains(item.name)) {
                        item.printDescriptionInv();
                    }
                }
                returnVal = true;
            }
        }

        //take items in room, put in inventory
        else if(Room.takeObjectCheck(input)) {
            if(itemsInRoom.size() > 0){
                for(Item item : itemsInRoom) {
                    if(item.pickUpable() && input.contains(item.name)){
                        //add to inventory
                        Room.addToInventory(item);
                        item.setToTaken();
                    } else if (!item.pickUpable()) {
                        System.out.println("You cannot pick up the " + item.name);
                    }
                }
                returnVal = true;
            }
        }

        //check usage
        else if(Room.textCheckItemUse(input)){
            if(itemsInRoom.size() > 0) {
                for(Item item : itemsInRoom) {
                    if(item.getUsableInPlace() && input.contains(item.name) ){
                        item.printDescriptionUsed();
                    }
                }
            }
        }



        return returnVal;

    }



}
