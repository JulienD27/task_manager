package modernproject;

public class Entry {
    private String name;
    private String entry;

    public Entry(String[] data) {
        name = data[0];
        entry = data[1];
    }

    public String getEntryName(){
        return name;
    }

    public String getEntry(){
        return entry;
    }

    public void setEntryName(String newName){
        name = newName;
    }

    public void setEntry(String newEntry){
        entry = newEntry;
    }
}
