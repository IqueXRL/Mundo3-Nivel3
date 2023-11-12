package cadastro.model.util;

import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaJuridica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaDAO {
    private ConecotrBD conecotrBD;

    public PessoaJuridicaDAO() {

        this.conecotrBD = new ConecotrBD();

    }

    public PessoaJuridica getPessoa(int id){
        String sql = """
                SELECT * FROM PESSOA AS P
                INNER JOIN Pessoa_juridica F ON P.idPessoa = F.idPessoa
                WHERE P.idPessoa = %d  
                """.formatted(id);

        PessoaJuridica pessoaJuridica;

        try {
            this.conecotrBD.getPrepared(sql);
            ResultSet res = this.conecotrBD.getSelect();
            res.next();
            pessoaJuridica = new PessoaJuridica(
                    res.getInt("idPessoa"),
                    res.getString("nome"),
                    res.getString("logradouro"),
                    res.getString("cidade"),
                    res.getString("estado"),
                    res.getString("telefone"),
                    res.getString("email"),
                    res.getString("cnpj")
            );
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        this.conecotrBD.fechar();
        return pessoaJuridica;
    }

    public List<PessoaJuridica> getPessoas(){
        String sql = """
                SELECT * FROM PESSOA AS P
                INNER JOIN Pessoa_juridica F ON P.idPessoa = F.idPessoa
                """;

        List<PessoaJuridica> pessoasJuridicas = new ArrayList<>();

        try {
            this.conecotrBD.getPrepared(sql);
            ResultSet res = this.conecotrBD.getSelect();
            while(res.next()){
                pessoasJuridicas.add(
                        new PessoaJuridica(
                                res.getInt("idPessoa"),
                                res.getString("nome"),
                                res.getString("logradouro"),
                                res.getString("cidade"),
                                res.getString("estado"),
                                res.getString("telefone"),
                                res.getString("email"),
                                res.getString("cnpj")
                        )
                );
            }


        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        this.conecotrBD.fechar();
        return pessoasJuridicas;
    };

    public void incluir(PessoaJuridica pessoaJuridica){
        String sql = String.format(""" 
            BEGIN TRANSACTION
            INSERT INTO Pessoa(nome, logradouro, cidade, estado, telefone, email)
            VALUES
            ('%s', '%s', '%s', '%s', '%s', '%s')
            INSERT INTO Pessoa_juridica(idPessoa, cnpj)
            VALUES
            ((SELECT TOP(1) SCOPE_IDENTITY() FROM Pessoa), '%s')
    
            IF @@ERROR=0
            COMMIT
                    ELSE
            ROLLBACK
            """, pessoaJuridica.getNome(),pessoaJuridica.getLogradouro(),pessoaJuridica.getCidade(),pessoaJuridica.getEstado(),pessoaJuridica.getCelular(),pessoaJuridica.getEmail(),pessoaJuridica.getCnpj());

        this.conecotrBD.getPrepared(sql);
        this.conecotrBD.executar();
    };
    public void excluir(int id){
        String sql = String.format(
                """
            BEGIN TRANSACTION
            delete from Pessoa_juridica
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

    public void alterar(int id, PessoaJuridica pessoaJuridica){
        String sqlQuery = String.format("""
                    BEGIN TRANSACTION

                    UPDATE Pessoa_juridica
                    SET cnpj = '%s'
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
                pessoaJuridica.getCnpj(),
                id,
                pessoaJuridica.getNome(),pessoaJuridica.getLogradouro(), pessoaJuridica.getCidade(), pessoaJuridica.getEstado(), pessoaJuridica.getCelular(), pessoaJuridica.getEmail(), id);

        this.conecotrBD.getPrepared(sqlQuery);
        this.conecotrBD.executar();
    };
}
