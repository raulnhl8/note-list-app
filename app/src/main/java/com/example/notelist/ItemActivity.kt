package com.example.notelist


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.android.synthetic.main.layout_header.view.*
import kotlinx.android.synthetic.main.layout_item.*


class ItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_item)

        var viewPosition: Int = -1

        lbl_header.textView2.visibility = View.GONE

        lbl_save_button.bringToFront()

        intent = getIntent()

        if (intent.hasExtra("content") && intent.hasExtra("date") && intent.hasExtra("id")) {

            lbl_edittext.setText(intent.getStringExtra("content"))
            viewPosition = intent.getIntExtra("id", -1)
        }

        lbl_save_button.setOnClickListener {

            val intent = Intent()
            intent.putExtra("content", lbl_edittext.text.toString())
            intent.putExtra("id", viewPosition)

            setResult(1, intent)
            finish()
        }
    }
}