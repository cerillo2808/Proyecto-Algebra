import java.util.Scanner;

public class Interaccion{

    Scanner in = new Scanner (System.in);

    public void interaccion(){
        System.out.println("Bienvenido al programa para calcular hipérbolas");

        boolean correr = true;
        while (correr){
            Hiperbola hiperbola = new Hiperbola();
            hiperbola.calcularHiperbola();
            String repeticion;
            do{
                repeticion = preguntarRepeticion();

                if (repeticion.equals("N")){
                    correr = false;
                    break;
                } else if (repeticion.equals("S")){
                    correr = true;
                }

            } while (repeticion.equals("noValido"));

        }

        System.out.println("Gracias por usar la calculadora");

    }

    public String preguntarRepeticion(){
        System.out.println("¿Desea calcular otra cónica?\n [S] Sí \n [N] No");
        String respuesta = in.nextLine().toUpperCase().trim();
        if (respuesta.equals("N") || respuesta.equals("S")){
            return (respuesta);
        }
    
        else{
            return ("noValido");
        }
    }
}