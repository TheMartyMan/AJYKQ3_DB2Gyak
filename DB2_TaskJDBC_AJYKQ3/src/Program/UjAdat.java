package Program;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class UjAdat extends JDialog {

	private final JPanel contentPanel = new JPanel();

	// Új adat felvitele
	public UjAdat() {
		setTitle("Új adat");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(192, 192, 192));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			
			// Bezárás
			JButton btnBezar = new JButton("Bez\u00E1r");
			btnBezar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			
			
			btnBezar.setBackground(new Color(169, 169, 169));
			btnBezar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnBezar.setBounds(307, 217, 99, 36);
			contentPanel.add(btnBezar);
		}
		{
			// Vevõ adat felvitele gomb, új vevõ adat metódus hívása
			JButton btnVevo = new JButton("Vev\u0151");
			btnVevo.setBackground(new Color(169, 169, 169));
			btnVevo.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnVevo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UjVevo nv = new UjVevo(null);
					nv.setVisible(true);
				}
			});
			btnVevo.setBounds(89, 116, 115, 47);
			contentPanel.add(btnVevo);
		}
		{
			// Film adat felvitele gomb, új film adat metódus hívása
			JButton btnFilm = new JButton("Film");
			btnFilm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UjFilm nf = new UjFilm(null);
					nf.setVisible(true);
				}
			});
			btnFilm.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnFilm.setBackground(new Color(169, 169, 169));
			btnFilm.setBounds(214, 116, 115, 47);
			contentPanel.add(btnFilm);
		}
		{
			JLabel lblValaszt = new JLabel("K\u00E9rem v\u00E1lasszon!");
			lblValaszt.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblValaszt.setHorizontalAlignment(SwingConstants.CENTER);
			lblValaszt.setBackground(new Color(192, 192, 192));
			lblValaszt.setBounds(10, 10, 160, 47);
			contentPanel.add(lblValaszt);
		}
		{
			JLabel lblTipus = new JLabel("Milyen tipus\u00FA adatot szeretne felvinni?");
			lblTipus.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblTipus.setBackground(new Color(192, 192, 192));
			lblTipus.setHorizontalAlignment(SwingConstants.CENTER);
			lblTipus.setBounds(34, 46, 372, 50);
			contentPanel.add(lblTipus);
		}
	}

}
