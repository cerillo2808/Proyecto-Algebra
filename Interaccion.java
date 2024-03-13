import java.util.Scanner;
Scanner in = new Scanner (System.in);


public class Interaccion{
    public void run(){
        System.out.println("Bienvenido al programa para calcular cónicas");
        //buen lugar para poner los integrantes;

        int conica;

        do {
            conica = preguntarConica();
        } while (conica == -1);

        System.out.println(conica);

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
}