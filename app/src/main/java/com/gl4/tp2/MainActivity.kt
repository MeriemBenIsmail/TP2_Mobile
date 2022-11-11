package com.gl4.tp2

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var matieres = arrayOf("Developpement Web", "Applications Mobiles", "Mathématiques", "Anglais")
    val spinner: Spinner by lazy { findViewById(R.id.spinner) }
    var students = ArrayList<Student>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, matieres)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val text = "la matière séléctionnée est : " + matieres[position]
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
            }
        }

        var genre: String = "male"
        for (i in 1..20) {
            genre = if (i % 2 == 0) {
                "male"
            } else {
                "female"
            }
            this.students.add(Student("ben foulen$i", "foulen$i", genre))
        }

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)

        var adapter = StudentAdapter(students)
        recyclerview.adapter = adapter
        val textInput: TextInputEditText = findViewById(R.id.textInputEditText)
        textInput.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                adapter = StudentAdapter(filter(s.toString()))
                recyclerview.adapter = adapter
            }
        })


    }


    fun filter(name: String): ArrayList<Student> {
        var filteredList = ArrayList<Student>()
        for (student in students) {
            if (student.nom.lowercase(Locale.ROOT)
                    .contains(name.lowercase(Locale.ROOT))
            ) {
                filteredList.add(student)
            }
        }
        return filteredList
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}