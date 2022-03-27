package Program;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import java.awt.Color;

@SuppressWarnings("serial")
public class Bejelentkezes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	DBMetodusok dbm = new DBMetodusok();
	private JTextField nev;
	private JPasswordField jelszo;
	private int db=0;
	private static String str;


	public static void main(String[] args) {
		try {
			Bejelentkezes dialog = new Bejelentkezes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Bejelentkezes() {
		setBounds(100, 100, 575, 395);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		
		// Bejelentkezés: Ha jó a fellhasználó és jelszó kombináció, beenged, egyéb esetben nem.
		
		JButton btnBejelentkezes = new JButton("Bejelentkez\u00E9s");
		btnBejelentkezes.setBackground(new Color(169, 169, 169));
		btnBejelentkezes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbm.driverReg1();
				str = nev.getText();
				int pc = dbm.Login(RTF(nev), RTF(jelszo));
				if(pc==1) {
					Menu abkezel = new Menu(Bejelentkezes.this);
					abkezel.setVisible(true);
					dispose();
				}
				else if (db<4) {
					db++;
					uzenet("A felhasználónév vagy a jelszó helytelen!\nMég "+(5-db)+" próbálkozása maradt.");
				}
				else if (db<5) {
						db++;
						uzenet("Sikertelen bejelentkezés!\nA program most kilép.");
						System.exit(0);
				}
			}
		});
		
		
		btnBejelentkezes.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnBejelentkezes.setBounds(181, 269, 205, 60);
		contentPanel.add(btnBejelentkezes);
		
		
		JLabel lblFelhasznalo = new JLabel("Felhaszn\u00E1l\u00F3n\u00E9v");
		lblFelhasznalo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFelhasznalo.setHorizontalAlignment(SwingConstants.CENTER);
		lblFelhasznalo.setBounds(181, 45, 205, 32);
		contentPanel.add(lblFelhasznalo);
		
		
		JLabel lblJelszo = new JLabel("Jelsz\u00F3");
		lblJelszo.setHorizontalAlignment(SwingConstants.CENTER);
		lblJelszo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblJelszo.setBounds(181, 133, 205, 32);
		contentPanel.add(lblJelszo);
		
		
		nev = new JTextField();
		nev.setHorizontalAlignment(SwingConstants.CENTER);
		nev.setBounds(164, 77, 247, 26);
		contentPanel.add(nev);
		nev.setColumns(10);
		
		
		
		// Jelszó láthatóság
		JCheckBox chckbxLathatosag = new JCheckBox("Legyen l\u00E1that\u00F3 a jelsz\u00F3?");
		chckbxLathatosag.setForeground(Color.WHITE);
		chckbxLathatosag.setFont(new Font("Tahoma", Font.PLAIN, 10));
		chckbxLathatosag.setBackground(Color.BLACK);
		chckbxLathatosag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxLathatosag.isSelected()) {
					jelszo.setEchoChar((char)0);
				} else {
					jelszo.setEchoChar('•');
				}
			}
		});
		
		
		chckbxLathatosag.setBounds(17, 153, 140, 41);
		
		contentPanel.add(chckbxLathatosag);
		
		jelszo = new JPasswordField();
		jelszo.setHorizontalAlignment(SwingConstants.CENTER);
		jelszo.setBounds(166, 163, 246, 22);
		contentPanel.add(jelszo);
		
		JButton btnKilepes = new JButton("Kil\u00E9p\u00E9s");
		btnKilepes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Biztosan ki akar lépni?", "Kilépés", JOptionPane.YES_NO_OPTION)==0) {
					dispose();
				}
			}
		});
		
		
		btnKilepes.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnKilepes.setBounds(455, 316, 96, 32);
		contentPanel.add(btnKilepes);
		
		setTitle("Bejelentkezés az adatbázisba");
		
	}
	
	
	public String RTF(JTextField jtf) {
		return jtf.getText();
	}
	
	public void uzenet(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Hiba", 2);
	}

	public static String getStr() {
		return str;
	}
}
