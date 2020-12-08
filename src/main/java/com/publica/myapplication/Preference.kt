package com.publica.myapplication

import android.content.Context
import android.content.SharedPreferences

class Preference private constructor(context: Context) {

    private var sharedPreferences: SharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    private val LIST_ITEMS = "list_items"
    private val IS_FIRST_TIME_USER_KEY = "is_first_time_user"


    companion object {
        private var instance: Preference? = null

        fun getInstance(context: Context): Preference {
            if (instance == null) {
                synchronized(Preference::class.java) {
                    if (instance == null) {
                        instance = Preference(context)
                    }
                }
            }

            return instance!!
        }
    }
    var isFirstTimeUser: Boolean
        get() = sharedPreferences.getBoolean(IS_FIRST_TIME_USER_KEY, true)
        set(data) {
            val editor = sharedPreferences.edit()
            editor.putBoolean(IS_FIRST_TIME_USER_KEY, data)
            editor.apply()
        }

    var list: Set<String>?
        get() = sharedPreferences.getStringSet(LIST_ITEMS, null)
        set(data) {
            val editor = sharedPreferences.edit()
            val set = HashSet<String>()
            set.addAll(data!!)
            editor.putStringSet(LIST_ITEMS, null).apply()
            editor.putStringSet(LIST_ITEMS, set)
            editor.apply()
        }


}