package com.yuyan.imemodule.utils

import android.util.Log
import org.json.JSONObject

/**
 * Debug session 8f8da0 — NDJSON-style lines to logcat for adb capture into `.cursor/debug-8f8da0.log`.
 */
object AgentDebugLog {
    private const val TAG = "AGENT_8F8DA0"

    // #region agent log
    fun line(hypothesisId: String, location: String, message: String, data: Map<String, Any?>) {
        val payload = JSONObject()
        payload.put("sessionId", "8f8da0")
        payload.put("hypothesisId", hypothesisId)
        payload.put("location", location)
        payload.put("message", message)
        payload.put("timestamp", System.currentTimeMillis())
        val d = JSONObject()
        data.forEach { (k, v) -> d.put(k, v?.toString() ?: "null") }
        payload.put("data", d)
        Log.w(TAG, payload.toString())
    }
    // #endregion
}
