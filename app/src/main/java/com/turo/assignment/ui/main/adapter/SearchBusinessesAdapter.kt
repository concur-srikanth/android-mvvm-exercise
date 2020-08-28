package com.turo.assignment.ui.main.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.turo.assignment.BR
import com.turo.assignment.R
import com.turo.assignment.ui.main.model.BusinessUIModel
import kotlinx.android.synthetic.main.business_list_item.view.*


open class SearchBusinessesAdapter  : RecyclerView.Adapter<SearchBusinessesAdapter.ShowBusinessesViewHolder>() {

    var businessList : MutableList<BusinessUIModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowBusinessesViewHolder {
        //defaulting to header
        val rowView: View = LayoutInflater.from(parent.context).inflate(R.layout.business_list_item, parent, false)
        return ShowBusinessesViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: ShowBusinessesViewHolder, position: Int) {
        holder.binding.setVariable(BR.businessUIModel, businessList[position])
        holder.binding.executePendingBindings()
        Picasso.with(holder.itemView.context).load(businessList.get(position).imageUrl).resize(200, 80)
            .into(holder.itemView.imageView);

        holder.itemView.webUrl.setOnClickListener(View.OnClickListener {
            val url =  businessList[position].webUrl
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            holder.itemView.context.startActivity(intent)
        })

    }

    override fun getItemCount(): Int {
        return businessList.size
    }
    /**
     * custom view holder class
     */
    inner class ShowBusinessesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ViewDataBinding = DataBindingUtil.bind(itemView)!!
    }
}