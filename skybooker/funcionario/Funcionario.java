package trabalho.funcionario;

import trabalho.passageiro.Passageiro;
import trabalho.pessoa.Pessoa;
import trabalho.reserva.Reserva;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Funcionario extends Pessoa {
	private int numCadastro; // desconto de aniversario?
	private List<Reserva> reservas = new ArrayList<>();
	public Funcionario(String nome, String cpf, int numeroCadastro) {
		super(nome, cpf);
		this.numCadastro = numeroCadastro;
	}
	public boolean mostraDadosFuncionario(String cpf, List<Funcionario> funcionarios){
        for (Funcionario f : funcionarios) {
            if (f.getCpf().equals(cpf)) {
                System.out.println("Nome: " + f.getNome());
                System.out.println("CPF: " + f.getCpf());
                System.out.println("\nReservas: ");
                f.getReservaFunc
				(); // Imprime as reservas
                return true;
				
              
            }
        }
        System.out.println("Passageiro não encontrado com CPF: " + cpf);
        return false;
    }
	
    public void getReservaFunc() {
        if (reservas.isEmpty()) {
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
	public void adicionarReserva(Reserva reserva) {
		reservas.add(reserva);
	}
	public int getNumCadastro() {
        return numCadastro;
    }

    public void setNumCadastro(int numCadastro) {
        this.numCadastro = numCadastro;
    }
}