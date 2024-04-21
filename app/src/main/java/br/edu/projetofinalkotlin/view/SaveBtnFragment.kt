package br.edu.projetofinalkotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.projetofinalkotlin.databinding.FragmentSaveBtnBinding

class SaveBtnFragment(private val saveCallback: () -> Unit) : Fragment() {
    private var binding: FragmentSaveBtnBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaveBtnBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.saveBtn?.setOnClickListener {
            saveCallback.invoke()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}