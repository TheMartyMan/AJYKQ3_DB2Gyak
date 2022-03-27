package Program;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Formatter;

import javax.swing.JOptionPane;

public class DBMetodusok {
	private Statement s = null;
	private Connection conn = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	private Formatter x;

	
	
	// Kapcsolódás
	public void kapcs() {
		try {
			String url = "jdbc:sqlite:src/beadandodb.db";
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			uzenet("JDBC kapcsolódási hiba: " + e.getMessage());
		}
	}

	
	
	// Lekapcsolódás
	public void leKapcs() {
		try {
			conn.close();
		} catch (SQLException e) {
			uzenet(e.getMessage());
		}
	}

	
	
	// Driver regisztárálás gombhoz
	public void driverReg() {
		try {
			Class.forName("org.sqlite.JDBC");
			uzenet("Sikeres driver regisztráció!");
		} catch (ClassNotFoundException e) {
			uzenet("Hiba lépett fel a driver regisztráció során!" + e.getMessage());
		}
	}

	
	
	// Driver regisztárálás
	public void driverReg1() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			uzenet("Hiba lépett fel a driver regisztráció során!" + e.getMessage());
		}
	}

	
	
	// Üzenet kiírás
	public void uzenet(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Üzenet", 2);
	}

	
	
	// Bejelentkezés
	public int Login(String nev, String jelszo) {
		kapcs();
		int pc = -1;
		String sql = "SELECT COUNT(*) pc FROM Felhasznalo WHERE nev='" + nev + "' AND jelszo='" + jelszo + "';";
		try {
			s = conn.createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				pc = rs.getInt("pc");
			}
			rs.close();
		} catch (Exception e) {
			uzenet(e.getMessage());
		}
		leKapcs();
		return pc;
	}

	
	
	// Vevo tábla adatok kiolvasása
	public VevoTM osszAdatVevo() {
		Object vevotmn[] = { "Jel", "Kód", "Név", "Telefonszám", "Lakcím", "Születési idõ" };
		VevoTM vtm = new VevoTM(vevotmn, 0);
		String nev = "", tel = "", lakcim = "", szülido = "";
		int vevoid = 0;
		String sql = "SELECT * from Vevo";
		kapcs();
		try {
			s = conn.createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				vevoid = rs.getInt("vevoid");
				nev = rs.getString("nev");
				tel = rs.getString("tel");
				lakcim = rs.getString("lakcim");
				szülido = rs.getString("szülido");
				vtm.addRow(new Object[] { false, vevoid, nev, tel, lakcim, szülido });
			}
			rs.close();
		} catch (SQLException e) {
			uzenet(e.getMessage());
		}
		leKapcs();
		return vtm;
	}

	
	
	// Film tábla adatok kiolvasása
	public FilmTM osszAdatFilm() {
		Object filmtmn[] = { "Jel", "Kód", "Filmcím", "Rendezõ", "Megjelenés dátuma", "Vevõ ID" };
		FilmTM ftm = new FilmTM(filmtmn, 0);
		String filmcim = "", rendezo = "", mdatum = "";
		int filmid = 0, vevoid = 0;
		String sql = "SELECT * from Film";
		kapcs();
		try {
			s = conn.createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				filmid = rs.getInt("filmid");
				filmcim = rs.getString("filmcim");
				rendezo = rs.getString("rendezo");
				mdatum = rs.getString("mdatum");
				vevoid = rs.getInt("vevoid");
				ftm.addRow(new Object[] { false, filmid, filmcim, rendezo, mdatum, vevoid });
			}
			rs.close();
		} catch (SQLException e) {
			uzenet(e.getMessage());
		}
		leKapcs();
		return ftm;
	}

	
	
	// Vevõ törlése
	public void vevoTorol(String vevoid) {
		String sql = "DELETE from Vevo WHERE vevoid=" + vevoid;
		try {
			s = conn.createStatement();
			s.execute(sql);
		} catch (Exception e) {
			uzenet("Vevõ rekord törlés sikertelen: " + e.getMessage());
		}
	}

	
	
	// Film adat törlése
	public void filmTorol(String filmid) {
		String sql = "DELETE from Film where filmid=" + filmid;
		try {
			s = conn.createStatement();
			s.execute(sql);
		} catch (Exception e) {
			uzenet("Film rekord törlés sikertelen: " + e.getMessage());
		}
	}

	
	
	// Vevõ tábla módosítása
	public void updateVevo(String vevoid, String nev, String tel, String lakcim, String szülido) {
		kapcs();
		String sql = "UPDATE Vevo SET nev= '" + nev + "', tel= '" + tel + "', lakcim= '" + lakcim + "', szülido= '"
				+ szülido + "' WHERE vevoid='" + vevoid + "'";
		try {
			s = conn.createStatement();
			s.execute(sql);
			uzenet("Vevõ tábla sikeresen módosítva!");
		} catch (Exception e) {
			uzenet("Adatmódosítás sikertelen: " + e.getMessage());
		}
		leKapcs();
	}

	
	
	// Film tábla módosítása
	public void updateFilm(String filmid, String filmcim, String rendezo, String mdatum, String vevoid) {
		kapcs();
		String sql = "UPDATE Film set filmcim= '" + filmcim + "', rendezo= '" + rendezo + "', mdatum= '" + mdatum + "', vevoid= '"
				+ vevoid + "' WHERE filmid='" + filmid + "'";
		try {
			s = conn.createStatement();
			s.execute(sql);
			uzenet("Film tábla sikeresen módosítva!");
		} catch (Exception e) {
			uzenet("Adatmódosítás sikertelen: " + e.getMessage());
		}
		leKapcs();
	}

	
	
	// Fájlmegnyitás
	public void fajlMegnyit(String txt) {
		try {
			x = new Formatter(txt);
		} catch (Exception e) {
			uzenet(e.getMessage());
		}
	}

	
	
	// Fájl bezárása
	public void fajlBezar() {
		x.close();
	}

	
	
	// Vevok.txt feltöltése
	public void vevoHozzaad() {
		String nev = "", tel = "", lakcim = "", szülido = "";
		int vevoid = 0;
		String sql = "SELECT * from Vevo";
		try {
			s = conn.createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				vevoid = rs.getInt("vevoid");
				nev = rs.getString("nev");
				tel = rs.getString("tel");
				lakcim = rs.getString("lakcim");
				szülido = rs.getString("szülido");
				x.format(vevoid + ";" + nev + ";" + tel + ";" + lakcim + ";" + szülido + "\n");
			}
			rs.close();
		} catch (SQLException e) {
			uzenet(e.getMessage());
		}
	}

	
	
	// Vevok.txt betöltése
	public void vevoBetolt(String fajl) {
		String sql = "";
		kapcs();
		try {
			BufferedReader in = new BufferedReader(new FileReader(fajl));
			String sf = in.readLine();
			sf = in.readLine();
			while (sf != null) {
				String[] st = sf.split(";");
				sql = "REPLACE INTO Vevo VALUES(" + st[0] + ", '" + st[1] + "', '" + st[2] + "', '" + st[3] + "', '"
						+ st[4] + "')";
				s = conn.createStatement();
				s.execute(sql);
				sf = in.readLine();
			}
			in.close();
			uzenet("Adatok sikeresen betöltve!");
		} catch (IOException | SQLException e) {
			uzenet("Az adatok betöltése sikertelen: " + e.getMessage());
		}
	}

	
	
	// Filmek.txt feltöltése
	public void filmHozzaad() {
		String filmcim = "", rendezo = "", mdatum = "";
		int filmid = 0, vevoid = 0;
		String sql = "SELECT * from Film";
		try {
			s = conn.createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				filmid = rs.getInt("filmid");
				filmcim = rs.getString("filmcim");
				rendezo = rs.getString("rendezo");
				mdatum = rs.getString("mdatum");
				vevoid = rs.getInt("vevoid");
				x.format(filmid + ";" + filmcim + ";" + rendezo + ";" + mdatum + ";" + vevoid + "\n");
			}
			rs.close();
		} catch (SQLException e) {
			uzenet(e.getMessage());
		}
	}

	
	
	// Filmek.txt betöltése
	public void filmBetolt(String fajl) {
		String sql = "";
		kapcs();
		try {
			BufferedReader in = new BufferedReader(new FileReader(fajl));
			String sf = in.readLine();
			sf = in.readLine();
			while (sf != null) {
				String[] st = sf.split(";");
				sql = "REPLACE INTO Film VALUES(" + st[0] + ", '" + st[1] + "', '" + st[2] + "', '" + st[3] + "', '"
						+ st[4] + "')";
				s = conn.createStatement();
				s.execute(sql);
				sf = in.readLine();
			}
			in.close();
			uzenet("Adatok sikeresen betöltve!");
		} catch (IOException | SQLException e) {
			uzenet("Az adatok betöltése sikertelen: " + e.getMessage());
		}
	}

	
	
	// Vevõk kódjai a legördülõ ablakokhoz
	public ArrayList<String> vevoidOlvas() {
		String sql = "SELECT vevoid from Vevo";
		ArrayList<String> vevoidStr = new ArrayList<String>();
		vevoidStr.add("Kérem válasszon!");
		try {
			s = conn.createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				vevoidStr.add(rs.getString("vevoid"));
			}
			rs.close();
		} catch (SQLException e) {
			uzenet(e.getMessage());
		}
		return vevoidStr;
	}

	public MindenTM ReadAllData() {
		Object alltmn[] = { "Jel", "Kód", "Filmcím", "Rendezõ", "Megjel. dátum", "Vevõ ID", "Név", "Telefonszám", "Lakcím",
				"Szül. idõ" };
		MindenTM atm = new MindenTM(alltmn, 0);
		String nev = "", tel = "", vevocim = "", szülido = "";
		int vevoid = 0;
		String rendezo = "", mdatum = "", filmcim = "";
		int filmid = 0;
		kapcs();
		String sql = "SELECT * from Film f LEFT JOIN Vevo v ON f.filmid = v.vevoid";
		try {
			s = conn.createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				filmid = rs.getInt("filmid");
				filmcim = rs.getString(2);
				rendezo = rs.getString("rendezo");
				mdatum = rs.getString("mdatum");
				vevoid = rs.getInt(5);
				nev = rs.getString("nev");
				tel = rs.getString("tel");
				vevocim = rs.getString(9);
				szülido = rs.getString("szülido");
				atm.addRow(new Object[] { false, filmid, filmcim, rendezo, mdatum, vevoid, nev, tel, vevocim, szülido });
			}
			rs.close();
		} catch (SQLException e) {
			uzenet(e.getMessage());
		}
		leKapcs();
		return atm;
	}

	
	
	// Táblák nevének lekérése
	public void getTabla() {
		kapcs();
		try {
			int i = 1;
			DatabaseMetaData metaData = conn.getMetaData();
			String[] types = { "TABLE" };
			ResultSet tables = metaData.getTables(null, null, "%", types);
			while (tables.next()) {
				uzenet("A(z) "+i + ". tábla neve: " + tables.getString("TABLE_NAME"));
				i++;
			}
		} catch (Exception e) {
			uzenet(e.getMessage());
		}
		leKapcs();
	}

	
	
	// Vevõ felvétel
	public void addVevo(String vevoid, String nev, String tel, String cim, String szülido) {
		kapcs();
		String sql = "INSERT INTO Vevo VALUES (" + vevoid + ", '" + nev + "', '" + tel + "', '" + cim + "', '"
				+ szülido + "')";
		try {
			s = conn.createStatement();
			s.executeUpdate(sql);
			uzenet("Új vevõ sikeresen hozzáadva!");
		} catch (SQLException e) {
			uzenet("Új vevõ hozzáadása sikertelen: " + e.getMessage());
		}
		leKapcs();
	}

	
	
	// Film felvétel
	public void addFilm(String filmid, String cim, String rendezo, String mdatum, String vevoid) {
		kapcs();
		String sql = "INSERT INTO Film VALUES (" + filmid + ", '" + cim + "', '" + rendezo + "', '" + mdatum + "', " + vevoid
				+ ")";
		try {
			s = conn.createStatement();
			s.executeUpdate(sql);
			uzenet("Új film sikeresen hozzáadva!");
		} catch (SQLException e) {
			uzenet("Új film hozzáadása sikertelen: " + e.getMessage());
		}
		leKapcs();
	}

	
	
	// Összevont.txt feltöltése
	public void addOsszes() {
		String nev = "", tel = "", tcim = "", szülido = "";
		int vevoid = 0;
		String rendezo = "", mdatum = "", kcim = "";
		int filmid = 0;
		kapcs();
		String sql = "SELECT * FROM Film CROSS JOIN Vevo";
		try {
			s = conn.createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				filmid = rs.getInt("filmid");
				kcim = rs.getString(2);
				rendezo = rs.getString("rendezo");
				mdatum = rs.getString("mdatum");
				vevoid = rs.getInt(5);
				nev = rs.getString("nev");
				tel = rs.getString("tel");
				tcim = rs.getString(9);
				szülido = rs.getString("szülido");
				x.format(filmid + ";" + kcim + ";" + rendezo + ";" + mdatum + ";" + vevoid + ";" + nev + ";" + tel + ";"
						+ tcim + ";" + szülido + "\n");
			}
			rs.close();
		} catch (SQLException e) {
			uzenet(e.getMessage());
		}
		
	}

	
	
	// 1. szûrõ: Városra keresés, Vevõk listázására
	public Szuro1TM szuro1(String varos) {
		Object sz1tmn[] = { "Jel", "Kód", "Név", "Telefonszám", "Lakcím", "Születési idõ" };
		Szuro1TM sz1tm = new Szuro1TM(sz1tmn, 0);
		String nev = "", tel = "", lakcim = "", szülido = "";
		int vevoid = 0, db = 0;
		String sql = "SELECT * FROM Vevo WHERE lakcim LIKE '" + varos + "%" + "'";
		try {
			s = conn.createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				vevoid = rs.getInt("vevoid");
				nev = rs.getString("nev");
				tel = rs.getString("tel");
				lakcim = rs.getString("lakcim");
				szülido = rs.getString("szülido");
				sz1tm.addRow(new Object[] { false, vevoid, nev, tel, lakcim, szülido });
				db++;
			}
			rs.close();
		} catch (SQLException e) {
			uzenet(e.getMessage());
		}
		if (db == 0) {
			JOptionPane.showMessageDialog(null, "Nincs adat!", "Üzenet", 2);
		}
		return sz1tm;
	}

	
	
	// 2. szûrõ: Vevõk születési ideje
	public Szuro2TM szuro2(String alsohatar, String felsohatar) {
		Object sz2tmn[] = { "Jel", "Kód", "Név", "Telefonszám", "Lakcím", "Születési idõ" };
		Szuro2TM sz2tm = new Szuro2TM(sz2tmn, 0);
		String nev = "", tel = "", lakcim = "", szülido = "";
		@SuppressWarnings("unused")
		
		int vevoid = 0, db = 0;
		String sql = "SELECT * FROM Vevo WHERE szülido BETWEEN '" + alsohatar + "' AND '" + felsohatar + "'";
		try {
			s = conn.createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				vevoid = rs.getInt("vevoid");
				nev = rs.getString("nev");
				tel = rs.getString("tel");
				lakcim = rs.getString("lakcim");
				szülido = rs.getString("szülido");
				sz2tm.addRow(new Object[] { false, vevoid, nev, tel, lakcim, szülido });
				db++;
			}
			rs.close();
		} catch (SQLException e) {
			uzenet(e.getMessage());
		}
		if (db == 0) {
			JOptionPane.showMessageDialog(null, "Nincs adat!", "Üzenet", 2);
		}
		
		return sz2tm;
	}

	
	
	// 3. szûrõ: Rendezõre keresés, filmjeinek listája
	public Szuro3TM szuro3(String rendezo) {
		Object sz3tmn[] = { "Jel", "Kód", "Filmcím", "Rendezõ", "Megjelenés dátuma", "Vevõ ID" };
		Szuro3TM sz3tm = new Szuro3TM(sz3tmn, 0);
		String filmcim = "", rendez = "", mdatum = "";
		int filmid = 0, vevoid = 0, db = 0;
		String sql = "SELECT * FROM Film WHERE rendezo LIKE '" + rendezo + "%" + "'";
		try {
			s = conn.createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				filmid = rs.getInt("filmid");
				filmcim = rs.getString("filmcim");
				rendez = rs.getString("rendezo");
				mdatum = rs.getString("mdatum");
				vevoid = rs.getInt("vevoid");
				sz3tm.addRow(new Object[] { false, filmid, filmcim, rendez, mdatum, vevoid });
				db++;
			}
			rs.close();
		} catch (SQLException e) {
			uzenet(e.getMessage());
		}
		if (db == 0) {
			JOptionPane.showMessageDialog(null, "Nincs adat!", "Üzenet", 2);
		}
		return sz3tm;
	}

	
	
	// 4. szûrõ: Filmek megjelenési dátuma (intervallummal)
	public Szuro4TM szuro4(String alsohatar, String felsohatar) {
		Object sz4tmn[] = { "Jel", "Kód", "Filmcím", "Rendezõ", "Megjelenés Dátuma", "Vevõ ID" };
		Szuro4TM sz4tm = new Szuro4TM(sz4tmn, 0);
		String filmcim = "", rendezo = "", mdatum = "";
		int filmid = 0, vevoid = 0, db = 0;
		String sql = "SELECT * FROM Film WHERE mdatum BETWEEN '" + alsohatar + "' AND '" + felsohatar + "'";
		try {
			s = conn.createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				filmid = rs.getInt("filmid");
				filmcim = rs.getString("filmcim");
				rendezo = rs.getString("rendezo");
				mdatum = rs.getString("mdatum");
				vevoid = rs.getInt("vevoid");
				sz4tm.addRow(new Object[] { false, filmid, filmcim, rendezo, mdatum, vevoid });
				db++;
			}
			rs.close();
		} catch (SQLException e) {
			uzenet(e.getMessage());
		}
		if (db == 0) {
			JOptionPane.showMessageDialog(null, "Nincs adat!", "Üzenet", 2);
		}
		return sz4tm;
	}

	
	
	// 5. szûrõ: Vevõ nevére szûrés, vevõ filmek listája
	public Szuro5TM szuro5(String vevonev) {
		Object sz5tmn[] = { "Jel", "Név", "Filmcím", "Rendezõ" };
		Szuro5TM sz5tm = new Szuro5TM(sz5tmn, 0);
		String filmcim = "", rendezo = "";
		String nev = "";
		int db = 0;
		
		String sql = "SELECT nev, filmcim, rendezo FROM Vevo LEFT JOIN Film ON Vevo.vevoid = Film.vevoid WHERE nev = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, vevonev);
			rs = ps.executeQuery();
			while (rs.next()) {
				nev = rs.getString("nev");
				filmcim = rs.getString("filmcim");
				rendezo = rs.getString("rendezo");
				sz5tm.addRow(new Object[] { false, nev, filmcim, rendezo });
				db++;
			}
			rs.close();
		} catch (SQLException e) {
			uzenet(e.getMessage());
		}
		if (db == 0) {
			JOptionPane.showMessageDialog(null, "Nincs adat!", "Üzenet", 2);
		}

		return sz5tm;
	}

}
