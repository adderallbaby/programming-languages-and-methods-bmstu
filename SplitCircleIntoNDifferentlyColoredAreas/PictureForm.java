import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PictureForm {
    private JPanel mainPanel ;
    private JSpinner radiusSpinner;
    private JTextField areaField;
    private CanvasPanel canvasPanel;
    private JSpinner lineSpinner;

    public PictureForm ( ) {
        radiusSpinner.addChangeListener (new ChangeListener( ) {
            public void stateChanged (ChangeEvent e) {
                int diameter = (int)radiusSpinner.getValue( );
                int n = (int)lineSpinner.getValue( );
                canvasPanel.setSplitting(diameter,n);
                double area = Math.PI*(diameter/2)*(diameter/2);
                areaField.setText (String.format("%.2f", area));
            }
        } ) ;
        lineSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int diameter = (int)radiusSpinner.getValue( );
                int n = (int)lineSpinner.getValue( );
                canvasPanel.setSplitting(diameter,n);
                double area = Math.PI*(diameter/2)*(diameter/2);
                areaField.setText (String.format("%.2f", area));
            }
        });
        radiusSpinner.setValue(100);
    }
    public static void main (String[ ] args) {
        JFrame frame = new JFrame("Окружность");
        frame.setContentPane (new PictureForm().mainPanel) ;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
        frame.pack();
        frame.setVisible(true);
    }
}
