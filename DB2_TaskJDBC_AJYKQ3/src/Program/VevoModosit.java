package Program;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VevoModosit extends JDialog {

	private final JPanel contentPanel = new JPanel();
	DBMetodusok dbm = new DBMetodusok();
	private JTextField vevoid;
	private JTextField nev;
	private JTextField tel;
	private JTextField cim;
	private JTextField szülido;
	private JTextField ujnev;
	private JTextField ujtel;
	private JTextField ujcim;
	private JTextField ujszulido;
	
	
	
	public VevoModosit(JDialog d, String befilmid, String benev, String betel, String becim, String beszülido) {
		super(d, "Adatok módosítása", true);
		setBounds(100, 100, 610, 401);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(192, 192, 192));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		JLabel lblKod = new JLabel("K\u00F3d:");
		lblKod.setBounds(10, 81, 45, 13);
		lblKod.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPanel.add(lblKod);
		
		
		JLabel lblNev = new JLabel("N\u00E9v:");
		lblNev.setBounds(10, 148, 45, 13);
		lblNev.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPanel.add(lblNev);
		
		
		JLabel lblTelefon = new JLabel("Telefonsz\u00E1m:");
		lblTelefon.setBounds(10, 178, 95, 16);
		lblTelefon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPanel.add(lblTelefon);
		
		
		JLabel lblLakcim = new JLabel("Lakc\u00EDm:");
		lblLakcim.setBounds(10, 214, 72, 13);
		lblLakcim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPanel.add(lblLakcim);
		
		
		JLabel lblSzulido = new JLabel("Sz\u00FClet\u00E9si id\u0151:");
		lblSzulido.setBounds(10, 249, 95, 13);
		lblSzulido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPanel.add(lblSzulido);
		
		
		vevoid = new JTextField(befilmid);
		vevoid.setBounds(45, 78, 135, 19);
		vevoid.setFont(new Font("Tahoma", Font.PLAIN, 14));
		vevoid.setHorizontalAlignment(SwingConstants.CENTER);
		vevoid.setEditable(false);
		contentPanel.add(vevoid);
		vevoid.setColumns(10);
		
		
		nev = new JTextField(benev);
		nev.setBounds(45, 145, 164, 19);
		nev.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nev.setHorizontalAlignment(SwingConstants.CENTER);
		nev.setEditable(false);
		nev.setColumns(10);
		contentPanel.add(nev);
		
		
		tel = new JTextField(betel);
		tel.setBounds(101, 177, 149, 19);
		tel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tel.setHorizontalAlignment(SwingConstants.CENTER);
		tel.setEditable(false);
		tel.setColumns(10);
		contentPanel.add(tel);
		
		
		cim = new JTextField(becim);
		cim.setBounds(72, 211, 164, 19);
		cim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cim.setHorizontalAlignment(SwingConstants.CENTER);
		cim.setEditable(false);
		cim.setColumns(10);
		contentPanel.add(cim);
		
		
		szülido = new JTextField(beszülido);
		szülido.setBounds(101, 246, 135, 19);
		szülido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		szülido.setHorizontalAlignment(SwingConstants.CENTER);
		szülido.setEditable(false);
		szülido.setColumns(10);
		contentPanel.add(szülido);
		
		
		ujnev = new JTextField();
		ujnev.setBounds(338, 147, 168, 19);
		contentPanel.add(ujnev);
		ujnev.setColumns(10);
		
		
		ujtel = new JTextField();
		ujtel.setBounds(338, 179, 168, 19);
		ujtel.setColumns(10);
		contentPanel.add(ujtel);
		
		
		ujcim = new JTextField();
		ujcim.setBounds(338, 213, 168, 19);
		ujcim.setColumns(10);
		contentPanel.add(ujcim);
		
		
		ujszulido = new JTextField();
		ujszulido.setBounds(338, 248, 168, 19);
		ujszulido.setColumns(10);
		contentPanel.add(ujszulido);
		
		
		// Adatmódosítás, dátumellenõrzés
		JButton btnModosit = new JButton("M\u00F3dos\u00EDt\u00E1s!");
		btnModosit.setBounds(237, 315, 120, 39);
		btnModosit.setBackground(new Color(169, 169, 169));
		btnModosit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbm.kapcs();
				if(!datumEll(ujszulido)) uzenet("A születési idõ helytelen!");
				else {
				dbm.updateVevo(RTF(vevoid), RTF2(ujnev,nev), RTF2(ujtel,tel), RTF2(ujcim,cim), RTF2(ujszulido,szülido));
				dbm.leKapcs();
				}
			}
		});
		
		
		btnModosit.setForeground(new Color(0, 0, 0));
		btnModosit.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPanel.add(btnModosit);
		
		JLabel lblJelenlegiAdat = new JLabel("Jelenlegi adat");
		lblJelenlegiAdat.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblJelenlegiAdat.setBounds(10, 10, 135, 40);
		contentPanel.add(lblJelenlegiAdat);
		
		JLabel lblUjAdat = new JLabel("\u00DAj adat");
		lblUjAdat.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUjAdat.setBounds(384, 97, 135, 40);
		contentPanel.add(lblUjAdat);
	}
	
	public String RTF(JTextField jtf) {
		return jtf.getText();
	}
	public String RTF2(JTextField jtf2, JTextField jtf) {
		if(jtf2.getText().length()==0)
			return jtf.getText();
		return jtf2.getText();
	}
	
	
	public boolean datumEll(JTextField jtf) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.mm.dd");
		String s = RTF(jtf);
		Date testDate = null;
		try {
			testDate = sdf.parse(s);
		} catch (ParseException e) {return false;}
		if(sdf.format(testDate).equals(s)) return true;
		else return false;
	}
	

	
	public void uzenet(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Hibaüzenet", 2);
	}
}
