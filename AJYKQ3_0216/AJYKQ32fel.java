package gyak;

public class AJYKQ32fel {

	public void DriverReg() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Sikeres driver regisztr�l�s!\n");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
