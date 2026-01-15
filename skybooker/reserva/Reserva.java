package trabalho.reserva;

import trabalho.passageiro.Passageiro;
import trabalho.voo.Voo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Reserva { // Classe que representa a Reserva
	private Passageiro passageiro;  
	private Voo voo;
	private String idReserva; 
	private LocalDate data;
    private int numCadeira;

	public  Reserva( Passageiro passageiro, Voo voo, LocalDate data, int numCadeira) { //Cria a reserva
		this.idReserva = gerarNumeroReserva(voo.getOrigem(), voo.getDestino(), data, numCadeira);
		this.passageiro = passageiro;
		this.voo = voo;
		this.data = data;
        this.numCadeira = numCadeira;
	}
    
        
	public String gerarNumeroReserva(String origem, String destino, LocalDate data, int numCadeira) { //Gera o numero da reserva automaticamente utilizando:
        StringBuilder numeroReserva = new StringBuilder();  //StringBuilder permite modificar(concatenar) a string sem criar novos
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("MMdd"); // Define o formato de data como "MMdd" 
        String dataFormatada = data.format(format1); //Utiliza o formato criado
         
        numeroReserva.append(origem.split("-")[1].trim().toUpperCase()); // Extrai e adiciona a UF da origem para criar o numVoo
        numeroReserva.append(dataFormatada); // Adiciona a data do voo
        numeroReserva.append(destino.split("-")[1].trim().toUpperCase()); // Extrai e adiciona a UF do destino para criar o numVoo
        numeroReserva.append(numCadeira);   // Adiciona o numCadeira    
        return numeroReserva.toString(); //Converte em string de novo e retorna o numero do voo

    }
    
//**********************************************************************************************************************************************************************************


    public String getIdReserva() {
        return idReserva;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public Voo getVoo() {
        return voo;
    }

    public LocalDate getData() {
        return data;
    }
    public int getNumCadeira() {
        return numCadeira; 
    }

	
}
