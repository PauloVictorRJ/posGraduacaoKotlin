package br.edu.projetofinalkotlin.util

sealed class DataResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : DataResult<T>() {
        override fun toString() = "Success[data=$data]"
    }

    data class Error(val exception: Exception, val message: String? = null) : DataResult<Nothing>() {
        override fun toString() = "Error[exception=$exception]"
    }

    object Loading : DataResult<Nothing>() {
        override fun toString() = "Loading"
    }
}