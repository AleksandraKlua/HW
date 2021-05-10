package com.example.lesson1.networking.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lesson1.R

class MenuFragment : Fragment(),
    View.OnClickListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.btn_users).setOnClickListener(this)
        view.findViewById<View>(R.id.btn_albums).setOnClickListener(this)
        view.findViewById<View>(R.id.btn_post).setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val btnUserId = R.id.btn_users
        val btnAlbumsId = R.id.btn_albums
        val btnPostId = R.id.btn_post

        when (view.id) {
            btnAlbumsId -> {
                val albumsFragment: Fragment =
                    AlbumFragment()
                getFragment(albumsFragment)
            }
            btnUserId -> {
                val userFragment: Fragment =
                    UserFragment()
                getFragment(userFragment)
            }
            btnPostId -> {
                val postFragment: Fragment =
                    CreatePostFragment()
                getFragment(postFragment)
            }
        }
    }

    private fun getFragment(fragment: Fragment) {
        val ft = requireFragmentManager().beginTransaction()
        ft.replace(R.id.container, fragment).addToBackStack(null)
        ft.commit()
    }
}