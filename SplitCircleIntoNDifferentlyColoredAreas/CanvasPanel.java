import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.Random;

public class CanvasPanel extends JPanel {
    private int radius = 100;
    private int n = 8;
    public void setSplitting(int radius, int n) {
        this.radius = radius;
        this.n = n;
        repaint();
    }
    private int x[];  // Class variable
    private int y[];  // Class variable

    private void getPoints(int x0,int y0,int r,int noOfDividingPoints, Graphics graphics)
    {

        double angle = 0;

        x = new int[noOfDividingPoints];
        y = new int[noOfDividingPoints];

        for(int i = 0 ; i < noOfDividingPoints  ;i++)
        {
            angle = i * (360/noOfDividingPoints);

            x[i] = (int) (x0 + r * Math.cos(Math.toRadians(angle)));
            y[i] = (int) (y0 + r * Math.sin(Math.toRadians(angle)));


        }
        for(int i = 0; i < noOfDividingPoints - 1; i++){
            //graphics.fillPolygon(new int[]{x0, x[i], x[i+1]}, new int[]{y[i], y0, y[i+1]}, 3);
            Path2D path = new Path2D.Double();
            path.moveTo(x0,y0);
            path.moveTo(x[i], y[i]);
            path.moveTo(x[i+1], y[i+1]);
            Random random = new Random();
            // Массив из пяти цветов
            Color[] colors = { Color.BLUE, Color.GREEN, Color.MAGENTA, Color.RED,
                    Color.CYAN };
            int pos = random.nextInt(colors.length);
            // Меняем цвет у кнопки
            graphics.setColor(colors[pos]);
            graphics.fillPolygon(new int[]{x0,x[i],x[i+1]}, new int[]{y0, y[i], y[i+1]}, 3);
        }


        for(int i = 0 ; i < noOfDividingPoints  ;i++)
        {

            graphics.drawLine(x0, y0, x[i], y[i]);
        }
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.drawOval(450,450,radius,radius);
        getPoints(500,500,radius/2,n,g);



    }
}
