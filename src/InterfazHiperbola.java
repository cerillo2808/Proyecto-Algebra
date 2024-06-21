import javax.swing.*; 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazHiperbola {
    public static void main(String[] args) {
        // Llamar a la función que crea y muestra la interfaz de usuario
        crearYMostrarInterfaz();
    }

    // Función para crear y mostrar la interfaz de usuario
    private static void crearYMostrarInterfaz() {
        // Crear el marco principal
        JFrame marcoPrincipal = new JFrame("Datos para graficar una Hipérbola");
        marcoPrincipal.setSize(400, 300); // Podemos cambiar el tamaño si es necesario para la presentación
        marcoPrincipal.setLayout(new GridLayout(5, 2)); // hace la cuadrícula de 5 filas y 2 columnas

        // Crear los componentes de la interfaz
        JLabel Textointerfaz_a = new JLabel("Valor de 'a':");
        JTextField textoA = new JTextField();
        JLabel Textointerfaz_b = new JLabel("Valor de 'a':");
        JTextField textoB = new JTextField();
        JLabel Textointerfaz_h = new JLabel("Centro h:");
        JTextField textoH = new JTextField();
        JLabel Textointerfaz_k = new JLabel("Centro k:");
        JTextField textoK = new JTextField();
        JButton botonGraficar = new JButton("Graficar");

        // Añadir los componentes al marco principal
        marcoPrincipal.add(Textointerfaz_a);
        marcoPrincipal.add(textoA);
        marcoPrincipal.add(Textointerfaz_b);
        marcoPrincipal.add(textoB);
        marcoPrincipal.add(Textointerfaz_h);
        marcoPrincipal.add(textoH);
        marcoPrincipal.add(Textointerfaz_k);
        marcoPrincipal.add(textoK);
        marcoPrincipal.add(botonGraficar);

        // hacer visible el marco principal con un boolean (por si lo queremos ocultar)
        marcoPrincipal.setVisible(true);

        // Configuracion del botón de graficar
        botonGraficar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // obtener los valores ingresados
                String valorA = textoA.getText();
                String valorB = textoB.getText();
                String valorH = textoH.getText();
                String valorK = textoK.getText();

                // Mostrar un mensaje con los datos ingresados (solo para demostración)
                JOptionPane.showMessageDialog(marcoPrincipal, "Valores ingresados:\n" +
                        "a: " + valorA + "\n" +
                        "b: " + valorB + "\n" +
                        "h: " + valorH + "\n" +
                        "k: " + valorK);
                // Los valores se pueden guardar en variables y usarse para los cálculos, como imprimir las formas cuadráticas, matrices asociadas, etc.
            }
        });
    }
}
