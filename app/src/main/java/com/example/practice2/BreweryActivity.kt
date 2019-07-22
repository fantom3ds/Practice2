package com.example.practice2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_brewery.*

class BreweryActivity : AppCompatActivity() {

    var brewery: Brewery? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brewery)

        brewery = this.intent.getSerializableExtra("brewery") as Brewery


    }
}
