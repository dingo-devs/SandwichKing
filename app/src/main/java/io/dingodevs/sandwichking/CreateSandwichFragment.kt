package io.dingodevs.sandwichking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders


class CreateSandwichFragment : Fragment() {
    private lateinit var viewModel: CreateSandwichViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_sandwich_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CreateSandwichViewModel::class.java)
        // TODO: Use the ViewModel
    }

    companion object {
        fun newInstance() = CreateSandwichFragment()
    }
}
