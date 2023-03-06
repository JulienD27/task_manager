package modernproject;

public class Events implements Comparable<Events>{
    private String eName;
    private String eDate;

    public Events(String[] dataArr) {
        eName = dataArr[0];
        eDate = dataArr[1];
    }

    public String getEventName(){
        return eName;
    }

    public String getEventDate(){
        return eDate;
    }

    public void setEventName(String name) {
        eName = name;
    }

    public void setEventDate(String date) {
        eDate = date;
    }

    @Override
    public int compareTo(Events o) {
        return eName.compareTo(o.getEventName());
    }

    public String toString() {
        return eName;
    }
}
