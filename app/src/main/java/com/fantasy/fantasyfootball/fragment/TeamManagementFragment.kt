package com.fantasy.fantasyfootball.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.fantasy.fantasyfootball.MainApplication
import com.fantasy.fantasyfootball.databinding.FragmentTeamManagementBinding
import com.fantasy.fantasyfootball.viewModel.TeamManagementViewModel
import android.util.Log
import android.widget.ImageView
import androidx.fragment.app.setFragmentResultListener
import com.fantasy.fantasyfootball.R
import com.fantasy.fantasyfootball.constant.Enums
import com.fantasy.fantasyfootball.util.AuthService

class TeamManagementFragment : Fragment() {
    private lateinit var binding: FragmentTeamManagementBinding
    private val pickPlayerFragment = PickPlayerFragment.getInstance()
    private val viewModel: TeamManagementViewModel by viewModels {
        TeamManagementViewModel.Provider(
            (requireContext().applicationContext as MainApplication).playerRepo,
            (requireContext().applicationContext as MainApplication).userRepo,
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val position = PickTeamFragmentArgs.fromBundle(requireArguments()).position
//        setImageForPosition(position, binding)
        binding = FragmentTeamManagementBinding.inflate(layoutInflater)
        return binding.root
    }
    private var currentPosition: String? = null
    private var currentImageView: ImageView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val authService = AuthService.getInstance(requireContext())
        val user = authService.getAuthenticatedUser()
        if (user != null) {
            viewModel.getUserWithTeam(user.userId!!)
        }

        viewModel.userTeam.observe(viewLifecycleOwner) {
            binding.apply {
                binding.tvTeamName.text = it.team.name
                binding.tvBudget.text = "£" + it.team.budget.toString() + "m"
                binding.gk.setImageResource(R.drawable.tshirt3)
            }
        }

//        setFragmentResultListener("from_pick_player") { _, result ->
//            val refresh = result.getBoolean("refresh")
//            if (refresh) {
//                pickPlayerFragment.refresh("", "")
//            }
//        }
        setFragmentResultListener("player_info") { a, b ->
            val price = b.getFloat("price")
            val position = b.getString("position")
            if (position != null) {
                when (position) {
                    "GK" -> currentImageView = binding.gk
                    "LB" -> currentImageView = binding.lb
                    "LCB" -> currentImageView = binding.lcb
                    "RCB" -> currentImageView = binding.rcb
                    "RB" -> currentImageView = binding.rb
                    "LM" -> currentImageView = binding.lm
                    "LCM" -> currentImageView = binding.lcm
                    "RCM" -> currentImageView = binding.rcm
                    "RM" -> currentImageView = binding.rm
                    "LS" -> currentImageView = binding.ls
                    "RS" -> currentImageView = binding.rs
                }
                setImageForPosition(position, currentImageView!!)
            }
            Log.d("debug", price.toString())
        }


        binding.gk.setOnClickListener {
            val area = Enums.Area.Goalkeeper
            val action =
                TeamManagementFragmentDirections.actionTeamManagementFragmentToPickPlayerFragment(area.toString())
            NavHostFragment.findNavController(this).navigate(action)
            NavHostFragment.findNavController(this).navigate(action)
        }

        binding.lb.setOnClickListener {
            val area = Enums.Area.Defender
            val action =
                TeamManagementFragmentDirections.actionTeamManagementFragmentToPickPlayerFragment(area.toString())
            NavHostFragment.findNavController(this).navigate(action)
        }

        binding.lcb.setOnClickListener {
            val area = Enums.Area.Defender
            val action =
                TeamManagementFragmentDirections.actionTeamManagementFragmentToPickPlayerFragment(area.toString())
            NavHostFragment.findNavController(this).navigate(action)
        }

        binding.rcb.setOnClickListener {
            val area = Enums.Area.Defender
            val action =
                TeamManagementFragmentDirections.actionTeamManagementFragmentToPickPlayerFragment(area.toString())
            NavHostFragment.findNavController(this).navigate(action)
        }

        binding.lm.setOnClickListener {
            val area = Enums.Area.Midfielder
            val action =
                TeamManagementFragmentDirections.actionTeamManagementFragmentToPickPlayerFragment(area.toString())
            NavHostFragment.findNavController(this).navigate(action)
        }

        binding.lcm.setOnClickListener {
            val area = Enums.Area.Midfielder
            val action =
                TeamManagementFragmentDirections.actionTeamManagementFragmentToPickPlayerFragment(area.toString())
            NavHostFragment.findNavController(this).navigate(action)
        }

        binding.rcm.setOnClickListener {
            val area = Enums.Area.Midfielder
            val action =
                TeamManagementFragmentDirections.actionTeamManagementFragmentToPickPlayerFragment(area.toString())
            NavHostFragment.findNavController(this).navigate(action)
        }

        binding.rm.setOnClickListener {
            val area = Enums.Area.Midfielder
            val action =
                TeamManagementFragmentDirections.actionTeamManagementFragmentToPickPlayerFragment(area.toString())
            NavHostFragment.findNavController(this).navigate(action)
        }

        binding.ls.setOnClickListener {
            val area = Enums.Area.Striker
            val action =
                TeamManagementFragmentDirections.actionTeamManagementFragmentToPickPlayerFragment(area.toString())
            NavHostFragment.findNavController(this).navigate(action)
        }

        binding.rs.setOnClickListener {
            val area = Enums.Area.Striker
            val action =
                TeamManagementFragmentDirections.actionTeamManagementFragmentToPickPlayerFragment(area.toString())
            NavHostFragment.findNavController(this).navigate(action)
        }
    }

    fun setImageForPosition(position: String, imageView: ImageView) {
        currentPosition = position
        when (position) {
            "GK" -> imageView.setImageResource(R.drawable.tshirt2)
            "LB" -> imageView.setImageResource(R.drawable.tshirt3)
            "LCB" -> imageView.setImageResource(R.drawable.tshirt2)
            "RCB" -> imageView.setImageResource(R.drawable.tshirt3)
            "RB" -> imageView.setImageResource(R.drawable.tshirt3)
            "LM" -> imageView.setImageResource(R.drawable.tshirt3)
            "LCM" -> imageView.setImageResource(R.drawable.tshirt3)
            "RCM" -> imageView.setImageResource(R.drawable.tshirt3)
            "RM" -> imageView.setImageResource(R.drawable.tshirt3)
            "LS" -> imageView.setImageResource(R.drawable.tshirt3)
            "RS" -> imageView.setImageResource(R.drawable.tshirt3)
        }
    }
}