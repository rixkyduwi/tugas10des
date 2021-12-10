package com.rizkydwisaputra.pindahfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentSatu = FragmentSatu()
        val fragment= supportFragmentManager.findFragmentByTag(FragmentSatu::class.java.simpleName)
        if(fragment !is FragmentSatu){
            supportFragmentManager.beginTransaction()
                .add(R.id.container,fragmentSatu, FragmentSatu::class.java.simpleName)
                .commit()
        }
    }
}