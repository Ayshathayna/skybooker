package trabalho.pessoa;

import trabalho.passageiro.Passageiro; 
import trabalho.reserva.Reserva; 
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class Pessoa { // Classe que representa o objeto Pessoa
	private String nome;
	private String cpf;
	private List<Reserva> reservas = new ArrayList<>();  // vai criar varias reservas para o passageiro, não tem tamanho pré definido

	public Pessoa(String nome, String cpf) {//Construtor
		this.nome = nome;
		this.cpf = cpf;
	}

//**********************************************************************************************************************************************************************************

	public String getNome() {
		return nome;
	}
    public String getCpf() {
        return cpf;
    }
    
	public String mostraCpf(){ //retorna o CPF de forma formatada(xxx.xxx.xxx-xx)

		if (cpf != null && cpf.length() == 11) {
            // Formata o CPF conforme a máscara: XXX.XXX.XXX-XX
            return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
			// A expressão regular: (\\d{3})(\\d{3})(\\d{3})(\\d{2})
       		// - (\\d{3}): Captura os três primeiros dígitos
        	// - (\\d{3}): Captura os três próximos dígitos
        	// - (\\d{3}): Captura os três próximos dígitos
       		// - (\\d{2}): Captura os dois últimos dígitos
        	// A máscara "$1.$2.$3-$4" usa essas capturas para formatar o CPF
        } else {
            // Se o CPF não tiver 11 caracteres, retorna uma mensagem de erro
            return "CPF inválido";
        }
	}
}