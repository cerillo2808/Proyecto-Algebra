import java.util.Scanner;
import java.text.DecimalFormat;
import java.lang.Math;
import org.apache.commons.math3.linear.*;

public class Hiperbola {
    Scanner in = new Scanner (System.in);

    public Hiperbola() {}

    //atributos
    double coeficienteXalCuadrado;
    double coeficienteYalCuadrado;
    double coeficienteX;
    double coeficienteY;
    double constante;

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
            Painter s= new Painter();
            s.paintHyperbola((int)a,(int)b);
        }else {
            System.out.print("Hiperbola Vertical\n");
            Painter2 s2= new Painter2();
            s2.paintHyperbola((int)b,(int)a);
        }
        System.out.print("Coordenadas del centro (" +x+ "," +y+ ")\n");
        System.out.print("Distancia del centro a un vértice : " +c+ "\n");
        System.out.print("Distancia del centro al foco : " +a+ "\n");
        calcularEcuacionCuadratica(dir,a,b,x,y);
        calcularEcuacionCanonica(dir,a,c,b,x,y);
        RealMatrix matriz = matrizAsociadaFormaCuadratica(coeficienteXalCuadrado, coeficienteYalCuadrado, coeficienteX, coeficienteY, constante);
        EigenDecomposition matrizDiagonal = valoresPropios(matriz);
        vectoresPropios(matrizDiagonal);
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
                //actualizar los atributos
                coeficienteXalCuadrado = bP;
                coeficienteYalCuadrado = aP*-1;
                constante = mult*-1;
            }else {
                System.out.print("Ecuación Cuadrática: " +formatDouble(bP)+ "x² - " +formatDouble(aP)+ "y² - " +formatDouble(auxCHorizontal)+ "x + " +formatDouble(auxDHorixontal)+ "y + " + formatDouble(auxEHorizontal)+ " = 0 \n");
                //actualizar los atributos
                coeficienteXalCuadrado = bP;
                coeficienteYalCuadrado = aP*-1;
                coeficienteX = auxCHorizontal*-1;
                coeficienteY = auxDHorixontal;
                constante = auxEHorizontal;
            }
            //Hiperbola vertical
        }else {
            //eje en el origen
            if (x==0 && y==0) {
                System.out.print("Ecuación Cuadrática: -" +formatDouble(aP)+ "x² + " +formatDouble(bP)+ "y² - " +formatDouble(mult)+ " = 0 \n");
                //actualizar atributos
                coeficienteXalCuadrado = aP*-1;
                coeficienteYalCuadrado = bP;
                constante = mult*-1;
            }else {
                System.out.print("Ecuación Cuadrática: -" +formatDouble(aP)+ "x² + " +formatDouble(bP)+ "y² + " +formatDouble(auxCVertical)+ "x - " +formatDouble(auxDVertical)+ "y + " + formatDouble(auxEVertical)+ " = 0 \n");
                //actualizar atributos
                coeficienteXalCuadrado = aP*-1;
                coeficienteYalCuadrado = bP;
                coeficienteX = auxCVertical;
                coeficienteY = auxDVertical*-1;
                constante = auxEVertical;
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

    public RealMatrix matrizAsociadaFormaCuadratica(double coeficienteXalCuadrado, double coeficienteYalCuadrado, double coeficienteX, double coeficienteY, double constante){
        double[][] matrizDouble = {{coeficienteXalCuadrado,0,coeficienteX/2},{0,coeficienteYalCuadrado,coeficienteY/2},{coeficienteX/2,coeficienteY/2,constante}};
        RealMatrix matrizAsociada = new Array2DRowRealMatrix(matrizDouble);
        System.out.println("Matriz asociada a la Fórmula Cuadrática: ");
        for (int i = 0; i < matrizAsociada.getRowDimension(); i++) {
            for (int j = 0; j < matrizAsociada.getColumnDimension(); j++) {
                System.out.printf("%.1f   ", matrizAsociada.getEntry(i, j));
                //se redondea a un decimal
            }
            System.out.println();
        }
        return matrizAsociada;
    }

    public EigenDecomposition valoresPropios(RealMatrix matriz){
        EigenDecomposition matrizDiagonal = new EigenDecomposition(matriz);
        double[] valoresPropios = matrizDiagonal.getRealEigenvalues();

        for (int i = 0; i< valoresPropios.length; i++){
            System.out.print("\nValor propio #" + (i+1) + ": ");
            System.out.printf("%.1f", valoresPropios[i]);
        }

        return matrizDiagonal;
        
    }

    public void vectoresPropios(EigenDecomposition matrizDiagonal){
        RealMatrix matrizC = matrizDiagonal.getV();

        for (int i = 0; i < matrizC.getColumnDimension(); i++) {

            
            System.out.print("\nVector propio #" + (i+1) + " (   ");
            for (int j = 0; j < matrizC.getRowDimension(); j++) {
                System.out.printf("%.1f    ", matrizC.getEntry(i, j));
            }
            
            
            System.out.print(")");
        }
    }
}