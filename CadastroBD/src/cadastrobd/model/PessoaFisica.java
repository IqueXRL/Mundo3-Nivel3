package cadastrobd.model;

public class PessoaFisica extends Pessoa{

    private String cpf;

    public PessoaFisica(int id, String nome, String logradouro, String cidade, String estado, String celular, String email, String cpf) {
        super(id, nome, logradouro, cidade, estado, celular, email);
        this.cpf = cpf;
    }

    @Override
    public void exibir() {
        super.exibir();

        System.out.println("CPF: " + this.cpf);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
