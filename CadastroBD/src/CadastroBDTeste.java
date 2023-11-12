import cadastro.model.util.PessoaFisicaDAO;
import cadastro.model.util.PessoaJuridicaDAO;
import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;

public class CadastroBDTeste {
    public static void main(String[] args) {
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();

        PessoaFisica pessoaFisica = new PessoaFisica(
                22,
                "Caique",
                "Rua das ruas",
                "Valinhos",
                "SP",
                "(01) 2222-4444",
                "@caique.com",
                "000.111.222.33");

        pessoaFisicaDAO.incluir(pessoaFisica);
        pessoaFisicaDAO.getPessoas().forEach((PessoaFisica p) -> {p.exibir();});
        pessoaFisicaDAO.excluir(1);

        PessoaJuridica pessoaJuridica = new PessoaJuridica(
                44,
                "Bait",
                "Rua da gralha azul",
                "Sousas",
                "SP",
                "(02) 4444-5555",
                "@bait.com",
                "11.222.333/0001-44"
        );

        pessoaJuridicaDAO.incluir(pessoaJuridica);
        pessoaJuridicaDAO.getPessoas().forEach((PessoaJuridica p) -> {p.exibir();});
        pessoaJuridicaDAO.excluir(2);
    }
}
