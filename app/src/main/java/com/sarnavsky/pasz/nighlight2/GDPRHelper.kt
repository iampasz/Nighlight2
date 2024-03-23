//package com.sarnavsky.pasz.nighlight2
//
//import android.app.Activity
//import com.google.android.ump.ConsentDebugSettings
//import com.google.android.ump.ConsentInformation
//import com.google.android.ump.ConsentRequestParameters
//import com.google.android.ump.UserMessagingPlatform
//
//class GDPRHelper {
//
//    companion object {
//
//         fun showGDPR(activity: Activity) {
//            val debugSettings = ConsentDebugSettings.Builder(activity)
//                .setDebugGeography(ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA)
//                //.addTestDeviceHashedId(StringTypes.GDPR_TEST_DEVICE_1.type)
//                //.addTestDeviceHashedId(StringTypes.GDPR_TEST_DEVICE_2.type)
//                .build()
//
//            val params = ConsentRequestParameters.Builder()
//                .setConsentDebugSettings(debugSettings)
//                .setTagForUnderAgeOfConsent(false)
//                .build()
//
//
//            val infoField = ConsentInformation.OnConsentInfoUpdateFailureListener {
//            }
//
//             val inform = ConsentInformation.OnConsentInfoUpdateSuccessListener {
//
//
//             }
//
//             val consentInformation = UserMessagingPlatform.getConsentInformation(activity)
//             consentInformation.requestConsentInfoUpdate(activity, params, inform, infoField)
//
//             loadForm(activity, consentInformation)
//
//             consentInformation.reset();
//        }
//
//        private fun loadForm(activity: Activity, consentInformation: ConsentInformation) {
//            // Loads a consent form. Must be called on the main thread.
//            UserMessagingPlatform.loadConsentForm(
//                activity,
//                { consentForm ->
//                    if (consentInformation.consentStatus == ConsentInformation.ConsentStatus.REQUIRED) {
//                        consentForm.show(
//                            activity
//                        ) {
//                            // Handle dismissal by reloading form.
//                            loadForm(activity, consentInformation)
//                        }
//                    }
//                }
//            ) {
//            }
//        }
//    }
//}