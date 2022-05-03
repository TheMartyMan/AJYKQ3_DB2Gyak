package gyak;

public class AJYKQ39fel {
  
  public void StatikusLekerdezes() {
    if (conn != null) {
      String sqlp = "SELECT * FROM auto";
      System.out.println("Rendszám Típus Szín Évjárat Ár Tulaj");
      System.out.println("--------------------------------------");
      
      
      try {
            s = conn.createStatement();
            s.executeQuery(sqlp);
            rs = r.getResultSet();
            while (rs.next()) {
                  
                  String rsz = rs.getString("rsz");
                  String tipus = rs.getString("tipus");
                  String szin = rs.getString("szin");
                  int evjarat = rs.getInt("evjarat");
                  int ar = rs.getInt("ar");
                  int tulaj_id = rs.getInt("tulaj_id");
                  
                  System.out.prinln(rsz+"\t\t"+tipus+"\t"+szin+"\t"+evjarat+"\t"+ar+"\t"+tulaj_id);
         }
        rs.close();
    } catch (Exception ex) {
                  System.err.prinln(ex.getMessage());
      }
    }
  }
}
