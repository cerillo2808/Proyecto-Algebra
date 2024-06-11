import java.util.Scanner;
import java.text.DecimalFormat;
import java.lang.Math;

public class Hiperbola {
    Scanner in = new Scanner (System.in);

    public Hiperbola() {}

    //Cambia el formato de los decimales
    private String formatFloat(float num) {
        DecimalFormat df = new DecimalFormat("0.##");
        return df.format(num);
    }
    private String formatDouble(double num) {
        DecimalFormat df = new DecimalFormat("0.##");
        return df.format(num);
    }

    //Validar entradas para que solo reciba numeros
    private float validarNumeros(String texto) {
        while (true) {
            System.out.println(texto);
            if (in.hasNextFloat()) {
                float num = in.nextFloat();
                return Float.parseFloat(formatFloat(num));
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                in.next();
            }
        }
    }

    public void calcularHiperbola() {
        System.out.print("Calcular Hiperbola\n");
        int seleccion = 0;
        int dir = 0;
        boolean validInput = false;
        do {
            System.out.print("1.Horizontal\n2.Vertical\n");
            String input = in.nextLine();
            try {
                seleccion = Integer.parseInt(input);
                if (seleccion == 1 || seleccion == 2) {
                    dir = seleccion;
                    validInput = true;
                } else {
                    System.out.print("Eliga una opción válida\n");
                }
            } catch (NumberFormatException e) {
                System.out.print("Eliga una opción válida\n");
            }
        } while (!validInput);

        System.out.print("Coordenadas del centro (x,y)\n");
        float x = validarNumeros("Digite x: ");
        float y = validarNumeros("Digite y: ");
        float a = validarNumeros("Digite la distancia del centro a un vértice (a): ");
        float c = validarNumeros("Digite la distancia del centro al foco (c): ");


        //Conseguir b (semieje conjugado)
        //c^2 = a^2 + b^2
        //b^2 = c^2 - a^2
        int auxPow = 2; //^2
        double aPow = Math.pow(a,auxPow); //a^2
        double cPow = Math.pow(c,auxPow); //c^2
        double bPow = cPow - aPow; // b^2 = c^2 - a^2
        double b = Math.sqrt(bPow); // b= √(c^2 - a^2)


        System.out.print("Datos de la Hipérbola: \n");
        if (dir == 1){
            System.out.print("Hiperbola Horizontal\n");
        }else {
            System.out.print("Hiperbola Vertical\n");
        }
        System.out.print("Coordenadas del centro (" +formatFloat(x)+ "," +formatFloat(y)+ ")\n");
        System.out.print("Distancia del centro a un vértice : " +formatFloat(c)+ "\n");
        System.out.print("Distancia del centro al foco : " +formatFloat(a)+ "\n");
        calcularEcuacionCanonica(dir,a,c,b,x,y);
    }

    public void calcularEcuacionCanonica(int dir, float a, float c, double b, float x, float y) {
        //Hiperbola horizontal
        if (dir == 1){
            //eje en el origen
            if (x==0 && y==0) {
                System.out.print("Ecuación Canónica: (x²/" +formatFloat(a)+ "²) - (y²/" +formatDouble(b)+ "²) = 1\n");
            }else {
                System.out.print("Ecuación Canónica: ( (x-" +formatFloat(x)+ ")² / " +formatFloat(a)+ "² - ( (y-" +formatDouble(y)+ ")² / " +formatDouble(b)+ "²) = 1 \n");
            }
        //Hiperbola vertical
        }else {
            //eje en el origen
            if (x==0 && y==0) {
                System.out.print("Ecuación Canónica: (y²/" +formatFloat(a)+ "²) - (x²/" +formatDouble(b)+ "²) = 1\n");
            }else {
                System.out.print("Ecuación Canónica: ( (y-" +formatFloat(y)+ ")² / " +formatFloat(a)+ "² - ( (x-" +formatDouble(x)+ ")² / " +formatDouble(b)+ "²) = 1 \n");
            }
        }
    }
}