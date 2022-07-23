package br.com.zup.exerciciofirebaseauthentication.ui.createmessage.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.exerciciofirebaseauthentication.R
import br.com.zup.exerciciofirebaseauthentication.databinding.ActivityCreateMessageBinding
import br.com.zup.exerciciofirebaseauthentication.databinding.ActivityHomeBinding
import br.com.zup.exerciciofirebaseauthentication.ui.createmessage.viewmodel.CreateMessageViewModel
import br.com.zup.exerciciofirebaseauthentication.ui.favorite.view.FavoriteActivity
import br.com.zup.exerciciofirebaseauthentication.ui.favorite.view.FavoriteAdapter
import br.com.zup.exerciciofirebaseauthentication.ui.home.viewmodel.HomeViewModel
import br.com.zup.exerciciofirebaseauthentication.ui.login.view.LoginActivity

class CreateMessageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateMessageBinding
    private var messageList = mutableListOf<String>()

    private val adapter: CreateMessageAdapter by lazy {
        CreateMessageAdapter(arrayListOf())
    }

    private val viewModel: CreateMessageViewModel by lazy {
        ViewModelProvider(this)[CreateMessageViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        addNewMessage()
        setRecyclerView()
        goToFavoriteList()


    }

//    private fun intObserver() {
//        viewModel.characterResponse.observe(this.viewLifecycleOwner) {
//
//            when (it) {
//                is ViewState.Success -> {
//                    adapter.updateCharacterList(it.data.toMutableList())
//                }
//                is ViewState.Error -> {
//                    Toast.makeText(context, "${it.throwable.message}", Toast.LENGTH_LONG).show()
//                }
//                else -> {}
//            }
//        }
//    }
//
//    private fun initObserver() {
//        viewModel.coffeeResponse.observe(this) {
//            loadImage(it)
//        }
//
//        viewModel.message.observe(this) {
//            loadMessage(it)
//        }
//
//        viewModel.loading.observe(this) {
//            binding.pbLoading.isVisible = it == true
//        }
//    }


    private lateinit var mensagem: String

    private fun addNewMessage() {
        binding.bvAdicionar.setOnClickListener {
            val text = binding.etMessageText.text
            text?.let {
                if (text.isNotBlank()) {
                    messageList.add(text.toString())
                    adapter.updateMessageList(messageList)
                    Toast.makeText(this, "Mensagem adicionada a lista", Toast.LENGTH_LONG).show()
                    clearEditText()
                }
            }
            Toast.makeText(this, "Escreva uma mensagem primeiro", Toast.LENGTH_LONG).show()
        }
    }

    private fun clearEditText() {
        binding.etMessageText.text.clear()
    }

    private fun setRecyclerView() {
        binding.rvMessagelist.adapter = adapter
        binding.rvMessagelist.layoutManager = LinearLayoutManager(this)
    }

    private fun goToFavoriteList() {
        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this, FavoriteActivity::class.java))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}