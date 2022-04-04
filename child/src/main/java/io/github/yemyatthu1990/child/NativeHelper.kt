package io.github.yemyatthu1990.child

class NativeHelper {
    companion object {
        var nativeHelper: NativeHelper? = null
        fun getInstance(): NativeHelper {
            if (nativeHelper == null)
                nativeHelper = NativeHelper()
            try {
                System.loadLibrary("locoparentis_native")
            }
            catch (e: SecurityException) {}
            catch (e: UnsatisfiedLinkError) {}
            catch (e: NullPointerException) {}
            return nativeHelper!!
        }
    }
    external fun initializeUninstallListener()
}