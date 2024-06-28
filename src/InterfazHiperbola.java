import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazHiperbola {
    // Variables para almacenar los valores ingresados
    public static String valorA;
    public static String valorB;
    public static String valorH;
    public static String valorK;
    public static String valorX;
    public static String valorY;
    public static String valorConstante;

    public static void crearYMostrarInterfaz() { // crear y mostrar la interfaz de usuario
        
        JFrame marcoPrincipal = new JFrame("Datos para graficar una Hipérbola"); // Crear el marco principal
        marcoPrincipal.setSize(400, 450); // Podemos cambiar el tamaño si es necesario para la presentación
        marcoPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marcoPrincipal.setLayout(new GridLayout(8, 2)); // cuadrícula de 8 filas y 2 columnas del interfaz 
        //inicializacion de los elementos del interfaz
        JLabel Textointerfaz_a = new JLabel("Valor de 'a':");
        JTextField textoA = new JTextField();
        JLabel Textointerfaz_b = new JLabel("Valor de 'b':");
        JTextField textoB = new JTextField();
        JLabel Textointerfaz_h = new JLabel("Centro h:");
        JTextField textoH = new JTextField();
        JLabel Textointerfaz_k = new JLabel("Centro k:");
        JTextField textoK = new JTextField();
        JLabel Textointerfaz_x = new JLabel("Valor de 'x':");
        JTextField textoX = new JTextField();
        JLabel Textointerfaz_y = new JLabel("Valor de 'y':");
        JTextField textoY = new JTextField();
        JLabel Textointerfaz_constante = new JLabel("Constante:");
        JTextField textoConstante = new JTextField();
        JButton botonGraficar = new JButton("Graficar");

        // Se añaden los componentes al marco principal 
        marcoPrincipal.add(Textointerfaz_a);
        marcoPrincipal.add(textoA);
        marcoPrincipal.add(Textointerfaz_b);
        marcoPrincipal.add(textoB);
        marcoPrincipal.add(Textointerfaz_h);
        marcoPrincipal.add(textoH);
        marcoPrincipal.add(Textointerfaz_k);
        marcoPrincipal.add(textoK);
        marcoPrincipal.add(Textointerfaz_x);
        marcoPrincipal.add(textoX);
        marcoPrincipal.add(Textointerfaz_y);
        marcoPrincipal.add(textoY);
        marcoPrincipal.add(Textointerfaz_constante);
        marcoPrincipal.add(textoConstante);
        marcoPrincipal.add(botonGraficar);

        marcoPrincipal.setVisible(true); //marco principal activado

        //configurar la acción del botón "Graficar"
        botonGraficar.addActionListener(new ActionListener() { //Al estripar este boton hay que conectarlo con painter
            public void actionPerformed(ActionEvent e) {
                //se obtienen valores ingresados por el usuario y guardarlos en las variables globales
                valorA = textoA.getText();
                valorB = textoB.getText();
                valorH = textoH.getText();
                valorK = textoK.getText();
                valorX = textoX.getText();
                valorY = textoY.getText();
                valorConstante = textoConstante.getText();
            }
        });
    }
}
