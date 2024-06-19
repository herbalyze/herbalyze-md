package com.dayeeen.herbalyze.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dayeeen.herbalyze.R
import com.dayeeen.herbalyze.databinding.ActivityMainBinding
import com.dayeeen.herbalyze.ui.adapters.PlantsAdapter
import com.dayeeen.herbalyze.viewmodel.MainViewModel
import com.dayeeen.herbalyze.viewmodel.ViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private val mvm by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set Toolbar as ActionBar
        setSupportActionBar(binding.topAppBar)

        isFirebaseLogin()
        showPlants()

        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, UploadActivity::class.java))
        }

    }

    private fun isFirebaseLogin() {
        // Initialize Firebase Auth
        auth = Firebase.auth

        val firebaseUser = auth.currentUser

        if (firebaseUser == null) {
            // Not signed in, launch the Login activity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }
    }

    private fun showPlants() {
        val adapter = PlantsAdapter()
        binding.rvPlants.adapter = adapter
        binding.rvPlants.layoutManager = LinearLayoutManager(this)

        showLoading(true)
        mvm.plants.observe(this) { plants ->
            showLoading(false)
            adapter.submitList(plants)
        }

//        mvm.error.observe(this) { errorMessage ->
//            showLoading(false)
//            Toast.makeText(this, "Error fetching plants: $errorMessage", Toast.LENGTH_SHORT).show()
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("MainActivity", "onOptionsItemSelected called with itemId: ${item.itemId}")
        return when (item.itemId) {
            R.id.profile_option -> {
                // Direct to profile page
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.saved_plants_option -> {
                // Direct to saved plants page
                true
            }
            R.id.logout_option -> {
                // Handle logout logic
                signOut()
                Toast.makeText(this, "Logout Success", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun signOut() {
        lifecycleScope.launch {
            val credentialManager = CredentialManager.create(this@MainActivity)

            auth.signOut()
            credentialManager.clearCredentialState(ClearCredentialStateRequest())
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}