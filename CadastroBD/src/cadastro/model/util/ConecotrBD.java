package cadastro.model.util;

import java.sql.*;

public class ConecotrBD {

    private String url = "jdbc:sqlserver://localhost:1433;database=Loja2;user=loja;password=loja;encrypt=true;trustServerCertificate=true;loginTimeout=10";
    private Connection con;
    private PreparedStatement prepared;

    public ConecotrBD(){
       try {
           this.con = DriverManager.getConnection(this.url);
       } catch (SQLException e){
           throw new RuntimeException(e);
       }
    }

    public Connection getConnection() {
        return con;
    }

    public PreparedStatement getPrepared(String sql) {
        try {
            this.prepared = con.prepareStatement(sql);
            return this.prepared;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public ResultSet getSelect(){
        try {
            return prepared.executeQuery();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void fechar (){
        try {
            this.prepared.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void executar(){
        try {
            prepared.execute();
            prepared.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    };
}
