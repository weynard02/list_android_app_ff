package com.weynard02.finalfantasyapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvGames: RecyclerView
    private val list = ArrayList<Game>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvGames = findViewById(R.id.rv_games)
        rvGames.setHasFixedSize(true)

        list.addAll(getListGames())
        showRecyclerList()
    }

    private fun getListGames(): ArrayList<Game> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataCharacter = resources.getStringArray(R.array.data_character)
        val dataScore = resources.getIntArray(R.array.data_score)
        val dataTrailer = resources.getStringArray(R.array.data_trailer)
        val listGame = ArrayList<Game>()

        for (i in dataName.indices) {
            val game = Game(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataCharacter[i], dataScore[i], dataTrailer[i])
            listGame.add(game)
        }

        return listGame
    }

    private fun showRecyclerList() {
        rvGames.layoutManager = LinearLayoutManager(this)
        val listGameAdapter = ListGameAdapter(list)
        rvGames.adapter = listGameAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val aboutMeIntent = Intent(this@MainActivity, AboutMeActivity::class.java)
                startActivity(aboutMeIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}