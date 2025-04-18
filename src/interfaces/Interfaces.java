
package interfaces;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import javax.swing.UIManager;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import java.awt.Color;

public class Interfaces {
  public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatCarbonIJTheme());
             UIManager.put("Button.arc", 30); // Mayor valor = más redondeado
            UIManager.put("Button.background", new Color(98, 182, 135)); // Color verde
            UIManager.put("Button.foreground", Color.WHITE); // Texto blanco
            UIManager.put("Button.borderColor", new Color(31, 147, 71)); // Borde verde oscuro
            UIManager.put("TextComponent.arc", 15); // Aplica a JTextField, JTextArea, JPasswordField
            UIManager.put("JTextField.border", new Color(128, 202, 166)); // Borde de color verde pastel

            
        } catch (Exception e) {
            e.printStackTrace();
        }

        frmInicio principal = new frmInicio();
        principal.setVisible(true);
    }
}
