import java.sql.*;

public class JDBC01_Querty01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1- ilgili Driveri yuklemeliyiz.Mysql kullandigimizi bildiriyoruz.
        // Driveri bulamama ihtimaline karsi bizden forName methodu icin
        //ClassNotFoundException method signature una exception olarak firlatmamizii istiyor.


        Class.forName("com.mysql.cj.jdbc.Driver");

        //2-Baglantiyi olusturmak icin username ve paswoord girmeliyiz.
        //Burada da user name ve paswoordun yanlis olma ihtimaline karsi
        //bir sql exception firlatmamizi istiyor.

        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "1234");

       //3-SQl query leri icin bir Statement objesi olusturup javada sql sorgulariniz icin bir alan sececegiz

        Statement st=con.createStatement();

        //4- sql querymizi yazip calistirabiliriz
        ResultSet veri=st.executeQuery("select * from personel");

        //5- Sonuclari gormek icin iteration ile set icerisindeki elemanlari
        //while dongusu ile yazdiriyoruz.

        while(veri.next()){
            System.out.println(veri.getInt(1)+" "+veri.getString(2)+" "+veri.getString(3)
                    +" "+veri.getInt(4)+" "+veri.getString(5));
        }

        //6- Artik olusturulan baglantilari kapatalim.

        con.close();
        st.close();
        veri.close();










    }
}
