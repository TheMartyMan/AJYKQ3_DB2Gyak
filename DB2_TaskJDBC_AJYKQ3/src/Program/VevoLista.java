package Program;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Color;

@SuppressWarnings("serial")
public class VevoLista extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private VevoTM vtm;
	DBMetodusok dbm = new DBMetodusok();

	@SuppressWarnings("unchecked")
	public VevoLista(JFrame f, VevoTM betm) {
		super(f, "Vevõk listája", true);
		setTitle("Vev\u0151k list\u00E1ja");
		vtm = betm;
		setBounds(100, 100, 670, 360);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton btnBezar = new JButton("Bez\u00E1r");
			btnBezar.setBackground(new Color(169, 169, 169));
			btnBezar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnBezar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			
			
			btnBezar.setBounds(542, 280, 104, 33);
			contentPanel.add(btnBezar);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 636, 260);
		contentPanel.add(scrollPane);
		
		table = new JTable(vtm);
		scrollPane.setViewportView(table);
		
		TableColumn tc = null;
		for (int i = 0; i < 6; i++) {
		tc = table.getColumnModel().getColumn(i);
		if (i==0 || i==1 ) tc.setPreferredWidth(20);
		else if (i==4) tc.setPreferredWidth(180);
		else {tc.setPreferredWidth(100);}
		}
		
		
		table.setAutoCreateRowSorter(true);
		
		
		// Adattörlés gombja és metódusai
		JButton btnTorol = new JButton("T\u00F6rl\u00E9s");
		btnTorol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db=0,jel=0,x=0;
				for (x = 0; x < vtm.getRowCount(); x++)
					if((Boolean)vtm.getValueAt(x, 0)) {db++; jel=x;}
				
				if(db==0) uzenet("Kérem jelöljön ki egy rekordot!");
				
				if(db>1) uzenet("Egyszerre csak egy rekord törölhetõ!");
				
				if(db==1) {
					dbm.kapcs();
					dbm.vevoTorol(RTM(jel,1));
					vtm.removeRow(jel);
					dbm.leKapcs();
				}
			}
		});
		
		
		btnTorol.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTorol.setBackground(new Color(169, 169, 169));
		btnTorol.setBounds(362, 280, 104, 33);
		contentPanel.add(btnTorol);
		
		
		// Adatmódosítás gomb és metódusai
		JButton btnModosit = new JButton("Modos\u00EDt\u00E1s");
		btnModosit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db=0, jel=0, x=0;
				
				for (x = 0; x <vtm.getRowCount(); x++)
					
					if((Boolean)vtm.getValueAt(x, 0)) {db++; jel=x;}
				
				if(db==0) uzenet("Kérem jelöljön ki egy rekordot!");
				
				if(db>1) uzenet("Egyszerre csak 1 rekord módosítható!");
				
				if(db==1) {
					VevoModosit mt = new VevoModosit(null, RTM(jel,1), RTM(jel,2), RTM(jel,3),  RTM(jel,4),  RTM(jel,5));
					mt.setVisible(true);
				}
			}
		});
		
		
		btnModosit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnModosit.setBackground(new Color(169, 169, 169));
		btnModosit.setBounds(134, 280, 104, 33);
		contentPanel.add(btnModosit);
		
		// Mentés
		JButton btnMent = new JButton("Ment\u00E9s");
		btnMent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dbm.kapcs();
					dbm.fajlMegnyit("Vevok.txt");
					dbm.vevoHozzaad();
					dbm.fajlBezar();
					dbm.leKapcs();
					uzenet("Sikeres fájlkiírás! Fájl neve: Vevok.txt");
				} catch (Exception e2) {
					uzenet("A fájlkiírás sikertelen volt: "+e2.getMessage());
				}
			}
		});
		
		
		btnMent.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnMent.setBackground(new Color(169, 169, 169));
		btnMent.setBounds(248, 280, 104, 33);
		contentPanel.add(btnMent);
		
		// Betöltés
		JButton btnBetolt = new JButton("Bet\u00F6lt\u00E9s");
		btnBetolt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbm.vevoBetolt("Vevok.txt");
			}
		});
		
		
		btnBetolt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBetolt.setBackground(new Color(169, 169, 169));
		btnBetolt.setBounds(20, 280, 104, 33);
		contentPanel.add(btnBetolt);
		TableRowSorter<VevoTM> trs =
		(TableRowSorter<VevoTM>)table.getRowSorter();
		trs.setSortable(0, false);

		
	}
	
	public String RTM(int row,int col) {
		return vtm.getValueAt(row, col).toString();
	}
	public void uzenet(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Üzenet", 2);
	}

}
