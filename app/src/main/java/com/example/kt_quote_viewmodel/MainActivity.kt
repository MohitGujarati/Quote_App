package com.example.kt_quote_viewmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.kt_quote_viewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var mainviewModel: MainviewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainviewModel=ViewModelProvider(this,MainviewModelFactory(application)).get(MainviewModel::class.java)

        setQuote(mainviewModel.getQuote())



    }

    fun setQuote(quote: Quote){

        binding.quoteText.text=quote.text
        binding.quoteAuthor.text=quote.author
    }

    fun onPrevious(view: View) {
        setQuote(mainviewModel.previousQuote())

    }
    fun onNext(view: View) {

        setQuote(mainviewModel.nextQuote())

    }

    fun onShare(view: View) {
        val intent=Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT,mainviewModel.getQuote().text)
        startActivity(intent)

    }
}