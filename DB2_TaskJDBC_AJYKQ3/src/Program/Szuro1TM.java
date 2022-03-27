package Program;

import javax.swing.table.DefaultTableModel;

// Elsõ szûrõ táblamodell
@SuppressWarnings("serial")
public class Szuro1TM extends DefaultTableModel {
	public Szuro1TM (Object mezoNevek[], int sor){
		super(mezoNevek, sor);
		}
	
	public boolean isCellEditable(int sor, int oszlop) {
		if (oszlop == 0) {return true;}
		return false;
		}
	
	public Class<?> getColumnClass(int index){
		if (index == 0) return(Boolean.class);
		else if (index == 1) return(Integer.class);
		return(String.class);
		}
}
