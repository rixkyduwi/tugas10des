package com.rizkydwisaputra.pindahfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentSatu = FragmentSatu()
        val fragment: Fragment?=supportFragmentManager.findFragmentByTag(FragmentSatu::class.java.simpleName)
        if(fragment !is FragmentSatu){
            supportFragmentManager.beginTransaction()
                .add(R.id.container,fragmentSatu, FragmentSatu::class.java.simpleName)
                .commit()
        }
    }
}