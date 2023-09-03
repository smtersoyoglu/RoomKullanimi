package com.sametersoyoglu.roomkullanimi

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Kisiler::class], version = 1)
abstract class Veritabani : RoomDatabase() {
    // abstract class yapmak zorundayız interface özelliği olan bir sınıf yapmak için
    // Veritabani erişim sınıfı : room veritabanına erişmek için oluşturduğumuz sınıf bu sınıf hem erişim hem de kopyalama işlemi yapıcak. veritabanımızı temsil eden sınıftır.
    // bu sınıf sayesinde veritabanı üzerinde işlemler yapabiliriz.

    // KisilerDao interface'ine erişmek için fonksiyon tanımladık
    abstract fun getKisilerDao() : KisilerDao

    // veritabanına erişme işlemi - sınıf ismine erişeceğimiz bir nesne oluşturucaz
    companion object {
        var INSTANCE: Veritabani? = null
        // bu bizim erişim için aldığımız nesne olucak bunu kullanıcağız

        fun veritabaniErisim(context: Context) : Veritabani? {
            if (INSTANCE == null) {
                // veritabanına erişeceğimiz için bunu sekronize bir bicimde yapması için synchronized kullanırız.
                synchronized(Veritabani::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,Veritabani::class.java,"rehber.sqlite")
                        .createFromAsset("rehber.sqlite").build()
                    // ilk satırda veritabanına erişme işlemini , .createFromAsset("rehber.sqlite").build() bölümündede kopyalama işlemini yaptık. uygulama ilk çalıştığında 1 kere kopyalama yapıcak daha sonra erişmeye başlıcak
                }

            }
            return INSTANCE
        }

    }


}