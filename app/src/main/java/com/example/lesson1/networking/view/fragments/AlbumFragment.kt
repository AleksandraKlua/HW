package com.example.lesson1.networking.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson1.R
import com.example.lesson1.networking.api.ApiResponse
import com.example.lesson1.networking.model.Album
import com.example.lesson1.networking.view.adapter.AlbumAdapter
import com.example.lesson1.networking.viewmodel.AlbumViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlbumFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_data_from_api, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.list)

        view.findViewById<View>(R.id.btn_back).setOnClickListener {
            val ft = requireFragmentManager().beginTransaction()
            ft.replace(
                R.id.container,
                MenuFragment()
            ).addToBackStack(null)
            ft.commit()
        }

        val service = ApiResponse.makeRetrofitRequest()

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getToAlbumFragment()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val albumViewModel: AlbumViewModel by viewModels()
                    albumViewModel.albumMutableLiveData.observe(
                        requireActivity(),
                        Observer<ArrayList<Album>> {
                            recyclerView.adapter =
                                AlbumAdapter(response.body()!!, requireActivity())
                            val linearLayoutManager = LinearLayoutManager(requireActivity())
                            recyclerView.layoutManager = linearLayoutManager
                            linearLayoutManager.scrollToPosition(0)
                        }
                    )
                } else {
                    Toast.makeText(
                        requireActivity(), "${response.code()}", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}