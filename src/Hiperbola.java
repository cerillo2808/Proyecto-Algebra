import java.util.Scanner;
import java.text.DecimalFormat;
import java.lang.Math;
import org.apache.commons.math4.legacy.linear.Array2DRowRealMatrix;
import org.apache.commons.math4.legacy.linear.EigenDecomposition;
import org.apache.commons.math4.legacy.linear.RealMatrix;
import org.apache.commons.math4.legacy.linear.MatrixUtils;
import org.apache.commons.math4.legacy.linear.LUDecomposition;

public class Hiperbola {
    Scanner in = new Scanner (System.in);

    public Hiperbola() {}

    private String formatDouble(double num) {
        DecimalFormat df = new DecimalFormat("0.##");
        return df.format(num);
    }

    //Validar entradas para que solo reciba numeros
    private double validarNumeros(String texto) {
        while (true) {
            System.out.println(texto);
            if (in.hasNextDouble()) {
                double num = in.nextDouble();
                return num;
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                in.next();
            }
        }
    }

    public void calcularHiperbola() {
        System.out.print("Calcular Hipérbola\n");
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
                    System.out.print("Elija una opción válida\n");
                }
            } catch (NumberFormatException e) {
                System.out.print("Elija una opción válida\n");
            }
        } while (!validInput);

        System.out.print("Coordenadas del centro (x,y)\n");
        double x = validarNumeros("Digite x: ");
        double y = validarNumeros("Digite y: ");
        double a = validarNumeros("Digite la distancia del centro a un vértice (a): ");
        //Valida a != 0
        while (a==0) {
            System.out.println("Asegúrese de que a sea diferente de cero.");
            a = validarNumeros("Digite la distancia del centro a un vértice (a): ");
        }
        double c = validarNumeros("Digite la distancia del centro al foco (c): ");

        //Valida a y c
        while (a>=c) {
            System.out.println("La distancia del centro a un vertice debe ser siempre menor que la distancia del centro al foco");
            a = validarNumeros("Digite la distancia del centro a un vértice (a): ");
            c = validarNumeros("Digite la distancia del centro al foco (c): ");
        }


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
        System.out.print("Coordenadas del centro (" +x+ "," +y+ ")\n");
        System.out.print("Distancia del centro a un vértice : " +c+ "\n");
        System.out.print("Distancia del centro al foco : " +a+ "\n");
        calcularEcuacionCuadratica(dir,a,b,x,y);
        calcularEcuacionCanonica(dir,a,c,b,x,y);
    }

    public void calcularEcuacionCuadratica(int dir, double a, double b, double x, double y) {
        double mult = a*b;
        double aux = 2; //^2
        double aP = Math.pow(a,aux);
        double bP = Math.pow(b,aux);
        double xP = Math.pow(x,aux);
        double yP = Math.pow(y,aux);

        double auxCHorizontal = (2 * x * bP);
        double auxDHorixontal = (2 * y * aP);
        double auxEHorizontal = (bP * xP) - (aP * yP) - (aP * bP);

        double auxCVertical = (2 * x * aP);
        double auxDVertical = (2 * y * bP);
        double auxEVertical = (yP * bP) - (aP * xP) - (aP * bP);

        //Hiperbola horizontal
        if (dir == 1){
            //eje en el origen
            if (x==0 && y==0) {
                System.out.print("Ecuación Cuadrática: " +formatDouble(bP)+ "x² - " +formatDouble(aP)+ "y² - " +formatDouble(mult)+ " = 0 \n");
            }else {
                System.out.print("Ecuación Cuadrática: " +formatDouble(bP)+ "x² - " +formatDouble(aP)+ "y² - " +formatDouble(auxCHorizontal)+ "x + " +formatDouble(auxDHorixontal)+ "y + " + formatDouble(auxEHorizontal)+ " = 0 \n");
            }
            //Hiperbola vertical
        }else {
            //eje en el origen
            if (x==0 && y==0) {
                System.out.print("Ecuación Cuadrática: -" +formatDouble(aP)+ "x² + " +formatDouble(bP)+ "y² - " +formatDouble(mult)+ " = 0 \n");
            }else {
                System.out.print("Ecuación Cuadrática: -" +formatDouble(aP)+ "x² + " +formatDouble(bP)+ "y² + " +formatDouble(auxCVertical)+ "x - " +formatDouble(auxDVertical)+ "y + " + formatDouble(auxEVertical)+ " = 0 \n");
            }
        }
    }

    public void calcularEcuacionCanonica(int dir, double a, double c, double b, double x, double y) {
        //Hiperbola horizontal
        if (dir == 1){
            //eje en el origen
            if (x==0 && y==0) {
                System.out.print("Ecuación Canónica: (x²/" +formatDouble(a)+ "²) - (y²/" +formatDouble(b)+ "²) = 1\n");
            }else {
                System.out.print("Ecuación Canónica: ( (x-" +formatDouble(x)+ ")² / " +formatDouble(a)+ "² - ( (y-" +formatDouble(y)+ ")² / " +formatDouble(b)+ "²) = 1 \n");
            }
            //Hiperbola vertical
        }else {
            //eje en el origen
            if (x==0 && y==0) {
                System.out.print("Ecuación Canónica: (y²/" +formatDouble(a)+ "²) - (x²/" +formatDouble(b)+ "²) = 1\n");
            }else {
                System.out.print("Ecuación Canónica: ( (y-" +formatDouble(y)+ ")² / " +formatDouble(a)+ "² - ( (x-" +formatDouble(x)+ ")² / " +formatDouble(b)+ "²) = 1 \n");
            }
        }
    }

    public void matrizAsociadaFormaCuadratica(double A, double B, double C){
        //TO-DO
    }

    public void valoresPropios(){
        //TO-DO
    }

    public void vectoresPropios(){
        //TO-DO
    }
}