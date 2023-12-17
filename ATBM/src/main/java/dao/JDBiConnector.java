//package dao;
//
//import com.mysql.cj.jdbc.MysqlDataSource;
//import org.jdbi.v3.core.Jdbi;
//
//import java.sql.SQLException;
//
//public class JDBiConnector {
//    static Jdbi jdbi;
//
//    public static Jdbi me() {
//        if (jdbi == null) makeConnect();
//        return jdbi;
//    }
//
//    private static void makeConnect() {
//        try {
//            MysqlDataSource source = new MysqlDataSource();
//            source.setURL("jdbc:mysql://" + DBProperties.host() + ":" + DBProperties.port() + "/" + DBProperties.name());
//            source.setUser(DBProperties.user());
//            source.setPassword(DBProperties.pass());
//            source.setAutoReconnect(true);
//            source.setUseCompression(true);
//            jdbi= Jdbi.create(source);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static void main(String[] args) {
////        Jdbi me = JDBiConnector.me();
////        List<Product> products = me.withHandle(
////                handle -> {
////                    return handle.createQuery("select  * from products").mapToBean(Product.class)
////                    .stream().collect(Collectors.toList());
////                }
////        );
////        System.out.println(products);
//    }
//}
