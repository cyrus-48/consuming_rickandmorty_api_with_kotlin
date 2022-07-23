package com.example.kui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.kui.adapter.CharactersAdapter
import com.example.kui.api.RickyMorty
import com.example.kui.model.CharactersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val adapter by lazy { CharactersAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myProgressbar = findViewById<ProgressBar>(R.id.charactersProgressBar)
        val myRecyclerview = findViewById<RecyclerView>(R.id.characterRecyclerView)

        RickyMorty.api.getCharacters().enqueue(object :  Callback<CharactersResponse>{
            override fun onResponse(
                call: Call<CharactersResponse>,
                response: Response<CharactersResponse>
            ) {
                myProgressbar.isVisible = false
                 if(response.isSuccessful){
                     adapter.submitList(response.body()?.results)
                     myRecyclerview.adapter = adapter

                 }
            }

            override fun onFailure(call: Call<CharactersResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                myProgressbar.isVisible = false
            }

        })

    }
}