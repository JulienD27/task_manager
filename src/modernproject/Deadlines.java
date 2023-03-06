package modernproject;

public class Deadlines {
    private String month;
    private int day;
    private String dayWeek;
    private String task;
    private String event;

    public Deadlines(String month, int day, String dayWeek , String task, String event){
        this.month = month;
        this.day = day;
        this.dayWeek = dayWeek;
        this.task = task;
        this.event = event;
    }

    public String getMonth(){
        return month;
    }

    public String getDay(){
        return String.valueOf(day);
    }

    public String getDayWeek(){
        return dayWeek;
    }

    public String getTask(){
        return task;
    }

    public String getEvent(){
        return event;
    }

    public void setTask(String newTask){
        this.task = newTask;
    }

    public void setEvent(String newEvent){
        this.event = newEvent;
    }
}
