package br.edu.projetofinalkotlin.view.task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.projetofinalkotlin.R
import br.edu.projetofinalkotlin.databinding.ActivityTaskBinding
import br.edu.projetofinalkotlin.util.DataResult
import br.edu.projetofinalkotlin.view.home.HomeActivity
import br.edu.projetofinalkotlin.view.task.task_rv.TaskRvAdapter
import br.edu.projetofinalkotlin.viewmodel.task.TaskViewModel
import kotlinx.coroutines.launch

class TaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskBinding
    private lateinit var adapter: TaskRvAdapter
    private val viewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureButtons()
        configureObservers()
        configureRv()
    }

    private fun configureButtons() {
        binding.backBtn.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        binding.addTaskBtn.setOnClickListener {
            startActivity(Intent(this, EditTaskActivity::class.java))
        }
    }

    private fun configureObservers() {
        viewModel.viewModelScope.launch {
            viewModel.allTaskViewModel().observe(this@TaskActivity) { result ->
                when (result) {
                    is DataResult.Loading -> {
                        showLoading()
                    }

                    is DataResult.Success -> {
                        hideLoading()
                        adapter.updateList(result.data)
                    }

                    is DataResult.Error -> {
                        hideLoading()
                        showMessage(getString(R.string.task_toast_error) + " ${result.exception.message}")
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
        Toast.makeText(this@TaskActivity, message, Toast.LENGTH_SHORT).show()
    }

    private fun configureRv() {
        binding.rvTasks.setHasFixedSize(true)
        binding.rvTasks.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = TaskRvAdapter(delete = {
            viewModel.deleteTaskViewModel(it)
        }, update = {
            viewModel.updateTaskViewModel(it)
        })
        binding.rvTasks.adapter = adapter
    }
}