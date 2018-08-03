import javax.swing.JTable;

/**
 * This interface is the View for the day, week and month view
 */
public interface View {
	abstract JTable createEventTable();
	abstract JTable timeTable();
	abstract void updateEventTable();
	abstract void updateLabel();
}
