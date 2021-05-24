package pe.edu.ulima.pm.blackjackmontemayorinocente.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import pe.edu.ulima.pm.blackjackmontemayorinocente.fragments.GameFragment
import pe.edu.ulima.pm.blackjackmontemayorinocente.fragments.StatsFragment


class GameSliderAdapter : FragmentStatePagerAdapter {
    var fragmentList : ArrayList<Fragment>? = null
    constructor(fm : FragmentManager)
            : super(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


        // Creamos arraylist de fragments con su data de producto
        fragmentList = ArrayList()
        fragmentList!!.add(StatsFragment())
        fragmentList!!.add(GameFragment())
    }

    override fun getCount(): Int {
        return fragmentList!!.count()
    }


    override fun getItem(position: Int): Fragment {
        return fragmentList!![position]
    }
}