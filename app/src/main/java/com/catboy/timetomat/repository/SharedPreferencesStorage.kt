package com.catboy.timetomat.repository

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesStorage(context: Context, baseName: String
) : SharedPreferences {

    private val sharedPref: SharedPreferences = context.getSharedPreferences(
        baseName, Context.MODE_PRIVATE)
    private val editor = sharedPref.edit()

    override fun getAll(): MutableMap<String, *> {
        return sharedPref.all
    }

    override fun getString(key: String?, defValue: String?): String? {
        return sharedPref.getString(key, defValue)
    }

    override fun getStringSet(key: String?, defValues: MutableSet<String>?): MutableSet<String>? {
        return sharedPref.getStringSet(key, defValues)
    }

    override fun getInt(key: String?, defValue: Int): Int {
        return sharedPref.getInt(key, defValue)
    }

    override fun getLong(key: String?, defValue: Long): Long {
        return sharedPref.getLong(key, defValue)
    }

    override fun getFloat(key: String?, defValue: Float): Float {
        return sharedPref.getFloat(key, defValue)
    }

    override fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return sharedPref.getBoolean(key, defValue)
    }

    override fun contains(key: String?): Boolean {
        return sharedPref.contains(key)
    }

    fun save(key: String, text: String) {
        editor.putString(key, text)
        editor.commit()
    }

    fun save(key: String, value: Int) {
        editor.putInt(key, value)
        editor.commit()
    }
    fun save(key: String, value: Long) {
        editor.putLong(key, value)
        editor.commit()
    }
    fun save(key: String, value: Float) {
        editor.putFloat(key, value)
        editor.commit()
    }

    fun save(KEY_NAME: String, status: Boolean) {
        editor.putBoolean(KEY_NAME, status)
        editor.commit()
    }

    fun removeValue(KEY_NAME: String) {
        editor.remove(KEY_NAME)
        editor.commit()
    }

    fun clear() {
        editor.clear()
        editor.commit()
    }

    override fun edit(): SharedPreferences.Editor {
        return sharedPref.edit()
    }

    override fun registerOnSharedPreferenceChangeListener(
        listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        sharedPref.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun unregisterOnSharedPreferenceChangeListener(
        listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        sharedPref.unregisterOnSharedPreferenceChangeListener(listener)
    }



}
