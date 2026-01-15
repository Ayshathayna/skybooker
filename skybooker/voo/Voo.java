package trabalho.voo;

import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Voo{ // Classe que representa os Voos
    private String numeroVoo;
	private String origem;
	private String destino;
	private LocalDate data;
	private int quantCadeirasLivres; 
    private int quantCadeirasReservadas;
	private boolean[] cadeirasReservadas;// Lista de cadeiras Reservadas
	private double valor; 

	public Voo( String origem, String destino, LocalDate data) { //Cria o Objeto Voo
	    this.numeroVoo = gerarNumeroVoo(origem, destino, data); //Utiliza o metodo para gerar o Numero do Voo
		this.origem = origem;
		this.destino = destino;
		this.data = data;
		this.quantCadeirasLivres = 20;
        this.quantCadeirasReservadas = 0;
		this.cadeirasReservadas = new boolean[quantCadeirasLivres]; //Inicializa o array com a quantidade de cadeiras
        this.valor = criaValor(origem, destino); //Utiliza o metodo para criar o valor
	}

    public String gerarNumeroVoo(String origem, String destino, LocalDate data) { //Gera o numero automaticamente utilizando:
        StringBuilder numeroVoo = new StringBuilder();   //StringBuilder permite modificar(concatenar) a string sem criar novos
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("ddMMyy"); // Define o formato de data como "ddMMyy" 
        String dataFormatada = data.format(format1);  //Utiliza o formato criado

        numeroVoo.append(origem.split("-")[1].trim().toUpperCase());  // Extrai e adiciona a UF da origem para criar o numVoo
        numeroVoo.append(dataFormatada); // adiciona a data do voo
        numeroVoo.append(destino.split("-")[1].trim().toUpperCase());// Extrai e adiciona a UF do destino para criar o numVoo
       
        return numeroVoo.toString(); //converte em string de novo e retorna o numero do voo
    }
    public double criaValor(String origem, String destino){  //Gera o valor automaticamente
        int valorOrigem = lerEstados(origem.split("-")[1].trim());  //separa a UF e procura dentro do arquivo para indentificar o numero correspondente
        int valorDestino = lerEstados(destino.split("-")[1].trim());
        int diferenca = valorDestino - valorOrigem; // diferença é utilizada como medida de distancia para alterar o valor (- +)

        if (diferenca == 0) { // Estados proximos ou no mesmo estado
            return 1000.00;
        } else if (Math.abs(diferenca) == 1) {  //Math.abs para interpretar 1 e -1 como o mesmo
            return 1100.00;
        } else if (Math.abs(diferenca) == 2) {
            return 1200.00;
        } else if (Math.abs(diferenca) == 3) {
            return 1300.00;
        } else if (Math.abs(diferenca) == 4) {
            return 1400.00;
        } else if (Math.abs(diferenca) == 5) { // Estados mais distantes
            return 1500.00;
        } else {
            return 1600.00;
        }
    }
    public int lerEstados(String sigla) { //Lê o arquivo e retorna o o numero correspondente
        try (BufferedReader reader = new BufferedReader(new FileReader("estados.txt"))) { // Buff le o arquivo linha por linha reader abre o arquivo
            String linha;
            while ((linha = reader.readLine()) != null) { //lê a linha até o fim do arquivo (null)
                String[] partes = linha.split(" "); // separa a linha em 2
                String estadoSigla = partes[0].trim();//sigla
                int valor = Integer.parseInt(partes[1].trim());//valor

                if (estadoSigla.equals(sigla)) {
                    return valor; // Retorna o valor correspondente à sigla
                }
            }
        } catch (IOException e) { //a exceção é "tratada" 
            System.out.println("\nErro ao ler o arquivo estados.txt: " + e.getMessage());
        }
        return -1; // Retorna -1 se a sigla não for encontrada
    }

//**********************************************************************************************************************************************************************************

    public static Voo verificarVoo(String origem,String destino, LocalDate data,List<Voo> voos){ // static por que consigo puxar de outra classe sem criar o objeto
        // verifica se o voo com a mesma data, origem e destino já existe, se não, cria.
        for (Voo v : voos) { 
            if (v.getOrigem().equals(origem) && v.getDestino().equals(destino) && v.getData().equals(data)) {
                v.getDetalhesVoo();// se existe, imprime as informações e retorna o voo
                return v;
            }
        }
        Voo novoVoo = new Voo( origem,  destino,  data);// se não cria, mostra detalhes e retorna o novo voo
        novoVoo.getDetalhesVoo();
        voos.add(novoVoo);
        return novoVoo; 

    }
	
	public void reservarCadeira(int numeroCadeira){ //verica se a cadeira está reservada 
        if(numeroCadeira<0 || numeroCadeira >= 20){
            System.out.println("\nNúmero de cadeira inválida!");
            return;
        }
        if(!getCadeirasReservadas()[numeroCadeira]){
            getCadeirasReservadas()[numeroCadeira] = true; //Reserva a cardeira
            System.out.println("\nCadeira "+ numeroCadeira +" reservada com sucesso");
            setQuantCadeirasReservada(getQuantCadeirasReservadas() + 1);
            setQuantCadeirasLivres(getQuantCadeirasLivres() - 1 );           
        }
    }
    
    public void cancelarCadeira(int numeroCadeira){  // Cancela a reserva da cadeira
        if(numeroCadeira<0 || numeroCadeira >= 20){
            System.out.println("\nNúmero de cadeira inválida!");
            return;
        }
        if(getCadeirasReservadas()[numeroCadeira]){
            getCadeirasReservadas()[numeroCadeira] = false;//Cancela a cadeira
            setQuantCadeirasReservada(getQuantCadeirasReservadas() - 1);   
            setQuantCadeirasLivres(getQuantCadeirasLivres() + 1 );
         
        }    
    }   

//**********************************************************************************************************************************************************************************
    
    public void getDetalhesVoo(){ //Imprime os detalhes do voo
        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("\n-----------Voo encontrato-----------");
        System.out.println("\nOrigem: "+ getOrigem());
        System.out.println("Destino: "+ getDestino());
        System.out.println("Data: "+ this.data.format(format2));
        System.out.printf("Quantidade de cadeiras livres: %d\n", getQuantCadeirasLivres());

        System.out.println("Valor: R$"+ getValor());
    }
    public void aviao(){ // imprime o avião e as cadeiras disponiveis/reservadas
        System.out.println("                    __|__");
        System.out.println("             --------(_)--------");
        System.out.println("                   /    \\ ");
        System.out.println("                  /      \\ ");
        System.out.println("                 /        \\ ");
        System.out.println("                /__________\\");
        System.out.printf(" _______________|%s  %s | %s  %s|_______________\n", cadeirasReservadas[0] ? "X" : "O", cadeirasReservadas[1] ? "X" : "1",
        cadeirasReservadas[2] ? "X" : "2", cadeirasReservadas[3] ? "X" : "3");
        System.out.printf(" \\              |%s  %s | %s  %s|              /\n",cadeirasReservadas[4] ? "X" : "4", cadeirasReservadas[5] ? "X" : "5",
        cadeirasReservadas[6] ? "X" : "6", cadeirasReservadas[7] ? "X" : "7");
        System.out.printf("   \\            |%s  %s |%s %s|            /\n",cadeirasReservadas[8] ? "X" : "8", cadeirasReservadas[9] ? "X" : "9",
        cadeirasReservadas[10] ? " X" : "10", cadeirasReservadas[11] ? " X" : "11");
        System.out.printf("      \\ ________|%s %s|%s %s|_________/\n",cadeirasReservadas[12] ? "X " : "12", cadeirasReservadas[13] ? "X " : "13",
        cadeirasReservadas[14] ? " X" : "14", cadeirasReservadas[15] ? " X" : "15");
        System.out.printf("                |%s %s|%s %s|\n",cadeirasReservadas[16] ? "X " : "16", cadeirasReservadas[17] ? "X " : "17",
        cadeirasReservadas[18] ? " X" : "18", cadeirasReservadas[19] ? " X" : "19");
        System.out.println("                 |         |");
        System.out.println("                  |       |");
        System.out.println("                   |     |");
        System.out.println("                    |___|");
        System.out.println("                 ----||----");    
    
    }

    public String getNumeroVoo() {
        return numeroVoo;
    }
    public double getValor() {
        return valor;
    }
    public String getOrigem() {
        return origem;
    }
    public String getDestino() {
        return destino;
    }
    public LocalDate getData() {
        return data; 
    }
    public int getQuantCadeirasLivres() {
        return quantCadeirasLivres;
    }
    public int getQuantCadeirasReservadas() {
        return quantCadeirasReservadas;
    }
    public boolean[] getCadeirasReservadas() {
        return cadeirasReservadas;  // Retorna o array inteiro
    }


    public void setValor(double valor) {
        this.valor = valor;
    }
    public void setQuantCadeirasLivres(int valor) {
        this.quantCadeirasLivres = valor;
    }
    public void setQuantCadeirasReservada(int valor) {
        this.quantCadeirasReservadas = valor;
    }
    
}