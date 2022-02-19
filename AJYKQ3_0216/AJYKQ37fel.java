package gyak;

public class AJYKQ37fel {
      
  
  public void StatikusTablaLetrehozas() {
    String sqlp_auto = "CREATE TABLE auto (rsz char(6) PRIMARY KEY, " + "tipus char(10) NOT NULL, szin char(10) DEFAULT 'fehér', " + "evjarat number(4), ar number(8) check(ar>100) ) ";
    String sqlp_tulaj = "CREATE TABLE tulaj (id number(3) PRIMARY KEY, " + " nev char(20) NOT NULL, cum char(20), szuldatum date)";
    
    if (conn != NULL) {
      
        try {
            s = conn.createStatement();
            s.executeUpdate(sqlp_auto);
            System.out.println("Az 'Autó' tábla sikeresen létrejött!\n");
            s.executeUpdate(sqlp_tulaj);
            System.out.println("A 'Tulajdonos' tábla sikeresen létrejött!\n");
            s.close();
          
        } catch (Exception ex) {
              System.err.println(ex.getMessage());
            }
         }
      }
  }
}
