package com.example.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.R
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener(this)

        supportActionBar?.hide()

    }


    override fun onClick(view: View) {
        if (view.id == R.id.button_save) {
            handleSave()
        }
    }


    // Essa FUN esta criando uma ligação entre a MAIN E A USER ACTIVITY
    private fun handleSave() {
        val name = binding.editTextYourName.text.toString()
        if (name != "") {
            //Aqui eu salvo o nome do meu usuario, Fiz uma classe para salvar.
            SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, name)


            // O finish serve para destruir a MAIN anterior
            finish()
        } else {
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
        }
    }
}

