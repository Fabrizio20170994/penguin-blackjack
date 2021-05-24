package pe.edu.ulima.pm.blackjackmontemayorinocente.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pe.edu.ulima.pm.blackjackmontemayorinocente.R
import pe.edu.ulima.pm.blackjackmontemayorinocente.views.CartaView
import pe.edu.ulima.pm.blackjackmontemayorinocente.views.OnCardClickListener


class GameFragment : Fragment(), OnCardClickListener {

    val deck = mutableListOf<Int>()
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Obtenemos las cartas
        val cartas = listOf<CartaView>(
            view!!.findViewById<CartaView>(R.id.cartaJugador1),
            view!!.findViewById<CartaView>(R.id.cartaJugador2),
            view!!.findViewById<CartaView>(R.id.cartaJugador3),
            view!!.findViewById<CartaView>(R.id.cartaJugador4),
            view!!.findViewById<CartaView>(R.id.cartaMesa1),
            view!!.findViewById<CartaView>(R.id.cartaMesa2),
            view!!.findViewById<CartaView>(R.id.cartaMesa3),
            view!!.findViewById<CartaView>(R.id.cartaMesa4))

        // Asignamos el Listener a las Cartas
        for (i in cartas) {
            i.setOnCardClickListener(this)
        }
        // Llenamos el mazo
        for (i in 1..4) {
            deck.addAll((1..13))
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
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