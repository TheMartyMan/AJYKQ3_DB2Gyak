package gyak;

public class AJYKQ35fel {

  public void InsertTul(String tulajazon, String nev, String szemig, String szulhely, String szulido) {

    String sqlp = "INSERT into Tulajdonos values ("+tulajazon+","+nev+","+szemig+","+szulhely+","+szulido+")";
    
    try {
      s = conn.createStatement();
      s.execute(sqlp);
    } catch (SQLException e) {
          e.printStackTrace();
    
      }
   }
}
