package ajykq3db2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class AJYKQ3db2 {
	static Connection conn = null;
	static Statement s;
	static PreparedStatement ps;
	static Scanner sc;
	static String user = "H22_ajykq3";
	static String pwd = "AJYKQ3";
	
	
	public static void main(String[] args) {
		
	DriverReg();
	Connect();
	StatikusTablaLetrehozas();
	
	LeKapcs();
	

	}
	
	public static void DriverReg() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Sikeres driver regisztrálás!\n");
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void Connect() {
		
		String url = "jdbc:oracle:thin:@193.6.5.58:1521:XE";
		
		
		try {
			conn = DriverManager.getConnection(url, user, pwd);
			System.out.println("Sikeres kapcsolódás!\n");
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void LeKapcs() {
		  if (conn != null) {
		  try {
		      conn.close();
		      System.out.println("Sikeres lekapcsolódás!\n");
		      } catch (Exception ex) {
		      System.err.println(ex.getMessage());
		      }
		    }
	}
	
	
	
	
	//  3. gyakorlat 1. feladat
	public static void StatikusTablaLetrehozas() {
		String sqlp_auto = "CREATE TABLE auto (rsz char(6) PRIMARY KEY, "+
				"tipus char(10) NOT NULL, szin char 10 DEFAULT 'feher', "+
				"evjarat number(4), ar number(8) CHECK (ar>0))";
		String sqlp_tulaj = "CREATE TABLE tulaj (id number(3) PRIMARY KEY, "+
				"nev char(20) NOT NULL, cim char(20), szuldatum date)";
		
		if (conn != null) {
			try {
				s = conn.createStatement();
				s.executeUpdate(sqlp_auto);
				System.out.println("Az 'auto' tábla sikeresen létrehozva!\n");
				
				s.executeUpdate(sqlp_tulaj);
				System.out.println("A 'tulaj' tábla sikeresen létrehozva!\n");
				s.close();
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
				
			}
		}	
	}
	
	
	
	
//  3. gyakorlat 2. feladat
	public static void StatikusTablaModositas() {
		if (conn != null) {
			try {
				String sqlp = "ALTER TABLE auto ADD (tulaj_id REFERENCES tulaj)";
				s = conn.createStatement();
				s.executeUpdate(sqlp);
				
				System.out.println("Az 'auto' tábla sikeresen módosítva!\n!");
				s.close();
			} catch (Exception ex) {
					System.err.println(ex.getMessage());
			}
		}
	}
	
	
	
	
//  3. gyakorlat 3. feladat
	public static void StatikusAdatfelvite() {
		if (conn != null) {
			String sqlp_tul = "INSERT INTO tulaj VALUES (1, 'Tóth Máté', "+
		" 'Miskolc', to_date('1980.05.12', 'yyy.mm.dd'))";
			
			String[] sqlp = {"INSERT into auto VALUES('ABC123', 'Opel' , 'Kék', 2014, 1650000,1)",
			         "INSERT into auto VALUES('DEF456', 'Audi' , 'Piros', 2016, 7255000,1)",
			         "INSERT into auto VALUES(rsz, tipus, evjarat, ar) VALUES (GHI789, 'Chevrolet', 'Sárga', 2020, 8920000)"
					};
			try {
				s = conn.createStatement();
				s.executeUpdate(sqlp_tul);
				
				System.out.println("Tulaj adatok sikeresen felvéve!\n");
				s.close();
				
			} catch (Exception ex) {
					System.err.println(ex.getMessage());
			}
			
			
			
			for (int i = 0; i < sqlp.length; i++) {
				try {
					s = conn.createStatement();
					s.executeUpdate(sqlp[i]);
					
					System.out.println("Autó adatok sikeresen felvéve!\n");
					s.close();
					
					
				} catch (Exception ex) {
					System.err.println(ex.getMessage());	
				}
			}
		}
	}
	
	
	
//  3. gyakorlat 4. feladat
	public static void DinamikusAdatfelvietl() {
		if (conn != null) {
			
			String sqlp = "INSERT INTO auto (rsz, tipus, szin, evjarat, ar ,tulaj_id) "+
			"VALUES (?, ?, ?, ?, ?, ?)";
			
			System.out.println("Kérem a rendszámot:\n");
			String rsz = sc.next().trim();
			
			System.out.println("Kérem a típust:\n");
			String tipus = sc.next().trim();
			
			System.out.println("Kérem a színt:\n");
			String szin = sc.next().trim();
			
			System.out.println("Kérem a évjáratot:\n");
			int evjarat = sc.nextInt();
			
			System.out.println("Kérem a árat:\n");
			float ar = sc.nextFloat();
			
			System.out.println("Kérem a tulajdonos azonosítóját:\n");
			int tulaj_id = sc.nextInt();
			
			try {
				ps = conn.prepareStatement(sqlp);
				ps.setString(1, rsz);
				ps.setString(2, tipus);
				ps.setString(3, szin);
				ps.setInt(4, evjarat);
				ps.setFloat(5, ar);
				ps.setInt(6, tulaj_id);
				
				ps.executeUpdate();
				ps.close();
				
				System.out.println("Az 'auto' tábla adatai dinamikusan felvéve!\n");
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	
	
//  3. gyakorlat 5. feladat
	
	
	public static void DinamikusAdattorles() {
		System.out.println("Kérem a törlendő autó rendszámát:\n");
		String rsz = sc.next();
		
		String sqlp = "DELETE FROM " + user + ".AUTO " + "WHERE rsz=?";
		if (conn != null) {
			try {
				ps = conn.prepareStatement(sqlp);
				ps.setString(1, rsz);
				ps.executeUpdate();
				ps.close();
				
				System.out.println("A " + rsz + "rendszámú autó sikeresen törölve az adatbázisból!\n");
				
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}
	
	
	
//  3. gyakorlat 6. feladat
	
	
	
	
	
}
