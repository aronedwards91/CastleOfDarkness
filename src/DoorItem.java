public class DoorItem extends Item {

    private Item keyToOpen;
    private boolean openDoor;

    public DoorItem(String name, String descriptionPlace, String descriptionUsedInPlace,Item keyToOpen){
        super(name,descriptionPlace,descriptionUsedInPlace,true);
        this.keyToOpen = keyToOpen;
        openDoor = false;
    }

    public boolean tryOpen(Item keyOpenTry) {
        if(keyOpenTry.name.equals(keyToOpen.name)){
            openDoor = true;
            return true;
        } else {
            return false;
        }
    }


}
