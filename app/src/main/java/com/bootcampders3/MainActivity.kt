package com.bootcampders3
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bootcampders3.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Çok fazla view olduğunda sadece view'ları tanımlamak için bile fazlaca kod gerekiyor.
        //val editText:EditText=findViewById(R.id.hizmet_bedeli_et)
        //VİEW BINDING
        binding =
            ActivityMainBinding.inflate(layoutInflater) //inflate:bağlayıcı olarak düşünebiliriz
        setContentView(binding.root)
        //xml deki değişkenlere erişildi.//
        binding.hesaplaButton.setOnClickListener {
            bahsisHesapla()
        }
    }
    fun bahsisHesapla() {
        val hizmetBedeliString =
            binding.hizmetBedeliEt.text.toString() //et den girilen değeri çekicez
        val tutar = hizmetBedeliString.toDoubleOrNull() //boşsa null a doluysa double a çevir.
        if (tutar == null) {
            binding.bahsisTutariTv.text = " "
            return
        }
        val secilenRadioButton =
            binding.bahsisSecenekleriRg.checkedRadioButtonId  //gruptan hangisi seçiliyse secilenRadiButton a atıyor.
        val bahsisYuzdesi = when (secilenRadioButton) {
            R.id.yuzde_yirmi_rb -> 0.20
            R.id.yuzde_onsekiz_rb -> 0.18
            else -> 0.15
        }
        var bahsis = bahsisYuzdesi * tutar
        val yukariYuvarla = binding.bahsisYuvarlaSwitch.isChecked //switch seçiliyse
        if (yukariYuvarla) { //false mu demek için başına ! koyulur.
            bahsis = kotlin.math.ceil(bahsis)  //ceil yukarı yuvarlattırır.
        }
        val formatlananBahsis = NumberFormat.getCurrencyInstance(Locale("tr", "TR")).format(bahsis)
        binding.bahsisTutariTv.text =
            getString(R.string.bahsis_tutari, formatlananBahsis)                    //"$bahsis ₺"
    }
}