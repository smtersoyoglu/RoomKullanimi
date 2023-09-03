package com.sametersoyoglu.roomkullanimi

import androidx.room.*

@Dao
interface KisilerDao {
    // Dao interface'i Veritabanı üzerindeki tablolarımızda yapacağımız işlemleri temsil eden sınıf.Örn; Silme, Ekleme vb işlemleri yaptığımız yer.
    // veritabanı üzerinde bizim sorgular oluşturmamızı sağlayan yerdir bu sorgularla veritabanı üzerinde işlemler yaparız.


    @Query("SELECT * FROM kisiler")
    suspend fun tumKisiler() : List<Kisiler>
    // veritabanımızdaki bütün bilgileri almak için sorgu oluşturduk.


    // veritabanına ekleme kodu  Insert ile de kayıt yapıcak diyoruz
    @Insert
    suspend fun kisiEkle(kisiler: Kisiler)


    // veritabanında güncelleme işlemini yapma
    @Update
    suspend fun kisiGuncelle(kisiler: Kisiler)


    // veritabanından silme işlemi
    @Delete
    suspend fun kisiSil(kisiler: Kisiler)

    //veritabanındaki bilgileri rastgele ve sınırlı sayıda getirme işlemi
    @Query("SELECT * FROM kisiler ORDER BY RANDOM() LIMIT 1")
    suspend fun rastgele1KisiGetir() : List<Kisiler>

    // Veritabanında Arama işlemi
    @Query("SELECT * FROM kisiler WHERE kisi_ad like '%' || :aramaKelimesi || '%'")
    suspend fun kisiAra(aramaKelimesi:String) : List<Kisiler>


    // veritabanından istediğimiz kaydı getirme işlemi KAYIT GETİRME id ler ile kaydı getirme
    @Query("SELECT * FROM kisiler WHERE kisi_id=:kisi_id")
    suspend fun kisiGetir(kisi_id:Int) : Kisiler

    // Kayıt kontrol işlemi veritabanında bir kaydın kaç tane olduğunu sorgulama
    @Query("SELECT count(*) FROM kisiler WHERE kisi_ad=:kisi_ad")
    suspend fun kayitKontrol(kisi_ad:String) : Int
}