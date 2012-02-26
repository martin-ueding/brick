import java.util.ResourceBundle;

public class Spr {
	static ResourceBundle B = ResourceBundle.getBundle("sprache");

	public static String get(String paramString) {
		return B.getString(paramString);
	}
}
