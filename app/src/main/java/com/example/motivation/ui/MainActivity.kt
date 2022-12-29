package com.example.motivation.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.R
import com.example.motivation.data.Mock
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConstants.FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //esconde a barra de navegação
        supportActionBar?.hide()

        handleFilter(R.id.image_all)

        //Função que inicializa a aplicação com uma frase sem precisar clicar no botão(nova frase)
        handleNextPhrase()


        //Eventos (ex clicks etc)
        setListeners()

    }

    /*aqui eu uso o ciclo de vida da activity, coloquei a função dentro do "onResume" porque se
        não sempre ia ter que matar uma activity para que o nome fosse mostrado quando clicado em cima
        dele para ser digitado um novo nome. */
    override fun onResume() {
        super.onResume()
        //função que salva e mostra o nome digitado.
        handleUserName()
    }


    override fun onClick(view: View) {
        val id: Int = view.id

        val listId = listOf(
            R.id.image_all,
            R.id.image_happy,
            R.id.image_sunny
        )

        if (id in listId) {
            handleFilter(id)
        } else if (id == R.id.new_sentence_button) {
            handleNextPhrase()
        } else if (id == R.id.text_your_name) {
            startActivity(Intent(this, UserActivity::class.java))
        }
    }

    private fun setListeners() {
        //Eventos (ex clicks etc)
        binding.newSentenceButton.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
        binding.textYourName.setOnClickListener(this)
    }

    private fun handleNextPhrase() {
        //aqui estamos o texto e gerando a frase atraves da classe MOCK
        binding.textMotivationalPhrase.text = Mock().getPhrase(categoryId, Locale.getDefault().language)
    }

    private fun handleFilter(id: Int) {
        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.ALL
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.HAPPY
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.SUNNY
            }
        }

    }

    private fun handleUserName() {
        //Aqui estamos pegando o nome que foi digitado e colocando na aplicação.
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textYourName.text = "Olá ${name}!"
    }
}
