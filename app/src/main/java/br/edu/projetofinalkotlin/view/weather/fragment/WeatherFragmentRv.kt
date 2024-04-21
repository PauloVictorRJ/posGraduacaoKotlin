package br.edu.projetofinalkotlin.view.weather.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import br.edu.projetofinalkotlin.R
import br.edu.projetofinalkotlin.databinding.FragmentWeatherRvBinding
import br.edu.projetofinalkotlin.util.DataResult
import br.edu.projetofinalkotlin.view.weather.weather_rv.WeatherRVAdapter
import br.edu.projetofinalkotlin.viewmodel.weather.WeatherViewModel
import kotlinx.coroutines.launch

class WeatherFragmentRv(private val cityGroup: CityGroup) : Fragment() {
    private var binding: FragmentWeatherRvBinding? = null
    private val viewModel: WeatherViewModel by viewModels()
    private var adapter = WeatherRVAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherRvBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.weatherRv?.adapter = adapter

        viewModel.viewModelScope.launch {
            viewModel.getWeatherForecastViewModel().observe(viewLifecycleOwner) { result ->
                when (result) {
                    is DataResult.Loading -> {
                        showLoading()
                    }

                    is DataResult.Success -> {
                        hideLoading()
                        val cityListGroup1 = result.data.take(3)
                        val cityListGroup2 = result.data.drop(3).take(3)
                        if (cityGroup == CityGroup.RioTokyoRome) {
                            adapter.updateList(cityListGroup1)
                        } else {
                            adapter.updateList(cityListGroup2)
                        }
                    }

                    is DataResult.Error -> {
                        hideLoading()
                        showMessage(getString(R.string.wheater_toast_error) + " ${result.exception.message}")
                    }

                    else -> {}
                }
            }
        }

    }

    private fun showLoading() {
    }

    private fun hideLoading() {
    }

    private fun showMessage(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    enum class CityGroup {
        RioTokyoRome,
        ManilaBrasovAntananarivo
    }
}