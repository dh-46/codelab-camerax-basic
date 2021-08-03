package tw.dh46.codelab.camerax

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.ImageCapture
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

// 這個不太知道是甚麼東西@@
typealias LumaListener = (luma: Double) -> Unit

class MainActivity : AppCompatActivity() {

    private var imageCapture: ImageCapture? = null

    /**
     * 相片輸出位置
     */
    private lateinit var outputDirectory: File

    /**
     * 相機的執行緒?
     */
    private lateinit var cameraExecutor: ExecutorService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. 檢查相機權限
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            );
        }

        // 因為有 kotlin-android-extensions 所以可以直接呼叫 View
        camera_capture_button.setOnClickListener {
            takePhoto()
        }

        outputDirectory = getOutputDirectory()

        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    // ---------------------------------------------------------------

    /**
     * 取得照片儲存路徑
     */
    private fun getOutputDirectory(): File {

        // 建立資料夾
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply {
                mkdirs()
            }
        }

        return if (mediaDir != null && mediaDir.exists())
            mediaDir else filesDir
    }

    /**
     * 拍照
     */
    private fun takePhoto() {
        TODO("Not yet implemented")
    }

    /**
     * 開啟相機
     */
    private fun startCamera() {
        TODO("Not yet implemented")
    }

    /**
     * 檢查所有權限
     */
    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        // 輪巡檢查權限
        ContextCompat.checkSelfPermission(
            baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    // ---------------------------------------------------------------

    /**
     * companion object (伴生物件)
     * - 不希望此類別(class)是 singleton，但又需要靜態區塊。
     * - 是隸屬於某個 class 底下的單一實體
     * - 基本上取代了 java class 對應的 static 區塊
     *
     */
    companion object {

        private const val TAG = "CodeLab_CameraX"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }
}