package trabalho.menu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import trabalho.voo.Voo;
import trabalho.reserva.Reserva;
import trabalho.passageiro.Passageiro;
import trabalho.pessoa.Pessoa;


public class Menu {
    private static List<Passageiro> passageiros = new ArrayList<>();
    private static List<Voo> voos = new ArrayList<>();   
    public static void menu(){ //cria o menu
        Scanner sc = new Scanner(System.in);
        int escolha = 0;

        while (escolha != 6) { // continua em loop até o usuario digitar para sair            
            System.out.printf("\n___________________________________\n               MENU                      \n¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨\n");
            System.out.printf("1. Reservar voo\n");
            System.out.printf("2. Cancelar reserva\n");
            System.out.printf("3. Verificar reservas\n");
            System.out.printf("4. Cadastrar Passageiro\n");
            System.out.printf("5. Sair\n");
            System.out.printf("Escolha uma opção (1-5): ");
            escolha = sc.nextInt();
            sc.nextLine(); // Consumir a nova linha

            switch (escolha) {
                case 1:
                    fazerReserva(sc);
                    break;
                case 2:
                    cancelarReserva(sc);
                    break;
                case 3:
                    verificarReservas(sc);
                    break;
                case 4:
                    cadastrarPassageiro(sc);
                    break;
                case 5:
                    System.out.printf("\nSaindo...\n");
                    return;
                default:
                    System.out.printf("\nOpção inválida. Tente novamente.\n");
            }
        }
        sc.close();

    }
//**********************************************************************************************************************************************************************************

    public static void fazerReserva(Scanner sc) { // função para fazer a reserva
        String CPF;
        int escolha = -1;
        // pede as informações necessárias, origem, destino e data
        System.out.printf("\nDigite a origem da viagem(cidade-UF): ");
        String origem = sc.nextLine().toUpperCase();
        System.out.printf("Digite o destino da viagem(cidade-UF): ");
        String destino = sc.nextLine().toUpperCase();
        System.out.printf("Digite a data da viagem(dd-mm-yyyy): ");
        String dataStr = sc.nextLine().toUpperCase();
        LocalDate data = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        Voo voo = Voo.verificarVoo( origem, destino,  data, voos); // verifica se o Voo já existe, se não cria e imprime as ionformações do voo

        System.out.println("\nGostaria de dar continuidade com a reserva da cadeira?\n1-sim \n0-não");
        
        escolha = obterEscolhaValida(sc,0,1);

        if(escolha == 1){ //sim
            System.out.println("\nDigite o seu CPF(xxxxxxxxxxx):");
            CPF = sc.nextLine().toUpperCase();
            while(CPF.length() != 11){ // continua até o usuario  digitar os 11 digitos
                System.out.println("\nNúmeros de caracteres invalido.\nDigite o seu CPF completo(xxxxxxxxxxx):");
                CPF = sc.nextLine().toUpperCase();
            }

            Passageiro passageiroExistente = Passageiro.buscarPassageiroCadastrado(CPF, passageiros); // verifica se o passageiro já está cadastrado
            if (passageiroExistente == null) { //Passageiro não está cadastrado 
                System.out.println("\nCPF não cadastrado");
                System.out.println("Deseja cadastradar um novo passageiro?\n1-sim \n0-não");
                escolha = obterEscolhaValida(sc,0,1);

                if( escolha == 1){// sim, quer cadastrar um novo passageiro
                    System.out.println("\nDigite o nome para o CPF informado: ");
                    String nome = sc.nextLine().toUpperCase();
                    passageiroExistente = new Passageiro(nome,CPF);
                    passageiros.add(passageiroExistente);
                    
                }else{ // não quer cadastrar, volta ao menu
                    System.out.println("Retornando ao menu...");
                    return;
                }
            }
            // Passageiro já  cadastrado 
            voo.getDetalhesVoo();
            voo.aviao();
            System.out.printf("\nDigite o número da cadeira para reservar (X-reservadas): ");
            int numeroCadeira = sc.nextInt();
            while(numeroCadeira>=20 || numeroCadeira < 0){ // continua o loop até o usuario inserir uma das opções validas
                System.out.println("\nNumero de cadeira invalida. Tente novamente.");
                numeroCadeira = sc.nextInt();
            }      

            if(!voo.getCadeirasReservadas()[numeroCadeira]){ // verifica se a cadeira está reservada
                Reserva reserva = new Reserva(passageiroExistente, voo, data, numeroCadeira );
                voo.reservarCadeira(numeroCadeira);
                passageiroExistente.adicionarReserva(reserva);
            }else{
                System.out.println("\nCadeira "+numeroCadeira+" já reservada.");
            }
        }
            System.out.println("Retornando ao menu...");
        
    }
//**********************************************************************************************************************************************************************************

    public static void cancelarReserva(Scanner sc){ //Função para cancelar a reserva
        
        System.out.println("\nDigite o seu CPF(xxxxxxxxxxx):");
        String CPF = sc.nextLine().toUpperCase();
        while(CPF.length() != 11){
            System.out.println("\nNúmeros de caracteres invalido.\nDigite o seu CPF completo(xxxxxxxxxxx):");
            CPF = sc.nextLine().toUpperCase();
        }

        Passageiro passageiroExistente = Passageiro.buscarPassageiroCadastrado(CPF, passageiros);// verificar se o Passageiro está cadastrado
        if (passageiroExistente == null) { //não está cadastrado 
            System.out.println("CPF não cadastrado");
            System.out.println("Retornando ao menu...");
            return;
        }

        passageiroExistente.mostraDadosPassageiro(CPF,passageiros); //imprime os dados do passageiro e as resevas
        if(passageiroExistente.getReservas().isEmpty()){ // se não tiver reserva volta ao meno, já que não tem reservas a ser canceladas
            System.out.println("\nRetornando ao menu...");
            return;
        }

        System.out.printf("Digite o numero da reserva que deseja cancelar: ");
        String idReserva = sc.nextLine().toUpperCase();
        
        Reserva R = passageiroExistente.voltaReserva(idReserva); // verifica se esse passageiro tem uma reserva com o ID informado
        if(R != null){ // se tiver cancela a reserva
            Voo voo = R.getVoo();
            voo.cancelarCadeira(R.getNumCadeira());
            passageiroExistente.cancelarReserva(idReserva);
        }
        // se não tiver volta ao menu
        System.out.println("\nRetornando ao menu...");
        return;


    
    }
//**********************************************************************************************************************************************************************************

    public static void verificarReservas(Scanner sc){ //Função para verificar as reservas
        //Pede as informações necessárias 
        System.out.println("\nDigite o seu CPF(xxxxxxxxxxx):");
        String CPF = sc.nextLine().toUpperCase();
        while(CPF.length() != 11){
            System.out.println("\nNúmeros de caracteres invalido.\nDigite o seu CPF completo(xxxxxxxxxxx):");
            CPF = sc.nextLine().toUpperCase();
        }

        Passageiro passageiroExistente = Passageiro.buscarPassageiroCadastrado(CPF, passageiros); // verificar se o Passageiro está cadastrado
        if (passageiroExistente == null) { //não está cadastrado chama cadastrar passageiro para perguntar se quer cadastrar
            System.out.println("CPF não cadastrado"); 
            cadastrarPassageiro(sc);
            return;
        }
        passageiroExistente.mostraDadosPassageiro(CPF,passageiros);// se estiver cadastrado mosta os dados do passageiro, junto as reservas
        System.out.println("\nRetornando ao menu...");
            return;
    }
//**********************************************************************************************************************************************************************************

    public static void cadastrarPassageiro(Scanner sc){ //Função para cadastrar passageiros
        int escolha;

        System.out.println("\nDeseja cadastradar um novo passageiro?\n1-Sim \n0-Voltar para menu");
   
        escolha = obterEscolhaValida(sc,0,1);
      

        if( escolha == 1){// sim quer cadastrar
            System.out.println("\nDigite o seu CPF(xxxxxxxxxxx):");
            String CPF = sc.nextLine().toUpperCase();
            while(CPF.length() != 11){
                System.out.println("\nNúmeros de caracteres invalido.\nDigite o seu CPF completo(xxxxxxxxxxx):");
                CPF = sc.nextLine().toUpperCase();
            }

            Passageiro passageiroExistente = Passageiro.buscarPassageiroCadastrado(CPF, passageiros);// verificar se o Passageiro está cadastrado
            if (passageiroExistente != null) { //Passageiro já cadastrado 
                System.out.println("\n------------Passageiro já Cadastrado-----------" );

            }else{ //Passageiro não cadastrado
                System.out.println("\nDigite o nome para o CPF informado: ");
                String nome = sc.nextLine().toUpperCase();
                passageiroExistente = new Passageiro(nome,CPF);
                passageiros.add(passageiroExistente);
                System.out.println("\n------------Novo passageiro cadastrado-----------" );
            }
          
            System.out.println("Nome: " + passageiroExistente.getNome());
            System.out.println("CPF: " +  passageiroExistente.mostraCpf());         
            
        }
            System.out.println("\nRetornando ao menu...");
            return;
    }

    
//**********************************************************************************************************************************************************************************

    public static int obterEscolhaValida(Scanner sc, int min, int max) {
        int escolha;
        do {
            escolha = sc.nextInt();
            sc.nextLine(); // Limpa o buffer
            if (escolha < min || escolha > max) {
                System.out.println("Opção inválida. Tente novamente.");
            }
        } while (escolha < min || escolha > max);
        return escolha;
    }

}
