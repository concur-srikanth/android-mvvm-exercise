package com.turo.assignment.ui.main.frag

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.turo.assignment.R
import com.turo.assignment.ui.main.adapter.SearchBusinessesAdapter
import com.turo.assignment.ui.main.viewmodel.SearchBusinessesViewModel
import kotlinx.android.synthetic.main.main_fragment.*

class SearchBusinessesFragment : Fragment() {

    companion object {
        fun newInstance() = SearchBusinessesFragment()
    }

    private val SEARCH_TERM = "pizza & beer"

    private val CURRENT_LOCATION: String ="111 Sutter St, San Francisco, CA"

    private lateinit var viewModel: SearchBusinessesViewModel

    private var businessesAdapter = SearchBusinessesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SearchBusinessesViewModel::class.java)
        subscribeToGetBusinessInfo()
        subscribeToNetworkError()

        setRecyclerView()
        initSearchView()
    }

    private fun initSearchView() {

        searchView.setQueryHint(getString(R.string.hint_search));
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // do something on text submit
                return false
            }

            override fun onQueryTextChange(query : String): Boolean {
                // do something when text changes
                getBusinessInfo(query, CURRENT_LOCATION)
                return false
            }
        })

    }

    private fun setRecyclerView() {
        activity.let {
            businessListView.layoutManager = LinearLayoutManager(activity!!)
            businessListView.setHasFixedSize(true)
            businessListView.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
            businessListView.setAdapter(businessesAdapter)
        }
    }

    override fun onResume() {
        super.onResume()
        getBusinessInfo(SEARCH_TERM, CURRENT_LOCATION)
    }

    private fun getBusinessInfo(term : String, location : String) {
        if(checkNetworkAvailability()) {
            businessListView.visibility = View.GONE
            progressBar1.visibility = View.VISIBLE
             // term can searchable & hard coded on load.
            // location is hard coded for now, will be replaced later iteration with current location.
            viewModel.callGetBusinessesRx(term, location)
        }else{
            // show network failure snack bar
            showMessage(getString(R.string.no_internet_connection))
        }
    }

    private fun showMessage(string : String ) {
        val view : View? = activity?.findViewById(android.R.id.content)as View?
        view?.let{
            Snackbar.make(view, string, Snackbar.LENGTH_LONG)
                .setAction("Retry", View.OnClickListener {

                })
                .setActionTextColor(Color.RED)
                .show();
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.unsubsribe()
    }

    private fun subscribeToGetBusinessInfo() {
        activity?.let {
            viewModel.businessInfoData.observe(it, Observer { businessesInfo ->
                // update the data into UI
                businessListView.visibility = View.VISIBLE
                progressBar1.visibility = View.GONE
                val businessUIModelList = viewModel.mapToUIModel(businessesInfo)
                businessesAdapter.businessList = businessUIModelList
                businessesAdapter.notifyDataSetChanged()
            })
        }
    }


    private fun subscribeToNetworkError() {
        activity?.let {
            viewModel.networkError.observe(it, Observer { networkError ->
                // update the data into UI
                showMessage(getString(R.string.network_failure))
            })
        }
    }


    // Checks network connectivity
    fun checkNetworkAvailability() : Boolean {
        // used quick solution for check network connectivity for demo purpose.
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }

}