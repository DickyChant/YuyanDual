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
        val keyCode = event.keyCode
        if (keyCode == 0) return 0
        val viaMap = try {
            KeyCharacterMap.load(KeyCharacterMap.VIRTUAL_KEYBOARD).get(keyCode, event.metaState)
        } catch (_: Throwable) {
            0
        }
        if (viaMap != 0) return viaMap
        val shifted = event.metaState and (KeyEvent.META_SHIFT_ON or KeyEvent.META_CAPS_LOCK_ON) != 0
        return when (keyCode) {
            in KeyEvent.KEYCODE_A..KeyEvent.KEYCODE_Z ->
                ((if (shifted) 'A'.code else 'a'.code) + (keyCode - KeyEvent.KEYCODE_A))
            in KeyEvent.KEYCODE_0..KeyEvent.KEYCODE_9 ->
                ('0'.code + (keyCode - KeyEvent.KEYCODE_0))
            else -> 0
        }
    }
}
