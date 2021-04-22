package com.example.simpleweatherapplication.state

import kotlinx.coroutines.Job
import java.util.concurrent.CancellationException
import java.util.concurrent.Semaphore


class DispatchedJobs {
    private val mLock: Semaphore by lazy { Semaphore(1, true) }
    private val mJobs = mutableListOf<Job>()

    fun add(job: Job) {
        try {
            mLock.acquire()
            mJobs.add(job)
        } finally {
            mLock.release()
        }
    }

    fun cancelPreviousJobs(includeLast: Boolean) {
        try {
            mLock.acquire()

            mJobs.forEachIndexed { index, job ->
                if (job.isActive && (includeLast || index != mJobs.size - 1)) {
                    job.cancel(CancellationException("Cancelled previous dispatches"))
                }
            }
        } finally {
            mLock.release()
        }
    }

    fun clearInactiveJobs() {
        try {
            mLock.acquire()
            mJobs.removeAll { !it.isActive }
        } finally {
            mLock.release()
        }
    }
}