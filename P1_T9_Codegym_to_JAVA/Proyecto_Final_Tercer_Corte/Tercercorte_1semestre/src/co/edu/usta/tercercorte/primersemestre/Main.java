package co.edu.usta.tercercorte.primersemestre;

import javax.swing.*;

public class Main {
    private static char[][] triquiarray;
    private static String respuesta;
    private static boolean findeljuego;

    public static void main(String[] args) {
        //La variable respuesta se usa para almacenar lo que el usuario ponga en el JOptionpane
        String answer;
        //Toma valores de 3 o 5, el 3 corresponde al modo 3x3 y el 5 al modo 5x5
        int game_mode;
        //Se usa para almacenar el valor de la fila en la cual el jugador al que le toque el turno desee poner su X o su O
        int player_row;
        //Se usa para almacenar el valor de la columna en la cual el jugador al que le toque el turno desee poner su X o su O
        int player_column;
        //Toma valores de 1 o 2, el 1 corresponde al turno de las X y el 2 al turno de las O
        int turno;
        //Esta variable solo sera usada al final para declarar el ganador, solo tomara valores de X o O
        char winner = 0;
        //Aumentara en 1 cada vez que el jugador ingrese un valor en el triqui y su funcion sera dar fin al juego cuando llegue a 9 en el modo 3x3
        //o a 25 en el modo 5x5, esto se hace por que el juego no puede continuar si todas las casillas estan llenas
        int limit_attempts = 0;
        //Permanecera en estado "false" hasta que se de alguna condicion que permita que el juego termine ya sea que halla un ganador o se acaben las casillas
        boolean finish_program=false;

//El usuario selecciona el modo de juego
        do {
            answer = JOptionPane.showInputDialog("Que modo quiere jugar? (3=3x3) (5=5x5)");
            game_mode = Integer.parseInt(answer);
            //Solo se permitiran valores de 3 o 5
            if (game_mode != 3 && game_mode != 5) {
                JOptionPane.showMessageDialog(null, "Error: Los valores permitidos son 3 o 5");
            }
        } while (game_mode != 3 && game_mode != 5);
//Llama a la clase crear arreglo
        crear_arreglo(game_mode);
//El codigo seguira pidiendo valores hasta que una condicion de victoria se cumpla
        while (finish_program==false){
            turno = 1;
            JOptionPane.showMessageDialog(null, "Turno de las X");
            respuesta = JOptionPane.showInputDialog("Seleccione la fila que desea ocupar");
            player_row = Integer.parseInt(respuesta);
            respuesta = JOptionPane.showInputDialog("Seleccione la columna que desea ocupar");
            player_column = Integer.parseInt(respuesta);
//Llama a el metodo interactuar matriz que se encarga de ocupar la casilla indicada previamentetes
            interactuarmatriz(player_row, player_column, turno, game_mode);
//Muestra la matriz
            JOptionPane.showMessageDialog(null, mostrarmatriz());
//comprueba si se cumple alguna de las condiciones de victoria
            finish_program = comprobarvictoria(game_mode);
            winner = 'X';
//En caso que todas las casillas sean llenadas el juego acabara
            limit_attempts++;
            if (game_mode == 1 && limit_attempts == 9) {
                finish_program = true;
            }
            if (game_mode == 2 && limit_attempts == 25) {
                finish_program = true;
            }
//Ejecuta el mismo codigo pero para el jugador 2
            if (finish_program == false) {
                turno = 2;
                JOptionPane.showMessageDialog(null, "Turno del jugador 2");
                respuesta = JOptionPane.showInputDialog("Seleccione la fila que desea ocupar");
                player_row = Integer.parseInt(respuesta);
                respuesta = JOptionPane.showInputDialog("Seleccione la columna que desea ocupar");
                player_column = Integer.parseInt(respuesta);
                interactuarmatriz(player_row, player_column, turno, game_mode);
                JOptionPane.showMessageDialog(null, mostrarmatriz());
                finish_program = comprobarvictoria(game_mode);
                winner = 'O';
                limit_attempts++;
                if (game_mode == 1 && limit_attempts == 9) {
                    finish_program = true;
                }
                if (game_mode== 2 && limit_attempts == 25) {
                    finish_program = true;
                }
            }
        }

        if (game_mode == 3) {
            if (limit_attempts != 9) {
                JOptionPane.showMessageDialog(null, "Game over\n" +
                        "Ganan las " + winner);
            } else JOptionPane.showMessageDialog(null, "Todas las casillas han sido llenadas\nGame over");
        }
        if (game_mode == 5) {
            if (limit_attempts != 25) {
                JOptionPane.showMessageDialog(null, "Game over\n" +
                        "Ganan las " + winner);
            } else JOptionPane.showMessageDialog(null, "Todas las casillas han sido llenadas\nGame over");
        }
    }

    public static void crear_arreglo(int mododejuego) {
        if(mododejuego==3){
            triquiarray = new char[3][3];}
        if (mododejuego==5){
            triquiarray = new char[5][5];}

        for (int i = 0; i < triquiarray.length; i++) {
            for (int j = 0; j < triquiarray.length; j++) {
                triquiarray[i][j] = '*';
            }
        }
    }

    public static String mostrarmatriz() {
        String view_array = "";
        for (int i = 0; i < triquiarray.length; i++) {
            for (int j = 0; j < triquiarray.length; j++) {
                view_array = view_array + triquiarray[i][j] + "    ";
            }
            view_array = view_array +"\n";
        }
        return view_array;
    }

    public static void interactuarmatriz(int jugador_fila, int jugador_columna, int turno, int mododejuego){

        if(mododejuego==3){
            while (jugador_columna<0||jugador_columna>2||jugador_fila<0||jugador_fila>2){
                JOptionPane.showMessageDialog(null, "Los numeros solo pueden ir del 0 al 2 en el modo 3x3");
                respuesta=JOptionPane.showInputDialog("Seleccione la fila que desea ocupar");
                jugador_fila=Integer.parseInt(respuesta);
                respuesta=JOptionPane.showInputDialog("Seleccione la columna que desea ocupar");
                jugador_columna=Integer.parseInt(respuesta);
            }
        }
        if(mododejuego==5){
            while (jugador_columna<0||jugador_columna>4||jugador_fila<0||jugador_fila>4){
                JOptionPane.showMessageDialog(null, "Los numeros solo pueden ir del 0 al 4 en el modo 5x5");
                respuesta=JOptionPane.showInputDialog("Seleccione la fila que desea ocupar");
                jugador_fila=Integer.parseInt(respuesta);
                respuesta=JOptionPane.showInputDialog("Seleccione la columna que desea ocupar");
                jugador_columna=Integer.parseInt(respuesta);
            }
        }

        while (triquiarray[jugador_fila][jugador_columna]!='*'){
            JOptionPane.showMessageDialog(null, "La casilla ya esta ocupada, por favor seleccione otra");
            JOptionPane.showMessageDialog(null, mostrarmatriz());
            respuesta= JOptionPane.showInputDialog("Seleccione la fila que desea ocupar");
            jugador_fila=Integer.parseInt(respuesta);
            respuesta=JOptionPane.showInputDialog("Seleccione la columna que desea ocupar");
            jugador_columna=Integer.parseInt(respuesta);
        }
        if (turno==1){
            triquiarray[jugador_fila][jugador_columna]='X';
        }else triquiarray[jugador_fila][jugador_columna]='O';
    }


    public static boolean comprobarvictoria(int mododejuego){

//casos en el modo 3x3 donde se cumple una condicion de victoria
        if (mododejuego==3){
            if (triquiarray[0][0]=='X'&&triquiarray[0][1]=='X'&&triquiarray[0][2]=='X')
            {findeljuego=true;}
            if (triquiarray[1][0]=='X'&&triquiarray[1][1]=='X'&&triquiarray[1][2]=='X')
            {findeljuego=true;}
            if (triquiarray[2][0]=='X'&&triquiarray[2][1]=='X'&&triquiarray[2][2]=='X')
            {findeljuego=true;}
            if (triquiarray[0][0]=='X'&&triquiarray[1][0]=='X'&&triquiarray[2][0]=='X')
            {findeljuego=true;}
            if (triquiarray[0][1]=='X'&&triquiarray[1][1]=='X'&&triquiarray[2][1]=='X')
            {findeljuego=true;}
            if (triquiarray[0][2]=='X'&&triquiarray[1][2]=='X'&&triquiarray[2][2]=='X')
            {findeljuego=true;}
            if (triquiarray[0][0]=='X'&&triquiarray[1][1]=='X'&&triquiarray[2][2]=='X')
            {findeljuego=true;}
            if (triquiarray[2][0]=='X'&&triquiarray[1][1]=='X'&&triquiarray[0][2]=='X')
            {findeljuego=true;}

            if (triquiarray[0][0]=='O'&&triquiarray[0][1]=='O'&&triquiarray[0][2]=='O')
            {findeljuego=true;}
            if (triquiarray[1][0]=='O'&&triquiarray[1][1]=='O'&&triquiarray[1][2]=='O')
            {findeljuego=true;}
            if (triquiarray[2][0]=='O'&&triquiarray[2][1]=='O'&&triquiarray[2][2]=='O')
            {findeljuego=true;}
            if (triquiarray[0][0]=='O'&&triquiarray[1][0]=='O'&&triquiarray[2][0]=='O')
            {findeljuego=true;}
            if (triquiarray[0][1]=='O'&&triquiarray[1][1]=='O'&&triquiarray[2][1]=='O')
            {findeljuego=true;}
            if (triquiarray[0][2]=='O'&&triquiarray[1][2]=='O'&&triquiarray[2][2]=='O')
            {findeljuego=true;}
            if (triquiarray[0][0]=='O'&&triquiarray[1][1]=='O'&&triquiarray[2][2]=='O')
            {findeljuego=true;}
            if (triquiarray[2][0]=='O'&&triquiarray[1][1]=='O'&&triquiarray[0][2]=='O')
            {findeljuego=true;}
        }
        if (mododejuego==5){
            //casos en el modo 5x5 donde se cumple una condicion de victoria
            if (triquiarray[0][0]=='X'&&triquiarray[0][1]=='X'&&triquiarray[0][2]=='X'&&triquiarray[0][3]=='X'&&triquiarray[0][4]=='X')
            {findeljuego=true;}
            if (triquiarray[1][0]=='X'&&triquiarray[1][1]=='X'&&triquiarray[1][2]=='X'&&triquiarray[1][3]=='X'&&triquiarray[1][4]=='X')
            {findeljuego=true;}
            if (triquiarray[2][0]=='X'&&triquiarray[2][1]=='X'&&triquiarray[2][2]=='X'&&triquiarray[2][3]=='X'&&triquiarray[2][4]=='X')
            {findeljuego=true;}
            if (triquiarray[3][0]=='X'&&triquiarray[3][1]=='X'&&triquiarray[3][2]=='X'&&triquiarray[3][3]=='X'&&triquiarray[3][4]=='X')
            {findeljuego=true;}
            if (triquiarray[4][0]=='X'&&triquiarray[4][1]=='X'&&triquiarray[4][2]=='X'&&triquiarray[4][3]=='X'&&triquiarray[4][4]=='X')
            {findeljuego=true;}
            if (triquiarray[0][0]=='X'&&triquiarray[1][0]=='X'&&triquiarray[2][0]=='X'&&triquiarray[3][0]=='X'&&triquiarray[4][0]=='X')
            {findeljuego=true;}
            if (triquiarray[0][1]=='X'&&triquiarray[1][1]=='X'&&triquiarray[2][1]=='X'&&triquiarray[3][1]=='X'&&triquiarray[4][1]=='X')
            {findeljuego=true;}
            if (triquiarray[0][2]=='X'&&triquiarray[1][2]=='X'&&triquiarray[2][2]=='X'&&triquiarray[3][2]=='X'&&triquiarray[4][2]=='X')
            {findeljuego=true;}
            if (triquiarray[0][3]=='X'&&triquiarray[1][3]=='X'&&triquiarray[2][3]=='X'&&triquiarray[3][3]=='X'&&triquiarray[4][3]=='X')
            {findeljuego=true;}
            if (triquiarray[0][4]=='X'&&triquiarray[1][4]=='X'&&triquiarray[2][4]=='X'&&triquiarray[3][4]=='X'&&triquiarray[4][4]=='X')
            {findeljuego=true;}
            if (triquiarray[0][0]=='X'&&triquiarray[1][1]=='X'&&triquiarray[2][2]=='X'&&triquiarray[3][3]=='X'&&triquiarray[4][4]=='X')
            {findeljuego=true;}
            if (triquiarray[4][0]=='X'&&triquiarray[3][1]=='X'&&triquiarray[2][2]=='X'&&triquiarray[1][3]=='X'&&triquiarray[0][4]=='X')
            {findeljuego=true;}
            if (triquiarray[0][0]=='O'&&triquiarray[0][1]=='O'&&triquiarray[0][2]=='O'&&triquiarray[0][3]=='O'&&triquiarray[0][4]=='O')
            {findeljuego=true;}
            if (triquiarray[1][0]=='O'&&triquiarray[1][1]=='O'&&triquiarray[1][2]=='O'&&triquiarray[1][3]=='O'&&triquiarray[1][4]=='O')
            {findeljuego=true;}
            if (triquiarray[2][0]=='O'&&triquiarray[2][1]=='O'&&triquiarray[2][2]=='O'&&triquiarray[2][3]=='O'&&triquiarray[2][4]=='O')
            {findeljuego=true;}
            if (triquiarray[3][0]=='O'&&triquiarray[3][1]=='O'&&triquiarray[3][2]=='O'&&triquiarray[3][3]=='O'&&triquiarray[3][4]=='O')
            {findeljuego=true;}
            if (triquiarray[4][0]=='O'&&triquiarray[4][1]=='O'&&triquiarray[4][2]=='O'&&triquiarray[4][3]=='O'&&triquiarray[4][4]=='O')
            {findeljuego=true;}
            if (triquiarray[0][0]=='O'&&triquiarray[1][0]=='O'&&triquiarray[2][0]=='O'&&triquiarray[3][0]=='O'&&triquiarray[4][0]=='O')
            {findeljuego=true;}
            if (triquiarray[0][1]=='O'&&triquiarray[1][1]=='O'&&triquiarray[2][1]=='O'&&triquiarray[3][1]=='O'&&triquiarray[4][1]=='O')
            {findeljuego=true;}
            if (triquiarray[0][2]=='O'&&triquiarray[1][2]=='O'&&triquiarray[2][2]=='O'&&triquiarray[3][2]=='O'&&triquiarray[4][2]=='O')
            {findeljuego=true;}
            if (triquiarray[0][3]=='O'&&triquiarray[1][3]=='O'&&triquiarray[2][3]=='O'&&triquiarray[3][3]=='O'&&triquiarray[4][3]=='O')
            {findeljuego=true;}
            if (triquiarray[0][4]=='O'&&triquiarray[1][4]=='O'&&triquiarray[2][4]=='O'&&triquiarray[3][4]=='O'&&triquiarray[4][4]=='O')
            {findeljuego=true;}
            if (triquiarray[0][0]=='O'&&triquiarray[1][1]=='O'&&triquiarray[2][2]=='O'&&triquiarray[3][3]=='O'&&triquiarray[4][4]=='O')
            {findeljuego=true;}
            if (triquiarray[4][0]=='O'&&triquiarray[3][1]=='O'&&triquiarray[2][2]=='O'&&triquiarray[1][3]=='O'&&triquiarray[0][4]=='O')
            {findeljuego=true;}
        }return findeljuego;
    }
}
