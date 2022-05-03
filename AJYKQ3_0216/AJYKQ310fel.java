package gyak;

public class AJYKQ310fel {

    public void StatikusAdattorles() {
            Scanner sc = new Scanner (System.in);
            System.out.println("Kérem adja meg a törlendő autó rendszámát:\n");
            String rsz = sc.next();
            String sqlp = "DELETE FROM auto WHERE rsz LIKE '" + rsz + "'";
            
            if (conn != null) {
                try {
                    s = conn.createStatement();
                    s.executeUpdate(sqlp);
                    s.close();
                    System.out.println(rsz + " rendszámú autó sikeresen törölve!\n");
                  
                } catch (Exeption ex) {
                    System.err.println(ex.getMessage());
			}
		} 
	}
}
