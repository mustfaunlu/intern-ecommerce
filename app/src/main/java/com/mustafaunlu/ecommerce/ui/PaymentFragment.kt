package com.mustafaunlu.ecommerce.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.mustafaunlu.ecommerce.R
import com.mustafaunlu.ecommerce.common.CreditCardTextFormatter
import com.mustafaunlu.ecommerce.databinding.FragmentPaymentBinding
import com.mustafaunlu.ecommerce.utils.checkMonthYear
import com.mustafaunlu.ecommerce.utils.isNullorEmpty
import com.mustafaunlu.ecommerce.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentFragment : Fragment() {

    private lateinit var binding: FragmentPaymentBinding

    private val monthList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
    private val yearList = listOf(2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030, 2031, 2032)

    private var monthValue = 0
    private var yearValue = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val monthAdapter = ArrayAdapter(requireContext(), R.layout.item_dropdown_menu, monthList)
        val yearAdapter = ArrayAdapter(requireContext(), R.layout.item_dropdown_menu, yearList)

        with(binding) {

            etCreditCardNumber.addTextChangedListener(CreditCardTextFormatter())

            actExpireOnMonth.setAdapter(monthAdapter)
            actExpireOnYear.setAdapter(yearAdapter)

            actExpireOnMonth.setOnItemClickListener { _, _, position, _ ->
                monthValue = monthList[position]
            }

            actExpireOnYear.setOnItemClickListener { _, _, position, _ ->
                yearValue = yearList[position]
            }

            btnPayNow.setOnClickListener {
                if (checkInfos(
                        etCardholderName,
                        etCreditCardNumber,
                        actExpireOnMonth,
                        actExpireOnYear,
                        etCvcCode,
                        etAddress
                    )
                )
                    requireView().showToast("Payment successful")
            }
        }
    }

    private fun checkInfos(
        cardHolderName: TextInputEditText,
        creditCardNumber: TextInputEditText,
        month: AutoCompleteTextView,
        year: AutoCompleteTextView,
        cvcCode: TextInputEditText,
        address: TextInputEditText,
    ): Boolean {
        val checkInfos = when {
            cardHolderName.isNullorEmpty(getString(R.string.please_enter_cardholder_name)).not() -> false
            creditCardNumber.isNullorEmpty(getString(R.string.please_enter_credit_card_number)).not() -> false
            month.checkMonthYear(monthValue, getString(R.string.please_select_month)).not() -> false
            year.checkMonthYear(yearValue, getString(R.string.please_select_year)).not() -> false
            cvcCode.isNullorEmpty(getString(R.string.please_enter_cvc_code)).not() -> false
            address.isNullorEmpty(getString(R.string.please_enter_address)).not() -> false
            else -> true
        }
        return checkInfos
    }
}