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
	
	
	
	
	//  1. feladat
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
		
//   2. feladat
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
	

// 3. feladat
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

	
//  4. feladat
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

// 5. feladat
	
	
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
			
			
// 6. feladat

	public void StatikusLekerdezes() {

		if (conn != null) {
			String sqlp = "SELECT * FROM auto";
			System.out.println("Rendszám Típus Szín Évjárat Ár Tulaj");
			System.out.println("--------------------------------------");

			try {
				s = conn.createStatement();
				s.executeQuery(sqlp);
				rs = s.getResultSet();
				while (rs.next()) {

					String rsz = rs.getString("rsz");
					String tipus = rs.getString("tipus");
					String szin = rs.getString("szin");
					int evjarat = rs.getInt("evjarat");
					int ar = rs.getInt("ar");
					int tulaj_id = rs.getInt("tulaj_id");

					System.out
							.println(rsz + "\t\t" + tipus + "\t" + szin + "\t" + evjarat + "\t" + ar + "\t" + tulaj_id);
				}
				rs.close();
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	// 7. feladat

	public void ModosithatoKurzor() {

		System.out.println("Szín: ");
		String szin = sc.next().trim();
		String sqlp = "SELECT ar FROM auto WHERE szin = '" + szin + "'";

		if (conn != null) {

			try {

				s = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
				rs = s.executeQuery(sqlp);
				while (rs.next()) {
					int regiar = rs.getInt("ar");
					rs.updateInt("ar", (regiar * 2));
					rs.updateRow();
				}
			} catch (Exception ex) {
				System.err.println(ex.getMessage());

			}

		}

	}

	// 8.feladat

	public void InEljarasHivas() {

		if (conn != null) {

			try {

				String sqlp = "CREATE OR REPLACE PROCEDURE arcsokkent " + "(kor IN number) is " + "begin "
						+ "UPDATE AUTO SET ar=ar*0.9 WHERE " + "to_char(sysdate, 'yyyy')-evjarat > kor ; " + "end; ";
				System.out.println("Kor: ");
				int kor = sc.nextInt();
				s = conn.createStatement();
				s.executeUpdate(sqlp);
				System.out.println("A függvény sikeresen létrejött!\n");
				cs = conn.prepareCall("{CALL arcsokkent(?)}");
				cs.setInt(1, kor);
				cs.execute();
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}

		}

	}

	// 9.feladat

	public void OutEljarasHivas() {

		if (conn != null) {

			try {

				String sqlp = "CREATE OR REPLACE PROCEDURE atlagar " + "(sz IN char, atl OUT number) is " + "begin "
						+ "SELECT (avg(ar) INTO atl FROM auto WHERE szin=sz; " + "end;";
				System.out.println("Szín: ");
				String szin = sc.next();
				s = conn.createStatement();
				s.executeUpdate(sqlp);
				System.out.println("Az eljárás sikeresen létrejött!\n");
				cs = conn.prepareCall("{CALL atlagar(?, ?)}");
				cs.setString(1, szin);
				cs.registerOutParameter(2, java.sql.Types.FLOAT);
				cs.execute();
				float atlag = cs.getFloat(2);
				System.out.println(szin + " autók átlagára: " + atlag + "\n");

			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}

		}

	}

	public void FuggvenyHivas() {

		if (conn != null) {

			try {

				String sqlp = "CREATE OR REPLACE FUNCTION atlagarfv " + "(sz IN char) RETURN NUMBER IS "
						+ "atl number(10,2); " + "BEGIN " + "SELECT avg(ar) INTO atl FROM auto WHERE szin=sz; "
						+ "RETURN atl; " + "END;";
				System.out.println("Szín: ");
				String szin = sc.next();
				s = conn.createStatement();
				s.executeUpdate(sqlp);
				System.out.println("A függvény sikeresen létrejött!\n");
				cs = conn.prepareCall("{? = CALL atlagarfv(?)}");
				cs.setString(2, szin);
				cs.registerOutParameter(1, java.sql.Types.FLOAT);
				cs.execute();
				float atlag = cs.getFloat(1);
				System.out.println(szin + " autók átlagára: " + atlag + "\n");

			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}

	}

	// 10. feladat

	public void DinamikusTablaTorles() {

		String sqlp = "CREATE OR REPLACE PROCEDURE tablaTorles(nev IN char) is " + "begin "
				+ "EXECUTE IMMIDIATE 'DROP TABLE' || nev; " + "END;";
		System.out.println("Törlendő tábla: ");
		String name = sc.next().trim();
		if (conn != null) {

			try {

				s = conn.createStatement();
				s.executeUpdate(sqlp);
				cs = conn.prepareCall("{CALL tablatorles(?)}");
				cs.setString(1, name);
				cs.execute();
				System.out.println(name + " tábla sikeresen törölve!\n");

			} catch (Exception ex) {

				System.err.println(ex.getMessage());

			}

		}

	}

	// 11. feladat

	public void DinamikusModositas() {
		
		if(conn!=null) {
			
			String sqlp = "UPDATE auto1 SET ar=ar-?";
			System.out.println("Mennyivel csökkentsük az autó árát?");
			int arcsokk = sc.nextInt();
			try {
				
				conn.setAutoCommit(false);
				
				try {
					
					ps = conn.prepareStatement(sqlp);
					ps.setInt(1, arcsokk);
					ps.executeUpdate();
					conn.commit();
					System.out.println("Sikeres módosítás!\n");
					
				}catch(Exception ex) {
					
					System.err.println(ex.getMessage());
					
				}
				
				conn.setAutoCommit(true);
				
			}catch(Exception ex) {
				
				System.err.println(ex.getMessage());
				
			}
			
		}
		
	}

}
