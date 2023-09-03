package com.sametersoyoglu.roomkullanimi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var vt: Veritabani
    private lateinit var kdao : KisilerDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // veritabanına erişmemizi sağlayan kodu tanımlıyoruz.
        vt = Veritabani.veritabaniErisim(this)!!

        //  veritabanı sınıfı aracılığıyla interface'i vericek bu sayede KisilerDao daki bütün fonksiyonlara erişmemizi sağlar. -interface'e erişme kodu ve bundan sonra interface ile işlemleri yapıcaz sık sık
        kdao = vt.getKisilerDao()
        //kdao.tumKisiler() gibi fonksiyonlara erişebiliyoruz

        //kisileriYukle()
        //ekle()
        //guncelle()
        //sil()
        //rastgele()
        //ara()
        //getir()
        kontrol()

    }

    fun kisileriYukle() {
        val job = CoroutineScope(Dispatchers.Main).launch {

            val gelenListe = kdao.tumKisiler()

            for (k in gelenListe) {
                Log.e("Kisi id:", k.kisi_id.toString())
                Log.e("Kisi ad:", k.kisi_ad)
                Log.e("Kisi yas:", k.kisi_yas.toString())

            }
        }
    }

    fun ekle() {
        val job = CoroutineScope(Dispatchers.Main).launch {

           val yeniKisi = Kisiler(kisi_id = 0, kisi_ad = "Ahmet", kisi_yas = 40)
            kdao.kisiEkle(yeniKisi)
        }
    }

    fun guncelle() {
        val job = CoroutineScope(Dispatchers.Main).launch {

            val guncellenenKisi = Kisiler(kisi_id = 5, kisi_ad = "Recep", kisi_yas = 21)
            kdao.kisiGuncelle(guncellenenKisi)
        }
    }


    fun sil() {
        val job = CoroutineScope(Dispatchers.Main).launch {

            val silinenKisi = Kisiler(kisi_id = 5, kisi_ad = "Recep", kisi_yas = 21)
            kdao.kisiSil(silinenKisi)
        }
    }


    fun rastgele() {
        val job = CoroutineScope(Dispatchers.Main).launch {

            val gelenListe = kdao.rastgele1KisiGetir()

            for (k in gelenListe) {
                Log.e("Kisi id:", k.kisi_id.toString())
                Log.e("Kisi ad:", k.kisi_ad)
                Log.e("Kisi yas:", k.kisi_yas.toString())

            }
        }
    }

    fun ara() {
        val job = CoroutineScope(Dispatchers.Main).launch {

            val gelenListe = kdao.kisiAra("sa")

            for (k in gelenListe) {
                Log.e("Kisi id:", k.kisi_id.toString())
                Log.e("Kisi ad:", k.kisi_ad)
                Log.e("Kisi yas:", k.kisi_yas.toString())

            }
        }
    }

    fun getir() {
        val job = CoroutineScope(Dispatchers.Main).launch {

            val gelenKisi = kdao.kisiGetir(2)

            Log.e("Kisi id:", gelenKisi.kisi_id.toString())
            Log.e("Kisi ad:", gelenKisi.kisi_ad)
            Log.e("Kisi yas:", gelenKisi.kisi_yas.toString())
        }
    }

    fun kontrol() {
        val job = CoroutineScope(Dispatchers.Main).launch {

            val gelenSonuc = kdao.kayitKontrol("samet")

            Log.e("Kisi kontrol sonucu:", gelenSonuc.toString())

        }
    }
}