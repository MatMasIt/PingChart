import java.io.IOException;
import java.util.Scanner;

public class Pinger {
	public static String execCmd(String c) throws java.io.IOException {//esegui comando shell
		String[] cmd = {
				"/bin/sh",
				"-c",
				c
		};
	    java.util.Scanner s = new java.util.Scanner(Runtime.getRuntime().exec(cmd).getInputStream()).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
	public static double ping() {//solo per linux, esegui ping in una shell bash e processa l'output con awk e sed per ottenere un double
		try {
			return Double.parseDouble(Pinger.execCmd("ping -c 1 -q 8.8.8.8 | awk -F\"/\" '{print $6}' | sed '/^[[:space:]]*$/d'"));
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1.0;
		}
	}
}

