package com.tanveer.fragmentactivity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.tanveer.fragmentactivity.databinding.FragmentActivity2Binding
import java.text.SimpleDateFormat
import java.util.Calendar
import kotlin.math.max
import kotlin.math.min

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentActivity2.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentActivity2 : Fragment(), ActivityInterface {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var mainActivity : MainActivity? = null
    var btnChangeActivityText : Button? = null
    var etEnter: EditText? = null
    var binding:FragmentActivity2Binding ? = null
    var simpleDateFormat = SimpleDateFormat("dd/MMM/yyyy")
    var timeFormat = SimpleDateFormat("hh:mm aa")
    private val TAG = "FragmentActivity2"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       mainActivity = activity as MainActivity
        mainActivity?.activityInterface = this
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentActivity2Binding.inflate(layoutInflater)
        return binding?.root
        //return inflater.inflate(R.layout.fragment_activity2, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         //btnChangeActivityText = view.findViewById(R.id.btnChangeActivityText)
        binding?.btnChangeActivityText?.setOnClickListener {
         mainActivity?.ChangeActivityText(binding?.etEnter?.text?.toString()?:"")
        }

        binding?.btnDate?.setOnClickListener {
            var datePickerDialog = DatePickerDialog(
                requireContext(),R.style.DatePickerStyle, { _, year, month, date ->
                    Log.e(TAG, "year $year month $month date $date")
                    var calendar = Calendar.getInstance()
                    calendar.set(year, month, date)
                    var formattedDate = simpleDateFormat.format(calendar.time)
                    binding?.btnDate?.setText(formattedDate)
                }
                ,Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DATE))
                    var calendar = Calendar.getInstance()
                     calendar.add(Calendar.DATE,-10)
                     datePickerDialog.datePicker.minDate = calendar.timeInMillis
                      calendar.add(Calendar.DATE,20)
                     datePickerDialog.datePicker.maxDate = calendar.timeInMillis
                datePickerDialog.show()
        }
        binding?.btnTime?.setOnClickListener {
             TimePickerDialog(
                requireContext(),R.style.TimePickerStyle,{_,hour, minute ->
                    Log.e(TAG,"hour $hour minute $minute")
                    var calendar = Calendar.getInstance()
                    calendar.set(Calendar.HOUR_OF_DAY,hour)
                    calendar.set(Calendar.MINUTE,minute)
                   var newCalendar = Calendar.getInstance()
                     newCalendar.set(Calendar.HOUR_OF_DAY,9)
                     var newCalendar1 = Calendar.getInstance()
                     newCalendar1.set(Calendar.HOUR_OF_DAY,6)
                     if (calendar.before(newCalendar)) {
                         Toast.makeText(
                             requireContext(),
                             "this time cannot set",
                             Toast.LENGTH_SHORT
                         ).show()
                     }
                      else if (calendar.after(newCalendar1)) {
                          Toast.makeText(
                              requireContext(),
                              "this time is not valid",
                              Toast.LENGTH_SHORT
                          ).show()
                      }
                     binding?.btnTime?.setText(timeFormat.format(calendar.time))
                        },
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE),
                false).show()
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentActivity2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentActivity2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    override fun ChangeFragmentText(string: String) {
        binding?.btnChangeActivityText?.setText(string)
    }
}


