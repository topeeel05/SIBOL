package com.example.sibol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import retrofit2.Response
import com.example.sibol.databinding.FragmentHomeBinding
import androidx.lifecycle.liveData
import androidx.lifecycle.Observer

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofitService = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)

        val responseLiveData: LiveData<Response<Dataaa>> =
            liveData {
                val response = retrofitService.getDataaa()
                emit(response)
            }

        responseLiveData.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                val dataItem = response.body()
                dataItem?.let {
                    val dataList = it.menus
                    for (dataItem in dataList) {
                        val menuId = "Menu: ${dataItem.id} \n \n"
                        val menuName = "${dataItem.name} \n \n"
                        val menuImg = "${dataItem.image} \n \n"
                        val menuDescription = "${dataItem.description} \n \n"
                        val menuHtc = "How to Cook: ${dataItem.howtocook} \n \n"
                        val menuPrice = "Total Cost: ${dataItem.price} \n \n"
                        val menuIngre = "Ingredients: ${dataItem.ingredients} \n \n"
                        binding.txtId.append("$menuId$menuName$menuImg$menuDescription$menuIngre$menuHtc$menuPrice")
                    }
                }
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}