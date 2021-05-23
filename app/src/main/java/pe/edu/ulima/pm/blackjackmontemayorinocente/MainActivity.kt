package pe.edu.ulima.pm.blackjackmontemayorinocente

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import pe.edu.ulima.pm.blackjackmontemayorinocente.views.CartaView
import pe.edu.ulima.pm.blackjackmontemayorinocente.views.OnCardClickListener

class MainActivity : AppCompatActivity(), OnCardClickListener {
    // Mazo
    val deck = mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Obtenemos las cartas
        val cartas = listOf<CartaView>(
                findViewById<CartaView>(R.id.cartaJugador1),
                findViewById<CartaView>(R.id.cartaJugador2),
                findViewById<CartaView>(R.id.cartaJugador3),
                findViewById<CartaView>(R.id.cartaJugador4),
                findViewById<CartaView>(R.id.cartaMesa1),
                findViewById<CartaView>(R.id.cartaMesa2),
                findViewById<CartaView>(R.id.cartaMesa3),
                findViewById<CartaView>(R.id.cartaMesa4))

        // Asignamos el Listener a las Cartas
        for (i in cartas) {
            i.setOnCardClickListener(this)
        }
        // Llenamos el mazo
        for (i in 1..4) {
            deck.addAll((1..13))
        }
    }

     override fun onClick(view : CartaView) {
        // Si la carta no ha sido volteada
        if (view.number == null) {
            var randomIndex = (0 until deck.count()).random()
            view.number = deck[randomIndex]
            deck.removeAt(randomIndex)
            // Volvemos a dibujar el View
            view.invalidate()
        }
    }
}