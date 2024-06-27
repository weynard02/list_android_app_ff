package com.weynard02.finalfantasyapplication

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var tvDetailName: TextView
    private lateinit var tvDetailDescription: TextView
    private lateinit var imgDetailPhoto: ImageView
    private lateinit var tvDetailCharacter: TextView
    private lateinit var tvDetailScore: TextView
    private lateinit var linkTrailer: String

    companion object {
        const val KEY_GAME = "key_game"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val dataGame = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Game>(KEY_GAME, Game::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Game>(KEY_GAME)
        }
        tvDetailName = findViewById(R.id.tv_detail_item_name)
        tvDetailDescription = findViewById(R.id.tv_detail_item_description)
        imgDetailPhoto = findViewById(R.id.img_detail_item_photo)
        tvDetailCharacter = findViewById(R.id.tv_detail_character)
        tvDetailScore = findViewById(R.id.tv_detail_game_score)

        if (dataGame != null) {
            tvDetailName.text = dataGame.title
            tvDetailDescription.text = dataGame.description
            imgDetailPhoto.setImageResource(dataGame.photo)
            tvDetailCharacter.text = dataGame.character
            val textScore = "${dataGame.score.toString()} / 5"
            tvDetailScore.text =  textScore

            linkTrailer = dataGame.trailer
        }

        val shareButton: Button = findViewById(R.id.action_share)
        shareButton.setOnClickListener(this)

        val trailerButton: Button = findViewById(R.id.action_trailer)
        trailerButton.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_back, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.back -> {
                val backIntent = Intent(this@DetailActivity, MainActivity::class.java)
                startActivity(backIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.action_share -> {
                val share = Intent(Intent.ACTION_SEND)
                share.setType("text/plain")
                share.putExtra(Intent.EXTRA_TEXT, "Learn more about ${tvDetailName.text}")
                startActivity(Intent.createChooser(share, "Share Link"))
            }
            R.id.action_trailer -> {
                val trailer = Intent(Intent.ACTION_VIEW, Uri.parse(linkTrailer))
                startActivity(trailer)
            }
        }
    }
}