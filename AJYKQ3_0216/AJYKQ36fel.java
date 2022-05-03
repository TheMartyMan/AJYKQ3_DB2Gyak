package gyak;

public class AJYKQ36fel {
  
  public void StatikusAdatfelvitel() {
    if (conn != null) {
      String sqlp_tul = "INSERT into tulaj VALUES(1, 'Faragó Tamás'," + " 'Győr', to_date('1984.04.14', 'yyy.mm.dd'))";
      
      String[] sqlp = {
        "INSERT into auto VALUES('ABC123', 'Opel' , 'Kék', 2014, 1650000,1)",
         "INSERT into auto VALUES('DEF456', 'Audi' , 'Piros', 2016, 7255000,1)",
         "INSERT into auto VALUES(rsz, tipus, evjarat, ar) VALUES (GHI789, 'Chevrolet', 'Sárga', 2020, 8920000)"
      };
      
      try {
        s = conn.createStatement();
        s.executeUpdate(sqlp_tul);
        System.out.println("Tulajdonos sikeresen felvéve!\n");
        s.close();
        
      } catch (Exception ex) {
          System.err.println(ex.getMessage());
      }
      
      
      for (int i = 0; i < sqlp.length; i++) {
              try {
                    s = conn.createStatement();
                    s.executeUpdate(sqlp[i]);
                    System.out.println("Autó sikeresen felvéve!\n");
                    s.close();
                
              } catch (Exception ex) {
                    System.err.println(ex.getMessage());
              }
         }
      }
  }
}
