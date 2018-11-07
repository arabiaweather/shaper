package com.osama.shaper

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.disposables.CompositeDisposable

open class BaseFragment : Fragment() {
    protected val fragmentComponentManager: FragmentComponentManager<*> = FragmentComponentManager(this)
    protected val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponentManager.triggerOnCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentComponentManager.triggerOnCreateView(inflater, savedInstanceState)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentComponentManager.triggerOnCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        fragmentComponentManager.triggerOnResume()
    }

    override fun onStop() {
        super.onStop()
        fragmentComponentManager.triggerOnStop()
    }

    override fun onStart() {
        super.onStart()
        fragmentComponentManager.triggerOnStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentComponentManager.triggerOnDestroy()
        disposables.dispose()
        disposables.clear()
    }
}
