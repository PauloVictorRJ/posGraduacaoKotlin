package br.edu.projetofinalkotlin.view.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.projetofinalkotlin.databinding.ActivityHomeBinding
import br.edu.projetofinalkotlin.view.login.LoginActivity
import br.edu.projetofinalkotlin.view.task.TaskActivity
import br.edu.projetofinalkotlin.model.TaskService
import br.edu.projetofinalkotlin.view.weather.WeatherActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val serviceIntent = Intent(this, TaskService::class.java)
        startService(serviceIntent)

        configureButtons()
    }

    private fun configureButtons() {
        binding.backBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.taskBtn.setOnClickListener {
            startActivity(Intent(this, TaskActivity::class.java))
        }

        binding.weatherBtn.setOnClickListener {
            startActivity(Intent(this, WeatherActivity::class.java))
        }
    }

}