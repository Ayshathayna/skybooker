package trabalho.passageiro;

import trabalho.pessoa.Pessoa;
import trabalho.reserva.Reserva;
import trabalho.voo.Voo;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Passageiro extends Pessoa {
	private List<Reserva> reservas = new ArrayList<>(); // vai criar varias reservas para o passageiro, não tem tamanho pré definido

	public Passageiro(String nome, String cpf ) { //Cria o Objeto passageiro
		super(nome,  cpf);
		this.reservas = new ArrayList<>();
	}

//**********************************************************************************************************************************************************************************

	public static Passageiro buscarPassageiroCadastrado(String cpf, List<Passageiro> passageiros){ //Verifica se o passageiro está cadastrado de acordo com o CPF
	    
        for (Passageiro p : passageiros) { // Se está cadastrado retorna o passageiro
            if (p.getCpf().equals(cpf)) {
                return p;
            }
        }
        return null;
	}

	public void adicionarReserva(Reserva reserva) { //Adiciona a reserva na lista de reservas do passageiro
		getReservas().add(reserva);
	}

    public void cancelarReserva(String idReserva) { // Cancela a Reserva
        Iterator<Reserva> iterator = reservas.iterator(); // cria um iterador para percorrer a lista

        while (iterator.hasNext()) { //percorre as reservas da lista do passageiro
            Reserva reserva = iterator.next();
            if (reserva.getIdReserva().equals(idReserva) ) {// verifica se o id de Reserva é o mesmo
                iterator.remove();  // Remove a reserva de forma segura
                System.out.println("Reserva cancelada com sucesso.");
                return;
            }
        }
    }

    public Reserva voltaReserva(String idReserva) { // Utilizado como um get reserva, utiliza a lista de resservas do passageiro no main, se o id é o mesmo volta a reserva.
        for (Reserva reserva : reservas) {          // Utilizado para ter mais segurança ao cancelar a reserva, para não cancelar uma reserva que não seja do mesmo cpf.
            if (reserva.getIdReserva().equals(idReserva)) {          
                return reserva;
            }
        }
        System.out.println("Reserva não encontrada para o ID: " + idReserva);
        return null;
    }

//**********************************************************************************************************************************************************************************

	public static void mostraDadosPassageiro(String cpf, List<Passageiro> passageiros){ //Imprime os detalhes do Rassageiro
        for (Passageiro p : passageiros) {
            if (p.getCpf().equals(cpf)) {
                System.out.println("\n------------Passageiro encontrado-----------" );
                System.out.println("Nome: " + p.getNome());
                System.out.println("CPF: " + p.mostraCpf());
                System.out.println("\nReservas: "); 
                p.getReservaPassag(); // Imprime as reservas do passageiro
                return;
            }
        }
        System.out.println("Passageiro não encontrado com CPF: " + cpf);    
    }
	
    public void getReservaPassag() { // imprime as reservas do passageiro de forma completa
        if (reservas.isEmpty()) {  //verifica se a lista está vazia
            System.out.println("Nenhuma reserva encontrada.");
        } else {
            for (Reserva r : reservas) {
                System.out.println("ID Reserva: " + r.getIdReserva());
                System.out.println("Data: " + r.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                System.out.println("Número do Voo: " + r.getVoo().getNumeroVoo());
				System.out.println(""+ r.getVoo().getOrigem()+" --> "+ r.getVoo().getDestino());
        		System.out.println("Poltrona: "+ r.getNumCadeira() );
                System.out.println("-------------------\n");
				
            }
        }
    }
    public List<Reserva> getReservas() {
        return reservas;
    }
    
}

