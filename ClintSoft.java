

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClintSoft implements Runnable {
	static Socket s;
	static boolean close = false;
	public static DataInputStream cdataISt;
	public static DataOutputStream cdataOSt;
	public static String input;
	public static String str = null;

	public static void main(String[] args) {
		try {
			s = new Socket("localhost", 8888);
			Scanner in = new Scanner(System.in);

			// DataInputStream cdataISt = new
			// DataInputStream(s.getInputStream());
			cdataOSt = new DataOutputStream(s.getOutputStream());

			new Thread(new ClintSoft()).start();
			while (!close) {
				input = in.nextLine();
				if (str != "qq")
					cdataOSt.writeUTF(input);

			}
			cdataOSt.flush();
			// cdataOSt.close();
			// cdataISt.close();
			s.close();
			in.close();
			System.out.println("Good Bye");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// DataInputStream cdataISt = null;
		try {
			cdataISt = new DataInputStream(s.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			DataOutputStream cdataOSt = new DataOutputStream(s.getOutputStream());
			if (!s.isClosed() && cdataOSt != null && cdataISt != null)
				while ((str = cdataISt.readUTF()) != null) {
					System.out.println(str);
					if (str.startsWith("qq")) {
						break;
					}

				}
			close = true;
			cdataOSt.writeUTF("qq");
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
