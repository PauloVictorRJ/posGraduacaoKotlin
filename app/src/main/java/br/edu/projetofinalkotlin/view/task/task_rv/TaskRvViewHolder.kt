package br.edu.projetofinalkotlin.view.task.task_rv

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.projetofinalkotlin.R
import br.edu.projetofinalkotlin.model.TaskEntity
import com.google.android.material.button.MaterialButton
import com.google.android.material.switchmaterial.SwitchMaterial

class TaskRvViewHolder(
    view: View,
    private val delete: (TaskEntity) -> Unit,
    private val update: (TaskEntity) -> Unit
) :
    RecyclerView.ViewHolder(view) {
    private val title: TextView = view.findViewById(R.id.task_title)
    private val description: TextView = view.findViewById(R.id.task_description_txt)
    private val deleteAction: MaterialButton = view.findViewById(R.id.del_btn)
    private val condition: SwitchMaterial = view.findViewById(R.id.switch1)

    fun bind(item: TaskEntity) {
        title.text = item.title
        description.text = item.description
        condition.isChecked = item.condition

        deleteAction.setOnClickListener {
            delete.invoke(item)
        }

        condition.setOnCheckedChangeListener { _, isChecked ->
            item.condition = isChecked
            update.invoke(item)
        }
    }
}

