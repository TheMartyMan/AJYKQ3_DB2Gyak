package gyak;

public class AJYKQ33fel {

	public void Connect() {
		Connection conn = null;
		String url = "jdbc:oracle:thin:@193.6.5.58:1521:XE";
		String user = "username";
		String pwd = "password";
		
		try {
			conn = DriverManager.getConnection(url, user, pwd);
			System.out.println("Sikeres kapcsolódás\n");
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
