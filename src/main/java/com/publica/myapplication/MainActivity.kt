package com.publica.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import dev.carrion.wheelpicker.WheelPicker
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class MainActivity : AppCompatActivity() {
    private lateinit var preference: Preference
    private var _list = HashSet<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preference = Preference.getInstance(this.applicationContext)
        if (preference.isFirstTimeUser) {
            _list.addAll(getList())
            preference.list = _list
            preference.isFirstTimeUser = false
        } else {
            _list = preference.list as HashSet<String>
        }
        val picker = findViewById<WheelPicker>(R.id.wheel_picker)
        val arrlist = ArrayList<String>()
        arrlist.addAll(_list)
        picker.init(arrlist)
        findViewById<Button>(R.id.accept).setOnClickListener {
            _list.remove(picker.getSelectedItem())
            preference.list = _list
            arrlist.clear()
            arrlist.addAll(_list)
            picker.init(arrlist)
        }
    }

    private fun getList(): HashSet<String> {
        val defaultList = HashSet<String>()
        defaultList.add("swimming")
        defaultList.add("eating")
        defaultList.add("gaming")
        defaultList.add("play")
        defaultList.add("track")
        defaultList.add("singing")
        defaultList.add("drawing")
        return defaultList
    }

}