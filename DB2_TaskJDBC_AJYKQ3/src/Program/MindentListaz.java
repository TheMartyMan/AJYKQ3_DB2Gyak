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
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

@SuppressWarnings("serial")
public class MindentListaz extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private MindenTM atm;
	DBMetodusok dbm = new DBMetodusok();
	
	
	public MindentListaz(JFrame f, MindenTM betm) {
		super(f,"Összes adat", true);
		setTitle("\u00D6sszes adat");
		atm = betm;
		setBounds(100, 100, 1100, 320);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		// Bezárás
		JButton btnBezar = new JButton("Bez\u00E1r");
		btnBezar.setBackground(new Color(169, 169, 169));
		btnBezar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); 
			}
		});
		
		
		btnBezar.setBounds(549, 252, 85, 24);
		contentPanel.add(btnBezar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 1066, 232);
		contentPanel.add(scrollPane);
		
		table = new JTable(atm);
		scrollPane.setViewportView(table);
		
		TableColumn tc = null;
		for (int i = 0; i < 10; i++) {
		tc = table.getColumnModel().getColumn(i);
		if (i==0 || i==1|| i==5 ) tc.setPreferredWidth(10);
		else if (i==4 || i==9 ) tc.setPreferredWidth(60);
		else if (i==2 || i==6) tc.setPreferredWidth(120);
		else if (i==8) tc.setPreferredWidth(140);
		else {tc.setPreferredWidth(100);}
		}
		
		table.setAutoCreateRowSorter(true);
		
		
		// Mentés
		JButton btnMent = new JButton("Ment\u00E9s");
		btnMent.setBackground(new Color(169, 169, 169));
		btnMent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dbm.kapcs();
					dbm.fajlMegnyit("Összes.txt");
					dbm.addOsszes();
					dbm.fajlBezar();
					dbm.leKapcs();
					SM("A fájlkiírás sikeres!\nA kiírt fájl neve: Összes.txt");
				} catch (Exception e2) {
					SM("Hiba a fájlkiírás során: "+e2.getMessage());
				}
			}
		});
		
		
		btnMent.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnMent.setBounds(454, 252, 85, 24);
		contentPanel.add(btnMent);
		
		@SuppressWarnings("unchecked")
		TableRowSorter<MindenTM> trs = (TableRowSorter<MindenTM>)table.getRowSorter();
		trs.setSortable(0, false);
	}
	
	public void SM(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Üzenet", 2);
	}
}
