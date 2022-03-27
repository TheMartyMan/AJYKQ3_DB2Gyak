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
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class FilmLista extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private FilmTM ftm;
	DBMetodusok dbm = new DBMetodusok();

	public FilmLista(JFrame f,FilmTM betm) {
		super(f, "Filmek list�ja", true);
		setTitle("Filmek list\u00E1ja");
		ftm =betm;
		setBounds(100, 100, 670, 360);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(192, 192, 192));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			
			// Bez�r�s
			JButton btnBezar = new JButton("Bez\u00E1r");
			btnBezar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			
			
			btnBezar.setBackground(new Color(169, 169, 169));
			btnBezar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnBezar.setBounds(552, 293, 94, 30);
			contentPanel.add(btnBezar);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 10, 636, 273);
			contentPanel.add(scrollPane);
			{
				table = new JTable(ftm);
				
				scrollPane.setViewportView(table);
				
				TableColumn tc = null;
				for (int i = 0; i < 6; i++) {
				tc = table.getColumnModel().getColumn(i);
				if (i==0 || i==1 ) tc.setPreferredWidth(20);
				else if (i==2 ||i==3) tc.setPreferredWidth(180);
				else if (i==4 ) tc.setPreferredWidth(80);
				else {tc.setPreferredWidth(100);}
				}
				
				table.setAutoCreateRowSorter(true);
				{
					
					// T�rl�s
					JButton btnTorol = new JButton("T\u00F6rl\u00E9s");
					btnTorol.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int db=0,jel=0,x=0;
							for (x = 0; x < ftm.getRowCount(); x++)
								if((Boolean)ftm.getValueAt(x, 0)) {db++; jel=x;}
							if(db==0) uzenet("Nem jel�lt ki semmit!");
							if(db>1) uzenet("Egyszerre csak egy t�r�lhet�!");
							if(db==1) {
								dbm.kapcs();
								dbm.filmTorol(RTM(jel,1));
								ftm.removeRow(jel);
								dbm.leKapcs();
							}
						}
					});
					
					
					btnTorol.setFont(new Font("Tahoma", Font.BOLD, 12));
					btnTorol.setBackground(new Color(169, 169, 169));
					btnTorol.setBounds(332, 293, 94, 30);
					contentPanel.add(btnTorol);
				}
				{
					
					// M�dos�t�s
					JButton btnModosit = new JButton("M\u00F3dos\u00EDt\u00E1s");
					btnModosit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int db=0, jel=0, x=0;
							for (x = 0; x <ftm.getRowCount(); x++) 
								if((Boolean)ftm.getValueAt(x, 0)) {db++; jel=x;}
							if(db==0) uzenet("V�lasszon ki egy rekordot!");
							if(db>1) uzenet("Egyszerre egy rekord v�laszthat� ki!");
							if(db==1) {
								FilmModosit mt = new FilmModosit(null, RTM(jel,1), RTM(jel,2), RTM(jel,3),  RTM(jel,4),  RTM(jel,5));
								mt.setVisible(true);
							}
						}
					});
					btnModosit.setFont(new Font("Tahoma", Font.PLAIN, 12));
					btnModosit.setBackground(new Color(169, 169, 169));
					btnModosit.setBounds(124, 293, 94, 30);
					contentPanel.add(btnModosit);
				}
				{
					
					// Ment�s
					JButton btnMent = new JButton("Ment\u00E9s");
					btnMent.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								dbm.kapcs();
								dbm.fajlMegnyit("Filmek.txt");
								dbm.filmHozzaad();
								dbm.fajlBezar();
								dbm.leKapcs();
								uzenet("A f�jlki�r�s sikeres!\nA f�jl neve: Filmek.txt");
							} catch (Exception e2) {
								uzenet("A f�jlki�r�s sikertelen volt: "+e2.getMessage());
							}
						}
					});
					
					
					btnMent.setFont(new Font("Tahoma", Font.PLAIN, 12));
					btnMent.setBackground(new Color(169, 169, 169));
					btnMent.setBounds(228, 293, 94, 30);
					contentPanel.add(btnMent);
				}
				{
					// Bet�lt�s
					JButton btnBetolt = new JButton("Bet\u00F6lt\u00E9s");
					btnBetolt.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dbm.filmBetolt("Filmek.txt");
						}
					});
					
					
					btnBetolt.setFont(new Font("Tahoma", Font.PLAIN, 12));
					btnBetolt.setBackground(new Color(169, 169, 169));
					btnBetolt.setBounds(20, 293, 94, 30);
					contentPanel.add(btnBetolt);
				}
				
				
				@SuppressWarnings("unchecked")
				TableRowSorter<FilmTM> trs =
						(TableRowSorter<FilmTM>)table.getRowSorter();
				trs.setSortable(0, false);

			}
		}
		
		
	}
	
	public String RTM(int row,int col) {
		return ftm.getValueAt(row, col).toString();
	}
	public void uzenet(String msg) {
		JOptionPane.showMessageDialog(null, msg, "�zenet", 2);
	}

}
