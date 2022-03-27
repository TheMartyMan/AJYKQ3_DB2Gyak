package Program;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

@SuppressWarnings("serial")
public class Menu extends JFrame {

	private JPanel contentPane;
	DBMetodusok dbm = new DBMetodusok();
	private VevoTM vtm;
	private FilmTM ftm;
	private MindenTM mindentm;


	public Menu(Bejelentkezes bejelentkezes) {
		super("Menü");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 461);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		// Driver regisztrálás
		JButton btnDriverReg = new JButton("Driver regisztr\u00E1l\u00E1s");
		btnDriverReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbm.driverReg();
			}
		});
		
		
		btnDriverReg.setBackground(new Color(169, 169, 169));
		btnDriverReg.setBounds(300, 357, 185, 55);
		btnDriverReg.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDriverReg.setForeground(SystemColor.desktop);
		contentPane.add(btnDriverReg);
		
		
		// Minden adat listázása
		JButton btnMindentListaz = new JButton("\u00D6sszes adat list\u00E1z\u00E1sa");
		btnMindentListaz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mindentm = dbm.ReadAllData();
				MindentListaz al = new MindentListaz(Menu.this, mindentm);
				al.setVisible(true);
			}
		});
		
		
		btnMindentListaz.setForeground(Color.BLACK);
		btnMindentListaz.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnMindentListaz.setBackground(new Color(169, 169, 169));
		btnMindentListaz.setBounds(10, 354, 223, 60);
		contentPane.add(btnMindentListaz);
		
		
		//Csak a vevõk listázása
		JButton btnVevoLista = new JButton("Vevõk listázása");
		btnVevoLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vtm = dbm.osszAdatVevo();
				VevoLista tl = new VevoLista(Menu.this, vtm);
				tl.setVisible(true);
			}
		});
		
		
		btnVevoLista.setForeground(Color.BLACK);
		btnVevoLista.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVevoLista.setBackground(new Color(169, 169, 169));
		btnVevoLista.setBounds(10, 59, 166, 60);
		contentPane.add(btnVevoLista);
		
		
		// Csak a filmek listázása
		JButton btnFilmLista = new JButton("Filmek list\u00E1z\u00E1sa");
		btnFilmLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ftm = dbm.osszAdatFilm();
				FilmLista kl = new FilmLista(Menu.this, ftm);
				kl.setVisible(true);
			}
		});
		
		
		btnFilmLista.setForeground(Color.BLACK);
		btnFilmLista.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnFilmLista.setBackground(new Color(169, 169, 169));
		btnFilmLista.setBounds(186, 59, 166, 60);
		contentPane.add(btnFilmLista);
		
		
		// Szûrõ menü hívása
		JButton btnSzuro = new JButton("Sz\u0171r\u00E9s");
		btnSzuro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Szuro sz = new Szuro();
				sz.setVisible(true);
			}
		});
		
		
		btnSzuro.setForeground(Color.BLACK);
		btnSzuro.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSzuro.setBackground(new Color(169, 169, 169));
		btnSzuro.setBounds(10, 196, 166, 60);
		contentPane.add(btnSzuro);
		
		
		// Táblák listázása
		JButton btnTablak = new JButton("T\u00E1bl\u00E1k");
		btnTablak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbm.getTabla();
			}
		});
		
		
		btnTablak.setForeground(Color.BLACK);
		btnTablak.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTablak.setBackground(new Color(169, 169, 169));
		btnTablak.setBounds(186, 196, 166, 60);
		contentPane.add(btnTablak);
		
		
		
		// Új adat hozzáadása
		JButton btnUjAdat = new JButton("\u00DAj adat felvitele");
		btnUjAdat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UjAdat ni = new UjAdat();
				ni.setVisible(true);
			}
		});
		
		
		btnUjAdat.setForeground(Color.BLACK);
		btnUjAdat.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUjAdat.setBackground(new Color(169, 169, 169));
		btnUjAdat.setBounds(490, 196, 166, 60);
		contentPane.add(btnUjAdat);
		
		
		// Kilépés
		JLabel lblKilep = new JLabel("Kil\u00E9p\u00E9s");
		lblKilep.setHorizontalAlignment(SwingConstants.CENTER);
		lblKilep.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblKilep.setBounds(553, 366, 103, 36);
		lblKilep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblKilep.setForeground(new Color(255,0,0));
				
			}
			public void mouseExited(MouseEvent e) {
				lblKilep.setForeground(new Color(0,0,0));
			}
			public void mouseClicked(MouseEvent arg0) {
				if(JOptionPane.showConfirmDialog(null, "Biztosan ki akar lépni?", "Kilépés", JOptionPane.YES_NO_OPTION)==0) {
					System.exit(0);
				}
			}
		});

		contentPane.add(lblKilep);
		
		JLabel lblDBKezelo = new JLabel("Adatb\u00E1zis kezel\u0151");
		lblDBKezelo.setForeground(new Color(0, 0, 0));
		lblDBKezelo.setHorizontalAlignment(SwingConstants.CENTER);
		lblDBKezelo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDBKezelo.setBounds(10, 10, 169, 25);
		lblDBKezelo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblDBKezelo.setForeground(new Color(192,192,192));				
			}
			public void mouseExited(MouseEvent e) {
				lblDBKezelo.setForeground(new Color(0,0,0));
			}
		});
		
		
		contentPane.add(lblDBKezelo);
		
		JLabel lblUser = new JLabel("Bejelentkezett felhasználó: "+Bejelentkezes.getStr());
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUser.setBounds(384, 11, 373, 25);
		contentPane.add(lblUser);
		
		
		// Kijelentkezés
		JButton btnKijelentkez = new JButton("Kijelentkez\u00E9s");
		btnKijelentkez.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Biztosan ki akar jelentkezni?", Bejelentkezes.getStr()+" felhasználó kijelentkezése", JOptionPane.YES_NO_OPTION)==0) {
				dispose();
				JOptionPane.showMessageDialog(null, Bejelentkezes.getStr()+" felhasználó sikeresen kijelentkezett!", "Üzenet", 2);
				Bejelentkezes.main(null);
				}
			}});
		
		
		btnKijelentkez.setBounds(516, 46, 116, 25);
		contentPane.add(btnKijelentkez);
		
		
		
		
		Object vevotmn[] = {"Jel","Kód","Név","Telefonszám","Lakcím","Születési idõ"};
		vtm = new VevoTM(vevotmn,0);
		
		
		Object filmtmn[] = {"Jel","Kód","Filmcím","Rendezõ","Megjelenés dátuma","Eladott jegyek"};
		ftm = new FilmTM(filmtmn, 0);
		
		
		Object alltmn[] = {"Jel","Kód","Filmcím","Rendezõ","Megjel. dátum","Eladott jegyek","Név","Telefonszám","Lakcím","Szül. idõ"};
		mindentm = new MindenTM(alltmn, 0);
		
		
		
		
		//Easter Egg
		
				if(Bejelentkezes.getStr().equals("Csucsu")) {
				JLabel lblEasterEgg = new JLabel("?");
				lblEasterEgg.setForeground(Color.GRAY);
				lblEasterEgg.setHorizontalAlignment(SwingConstants.CENTER);
				lblEasterEgg.setFont(new Font("Yu Gothic", Font.BOLD, 11));
				lblEasterEgg.setBounds(180, 15, 20, 20);
				contentPane.add(lblEasterEgg);
				lblEasterEgg.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						lblEasterEgg.setForeground(new Color(255,0,0));
								
					}
					public void mouseExited(MouseEvent e) {
						lblEasterEgg.setForeground((Color.GRAY));
					}
					public void mouseClicked(MouseEvent arg0) {
						try {
							URI ee = new URI("https://www.youtube.com/watch?v=t-Qju5Fow9I&t=18s");
							java.awt.Desktop.getDesktop().browse(ee);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			);}
	}
	public void uzenet(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Hibaüzenet", 2);
	}
}
