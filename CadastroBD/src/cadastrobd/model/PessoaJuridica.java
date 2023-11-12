package cadastrobd.model;

public class PessoaJuridica extends Pessoa{

    public String cnpj;

    public PessoaJuridica(int id, String nome, String logradouro, String cidade, String estado, String celular, String email, String cnpj) {
        super(id, nome, logradouro, cidade, estado, celular, email);
        this.cnpj = cnpj;
    }

    @Override
    public void exibir() {
        super.exibir();

        System.out.println("CNPJ: " + this.cnpj);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
