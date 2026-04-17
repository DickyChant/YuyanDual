package com.yuyan.imemodule.utils

import android.view.KeyCharacterMap
import android.view.KeyEvent

/**
 * Synthetic soft-key [KeyEvent]s are often built with [KeyEvent.ACTION_UP] only; on many API levels
 * [KeyEvent.getUnicodeChar] is 0 for those events even when [KeyEvent.getKeyCode] is a letter.
 * Long-press / popup paths commit literal text and do not depend on this.
 */
object KeyEventUnicodeCompat {
    fun resolveUnicodeChar(event: KeyEvent): Int {
        val direct = event.unicodeChar
        if (direct != 0) return direct
        if (event.keyCode == 0) return 0
        return try {
            KeyCharacterMap.load(KeyCharacterMap.VIRTUAL_KEYBOARD).get(event.keyCode, event.metaState)
        } catch (_: Throwable) {
            0
        }
    }
}
