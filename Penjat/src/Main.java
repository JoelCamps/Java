public class Main {
    public static void main(String[] args) {
        String [] palabrasAhorcado;
        String palabraAhorcado;
        char[] mascara, guardar;
        char letra;
        boolean exit, comprobar;
        int errores = 0;

        palabrasAhorcado = guardarPalabras();
        palabraAhorcado = elegirPalabra(palabrasAhorcado);
        mascara = crearMascara(palabraAhorcado);
        guardar = letrasJugadas();

        do {
            mostrarMascara(mascara);
            letra = escogerLetra(guardar);
            comprobar = comprobarLetra(letra, palabraAhorcado, mascara);
            errores = comprobarErrores(errores, comprobar);
            mostrarDibujoAhorcado(errores);
            exit = comprobarExit(errores, mascara, palabraAhorcado);

        }while(!exit);
    }

    private static void mostrarDibujoAhorcado(int errores) {
        switch (errores){
            case 0:
                System.out.println("-------------");
                System.out.println("|           |");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("-----");
                break;
            case 1:
                System.out.println("-------------");
                System.out.println("|           |");
                System.out.println("|           o");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("-----");
                break;
            case 2:
                System.out.println("-------------");
                System.out.println("|           |");
                System.out.println("|           o");
                System.out.println("|           |");
                System.out.println("|");
                System.out.println("|");
                System.out.println("-----");
                break;
            case 3:
                System.out.println("-------------");
                System.out.println("|           |");
                System.out.println("|           o");
                System.out.println("|          (|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("-----");
                break;
            case 4:
                System.out.println("-------------");
                System.out.println("|           |");
                System.out.println("|           o");
                System.out.println("|          (|)");
                System.out.println("|");
                System.out.println("|");
                System.out.println("-----");
                break;
            case 5:
                System.out.println("-------------");
                System.out.println("|           |");
                System.out.println("|           o");
                System.out.println("|          (|)");
                System.out.println("|          /");
                System.out.println("|");
                System.out.println("-----");
                break;
            case 6:
                System.out.println("-------------");
                System.out.println("|           |");
                System.out.println("|           o");
                System.out.println("|          (|)");
                System.out.println("|          / )");
                System.out.println("|");
                System.out.println("-----");
                break;
        }
    }

    private static boolean comprobarExit(int errores, char[] mascara, String palabraAhorcado) {
        boolean exit = false;
        int ganar = 0;
        if (errores == 6){
            System.out.println("Has perdido, la palabra era " + palabraAhorcado);
            exit = true;
        }
        for (int i = 0; i < mascara.length; i++) {
            if (mascara[i] == '_'){
                ganar++;
            }
        }
        if(ganar == 0){
            System.out.println("Has adivinado la palabra " + palabraAhorcado);
            exit = true;
        }
        return exit;
    }

    private static int comprobarErrores(int errores, boolean comprobar) {
        if(!comprobar){
            System.out.println("La letra no esta en la palabra");
            errores++;
        }
        return errores;
    }

    private static boolean comprobarLetra(char letra, String palabraAhorcado, char[] mascara) {
        boolean comprobar = false;
        for (int i = 0; i <  palabraAhorcado.length(); i++) {
            if (letra == palabraAhorcado.charAt(i)){
                comprobar = true;
                mascara[i] = letra;
                System.out.println("Has encontrado la letra");
            }
        }
        return comprobar;
    }


    private static char escogerLetra(char[] guardar) {
        char letra;
        boolean exit = false;
        System.out.println();
        System.out.print("Escribe una letra: ");
        letra = Teclat.llegirChar();
        int i = 0;
        do  {
            if(letra == guardar[i]){
                System.out.print("Esta letra ya la has puesto, escribe otra: ");
                letra = Teclat.llegirChar();
                i = 0;
            } else if (guardar[i] == '!'){
                guardar[i] = letra;
                exit = true;
            }else{
                i++;
            }
        }while (!exit);
        return letra;
    }
    private static char[] letrasJugadas() {
        char [] guardar = {'!', '!', '!', '!', '!', '!', '!', '!', '!', '!', '!', '!', '!', '!', '!', '!', '!', '!', '!', '!', '!', '!', '!', '!', '!'};
        return guardar;
    }
    private static void mostrarMascara(char[] mascara) {
        System.out.print(mascara);
    }

    private static char[] crearMascara(String palabraAhorcado) {
        char [] mascara = new char[palabraAhorcado.length()];
        for (int i = 0; i < mascara.length; i++) {
            mascara[i] = '_';
        }
        return mascara;
    }

    private static String elegirPalabra(String[] palabrasAhorcado) {
        int numPalabras = (int)Math.floor(Math.random()*30);
        return palabrasAhorcado[numPalabras];
    }

    private static String [] guardarPalabras() {
        String [] palabrasAhoracdo = {"palabra", "juego", "casa", "mesa", "silla", "teclado", "raton", "movil", "clase", "pantalla", "ventana", "aula", "reloj", "pulsera", "collar", "gafas", "gorra", "portatil", "ordenador", "zapato", "camiseta", "pantalon", "digital", "torre", "cable", "planta", "carpeta", "boli", "lapiz", "goma"};
        return palabrasAhoracdo;
    }
}