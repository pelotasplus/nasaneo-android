package com.example.nasaneo.di

import android.app.Activity
import androidx.fragment.app.Fragment

@Suppress("UNCHECKED_CAST")
fun <Injector> Fragment.getComponent() =
    (requireContext().applicationContext as InjectorProvider).appComponent() as Injector

@Suppress("UNCHECKED_CAST")
fun <Injector> Activity.getComponent() =
    (applicationContext as InjectorProvider).appComponent() as Injector
