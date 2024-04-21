package br.edu.projetofinalkotlin.view.task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import br.edu.projetofinalkotlin.R
import br.edu.projetofinalkotlin.databinding.ActivityEditTaskBinding
import br.edu.projetofinalkotlin.model.TaskEntity
import br.edu.projetofinalkotlin.view.SaveBtnFragment
import br.edu.projetofinalkotlin.viewmodel.task.EditTaskViewModel

class EditTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditTaskBinding
    private val viewModel: EditTaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureButtons()
    }

    private fun registerNewTask() {
        val title = binding.taskTitleTiet.text.toString()
        val description = binding.taskDescriptionTiet.text.toString()
        val condition = binding.switch1.isChecked

        if (title.isNotEmpty() && description.isNotEmpty()) {
            viewModel.insertTaskViewModel(TaskEntity(null, title, description, condition))
            sendToast(true)
            startActivity(Intent(this, TaskActivity::class.java))
        } else {
            sendToast(false)
        }
    }

    private fun sendToast(success: Boolean) {
        if (success) {
            showMessage(getString(R.string.edit_task_toast_success_txt))
        } else {
            showMessage(getString(R.string.edit_task_toast_error_txt))
        }
    }

    private fun configureButtons() {
        binding.backBtn.setOnClickListener {
            this.finish()
        }

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_save_btn,
                SaveBtnFragment(
                    saveCallback = { registerNewTask() })
            )
            .commit()
    }

    private fun showMessage(message: String) {
        Toast.makeText(this@EditTaskActivity, message, Toast.LENGTH_SHORT).show()
    }
}