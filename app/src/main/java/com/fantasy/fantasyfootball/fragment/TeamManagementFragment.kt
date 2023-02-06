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
    private val viewModel: TeamManagementViewModel by viewModels {
        TeamManagementViewModel.Provider(
            (requireContext().applicationContext as MainApplication).playerRepo,
            (requireContext().applicationContext as MainApplication).teamRepo,
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

        var _position = ""
        NavHostFragment.findNavController(this).currentBackStackEntry?.savedStateHandle?.getLiveData<String>(
            "key"
        )?.observe(viewLifecycleOwner) {
            _position = it
        }

        val authService = AuthService.getInstance(requireContext())
        val user = authService.getAuthenticatedUser()

        if (user != null) {
            viewModel.getUserWithTeam(user.userId!!)
        }

//        setFragmentResultListener(Enums.Result.ADD_PLAYER_RESULT.name) { _, result ->
//            val price = result.getFloat(Enums.Result.PLAYER_PRICE.name)
//            val playerId = result.getInt(Enums.Result.PLAYER_ID.name)
//            _playerId = playerId
//
////            val position = result.getString(Enums.Result.POSITION_BUTTON.name)
////            if (position != null) {
////                setImageForPosition(position)
////            }
//            Log.d("debug", price.toString())
//        }

        viewModel.userTeam.observe(viewLifecycleOwner) {
            viewModel.getTeamWithPlayersByTeamId(it.team.teamId!!)

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
                tvPoints.text = it.team.points.toString()
                tvTeamName.text = it.team.name
                tvBudget.text = "£" + it.team.budget.toString() + "m"
            }
        }

        viewModel.teamWithPlayers.observe(viewLifecycleOwner) {
            it.players.forEach { player ->
//                binding.tvPoints.text = player.name

                if (player.area == Enums.Area.Goalkeeper) {
                    val color = setShirtColor(Enums.ShirtColor.DARKRED)
                    setImageForPosition("GK", color)
                }
            }
        }

        binding.gk.setOnClickListener {
            val area = Enums.Area.Goalkeeper
            val position = Enums.Position.GK
            val action =
                TeamManagementFragmentDirections.actionTeamManagementFragmentToPickPlayerFragment(
                    area.toString(), position.toString()
                )
            NavHostFragment.findNavController(this).navigate(action)
            NavHostFragment.findNavController(this).navigate(action)
        }

        binding.lb.setOnClickListener {
            val area = Enums.Area.Defender
            val position = Enums.Position.LB
            val action =
                TeamManagementFragmentDirections.actionTeamManagementFragmentToPickPlayerFragment(
                    area.toString(), position.toString()
                )
            NavHostFragment.findNavController(this).navigate(action)
        }

        binding.lcb.setOnClickListener {
            val area = Enums.Area.Defender
            val position = Enums.Position.LCB
            val action =
                TeamManagementFragmentDirections.actionTeamManagementFragmentToPickPlayerFragment(
                    area.toString(), position.toString()
                )
            NavHostFragment.findNavController(this).navigate(action)
        }

        binding.rcb.setOnClickListener {
            val area = Enums.Area.Defender
            val position = Enums.Position.RCB
            val action =
                TeamManagementFragmentDirections.actionTeamManagementFragmentToPickPlayerFragment(
                    area.toString(), position.toString()
                )
            NavHostFragment.findNavController(this).navigate(action)
        }

        binding.lm.setOnClickListener {
            val area = Enums.Area.Midfielder
            val position = Enums.Position.LM
            val action =
                TeamManagementFragmentDirections.actionTeamManagementFragmentToPickPlayerFragment(
                    area.toString(), position.toString()
                )
            NavHostFragment.findNavController(this).navigate(action)
        }

        binding.lcm.setOnClickListener {
            val area = Enums.Area.Midfielder
            val position = Enums.Position.LCM
            val action =
                TeamManagementFragmentDirections.actionTeamManagementFragmentToPickPlayerFragment(
                    area.toString(), position.toString()
                )
            NavHostFragment.findNavController(this).navigate(action)
        }

        binding.rcm.setOnClickListener {
            val area = Enums.Area.Midfielder
            val position = Enums.Position.RCM
            val action =
                TeamManagementFragmentDirections.actionTeamManagementFragmentToPickPlayerFragment(
                    area.toString(), position.toString()
                )
            NavHostFragment.findNavController(this).navigate(action)
        }

        binding.rm.setOnClickListener {
            val area = Enums.Area.Midfielder
            val position = Enums.Position.RM
            val action =
                TeamManagementFragmentDirections.actionTeamManagementFragmentToPickPlayerFragment(
                    area.toString(), position.toString()
                )
            NavHostFragment.findNavController(this).navigate(action)
        }

        binding.ls.setOnClickListener {
            val area = Enums.Area.Striker
            val position = Enums.Position.LS
            val action =
                TeamManagementFragmentDirections.actionTeamManagementFragmentToPickPlayerFragment(
                    area.toString(), position.toString()
                )
            NavHostFragment.findNavController(this).navigate(action)
        }

        binding.rs.setOnClickListener {
            val area = Enums.Area.Striker
            val position = Enums.Position.RS
            val action =
                TeamManagementFragmentDirections.actionTeamManagementFragmentToPickPlayerFragment(
                    area.toString(), position.toString()
                )
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
    private fun setImageForPosition(position: String, color: Int) {
        when (position) {
            "GK" -> binding.gk.setImageResource(color)
            "LB" -> binding.lb.setImageResource(R.drawable.yellow_shirt)
            "LCB" -> binding.lcb.setImageResource(R.drawable.yellow_shirt)
            "RCB" -> binding.rcb.setImageResource(R.drawable.yellow_shirt)
            "RB" -> binding.rb.setImageResource(R.drawable.yellow_shirt)
            "LM" -> binding.lm.setImageResource(R.drawable.yellow_shirt)
            "LCM" -> binding.lcm.setImageResource(R.drawable.yellow_shirt)
            "RCM" -> binding.rcm.setImageResource(R.drawable.yellow_shirt)
            "RM" -> binding.rm.setImageResource(R.drawable.yellow_shirt)
            "LS" -> binding.ls.setImageResource(R.drawable.yellow_shirt)
            "RS" -> binding.rs.setImageResource(R.drawable.yellow_shirt)
        }
    }

    private fun setShirtColor(color: Enums.ShirtColor): Int {
        return when (color) {
            Enums.ShirtColor.DARKRED -> R.drawable.red_shirt
            else -> R.drawable.shirt2
        }
    }
}