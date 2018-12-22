package me.cmulugeta.airlinesbook.domain.executor.scheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import me.cmulugeta.airlinesbook.domain.executor.ExecutionThread
import javax.inject.Inject

/**
 * UIThread is a Scheduler provider, which provides UI scheduler.
 */
class UIThread @Inject constructor() : ExecutionThread {

    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}