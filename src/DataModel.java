import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DataModel {
    private ArrayList<String> data;
    private ArrayList<ChangeListener> listeners;
    private GregorianCalendar cal;

    public DataModel()
    {
        data = new ArrayList<>();
        listeners = new ArrayList<>();
        cal = new GregorianCalendar();
    }

    public int getMonthDays(){
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public int getCurrentMonth(){
        return cal.get(Calendar.MONTH);
    }

    public int getCurrentYear(){
        return cal.get(Calendar.YEAR);
    }

    public int getCurrentDay(){
        return cal.get(Calendar.DATE);
    }

    // Accessor
    public ArrayList<String> getData(){
        return this.data;
    }

    // Mutator
    public void update(String event){
        data.add(event);
        ChangeEvent e = new ChangeEvent(this);
        for (ChangeListener l: listeners)
        {
            l.stateChanged(e);
        }
    }

    // Attach
    public void attach(ChangeListener listener){
        listeners.add(listener);
    }

}
