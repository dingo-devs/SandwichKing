package io.dingodevs.sandwichking.ui.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import io.dingodevs.sandwichking.R
import io.dingodevs.sandwichking.ui.model.SandwichIngredientModel

class SandwichIngredientViewAdapter(
    private val sandwichIngredientModelList: List<SandwichIngredientModel>,
    private val itemSelectionListener: (item: SandwichIngredientModel, isSelected: Boolean) -> Unit
) :
    RecyclerView.Adapter<SandwichIngredientViewAdapter.SandwichIngredientViewHolder>() {

    class SandwichIngredientViewHolder(sandwichIngredientListItemView: View) :
        RecyclerView.ViewHolder(sandwichIngredientListItemView) {
        val sandwichIngredientImageView: ImageView =
            sandwichIngredientListItemView.findViewById(R.id.sandwichIngredientImageView)!!
        val sandwichIngredientCheckBox: CheckBox =
            sandwichIngredientListItemView.findViewById(R.id.sandwichIngredientCheckBox)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SandwichIngredientViewHolder {
        // create a new view
        val listItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_sandwich_ingredient, parent, false)
        // set the view's size, margins, paddings and layout parameters

        return SandwichIngredientViewHolder(listItemView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: SandwichIngredientViewHolder, position: Int) {
        val sandwichIngredientModel = sandwichIngredientModelList[position]
        // Bind data to view
        holder.sandwichIngredientCheckBox.text = sandwichIngredientModel.name
        holder.sandwichIngredientCheckBox.isChecked = sandwichIngredientModel.selected
        holder.sandwichIngredientImageView.setImageURI(Uri.parse(sandwichIngredientModel.imageUri))

        holder.sandwichIngredientCheckBox.setOnCheckedChangeListener { _, isChecked ->
            itemSelectionListener(sandwichIngredientModel, isChecked)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = sandwichIngredientModelList.size
}