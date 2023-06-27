import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public Window(){
        super("Et bim jvous nique");

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        setBounds(screenSize.width,0,600, screenSize.height/2);

        tk.beep();

        setVisible(true);
    }

    public static void main(String[] args) {
        JFrame window = new Window();
    }
}
