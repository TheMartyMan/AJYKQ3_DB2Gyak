package gyak;

public class AJYKQ38fel {
  
    public static void main (String[] args) {
    try {
        Class.forName("orace.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection ("jdbc:oracle:thin:FSPA4J/FSPA4J@193.6.5.58:1521:XE");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT user FROM dual");
        rs.next();
        System.out.println(rs.getString(1));
        rs.close();
        conn.close();
      
      
        System.out.println("Hello");
        
    }
    catch (Exception ee) {
      System.out.println("Hiba:" + (ee.getMessage());
		}
	}
}
