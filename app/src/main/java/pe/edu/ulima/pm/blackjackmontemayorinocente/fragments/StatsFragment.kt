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


class StatsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Statistics.onResultRecorded = { wins, loses, effectiveness ->
            view!!.findViewById<TextView>(R.id.partidasGanadas).setText("Cantidad de partidas ganadas: $wins")
            view!!.findViewById<TextView>(R.id.partidasPerdidas).setText("Cantidad de partidas perdidas: $loses")
            view!!.findViewById<TextView>(R.id.Efectividad).setText("Efectividad: $effectiveness")
        }
    }
}