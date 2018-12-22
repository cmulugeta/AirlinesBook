package me.cmulugeta.airlinesbook.domain.executor

import io.reactivex.Scheduler

/**
 * Scheduler thread contract.
 */
interface SubscribeThread {
    val scheduler: Scheduler
}
