import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import javax.swing.JFrame;

public class Brick {
	static byte[][] steine;
	static Point p1;
	static Point p2;
	static Point ball;
	static double alpha;
	static double ballV;
	static long p1p;
	static long p2p;
	static boolean gameOver = false;
	static boolean oot;
	static final int spielerBreite = 75;
	static final int spielerHoehe = 15;
	static final int steinBreite = 40;
	static final int steinHoehe = 20;
	static final int steinAbstand = 5;
	static int feldBreite = 1024;
	static int feldHoehe = 600;
	static JFrame frame;
	static byte p1dir;
	static byte p2dir;
	static int spielerV = 20;
	static Timer timer;

	static void initSteine(int paramInt1, int paramInt2) {
		steine = new byte[paramInt1][paramInt2];

		for (int i = 0; i < paramInt1; i++)
			for (int j = 0; j < paramInt2; j++) {
				steine[i][j] = (Math.random() < 0.5D ? 1 : 2);
			}
	}

	static void initRest() {
		oot = Math.random() < 0.5D;

		if (oot) {
			p1 = new Point((int)(feldBreite * 0.6D), feldHoehe - 60);
			p2 = new Point((int)(feldBreite * 0.3D), feldHoehe - 15);
		}
		else {
			p1 = new Point((int)(feldBreite * 0.6D), feldHoehe - 15);
			p2 = new Point((int)(feldBreite * 0.3D), feldHoehe - 60);
		}

		p2p = Brick.p1p = 0L;

		ball = new Point((int)(feldBreite * 0.5D), feldHoehe - 20);
		alpha = 4.39822971502571D;
		ballV = 5.0D;

		oot = Math.random() < 0.5D;
	}

	static void initSpiel() {
		initSteine(feldBreite / 45, feldHoehe / 25 / 2);
		initRest();

		frame = new JFrame("Brick");
		frame.setSize(feldBreite, feldHoehe + 20);

		frame.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent paramKeyEvent) {
				if (paramKeyEvent.getKeyChar() == 'a') {
					Brick.p2dir = -1;
				}
				if (paramKeyEvent.getKeyChar() == '.') {
					Brick.p1dir = -1;
				}
				if (paramKeyEvent.getKeyChar() == 'd') {
					Brick.p2dir = 1;
				}
				if (paramKeyEvent.getKeyChar() == '-') {
					Brick.p1dir = 1;
				}
			}

			public void keyReleased(KeyEvent paramKeyEvent) {
				if ((paramKeyEvent.getKeyChar() == 'a') || (paramKeyEvent.getKeyChar() == 'd')) {
					Brick.p2dir = 0;
				}
				if ((paramKeyEvent.getKeyChar() == '.') || (paramKeyEvent.getKeyChar() == '-')) {
					Brick.p1dir = 0;
				}
			}

			public void keyTyped(KeyEvent paramKeyEvent) {
				if (paramKeyEvent.getKeyChar() == 'q') {
					System.exit(0);
				}
			}
		});
		frame.add(new BrickPanel());
		frame.setDefaultCloseOperation(3);

		timer = new Timer();
		timer.schedule(new MyTimerTask(), 5000L, 40L);
	}

	public static void main(String[] paramArrayOfString) {
		Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize();

		feldBreite = localDimension.width;
		feldHoehe = (int)(localDimension.height * 0.8D);

		if ((paramArrayOfString.length == 1) && (paramArrayOfString[0].equals("-fs"))) {
			feldHoehe = localDimension.height;
		}

		initSpiel();

		frame.setVisible(true);
	}

	public static void gameOver() {
		gameOver = true;
		frame.setVisible(false);
		timer.cancel();
		SaveScore.save(p1p, "Brick");
		SaveScore.save(p2p, "Brick");
		System.exit(0);
	}
}
