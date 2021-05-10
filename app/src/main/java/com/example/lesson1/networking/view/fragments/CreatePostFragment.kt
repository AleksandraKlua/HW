package com.example.lesson1.networking.view.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lesson1.R
import com.example.lesson1.networking.api.ApiResponse
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreatePostFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleTextView = view.findViewById<TextInputEditText>(R.id.title_field)
        val bodyTextView = view.findViewById<TextInputEditText>(R.id.body_field)

        view.findViewById<Button>(R.id.btn_send).setOnClickListener {
            sendPost(titleTextView, bodyTextView)
        }
        view.findViewById<Button>(R.id.btn_back).setOnClickListener {
            val ft = requireFragmentManager().beginTransaction()
            ft.replace(
                R.id.container,
                MenuFragment()
            ).addToBackStack(null)
            ft.commit()
        }
    }

    fun sendPost(titleTextView: TextInputEditText, bodyTextView: TextInputEditText) {
        val titleText = titleTextView.text.toString()
        val bodyText = bodyTextView.text.toString()
        val service = ApiResponse.makeRetrofitRequest()

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.sendToPostFragment(titleText, bodyText, 1)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val toast =
                        Toast.makeText(
                            context,
                            "OK: " + response.code().toString(),
                            Toast.LENGTH_SHORT
                        )
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                } else {
                    val toast =
                        Toast.makeText(context, "Something went wrong...", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                }
            }
        }
    }
}