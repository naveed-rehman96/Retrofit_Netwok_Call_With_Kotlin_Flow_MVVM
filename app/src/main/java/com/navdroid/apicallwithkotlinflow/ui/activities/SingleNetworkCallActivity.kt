package com.navdroid.apicallwithkotlinflow.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.navdroid.apicallwithkotlinflow.base.ApiUserAdapter
import com.navdroid.apicallwithkotlinflow.data.model.ApiUser
import com.navdroid.apicallwithkotlinflow.databinding.ActivityRecyclerViewBinding
import com.navdroid.apicallwithkotlinflow.states.Status
import com.navdroid.apicallwithkotlinflow.viewModels.SingleNetworkCallViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SingleNetworkCallActivity : AppCompatActivity() {

    private val viewModel: SingleNetworkCallViewModel by inject()
    private lateinit var adapter: ApiUserAdapter

    private val binding: ActivityRecyclerViewBinding by lazy {
        ActivityRecyclerViewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter =
            ApiUserAdapter(
                arrayListOf()
            )
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.users.collect {
                    when (it.status) {
                        Status.SUCCESS -> {
                            binding.progressBar.visibility = View.GONE
                            it.data?.let { users -> renderList(users) }
                            binding.recyclerView.visibility = View.VISIBLE
                        }
                        Status.LOADING -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }
                        Status.ERROR -> {
                            // Handle Error
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(
                                this@SingleNetworkCallActivity,
                                it.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(users: List<ApiUser>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }
}
