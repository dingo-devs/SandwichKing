package io.dingodevs.sandwichking.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.dingodevs.sandwichking.R
import io.dingodevs.sandwichking.ui.adapter.SandwichIngredientViewAdapter
import io.dingodevs.sandwichking.viewmodel.CreateSandwichViewModel


class CreateSandwichFragment : Fragment() {
    private lateinit var viewModel: CreateSandwichViewModel
    private lateinit var sandwichIngredientListRecyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(CreateSandwichViewModel::class.java)

        val root = inflater.inflate(R.layout.create_sandwich_fragment, container, false)

        // When create sandwich button is clicked
        root.findViewById<Button>(R.id.createSandwichButton).setOnClickListener {
            Toast.makeText(context, "Making your sandwich!", Toast.LENGTH_SHORT).show()
            viewModel.createSandwich()
        }

        viewManager = LinearLayoutManager(context)
        viewAdapter = SandwichIngredientViewAdapter(viewModel.sandwichIngredientsModelList) { item, isSelected ->
            viewModel.updateSelected(item.id, isSelected)
        }

        sandwichIngredientListRecyclerView =
            root.findViewById<RecyclerView>(R.id.sandwichIngredientListRecyclerView).apply {
                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                setHasFixedSize(true)

                // use a linear layout manager
                layoutManager = viewManager

                // specify an viewAdapter (see also next example)
                adapter = viewAdapter
            }


        return root
    }
}
