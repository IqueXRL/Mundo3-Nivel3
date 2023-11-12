import java.sql.*;

public class Teste {
    private Connection conexao;
    private PreparedStatement prepared;

    public void teste(){
        String url = "jdbc:sqlserver://localhost:1433;database=Loja2;user=loja;password=loja;encrypt=true;trustServerCertificate=true;loginTimeout=10;";
        try {
            this.conexao = DriverManager.getConnection(url);
             ResultSet resultado = conexao.prepareStatement("select * from Pessoa").executeQuery();
             while(resultado.next()){
                 System.out.println(resultado.getString("nome"));

             };

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    };
}
