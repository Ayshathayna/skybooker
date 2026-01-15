package trabalho;

import trabalho.voo.Voo;
import trabalho.reserva.Reserva;
import trabalho.passageiro.Passageiro;
import trabalho.pessoa.Pessoa;
import trabalho.menu.Menu;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // Declare o Scanner aqui

        Menu.menu();
        sc.close();


    }

}
