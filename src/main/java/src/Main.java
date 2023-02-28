package src;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String notificar = "ReactiveX funcionando"; //Mensaje final que debe imprimirse en consola.

        ProcesoObservable procesito = new ProcesoObservable(notificar); //Creamos el objeto de nuestra clase de observadores - Proceso observable
        procesito.subscribe(notificado ->{
            System.out.println(notificado);
            //Creamos observador, en esta ocasion usamos una lambda simple con tal de recibir los datos.
        });
        procesito.avisar();

        masProcesos();
        System.out.println("Esperando procesos pendientes.");
    }


    // Codigo sin relevancia para el programa, pero con el fin de demostrar que se puede hacer otros procesos en el programa principal.
    // A pesar de que llamamos primero a la concurrencia, se ejecutan los numeros de masProcesos(), una vez terminado da paso al timer y finalmente termina.
    private static void masProcesos() {
        int num = 0;
        for (int i=0;i<10;i++) {
            num+=i;
            System.out.println("Numero:"+num);
        }
    }
}
