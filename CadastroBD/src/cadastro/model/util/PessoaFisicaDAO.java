package cadastro.model.util;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaDAO {
    private ConecotrBD conecotrBD;

    public PessoaFisicaDAO() {

        this.conecotrBD = new ConecotrBD();

    }

    public PessoaFisica getPessoa(int id){
        String sql = """
                SELECT * FROM PESSOA AS P
                INNER JOIN Pessoa_fisica F ON P.idPessoa = F.idPessoa
                WHERE P.idPessoa = %d  
                """.formatted(id);

        PessoaFisica pessoaFisica;

        try {
            this.conecotrBD.getPrepared(sql);
            ResultSet res = this.conecotrBD.getSelect();
            res.next();
            pessoaFisica = new PessoaFisica(
                    res.getInt("idPessoa"),
                    res.getString("nome"),
                    res.getString("logradouro"),
                    res.getString("cidade"),
                    res.getString("estado"),
                    res.getString("telefone"),
                    res.getString("email"),
                    res.getString("cpf")
            );
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        this.conecotrBD.fechar();
        return pessoaFisica;
    }

    public List<PessoaFisica> getPessoas(){
        String sql = """
                SELECT * FROM PESSOA AS P
                INNER JOIN Pessoa_fisica F ON P.idPessoa = F.idPessoa
                """;

        List<PessoaFisica> pessoasFisicas = new ArrayList<>();

        try {
            this.conecotrBD.getPrepared(sql);
            ResultSet res = this.conecotrBD.getSelect();
            while(res.next()){
                pessoasFisicas.add(
                        new PessoaFisica(
                                res.getInt("idPessoa"),
                                res.getString("nome"),
                                res.getString("logradouro"),
                                res.getString("cidade"),
                                res.getString("estado"),
                                res.getString("telefone"),
                                res.getString("email"),
                                res.getString("cpf")
                        )
                        );
            }


        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        this.conecotrBD.fechar();
        return pessoasFisicas;
    };

    public void incluir(PessoaFisica pessoaFisica){
        String sql = String.format(""" 
            BEGIN TRANSACTION
            INSERT INTO Pessoa(nome, logradouro, cidade, estado, telefone, email)
            VALUES
            ('%s', '%s', '%s', '%s', '%s', '%s')
            INSERT INTO Pessoa_fisica(idPessoa, cpf)
            VALUES
            ((SELECT TOP(1) SCOPE_IDENTITY() FROM Pessoa), '%s')
    
            IF @@ERROR=0
            COMMIT
                    ELSE
            ROLLBACK
            """, pessoaFisica.getNome(),pessoaFisica.getLogradouro(),pessoaFisica.getCidade(),pessoaFisica.getEstado(),pessoaFisica.getCelular(),pessoaFisica.getEmail(),pessoaFisica.getCpf());

        this.conecotrBD.getPrepared(sql);
        this.conecotrBD.executar();
    };
    public void excluir(int id){
        String sql = String.format(
                """
            BEGIN TRANSACTION
            delete from Pessoa_fisica
            where idPessoa = %d
            
            delete from Pessoa
            where idPessoa = %d
    
            IF @@ERROR=0
            COMMIT
                    ELSE
            ROLLBACK
                        """, id,id);
        this.conecotrBD.getPrepared(sql);
        this.conecotrBD.executar();

    };

    public void alterar(int id, PessoaFisica pessoaFisica){
        String sqlQuery = String.format("""
                    BEGIN TRANSACTION

                    UPDATE Pessoa_fisica
                    SET cpf = '%s'
                    WHERE idPessoa = %d

                    UPDATE Pessoa
                    SET
                    nome = '%s',
                    logradouro = '%s',
                    cidade = '%s',
                    estado = '%s',
                    telefone = '%s',
                    email = '%s'
                    WHERE idPessoa = %d


                    IF @@ERROR=0
                    COMMIT
                    ELSE
                    ROLLBACK
                    """,
                pessoaFisica.getCpf(),
                id,
                pessoaFisica.getNome(),pessoaFisica.getLogradouro(), pessoaFisica.getCidade(), pessoaFisica.getEstado(), pessoaFisica.getCelular(), pessoaFisica.getEmail(), id);

                this.conecotrBD.getPrepared(sqlQuery);
                this.conecotrBD.executar();
    };


}


