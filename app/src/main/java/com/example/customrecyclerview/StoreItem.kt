package com.example.customrecyclerview

import android.net.Uri

sealed interface LogoType {
    data class LogoURI(val uri: Uri) : LogoType
    data class LogoDrawable(val resId: Int) : LogoType
}

data class StoreItem(val name: String, val logo: LogoType)
