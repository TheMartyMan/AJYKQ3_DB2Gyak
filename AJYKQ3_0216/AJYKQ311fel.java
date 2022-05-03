package gyak;

public class AJYKQ311fel {
  
   try {
     DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
     Connection kap = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "u1", "u1");
     
     Statement pp = kap.createStatement();
     ResultSet eredmeny = pp.executeQuery("SELECT nev, fizetes FROM dolgozok WHERE nev = '"+dnev+"'");
     
     
     while (eredmeny.next()) {
          System.out.println(eredmeny.getString("nev") + " : " + eredmeny.getInt("fizetese"));
     }
     
     eredmeny.close();
     kap.close();
     
     
   } catch (exception e) {
     e.printStackTrace();
   }
}
