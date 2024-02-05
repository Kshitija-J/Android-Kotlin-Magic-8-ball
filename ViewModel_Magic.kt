
    private const val TAG = "ViewModel_Magic"
    class ViewModel_Magic : ViewModel() {
        init {
            Log.d(TAG, "ViewModel instance created")
        }
        override fun onCleared() {
            super.onCleared()
            Log.d(TAG, "ViewModel instance about to be destroyed")
        }
    }


