package br.edu.projetofinalkotlin.view.weather

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.projetofinalkotlin.R
import br.edu.projetofinalkotlin.databinding.ActivityWeatherBinding
import br.edu.projetofinalkotlin.view.home.HomeActivity
import br.edu.projetofinalkotlin.view.weather.fragment.WeatherFragmentRv

class WeatherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureFragments()
        configureButtons()
    }

    private fun configureButtons() {
        binding.backBtn.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    private fun configureFragments() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_rio_toquio_roma,
                WeatherFragmentRv(WeatherFragmentRv.CityGroup.RioTokyoRome)
            )
            .commit()

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_manila_brasov_antananarivo,
                WeatherFragmentRv(WeatherFragmentRv.CityGroup.ManilaBrasovAntananarivo)
            )
            .commit()
    }
}