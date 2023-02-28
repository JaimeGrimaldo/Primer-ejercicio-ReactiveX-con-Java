package src;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ProcesoObservable extends Observable <String>{

    //Lista enlazada fácil de usar en diseño Observer.
    private List<@NonNull Observer<? super String>> observadores = new LinkedList<>();
    private String notificar; //Preparamos la variable que recibiremos del main.

    public ProcesoObservable(String notificar) {
        this.notificar = notificar; //Creamos constructor para recibir la variable del main.
    }


    @Override
    protected void subscribeActual(@NonNull Observer<? super String> observer) {
        observadores.add(observer);
        //Cuando el proceso reciba una suscripcion, se agrega el observador a la lista.
    }

    public void avisar() {
        //En esta ocasión se uso un Timer para agregar iteración a los procesos y checar como funcionan los hilos.
        Timer timersito = new Timer();
        timersito.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                observadores.forEach(obs->obs.onNext(notificar));
                observadores.forEach(obs->obs.onComplete()); // El observador ya puede descansar
                timersito.cancel(); //Finalizamos el timer
            }
        },
                5000, //Damos 5 segundos al timer
                1); //Si es negativo explota.
    }
}
