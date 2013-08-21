import java.rmi.Naming;

public class RmiClient {
	public static void main(String args[]) throws Exception {
		RmiServerIntf obj = new (RmiServerIntf) Naming.lookup("//localhost/RmiServer");
		return obj.getMessage();
	}
}