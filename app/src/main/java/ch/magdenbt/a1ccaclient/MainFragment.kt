//package ch.magdenbt.a1ccaclient
//
//import android.os.Bundle
//import android.view.View
//import androidx.fragment.app.Fragment
//import androidx.navigation.fragment.NavHostFragment
//import androidx.navigation.ui.NavigationUI
//import ch.magdenbt.a1ccaclient.databinding.FragmentMainBinding
//
//class MainFragment : Fragment(R.layout.fragment_main) {
//
//    private lateinit var binding: FragmentMainBinding
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding = FragmentMainBinding.bind(view)
//
//        val navHost = childFragmentManager.findFragmentById(R.id.mainGraphContainer) as NavHostFragment
//
//        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController = navHost.navController)
//
//    }
//}