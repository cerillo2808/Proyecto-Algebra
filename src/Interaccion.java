import java.util.Scanner;

public class Interaccion{

    Scanner in = new Scanner (System.in);

    public void interaccion(){
        System.out.println("Bienvenido al programa para calcular cónicas");

        boolean correr = true;
        while (correr){
            seleccionarConica();
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

    public void seleccionarConica(){
        int conica;

        do {
            conica = preguntarConica();
        } while (conica == -1);

        if (conica == 1){
            System.out.println("elipse/circulo");
        }

        else if (conica == 2){
            System.out.println("parabola");
        }

        else if (conica == 3){
            System.out.println("hiperbola");
        }
    }

    public int preguntarConica(){

        System.out.println("Ingrese el número de la opción que desea calcular \n [1] Elipse/Círculo \n [2] Parábola \n [3] Hipérbola");
        String respuesta = in.nextLine();
        try{
            int retorno = Integer.parseInt(respuesta);
            if (retorno == 1){
                return 1;
            }
            else if (retorno == 2){
                return 2;
            }
            else if (retorno == 3){
                return 3;
            }
            else{
                return -1;
            }
        } catch (NumberFormatException e){
            return -1;
        }
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