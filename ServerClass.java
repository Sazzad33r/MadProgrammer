

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerClass {

	public static final ReadWrite[] clints = new ReadWrite[2];

	public static void main(String[] args) {
		try {
			ServerSocket sSocket = new ServerSocket(8888);
			Socket ss1, ss2;
			String user1, user2;
			ss1 = sSocket.accept();
			ss2 = sSocket.accept();
			DataInputStream u1is = new DataInputStream(ss1.getInputStream());
			DataInputStream u2is = new DataInputStream(ss2.getInputStream());
			DataOutputStream u1os = new DataOutputStream(ss1.getOutputStream());
			DataOutputStream u2os = new DataOutputStream(ss2.getOutputStream());
			u1os.writeUTF("Enter Your Name: ");
			user1 = u1is.readUTF();
			u2os.writeUTF("Enter Your Name: ");
			user2 = u2is.readUTF();
			// ReadWrite obj1, obj2;
			(clints[0] = new ReadWrite(ss1, user1, 0, clints)).start();
			(clints[1] = new ReadWrite(ss2, user2, 1, clints)).start();
			// ss1.close();
			// ss2.close();
			sSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class ReadWrite extends Thread {
	Thread clintThread;
	String clintName;
	Socket own;
	int number;
	ReadWrite[] clints;
	DataOutputStream odos;
	DataInputStream odis;
	public static boolean close = false;

	public ReadWrite(Socket own, String clintName, int number, ReadWrite[] clints) {
		this.own = own;
		this.clintName = clintName;
		this.number = number;
		this.clints = clints;
	}

	public void run() {
		try {
			odis = new DataInputStream(own.getInputStream());
			odos = new DataOutputStream(own.getOutputStream());
			while (true) {
				if (!close) {
					String line = odis.readUTF();
					// System.out.println(line);
					if (line.startsWith("qq")) {
						close = true;
						break;
					}
					if (number == 0) {
						clints[1].odos.writeUTF(clints[0].clintName + ": " + line);
					} else {
						clints[0].odos.writeUTF(clints[1].clintName + ": " + line);
					}
				} else {
					break;
				}
				// sdos.writeUTF(clintName + ": " + line);
			}
			clints[0].odos.writeUTF("qq");
			clints[1].odos.writeUTF("qq");
			// odis.close();
			// sdos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}