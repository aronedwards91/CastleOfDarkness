public class Item {

    //factors to consider; taken, used, location, description
    public final String name;
    private boolean taken = false;
    private boolean takeable = true;
    private boolean used = false;
    private boolean usableInPlace = false;

    private String descriptionInv;
    private String descriptionPlace;
    private String descriptionUsed;

    //constructor
    public Item(String name, String descriptionPlace){
        this.name = name;
        this.descriptionPlace = descriptionPlace;
        takeable = false;
    }

    public Item(String name, String descriptionPlace, String descriptionInventory) {
        this(name, descriptionPlace);
        this.descriptionInv = descriptionInventory;
    }

    public Item(String name, String descriptionPlace, String descriptionUsedInPlace, boolean usable){
        //for items not able to pickup
        this(name, descriptionPlace,descriptionUsedInPlace);
        usableInPlace = usable;
        this.descriptionUsed = descriptionUsedInPlace;
    }



    public void setToTaken() {
        this.taken = true;
    }

    public boolean getTaken() {
        return taken;
    }

    public void setToUsed() {
        this.used = true;
    }

    public boolean getUsed() {
        return used;
    }

    public boolean getUsableInPlace() {
        return usableInPlace;
    }

    public void printDescriptionPlace(){
        System.out.println(descriptionPlace);
    }

    public void printDescriptionInv() {
        System.out.println(descriptionInv);
    }

    public void printDescriptionUsed() {
        System.out.println(descriptionUsed);
    }


    public boolean pickUpable(){
        return takeable;
    }


}
