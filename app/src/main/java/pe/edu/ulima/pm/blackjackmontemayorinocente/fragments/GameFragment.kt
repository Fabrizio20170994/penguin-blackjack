package pe.edu.ulima.pm.blackjackmontemayorinocente.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import pe.edu.ulima.pm.blackjackmontemayorinocente.R
import pe.edu.ulima.pm.blackjackmontemayorinocente.models.Statistics
import pe.edu.ulima.pm.blackjackmontemayorinocente.views.CartaView
import pe.edu.ulima.pm.blackjackmontemayorinocente.views.OnCardClickListener


class GameFragment : Fragment(), OnCardClickListener {

    val deck = mutableListOf<Int>()

    var message: TextView? = null

    var standButton: Button? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Obtenemos la referencia al mensaje
        message = view!!.findViewById(R.id.mensaje)

        // Obtenemos la referencia al boton para quedar
        standButton = view!!.findViewById(R.id.quedar)

        // Se setean los listeners de los botones
        view!!.findViewById<Button>(R.id.reiniciar).setOnClickListener { initializeGame() }

        //Se inicia el juego
        initializeGame()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    private fun initializeGame() {
        // Se setea el listener de quedar
        standButton!!.setOnClickListener { stand() }

        // Se reinicia el texto
        message!!.setText("BlackJack")

        // Se resetean todas las cartas
        for (card in getPlayerCards() + getTableCards()) {
            card.number = null
            card.invalidate()
        }

        // Asignamos el Listener a las cartas del jugador
        for (i in getPlayerCards()) {
            i.setOnCardClickListener(this)
        }
        // Llenamos el mazo
        for (i in 1..4) {
            deck.addAll((1..13))
        }

        // Revelamos las 2 primeras cartas
        for (i in 0..1) {
            flipCard(getPlayerCards()[i])
            flipCard(getTableCards()[i])
        }

        // Se corre la detección de ganador
        determineWinner(false)
    }

    private fun flipCard(view: CartaView) {
        // Se extrae un número para asignarselo a la carta
        var randomIndex = (0 until deck.count()).random()
        view.number = deck[randomIndex]
        deck.removeAt(randomIndex)
        // Volvemos a dibujar el View
        view.invalidate()
    }

    override fun onClick(view : CartaView) {
        // Si la carta no ha sido volteada
        if (view.number == null) {
            // Se voltea la carta
            flipCard(view)
            // Se detecta al ganador
            determineWinner(true)
        }
    }

    private fun determineWinner(flipTableCard: Boolean) {

        var gameEnded = true

        // Se revisa si el jugador ganó o perdió
        val playerStatus = checkWin(getCount(getPlayerCards()))
        when (playerStatus) {
            "win" -> {
                message!!.setText("Usted ganó")
                Statistics.recordResult(true)
            }
            "lose" -> {
                message!!.setText("La mesa ganó")
                Statistics.recordResult(false)
            }
            else -> {
                // Si no ha pasado nada se voltea una carta de la mesa
                for (card in getTableCards()) {
                    if (card.number == null) {
                        if (flipTableCard) flipCard(card)
                        break
                    }
                }
                // Se revisa si la mesa ganó o perdió
                val tableStatus = checkWin(getCount(getTableCards()))
                when (tableStatus) {
                    "win" -> {
                        message!!.setText("La mesa ganó")
                        Statistics.recordResult(false)
                    }
                    "lose" -> {
                        message!!.setText("Usted ganó")
                        Statistics.recordResult(true)
                    }
                    else -> {
                        // Si todas las cartas están reveladas se decide por quien tenga más
                        if (getPlayerCards().all { carta -> carta.number != null }) {
                            val playerCount = getCount(getPlayerCards())
                            val tableCount = getCount(getTableCards())
                            if (playerCount == tableCount) {
                                message!!.setText("Empate")
                            }
                            if (playerCount > tableCount) {
                                message!!.setText("Usted ganó")
                                Statistics.recordResult(true)
                            } else {
                                message!!.setText("La mesa ganó")
                                Statistics.recordResult(false)
                            }
                        } else {
                            gameEnded = false
                        }
                    }
                }
            }
        }

        // Si el juego ha terminado se impide hacer clic a las cartas y en "quedar"
        if (gameEnded) {
            for (card in getPlayerCards()) card.setOnCardClickListener(null)
            standButton!!.setOnClickListener { null }
        }
    }

    private fun stand() {
        for (card in getTableCards()) {
            if (card.number == null) flipCard(card)
        }
        determineWinner(false)
    }

    private fun getPlayerCards(): List<CartaView> {
        return listOf<CartaView>(
            view!!.findViewById(R.id.cartaJugador1),
            view!!.findViewById(R.id.cartaJugador2),
            view!!.findViewById(R.id.cartaJugador3),
            view!!.findViewById(R.id.cartaJugador4))
    }

    private fun getTableCards(): List<CartaView> {
        return listOf<CartaView>(
            view!!.findViewById(R.id.cartaMesa1),
            view!!.findViewById(R.id.cartaMesa2),
            view!!.findViewById(R.id.cartaMesa3),
            view!!.findViewById(R.id.cartaMesa4))
    }

    private fun getCount(cardList: List<CartaView>): Int {
        var sum = 0
        for (card in cardList) {
            sum += if (card.number != null) card.number!! else 0
        }
        return sum
    }

    private fun checkWin(count: Int): String {
        if (count == 21) return "win"
        if (count > 21) return "lose"

        return "nothing"
    }
}