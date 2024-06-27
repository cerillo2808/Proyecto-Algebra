import java.awt.Graphics;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Painter2 extends JFrame {
    float scalex = 5, scaley = 5;
    int width, height;

    int _a, _b;
    shp shape = shp.nothing;
    String eq = "";

    public Painter2() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void windowClosed(WindowEvent e) {
        System.exit(0);
    }

    public void paint(Graphics g) {
        super.paint(g);
        boolean toinnerdraw;
        width = getWidth();
        height = getHeight();

        int centerx = width / 2;
        int centery = height / 2;

        g.drawString(eq, 5, 15);
        // Dibujar ejes
        g.drawLine(0, centery, width, centery);
        g.drawLine(centerx, 0, centerx, height);

        int oldx = 0, oldy = 0, newx = 0, newy = 0;
        int oldx2 = 0, oldy2 = 0, newx2 = 0, newy2 = 0;
        boolean isdrawn1 = false, isdrawn2 = false;
        for (int i = -centerx; i <= centerx; i += 1) {
            int p1x, p2x, p1y, p2y;

            oldx = newx;
            oldy = newy;
            newx = i;
            newy = fnPositivo(i);

            p1x = (int) (oldx * scalex);
            p1y = (int) (oldy * scaley);
            p2x = (int) (newx * scalex);
            p2y = (int) (newy * scaley);
            toinnerdraw = true;
            if (p1x < -centerx || p1y < -centery || p2x < -centerx || p2y < -centery || p1x > centerx || p1y > centery || p2x > centerx || p2y > centery) {
                isdrawn1 = false;
                toinnerdraw = false;
            }
            if (isdrawn1 && toinnerdraw) {
                g.drawLine(centerx + p1x, centery - p1y, centerx + p2x, centery - p2y);
            }
            if (toinnerdraw)
                isdrawn1 = true;

            oldx2 = newx2;
            oldy2 = newy2;
            newx2 = i;
            newy2 = fnNegativo(i);

            p1x = (int) (oldx2 * scalex);
            p1y = (int) (oldy2 * scaley);
            p2x = (int) (newx2 * scalex);
            p2y = (int) (newy2 * scaley);
            toinnerdraw = true;
            if (p1x < -centerx || p1y < -centery || p2x < -centerx || p2y < -centery || p1x > centerx || p1y > centery || p2x > centerx || p2y > centery) {
                isdrawn2 = false;
                toinnerdraw = false;
            }
            if (isdrawn2 && toinnerdraw) {
                g.drawLine(centerx + p1x, centery - p1y, centerx + p2x, centery - p2y);
            }
            if (toinnerdraw)
                isdrawn2 = true;
        }
    }

    public void paintNothing(int radius) {
        eq = "";
        shape = shp.nothing;
    }

    public void paintHyperbola(int a, int b) {
        _a = a;
        _b = b;
        eq = "Y^2 / " + b * b + " - X^2 / " + a * a + " = 1";
        shape = shp.hyperbola;
    }

    private int fnPositivo(int x) {
        if (shape == shp.hyperbola)
            return (int) (_b * Math.sqrt((double) (x * x + _a * _a) / _a / _a));
        if (shape == shp.test)
            return x * x;
        return 0;
    }

    private int fnNegativo(int x) {
        if (shape == shp.hyperbola)
            return -(int) (_b * Math.sqrt((double) (x * x + _a * _a) / _a / _a));
        if (shape == shp.test)
            return -x * x;
        return 0;
    }
}
