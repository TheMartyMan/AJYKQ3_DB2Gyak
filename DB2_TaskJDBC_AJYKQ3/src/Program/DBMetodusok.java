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

	
	
	// Kapcsol�d�s
	public void kapcs() {
		try {
			String url = "jdbc:sqlite:src/beadandodb.db";
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			uzenet("JDBC kapcsol�d�si hiba: " + e.getMessage());
		}
	}

	
	
	// Lekapcsol�d�s
	public void leKapcs() {
		try {
			conn.close();
		} catch (SQLException e) {
			uzenet(e.getMessage());
		}
	}

	
	
	// Driver regiszt�r�l�s gombhoz
	public void driverReg() {
		try {
			Class.forName("org.sqlite.JDBC");
			uzenet("Sikeres driver regisztr�ci�!");
		} catch (ClassNotFoundException e) {
			uzenet("Hiba l�pett fel a driver regisztr�ci� sor�n!" + e.getMessage());
		}
	}

	
	
	// Driver regiszt�r�l�s
	public void driverReg1() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			uzenet("Hiba l�pett fel a driver regisztr�ci� sor�n!" + e.getMessage());
		}
	}

	
	
	// �zenet ki�r�s
	public void uzenet(String msg) {
		JOptionPane.showMessageDialog(null, msg, "�zenet", 2);
	}

	
	
	// Bejelentkez�s
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

	
	
	// Vevo t�bla adatok kiolvas�sa
	public VevoTM osszAdatVevo() {
		Object vevotmn[] = { "Jel", "K�d", "N�v", "Telefonsz�m", "Lakc�m", "Sz�let�si id�" };
		VevoTM vtm = new VevoTM(vevotmn, 0);
		String nev = "", tel = "", lakcim = "", sz�lido = "";
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
				sz�lido = rs.getString("sz�lido");
				vtm.addRow(new Object[] { false, vevoid, nev, tel, lakcim, sz�lido });
			}
			rs.close();
		} catch (SQLException e) {
			uzenet(e.getMessage());
		}
		leKapcs();
		return vtm;
	}

	
	
	// Film t�bla adatok kiolvas�sa
	public FilmTM osszAdatFilm() {
		Object filmtmn[] = { "Jel", "K�d", "Filmc�m", "Rendez�", "Megjelen�s d�tuma", "Vev� ID" };
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

	
	
	// Vev� t�rl�se
	public void vevoTorol(String vevoid) {
		String sql = "DELETE from Vevo WHERE vevoid=" + vevoid;
		try {
			s = conn.createStatement();
			s.execute(sql);
		} catch (Exception e) {
			uzenet("Vev� rekord t�rl�s sikertelen: " + e.getMessage());
		}
	}

	
	
	// Film adat t�rl�se
	public void filmTorol(String filmid) {
		String sql = "DELETE from Film where filmid=" + filmid;
		try {
			s = conn.createStatement();
			s.execute(sql);
		} catch (Exception e) {
			uzenet("Film rekord t�rl�s sikertelen: " + e.getMessage());
		}
	}

	
	
	// Vev� t�bla m�dos�t�sa
	public void updateVevo(String vevoid, String nev, String tel, String lakcim, String sz�lido) {
		kapcs();
		String sql = "UPDATE Vevo SET nev= '" + nev + "', tel= '" + tel + "', lakcim= '" + lakcim + "', sz�lido= '"
				+ sz�lido + "' WHERE vevoid='" + vevoid + "'";
		try {
			s = conn.createStatement();
			s.execute(sql);
			uzenet("Vev� t�bla sikeresen m�dos�tva!");
		} catch (Exception e) {
			uzenet("Adatm�dos�t�s sikertelen: " + e.getMessage());
		}
		leKapcs();
	}

	
	
	// Film t�bla m�dos�t�sa
	public void updateFilm(String filmid, String filmcim, String rendezo, String mdatum, String vevoid) {
		kapcs();
		String sql = "UPDATE Film set filmcim= '" + filmcim + "', rendezo= '" + rendezo + "', mdatum= '" + mdatum + "', vevoid= '"
				+ vevoid + "' WHERE filmid='" + filmid + "'";
		try {
			s = conn.createStatement();
			s.execute(sql);
			uzenet("Film t�bla sikeresen m�dos�tva!");
		} catch (Exception e) {
			uzenet("Adatm�dos�t�s sikertelen: " + e.getMessage());
		}
		leKapcs();
	}

	
	
	// F�jlmegnyit�s
	public void fajlMegnyit(String txt) {
		try {
			x = new Formatter(txt);
		} catch (Exception e) {
			uzenet(e.getMessage());
		}
	}

	
	
	// F�jl bez�r�sa
	public void fajlBezar() {
		x.close();
	}

	
	
	// Vevok.txt felt�lt�se
	public void vevoHozzaad() {
		String nev = "", tel = "", lakcim = "", sz�lido = "";
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
				sz�lido = rs.getString("sz�lido");
				x.format(vevoid + ";" + nev + ";" + tel + ";" + lakcim + ";" + sz�lido + "\n");
			}
			rs.close();
		} catch (SQLException e) {
			uzenet(e.getMessage());
		}
	}

	
	
	// Vevok.txt bet�lt�se
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
			uzenet("Adatok sikeresen bet�ltve!");
		} catch (IOException | SQLException e) {
			uzenet("Az adatok bet�lt�se sikertelen: " + e.getMessage());
		}
	}

	
	
	// Filmek.txt felt�lt�se
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

	
	
	// Filmek.txt bet�lt�se
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
			uzenet("Adatok sikeresen bet�ltve!");
		} catch (IOException | SQLException e) {
			uzenet("Az adatok bet�lt�se sikertelen: " + e.getMessage());
		}
	}

	
	
	// Vev�k k�djai a leg�rd�l� ablakokhoz
	public ArrayList<String> vevoidOlvas() {
		String sql = "SELECT vevoid from Vevo";
		ArrayList<String> vevoidStr = new ArrayList<String>();
		vevoidStr.add("K�rem v�lasszon!");
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
		Object alltmn[] = { "Jel", "K�d", "Filmc�m", "Rendez�", "Megjel. d�tum", "Vev� ID", "N�v", "Telefonsz�m", "Lakc�m",
				"Sz�l. id�" };
		MindenTM atm = new MindenTM(alltmn, 0);
		String nev = "", tel = "", vevocim = "", sz�lido = "";
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
				sz�lido = rs.getString("sz�lido");
				atm.addRow(new Object[] { false, filmid, filmcim, rendezo, mdatum, vevoid, nev, tel, vevocim, sz�lido });
			}
			rs.close();
		} catch (SQLException e) {
			uzenet(e.getMessage());
		}
		leKapcs();
		return atm;
	}

	
	
	// T�bl�k nev�nek lek�r�se
	public void getTabla() {
		kapcs();
		try {
			int i = 1;
			DatabaseMetaData metaData = conn.getMetaData();
			String[] types = { "TABLE" };
			ResultSet tables = metaData.getTables(null, null, "%", types);
			while (tables.next()) {
				uzenet("A(z) "+i + ". t�bla neve: " + tables.getString("TABLE_NAME"));
				i++;
			}
		} catch (Exception e) {
			uzenet(e.getMessage());
		}
		leKapcs();
	}

	
	
	// Vev� felv�tel
	public void addVevo(String vevoid, String nev, String tel, String cim, String sz�lido) {
		kapcs();
		String sql = "INSERT INTO Vevo VALUES (" + vevoid + ", '" + nev + "', '" + tel + "', '" + cim + "', '"
				+ sz�lido + "')";
		try {
			s = conn.createStatement();
			s.executeUpdate(sql);
			uzenet("�j vev� sikeresen hozz�adva!");
		} catch (SQLException e) {
			uzenet("�j vev� hozz�ad�sa sikertelen: " + e.getMessage());
		}
		leKapcs();
	}

	
	
	// Film felv�tel
	public void addFilm(String filmid, String cim, String rendezo, String mdatum, String vevoid) {
		kapcs();
		String sql = "INSERT INTO Film VALUES (" + filmid + ", '" + cim + "', '" + rendezo + "', '" + mdatum + "', " + vevoid
				+ ")";
		try {
			s = conn.createStatement();
			s.executeUpdate(sql);
			uzenet("�j film sikeresen hozz�adva!");
		} catch (SQLException e) {
			uzenet("�j film hozz�ad�sa sikertelen: " + e.getMessage());
		}
		leKapcs();
	}

	
	
	// �sszevont.txt felt�lt�se
	public void addOsszes() {
		String nev = "", tel = "", tcim = "", sz�lido = "";
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
				sz�lido = rs.getString("sz�lido");
				x.format(filmid + ";" + kcim + ";" + rendezo + ";" + mdatum + ";" + vevoid + ";" + nev + ";" + tel + ";"
						+ tcim + ";" + sz�lido + "\n");
			}
			rs.close();
		} catch (SQLException e) {
			uzenet(e.getMessage());
		}
		
	}

	
	
	// 1. sz�r�: V�rosra keres�s, Vev�k list�z�s�ra
	public Szuro1TM szuro1(String varos) {
		Object sz1tmn[] = { "Jel", "K�d", "N�v", "Telefonsz�m", "Lakc�m", "Sz�let�si id�" };
		Szuro1TM sz1tm = new Szuro1TM(sz1tmn, 0);
		String nev = "", tel = "", lakcim = "", sz�lido = "";
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
				sz�lido = rs.getString("sz�lido");
				sz1tm.addRow(new Object[] { false, vevoid, nev, tel, lakcim, sz�lido });
				db++;
			}
			rs.close();
		} catch (SQLException e) {
			uzenet(e.getMessage());
		}
		if (db == 0) {
			JOptionPane.showMessageDialog(null, "Nincs adat!", "�zenet", 2);
		}
		return sz1tm;
	}

	
	
	// 2. sz�r�: Vev�k sz�let�si ideje
	public Szuro2TM szuro2(String alsohatar, String felsohatar) {
		Object sz2tmn[] = { "Jel", "K�d", "N�v", "Telefonsz�m", "Lakc�m", "Sz�let�si id�" };
		Szuro2TM sz2tm = new Szuro2TM(sz2tmn, 0);
		String nev = "", tel = "", lakcim = "", sz�lido = "";
		@SuppressWarnings("unused")
		
		int vevoid = 0, db = 0;
		String sql = "SELECT * FROM Vevo WHERE sz�lido BETWEEN '" + alsohatar + "' AND '" + felsohatar + "'";
		try {
			s = conn.createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				vevoid = rs.getInt("vevoid");
				nev = rs.getString("nev");
				tel = rs.getString("tel");
				lakcim = rs.getString("lakcim");
				sz�lido = rs.getString("sz�lido");
				sz2tm.addRow(new Object[] { false, vevoid, nev, tel, lakcim, sz�lido });
				db++;
			}
			rs.close();
		} catch (SQLException e) {
			uzenet(e.getMessage());
		}
		if (db == 0) {
			JOptionPane.showMessageDialog(null, "Nincs adat!", "�zenet", 2);
		}
		
		return sz2tm;
	}

	
	
	// 3. sz�r�: Rendez�re keres�s, filmjeinek list�ja
	public Szuro3TM szuro3(String rendezo) {
		Object sz3tmn[] = { "Jel", "K�d", "Filmc�m", "Rendez�", "Megjelen�s d�tuma", "Vev� ID" };
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
			JOptionPane.showMessageDialog(null, "Nincs adat!", "�zenet", 2);
		}
		return sz3tm;
	}

	
	
	// 4. sz�r�: Filmek megjelen�si d�tuma (intervallummal)
	public Szuro4TM szuro4(String alsohatar, String felsohatar) {
		Object sz4tmn[] = { "Jel", "K�d", "Filmc�m", "Rendez�", "Megjelen�s D�tuma", "Vev� ID" };
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
			JOptionPane.showMessageDialog(null, "Nincs adat!", "�zenet", 2);
		}
		return sz4tm;
	}

	
	
	// 5. sz�r�: Vev� nev�re sz�r�s, vev� filmek list�ja
	public Szuro5TM szuro5(String vevonev) {
		Object sz5tmn[] = { "Jel", "N�v", "Filmc�m", "Rendez�" };
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
			JOptionPane.showMessageDialog(null, "Nincs adat!", "�zenet", 2);
		}

		return sz5tm;
	}

}
