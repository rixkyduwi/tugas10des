package com.rizkydwisaputra.pindahfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_duaa.*


/**
 * A simple [Fragment] subclass.
 * Use the [FragmentDua.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentDua : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_duaa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentSatu = FragmentSatu()
        btn_satu.setOnClickListener {
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.container, fragmentSatu, FragmentSatu::class.java.simpleName)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

}