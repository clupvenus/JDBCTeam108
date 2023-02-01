import java.sql.*;

public class Deneme2Execute_ExcequeteUpdate {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
         /*
    A) CREATE TABLE, DROP TABLE, ALTER TABLE gibi DDL ifadeleri icin sonuc kümesi (ResultSet)
       dondurmeyen metotlar kullanilmalidir. Bunun icin JDBC'de 2 alternatif bulunmaktadir.
        1) execute() metodu - boolean dondurur.
        2) executeUpdate() metodu - int deger dondurur.
    B) - execute() metodu her tur SQL ifadesiyle kullanilabilen genel bir komuttur.
       - execute(), Boolean bir deger dondurur. DDL islemlerinde false dondururken,
         DML islemlerinde true deger dondurur.
       - Ozellikle, hangi tip SQL ifadesine hangi metodun uygun oldugunun bilinemedigi
         durumlarda tercih edilmektedir.
    C) - executeUpdate() metodu ise INSERT, Update gibi DML islemlerinde yaygin kullanilir.
       - bu islemlerde islemden etkilenen satir sayisini dondurur.
       - Ayrıca, DDL islemlerinde de kullanilabilir ve bu islemlerde 0 dondurur.
    */
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "1234");
        Statement st = con.createStatement();

        /*======================================================================
		                ORNEK1: insanlar tablosunu siliniz.
 	    ========================================================================*/
       /*  String dropQuery="drop table isciler2";
        boolean sonuc=st.execute(dropQuery);
        System.out.println("DDl islemlerde sonuc false ise silmistir, sonuc:"+sonuc);*/
       /* if (!st.execute(dropQuery)) {//if in icine girmesi icin false olan sonucu true ya cevirdik,yani sonuc false ise "isciler tablosu silinmis"
            System.out.println("Isciler tablosu silindi!");
        }
        bu hocanin cozumu sonuc false olmali onun tersi true ve boylece artik if in icine giriliyor.
        ve sout u yazabiliyor.
        */


        /*=======================================================================
          ORNEK2: isciler adinda bir tablo olusturunuz id int,
          birim VARCHAR(10), maas int
	    ========================================================================*/
/*
       String createQuery= "create table isciler2(id int, birim VARCHAR(10), maas int)";
        System.out.println("DDl islemlerde sonuc false ise islem gerceklesmitir, sonuc:"+st.execute(createQuery));
*/
        /*=======================================================================
		  ORNEK3: isciler tablosuna yeni bir kayit (80, 'ARGE', 4000)
		  ekleyelim.
		========================================================================*/
     /*  String insertQuery= "insert into isciler2 values (80, 'ARGE', 4000)";

        System.out.println("bu islemlerde islemden etkilenen satir sayisini dondurur. Etkilenen satir sayisi: "+st.executeUpdate(insertQuery));
*/
//bu islemlerde islemden etkilenen satir sayisini dondurur. Etkilenen satir sayisi: 1

/*=======================================================================
	      ORNEK4: isciler tablosuna birden fazla yeni kayıt ekleyelim.
	        INSERT INTO isciler VALUES(70, 'HR', 5000)
            INSERT INTO isciler VALUES(60, 'LAB', 3000)
            INSERT INTO isciler VALUES(50, 'ARGE', 4000)
	     ========================================================================*/

            String[] queries={"INSERT INTO isciler2 VALUES(71, 'HR', 5000)",
                    "INSERT INTO isciler2 VALUES(60, 'LAB', 3000)",
                    "INSERT INTO isciler2 VALUES(50, 'ARGE', 4000)"};
                   int count=0;
                  for (String each:queries) {
                      count+= st.executeUpdate(each);


                  }

        System.out.println(count+" satir eklendi.");

        // 2.YONTEM (addBatch ve executeBatch() metotlari ile)
        // ----------------------------------------------------
        // addBatch metodu ile SQL ifadeleri gruplandirilabilir ve executeBatch()
        // metodu ile veritabanina bir kere gonderilebilir.
        // executeBatch() metodu bir int [] dizi dondurur. Bu dizi her bir ifade sonucunda
        // etkilenen satir sayisini gosterir.
        String[] queries2={"INSERT INTO isciler2 VALUES(10, 'gHR', 15000)",
                "INSERT INTO isciler2 VALUES(20, 'gLAB', 13000)",
                "INSERT INTO isciler2 VALUES(30, 'gARGE', 14000)"};
        for (String each:queries2
             ) {
            st.addBatch(each);

        }
        st.executeBatch();

         /*=======================================================================
	      ORNEK5: isciler2 tablosunu goruntuleyelim.

	     ========================================================================*/
        String selectQuery3="select * from isciler2";

        ResultSet isciler2= st.executeQuery(selectQuery3);

        while(isciler2.next()){
            System.out.println(isciler2.getInt("id")+isciler2.getString("birim")+isciler2.getInt("maas"));
        }

        /*=======================================================================
		  ORNEK6: isciler tablosundaki maasi 5000'den az olan iscilerin maasina
		   %10 zam yapiniz.
		========================================================================*/
         String zam="update isciler2 set maas=maas*1.1 where maas<5000";
        int etkilenSatir= st.executeUpdate(zam);
        System.out.println("Etkilenen satir sayisi : "+etkilenSatir);


        /*=======================================================================
	      ORNEK7: isciler tablosunun son halini goruntuleyin.
	     ========================================================================*/
          ResultSet iscilerTablosu= st.executeQuery(selectQuery3);
        while(iscilerTablosu.next()){
            System.out.println(iscilerTablosu.getInt("id")+iscilerTablosu.getString("birim")+iscilerTablosu.getInt("maas"));
        }
    }
}
