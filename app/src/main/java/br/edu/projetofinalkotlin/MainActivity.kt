package br.edu.projetofinalkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import br.edu.projetofinalkotlin.databinding.ActivityMainBinding
import br.edu.projetofinalkotlin.view.login.CreateAccountActivity
import br.edu.projetofinalkotlin.view.login.LoginActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureThemeBtn()
        configureButtons()
        updateThemeBtnIcon()
    }

    private fun configureThemeBtn() {
        binding.themeBtn.setOnClickListener {
            val currentNightMode =
                resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK
            if (currentNightMode == android.content.res.Configuration.UI_MODE_NIGHT_NO) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            updateThemeBtnIcon()
        }
    }

    private fun configureButtons() {
        binding.loginBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.createAccountBtn.setOnClickListener {
            startActivity(Intent(this, CreateAccountActivity::class.java))
        }
    }

    private fun updateThemeBtnIcon() {
        val currentNightMode = AppCompatDelegate.getDefaultNightMode()
        if (currentNightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            binding.themeBtn.setIconResource(R.drawable.ic_light)
        } else {
            binding.themeBtn.setIconResource(R.drawable.ic_moon)
        }
    }
}