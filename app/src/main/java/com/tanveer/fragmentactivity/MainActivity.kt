package com.tanveer.fragmentactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.tanveer.fragmentactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding : ActivityMainBinding? = null
    var activityInterface : ActivityInterface? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.btnChangeActivityText?.setOnClickListener{
            activityInterface?.ChangeFragmentText(binding?.etText?.text?.toString()?:"")
        }
    }
    fun ChangeActivityText(string:String){
        binding?.btnChangeActivityText?.setText(string)
    }



}