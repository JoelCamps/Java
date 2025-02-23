public class Main {
    public static void main(String[] args) {
        boolean reiniciar;
        String [] preguntasGuardadas;
        String [] respuestasGuardadas;
        String[] preguntasJuego;
        String[] respuestasJuego;
        int numPreguntas;
        int turno = 0;
        String respuesta;
        int acierto = 0;

        do {
            preguntasGuardadas = guardarPreguntas();
            respuestasGuardadas = respuestasGuardadas();
            numPreguntas = escogerCantidadPreguntas();
            preguntasJuego = mascaraPreguntas(numPreguntas);
            respuestasJuego = mascaraRespuestas(numPreguntas);
            escogerPreguntasRandom(preguntasJuego, preguntasGuardadas, respuestasJuego, respuestasGuardadas);

            do {
                respuesta = preguntar(preguntasJuego, turno);
                acierto = comprobarRespuesta(respuestasJuego, turno, respuesta, acierto);
                turno++;
            } while (turno < numPreguntas);
            reiniciar = fianlJuego(acierto, numPreguntas);
        } while (reiniciar);

    }

    private static boolean fianlJuego(int acierto, int numPreguntas) {
        int fallos = numPreguntas - acierto;
        double porcentageAciertos = acierto*100 / numPreguntas;
        boolean reiniciar = false;
        String volverAJugar;

        System.out.println("El numero de aciertos es " + acierto + " y el de fallados es " + fallos);
        if (porcentageAciertos == 100){
            System.out.println("Lo que quiere decir que has acertado el " + porcentageAciertos + "%, que genio :).");
        } else if (porcentageAciertos >= 67) {
            System.out.println("Lo que quiere decir que has acertado el " + ((double)Math.round(porcentageAciertos * 100d) / 100d) + "%, nada mal ;).");
        } else if (porcentageAciertos >= 34){
            System.out.println("Lo que quiere decir que has acertado el " + ((double)Math.round(porcentageAciertos * 100d) / 100d) + "%, mejorable :/.");
        } else {
            System.out.println("Lo que quiere decir que has acertado el " + ((double)Math.round(porcentageAciertos * 100d) / 100d) + "%, vuelver a probar :(.");
        }

        do {
            System.out.println("Quieres volver a jugar? (yes/no)");
            volverAJugar = Teclat.llegirString();
            volverAJugar = volverAJugar.toLowerCase();
            if (volverAJugar.equals("yes") || volverAJugar.equals("no")){
                reiniciar = true;
            } else {
                System.out.println("Introduce un valor válido.");
            }
        } while(!reiniciar);

        if (volverAJugar.equals("yes")){
            reiniciar = true;
        }else {
            reiniciar = false;
        }
        return reiniciar;
    }

    private static int comprobarRespuesta(String[] respuestasJuego, int turno, String respuesta, int acierto) {
        if (respuestasJuego[turno].equals(respuesta)){
            acierto++;
            System.out.println("Has acertado la prgunta.");
        } else {
            System.out.println("Has fallado la prgunta.");
        }
        return acierto;
    }

    private static String preguntar(String[] preguntasJuego, int turno) {
        boolean exit = false;
        String respuesta;
        do {
            System.out.println(preguntasJuego[turno] + " (yes/no)");
            respuesta = Teclat.llegirString();
            respuesta= respuesta.toLowerCase();
            if (respuesta.equals("yes") || respuesta.equals("no")){
                exit = true;
            } else {
                System.out.println("Introduce un valor válido.");
            }
        } while(!exit);
        return respuesta;
    }

    private static void escogerPreguntasRandom(String[] preguntasJuego, String[] preguntasGuardadas, String[] respuestasJuego, String[] respuestasGuardadas) {
        boolean exit = true;
        int numRandom;
        do{
            boolean salir = false;
            int j = 0;
            numRandom = (int)Math.floor(Math.random()*20);
            do{
                if(preguntasJuego[j].equals("!")){
                    preguntasJuego[j] = preguntasGuardadas[numRandom];
                    guardarRespuestasJuego(respuestasJuego, respuestasGuardadas, numRandom, j);
                    salir = true;
                } else if (preguntasJuego[j].equals(preguntasGuardadas[numRandom])) {
                    salir = true;
                }else {
                    j++;
                }
            }while (!salir);
            for (int i = 0; i < preguntasJuego.length; i++) {
                if (preguntasJuego[i].equals("!")){
                    exit = false;
                }else {
                    exit = true;
                }
            }
        }while(!exit);
    }

    private static void guardarRespuestasJuego(String[] respuestasJuego, String[] respuestasGuardadas, int numRandom, int j) {
        respuestasJuego[j] = respuestasGuardadas[numRandom];
    }

    private static String[] mascaraRespuestas(int numPreguntas) {
        String [] respuestasJuego = new String[numPreguntas];
        for (int i = 0; i < respuestasJuego.length; i++) {
            respuestasJuego[i] = "!";
        }
        return respuestasJuego;
    }

    private static String[] mascaraPreguntas(int numPreguntas) {
        String [] preguntasJuego = new String[numPreguntas];
        for (int i = 0; i < preguntasJuego.length; i++) {
            preguntasJuego[i] = "!";
        }
        return preguntasJuego;
    }

    private static int escogerCantidadPreguntas() {
        int numpreguntas;
        boolean exit = false;
        System.out.print("Escribe un numero entre el 5 y el 20 para escoger cuantas preguntas quieres: ");
        do {
            numpreguntas = Teclat.llegirInt();
            if (numpreguntas < 5 || numpreguntas > 20){
                System.out.println("Has de escoger entre 5 y 20 preguntas.");
            }else {
                exit = true;
            }
        } while (!exit);
        return numpreguntas;
    }
    private static String[] respuestasGuardadas() {
        String [] respuestas = {"yes", "no", "no", "yes", "no", "no", "yes", "yes", "yes", "yes", "yes", "no", "no", "no", "no", "no", "yes", "no", "yes", "yes"};
        return respuestas;
    }
    private static String[] guardarPreguntas() {
        String [] preguntas = {"¿La Copa América es el torneo de selecciones nacionales más antiguo del mundo?", "¿La UEFA Champions League se llama así desde su creación en 1955?", "¿El VAR se utiliza para revisar todas las decisiones del árbitro en el fútbol?", "¿La consola Nintendo 64 fue lanzada en la década de 1990?", "¿La franquicia de juegos \"Assassin's Creed\" es publicada por Electronic Arts?", "¿La Gran Muralla China es visible desde la Luna a simple vista?", "¿La Revolución Rusa tuvo lugar en 1917?", "¿El ADN humano está compuesto por cuatro bases nitrogenadas: adenina, timina, citosina y guanina?", "¿La Gran Muralla China tiene más de 13,000 millas de longitud?", "¿Mickey Mouse fue el primer personaje creado por Disney?", "¿Walt Disney fue la voz original de Mickey Mouse?", "¿Walt Disney World se encuentra en California?", "¿El personaje principal de \"Moana\" es una princesa de Disney?", "¿El español es la lengua oficial de todas las comunidades autónomas de España?", "¿La Feria de Abril se celebra en la ciudad de Valencia?", "¿Los seres humanos solo usan el 10% de su cerebro?", "¿La Torre Eiffel fue diseñada como una estructura temporal para una exposición?", "¿El sol es una estrella de tipo enana roja?", "¿El plátano es una baya botánica?", "¿Hay una ciudad llamada Roma en cada continente?"};
        return preguntas;
    }
}
