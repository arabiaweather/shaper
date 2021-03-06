package com.osama.shaper

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import io.reactivex.disposables.CompositeDisposable


abstract class FragmentComponent<T : Fragment> {

    private var isContentViewSet: Boolean = false


    protected lateinit var fragment: T

    private var inflater: LayoutInflater? = null

    protected val context: Context?
        get() = fragment.context

    protected val activity: Activity?
        get() = fragment.activity

    protected val disposables = CompositeDisposable()

    open fun onCreate(mSavedInstanceState: Bundle?) {}

    open fun onCreateView(@NonNull fragment: T, inflater: LayoutInflater, mSavedInstanceState: Bundle?) {
        this.fragment = fragment
        this.inflater = inflater
    }

    open fun onViewCreated(@NonNull fragment: T, view: View, mSavedInstanceState: Bundle?) {}

    open fun onResume(@NonNull fragment: T) {

    }

    open fun onStop(@NonNull fragment: T) {
        disposables.dispose()
        disposables.clear()
    }

    open fun onStart(@NonNull fragment: T) {

    }

    open fun onDestroy(@NonNull fragment: T) {

    }

    protected fun setContentView(@LayoutRes layout: Int, @IdRes hostLayout: Int): View? {
        return if (!isContentViewSet) {
            isContentViewSet = true
            LayoutInflater.from(context).inflate(layout, activity?.findViewById(hostLayout))
        } else null
    }
}
