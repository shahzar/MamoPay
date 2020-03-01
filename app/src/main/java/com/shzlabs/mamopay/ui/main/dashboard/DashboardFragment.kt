package com.shzlabs.mamopay.ui.main.dashboard

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.shzlabs.mamopay.R
import com.shzlabs.mamopay.di.components.AppComponent
import com.shzlabs.mamopay.ui.base.BaseFragment
import com.shzlabs.mamopay.util.display.Toaster
import com.shzlabs.mamopay.util.notification.NotificationHelper
import kotlinx.android.synthetic.main.dashboard_bottomsheet_layout.*
import kotlinx.android.synthetic.main.dashboard_content.*


class DashboardFragment : BaseFragment() {

    private val transactionsAdapter = TransactionsAdapter()

    companion object {
        fun newInstance() = DashboardFragment()
    }

    private lateinit var viewModel: DashboardViewModel

    override fun injectDependencies(diComponent: AppComponent) = diComponent.inject(this)

    override fun provideLayoutId(): Int = R.layout.dashboard_fragment

    override fun initView(view: View) {

        transaction_list.layoutManager = LinearLayoutManager(rootView.context)
        transaction_list.adapter = transactionsAdapter

        add_money.setOnClickListener {
            addMoney()
        }

    }

    override fun setupObservers() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DashboardViewModel::class.java)

        viewModel.onMoneyAdded.observe(viewLifecycleOwner, Observer {
            moneyAdded(it)
        })

        viewModel.balanceData.observe(viewLifecycleOwner, Observer {
            amount.text = it.toString()
        })

        viewModel.transactionData.observe(viewLifecycleOwner, Observer {
            transactionsAdapter.updateItems(it)
        })

        viewModel.onError.observe(viewLifecycleOwner, Observer { showError(rootView, it) })

        viewModel.getTransactionData()
    }

    private fun addMoney() {
        viewModel.addMoney(100)
    }

    private fun moneyAdded(amount: Int) {


        Toaster.show(rootView.context, "Money Added")

        // TODO: interviewer review
        // Show notification
        val channelId = getString(R.string.val_channel_gen_id)
        val channelName = getString(R.string.val_channel_gen_name)

        NotificationHelper.createChannel(rootView.context, channelId, channelName)

        NotificationHelper.showNotification(
            rootView.context,
            channelId,
            getString(R.string.label_notification_title_money_added),
            getString(R.string.label_notification_desc_money_added, viewModel.currency.value, amount.toString()))
    }



}


















