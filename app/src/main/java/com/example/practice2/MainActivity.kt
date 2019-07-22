package com.example.practice2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var adapter = BreweryAdapter(ArrayList())
    private val apiClient = APIClient.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter.onItemClickFunction = { _, pos ->
            startActivity(Intent(this@MainActivity, BreweryActivity::class.java).putExtra("brewery", adapter.getBrewery(pos)))
        }

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter


        apiClient.apiService.getBreweries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                adapter.setBreweries(it)
            }, {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG)
            })
    }
}
