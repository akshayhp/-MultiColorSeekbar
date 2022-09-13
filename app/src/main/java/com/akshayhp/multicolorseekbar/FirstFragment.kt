package com.akshayhp.multicolorseekbar

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.RelativeLayout
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.akshayhp.multicolorseekbar.customview.CustomSeekBar.ProgressItem
import com.akshayhp.multicolorseekbar.databinding.FragmentFirstBinding


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }


        val progressItemList = ArrayList<ProgressItem>()
        // red span
        // red span
        var mProgressItem = ProgressItem()
        mProgressItem.progressItemPercentage = 20F
        mProgressItem.color = R.color.red
        progressItemList.add(mProgressItem)
        // blue span
        // blue span
        mProgressItem = ProgressItem()
        mProgressItem.progressItemPercentage = 40F
        mProgressItem.color = R.color.blue
        progressItemList.add(mProgressItem)
        // green span
        // green span
        mProgressItem = ProgressItem()
        mProgressItem.progressItemPercentage = 30F
        mProgressItem.color = R.color.green
        progressItemList.add(mProgressItem)
        // yellow span
        // yellow span
        mProgressItem = ProgressItem()
        mProgressItem.progressItemPercentage = 10F
        mProgressItem.color = R.color.yellow
        progressItemList.add(mProgressItem)

        binding.customSeekBar.setProgressList(progressItemList)
        binding.customSeekBar.progress = 50

        binding.customSeekBar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.markerTop.translationX = ((resources.displayMetrics.widthPixels-binding.markerTop.width)*(p1+1F)/100).coerceAtLeast(0F).coerceAtMost((resources.displayMetrics.widthPixels-binding.markerTop.width).toFloat())

                var parentLayoutWidth = (binding.shapeArrow.parent as RelativeLayout).width
                binding.shapeArrow.translationX = ((parentLayoutWidth-binding.shapeArrow.width)*(p1+1F)/100).coerceAtLeast(0F).coerceAtMost((parentLayoutWidth-binding.shapeArrow.width).toFloat())

                Log.e("Seekbar", "$p1  $parentLayoutWidth")
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        binding.customSeekBar.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.markerBottom.translationX = (resources.displayMetrics.widthPixels*0.5f)-(binding.markerBottom.width/2)
                binding.markerTop.translationX = resources.displayMetrics.widthPixels*0.8f
                binding.shapeArrow.translationX = (binding.shapeArrow.parent as RelativeLayout).width*0.8F
                //Do your operations here.
                binding.customSeekBar.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}