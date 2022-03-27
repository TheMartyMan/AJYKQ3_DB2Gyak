package Program;

import javax.swing.table.DefaultTableModel;

// Film táblamodellje

@SuppressWarnings("serial")
public class FilmTM extends DefaultTableModel {
	public FilmTM (Object mezoNev[], int sor){
		super(mezoNev, sor);
		}
	
	public boolean isCellEditable(int row, int oszlop) {
		if (oszlop == 0) {return true;}
		return false;
		}
	
	public Class<?> getColumnClass(int index){
		if (index == 0) return(Boolean.class);
		else if (index==1 || index==5) return(Integer.class);
		return(String.class);
		}
}
