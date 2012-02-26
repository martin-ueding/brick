import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class SaveScore {
	public static void save(long paramLong, String paramString) {
		String str = JOptionPane.showInputDialog("Du hast " + paramLong + " Punkte. Dein Name: \nYou have got " + paramLong + " points. Your Name:");

		if (str != null) {
			JFrame localJFrame = new JFrame("Highscore senden - Sending Highscore");
			localJFrame.setSize(400, 50);
			localJFrame.setLocation(312, 300);
			JProgressBar localJProgressBar = new JProgressBar();
			localJProgressBar.setIndeterminate(true);
			localJFrame.add(localJProgressBar);
			localJFrame.setVisible(true);
			try {
				URL localURL = new URL("http://www.martin-ueding.de/score/save_score.php?spiel=" + paramString + "&name=" + str + "&punkte=" + paramLong);
				localURL.openStream();
			}
			catch (MalformedURLException localMalformedURLException) {
				System.out.println("Highscore konnte nicht eingetragen werden. - Highscore could not be entered.");
			}
			catch (IOException localIOException) {
				System.out.println("Highscore konnte nicht eingetragen werden. - Highscore could not be entered.");
			}

			localJFrame.setVisible(false);
		}
	}
}
