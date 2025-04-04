package com.mycompany.estadoecidade.visualizacao;
import org.flywaydb.core.Flyway;
import org.hibernate.Session;
import com.mycompany.estadoecidade.Util.HibernateUtil;

public class Estadoecidade {
    public static void main(String[] args) {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:h2:~/cidadeestado", "sa", "")
                .load();
        flyway.migrate();
        
        try (Session session = HibernateUtil.pegarSessionFactory().openSession()) {
            System.out.println("✅ Conexão com H2 Database estabelecida com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
