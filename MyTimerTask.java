import java.awt.Point;
import java.util.TimerTask;
import javax.swing.JFrame;

public class MyTimerTask extends TimerTask {
	private int iter = 1;

	public void run() {
		this.iter += 1;

		if (this.iter % 100 == 0) {
			this.iter = 1;
			Brick.ballV *= 1.05D;
		}

		Brick.p1.x += Brick.p1dir * Brick.spielerV;
		Brick.p2.x += Brick.p2dir * Brick.spielerV;
		Point tmp74_71 = Brick.ball;
		tmp74_71.x = (int)(tmp74_71.x + Math.cos(Brick.alpha) * Brick.ballV);
		Point tmp97_94 = Brick.ball;
		tmp97_94.y = (int)(tmp97_94.y + Math.sin(Brick.alpha) * Brick.ballV);

		if ((Brick.ball.x < 0) || (Brick.ball.x > Brick.feldBreite)) {
			Brick.alpha = 3.141592653589793D - Brick.alpha;
			Point tmp151_148 = Brick.ball;
			tmp151_148.x = (int)(tmp151_148.x + Math.cos(Brick.alpha) * Brick.ballV);
			Point tmp174_171 = Brick.ball;
			tmp174_171.y = (int)(tmp174_171.y + Math.sin(Brick.alpha) * Brick.ballV);
		}

		if (Brick.ball.y < 0) {
			Brick.alpha = 6.283185307179586D - Brick.alpha;
		}

		int i = (-5 + Brick.ball.x) / 45;
		int j = (-5 + Brick.ball.y) / 25;

		if ((i >= 0) && (i < Brick.steine.length) && (j >= 0) && (j < Brick.steine[0].length) &&
		        (Brick.steine[i][j] != 0)) {
			if (Brick.steine[i][j] == 1) {
				Brick.p1p += 1L;
			}
			else {
				Brick.p2p += 1L;
			}
			Brick.steine[i][j] = 0;

			Brick.alpha = 6.283185307179586D - Brick.alpha;
		}

		if ((Brick.ball.x >= Brick.p1.x) && (Brick.ball.x <= Brick.p1.x + 75) && (Brick.ball.y >= Brick.p1.y) && (Brick.ball.y < Brick.p1.y + 50)) {
			Brick.alpha = 3.141592653589793D + (Brick.ball.x - Brick.p1.x) / 75.0D * 3.141592653589793D / 2.0D + 0.7853981633974483D;
		}
		else if ((Brick.ball.x >= Brick.p2.x) && (Brick.ball.x <= Brick.p2.x + 75) && (Brick.ball.y >= Brick.p2.y) && (Brick.ball.y < Brick.p2.y + 50)) {
			Brick.alpha = 3.141592653589793D + (Brick.ball.x - Brick.p2.x) / 75.0D * 3.141592653589793D / 2.0D + 0.7853981633974483D;
		}
		if (Brick.ball.y > Brick.feldHoehe + 30) {
			Brick.gameOver();
		}
		Brick.frame.repaint();
	}
}
