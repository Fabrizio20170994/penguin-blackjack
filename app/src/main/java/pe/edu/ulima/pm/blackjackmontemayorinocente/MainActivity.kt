package pe.edu.ulima.pm.blackjackmontemayorinocente

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import pe.edu.ulima.pm.blackjackmontemayorinocente.adapters.GameSliderAdapter

// Proyecto de Programación Móvil de Fabrizio Montemayor y Marcelo Inocente
class MainActivity : AppCompatActivity() {
    // Mazo
    var pager : ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pager = findViewById(R.id.pager)
        pager!!.adapter = GameSliderAdapter(supportFragmentManager)

    }


}