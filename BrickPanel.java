import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

public class BrickPanel extends JPanel
{
  private static Color p1c;
  private static Color p2c;
  private boolean first = true;

  public BrickPanel() {
    p1c = Color.RED;
    p2c = Color.BLUE;
  }

  protected void paintComponent(Graphics paramGraphics) {
    paramGraphics.setColor(Color.BLACK);
    paramGraphics.fillRect(0, 0, getWidth(), getHeight());

    paramGraphics.setColor(p1c);
    paramGraphics.fillRect(Brick.p1.x, Brick.p1.y, 75, 15);
    paramGraphics.drawString(String.valueOf(Brick.p1p), (int)(Brick.feldBreite * 0.9D), (int)(Brick.feldHoehe * 0.75D));

    paramGraphics.setColor(p2c);
    paramGraphics.fillRect(Brick.p2.x, Brick.p2.y, 75, 15);
    paramGraphics.drawString(String.valueOf(Brick.p2p), (int)(Brick.feldBreite * 0.05D), (int)(Brick.feldHoehe * 0.75D));

    for (int i = 0; i < Brick.steine.length; i++) {
      for (int j = 0; j < Brick.steine[0].length; j++) {
        if (Brick.steine[i][j] > 0) {
          if (Brick.steine[i][j] == 1)
            paramGraphics.setColor(p1c);
          else {
            paramGraphics.setColor(p2c);
          }
          paramGraphics.fillRect(i * 40 + (i + 1) * 5, j * 20 + (j + 1) * 5, 40, 20);
        }
      }
    }

    paramGraphics.setColor(Color.WHITE);
    paramGraphics.fillOval(Brick.ball.x, Brick.ball.y, 3, 3);

    if (Brick.gameOver) {
      paramGraphics.setColor(Color.GREEN);
      paramGraphics.drawString(Spr.get("gameover"), Brick.feldBreite / 2 - 30, (int)(Brick.feldHoehe * 0.7D));
    }

    if (this.first) {
      paramGraphics.setColor(Color.WHITE);
      paramGraphics.drawString(Spr.get("steuerungspieler") + " 1: . & -", Brick.feldBreite / 3, (int)(Brick.feldHoehe * 0.6D));
      paramGraphics.drawString(Spr.get("steuerungspieler") + " 2: a & d", Brick.feldBreite / 3, (int)(Brick.feldHoehe * 0.6D) + 15);
      paramGraphics.drawString(Spr.get("cheftaste") + ": q", Brick.feldBreite / 3, (int)(Brick.feldHoehe * 0.6D) + 55);
      paramGraphics.drawString(Spr.get("spass"), Brick.feldBreite / 3, (int)(Brick.feldHoehe * 0.6D) + 80);

      this.first = false;
    }
  }
}
