package com.elektra.ektp.ektputilies.smsreader

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status


class EKTPSMSBrodcastReciver : BroadcastReceiver() {
    var smsBroadcastReceiverListener : SmsReceiveListener? = null
    override fun onReceive(context: Context, intent: Intent?) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION == intent?.action) {
            val extras = intent.extras
            val status = extras?.get(SmsRetriever.EXTRA_STATUS) as Status
            when(status.statusCode){
                CommonStatusCodes.SUCCESS -> {
                    val messageIntent = extras.getParcelable<Intent>(SmsRetriever.EXTRA_CONSENT_INTENT)
                    smsBroadcastReceiverListener?.onSuccess(messageIntent)
                }
                CommonStatusCodes.TIMEOUT -> {
                    smsBroadcastReceiverListener?.onFailure()
                }
            }
        }
    }

    interface SmsReceiveListener {
        fun onSuccess(intent: Intent?)
        fun onFailure()
    }
}