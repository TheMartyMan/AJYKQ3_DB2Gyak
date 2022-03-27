package Program;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class FilmModosit extends JDialog {

	private final JPanel contentPanel = new JPanel();
	DBMetodusok dbm = new DBMetodusok();
	private JTextField filmid;
	private JTextField cim;
	private JTextField rendezo;
	private JTextField mdatum;
	private JTextField vettjegy;
	private JTextField cim2;
	private JTextField rendezo2;
	private JTextField mdatum2;
	private ArrayList<String> vevoidStr;
	DBMetodusok dbm1 = new DBMetodusok();
	private JTextField vettjegy2;
	
	
	
	public FilmModosit(JDialog d, String befilmid, String becim, String berendezo, String bemdatum, String bevevoid) {
		super(d, "Adatok módosítása", true);
		setBounds(100, 100, 565, 332);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(192, 192, 192));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		JLabel lblKod = new JLabel("K\u00F3d:");
		lblKod.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKod.setBounds(10, 27, 45, 42);
		contentPanel.add(lblKod);
		
		
		JLabel lblCim = new JLabel("C\u00EDm:");
		lblCim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCim.setBounds(10, 79, 45, 13);
		contentPanel.add(lblCim);
		
		
		JLabel lblRendezo = new JLabel("Rendez\u0151:");
		lblRendezo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRendezo.setBounds(10, 118, 72, 13);
		contentPanel.add(lblRendezo);
		
		
		JLabel lblMegjDatum = new JLabel("Megjelen\u00E9s d\u00E1tuma :");
		lblMegjDatum.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMegjDatum.setBounds(10, 141, 144, 41);
		contentPanel.add(lblMegjDatum);
		
		
		JLabel lblMegvett = new JLabel("Hozz\u00E1rendelt Vev\u0151 ID-ja:");
		lblMegvett.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMegvett.setBounds(10, 192, 163, 21);
		contentPanel.add(lblMegvett);
		
		
		filmid = new JTextField(befilmid);
		filmid.setFont(new Font("Tahoma", Font.PLAIN, 14));
		filmid.setBounds(58, 38, 135, 21);
		filmid.setHorizontalAlignment(SwingConstants.CENTER);
		filmid.setEditable(false);
		contentPanel.add(filmid);
		filmid.setColumns(10);
		
		
		cim = new JTextField(becim);
		cim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cim.setBounds(58, 76, 164, 19);
		cim.setHorizontalAlignment(SwingConstants.CENTER);
		cim.setEditable(false);
		cim.setColumns(10);
		contentPanel.add(cim);
		
		
		rendezo = new JTextField(berendezo);
		rendezo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rendezo.setBounds(81, 115, 164, 19);
		rendezo.setHorizontalAlignment(SwingConstants.CENTER);
		rendezo.setEditable(false);
		rendezo.setColumns(10);
		contentPanel.add(rendezo);
		
		mdatum = new JTextField(bemdatum);
		mdatum.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mdatum.setBounds(149, 152, 96, 19);
		mdatum.setHorizontalAlignment(SwingConstants.CENTER);
		mdatum.setEditable(false);
		mdatum.setColumns(10);
		contentPanel.add(mdatum);
		
		
		vettjegy = new JTextField(bevevoid);
		vettjegy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		vettjegy.setBounds(172, 193, 135, 19);
		vettjegy.setHorizontalAlignment(SwingConstants.CENTER);
		vettjegy.setEditable(false);
		vettjegy.setColumns(10);
		contentPanel.add(vettjegy);
		
		
		cim2 = new JTextField();
		cim2.setBounds(351, 78, 190, 19);
		contentPanel.add(cim2);
		cim2.setColumns(10);
		
		
		rendezo2 = new JTextField();
		rendezo2.setBounds(351, 117, 190, 19);
		rendezo2.setColumns(10);
		contentPanel.add(rendezo2);
		
		
		mdatum2 = new JTextField();
		mdatum2.setBounds(351, 154, 190, 19);
		mdatum2.setColumns(10);
		contentPanel.add(mdatum2);
		
		JButton btnModosit = new JButton("M\u00F3dos\u00EDt\u00E1s!");
		btnModosit.setBounds(194, 246, 124, 39);
		btnModosit.setBackground(new Color(169, 169, 169));
		btnModosit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbm.kapcs();
				if (!datumEll(mdatum2)) uzenet("A megjelenési dátum formátuma helytelen!");
				if (!szamEll(vettjegy2)) uzenet("A jegyek darabszáma egész szám kell legyen!");
				else{
				dbm.updateFilm(RTF(filmid), RTF2(cim2,cim), RTF2(rendezo2,rendezo), RTF2(mdatum2,mdatum), RTF2(vettjegy2,vettjegy));
				dbm.leKapcs();
				}
			}
		});
		
		
		btnModosit.setForeground(new Color(0, 0, 0));
		btnModosit.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPanel.add(btnModosit);
		
		dbm1.kapcs();
		vevoidStr = dbm1.vevoidOlvas();
		dbm1.leKapcs();
		
		@SuppressWarnings("unused")
		Object[] real = vevoidStr.toArray();
		
		JLabel lblJelenlegiAdat = new JLabel("Jelenlegi adat");
		lblJelenlegiAdat.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblJelenlegiAdat.setBounds(10, 10, 183, 21);
		contentPanel.add(lblJelenlegiAdat);
		
		JLabel lbljUjAdat = new JLabel("\u00DAj adat");
		lbljUjAdat.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbljUjAdat.setBounds(412, 37, 183, 21);
		contentPanel.add(lbljUjAdat);
		
		vettjegy2 = new JTextField();
		vettjegy2.setColumns(10);
		vettjegy2.setBounds(351, 191, 190, 19);
		contentPanel.add(vettjegy2);

	}
	
	public String RTF(JTextField jtf) {
		return jtf.getText();
	}
	
	
	
	public String RTF2(JTextField jtf2, JTextField jtf) {
		if(jtf2.getText().length()==0)
			return jtf.getText();
		return jtf2.getText();
	}
	
	
	public String RTF3(@SuppressWarnings("rawtypes") JComboBox jcb, JTextField jtf) {
		if(jcb.getSelectedItem().toString().equals("Válasszon!"))
			return jtf.getText();
		return jcb.getSelectedItem().toString();
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
	
	
	public boolean szamEll(JTextField jtf) {
		String s = RTF(jtf);
		try {
			Integer.parseInt(s); return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public void uzenet(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Hibaüzenet", 2);
	}
}
