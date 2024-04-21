package br.edu.projetofinalkotlin.view.task.task_rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.edu.projetofinalkotlin.R
import br.edu.projetofinalkotlin.model.TaskEntity

class TaskRvAdapter(
    private val delete: (TaskEntity) -> Unit,
    private val update: (TaskEntity) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val diffUtil = AsyncListDiffer(this, DIFF_UTIL)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TaskRvViewHolder(
            inflater.inflate(R.layout.task_rv_item, parent, false), delete, update
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TaskRvViewHolder -> holder.bind(diffUtil.currentList[position])
        }
    }

    override fun getItemCount() = diffUtil.currentList.size

    fun updateList(items: List<TaskEntity>) {
        diffUtil.submitList(items)
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<TaskEntity>() {
            override fun areItemsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}