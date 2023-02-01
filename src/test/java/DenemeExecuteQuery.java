import java.sql.*;

public class DenemeExecuteQuery {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "1234");
        Statement st=con.createStatement();
         /*=======================================================================
         ORNEK SORU1: Id'si 1'den buyuk firmalarin ismini ve iletisim_isim'ini isim
         ters sirali yazdirin.
        ========================================================================*/
        String selectQuery="select isim,iletisim_isim from firmalar where id>1 order by isim desc";
        ResultSet data=st.executeQuery(selectQuery);
        while(data.next()){
            System.out.println(data.getString(1)+" "+data.getString(2));
        }
        System.out.println("=============================ORNEK 2==========================");
        /*=======================================================================
          ORNEK2: Iletisim isminde 'li' iceren firmalarin id'lerini ve isim'ini
          id sirali yazdirin.
        ========================================================================*/
        String selectQuery2="select id,isim from firmalar where iletisim_isim like '%li%' order by id";
        ResultSet veri=st.executeQuery(selectQuery2);
        while (veri.next()){
            System.out.println(veri.getString(1)+" "+veri.getString(2));
        }
        con.close();
        st.close();
        data.close();
        veri.close();


    }
}
