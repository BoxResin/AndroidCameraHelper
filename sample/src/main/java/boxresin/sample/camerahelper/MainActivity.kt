package boxresin.sample.camerahelper

import android.Manifest.permission
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat.checkSelfPermission
import kotlinx.android.synthetic.main.activity_main.*


/**
 * 카메라 열기 권한 요청
 */
private const val REQ_OPEN_CAMERA = 0


/**
 * # 메인 액티비티
 *
 * @author Minsuk Eom
 * @since v0.1.0
 */
class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume()
    {
        super.onResume()

        // 카메라 권한이 있으면 카메라를 연다.
        if (checkSelfPermission(baseContext, permission.CAMERA) == PERMISSION_GRANTED)
            camera_view.openCamera()

        // 카메라 권한이 없으면 요청한다.
        else
            requestPermissions(this, arrayOf(permission.CAMERA), REQ_OPEN_CAMERA)
    }

    override fun onPause()
    {
        // 카메라를 해제한다.
        camera_view.releaseCamera()

        super.onPause()
    }

    /**
     * 권한 요청 결과가 나왔을 때 호출된다.
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray)
    {
        // 카메라 열기 권한 요청
        if (requestCode == REQ_OPEN_CAMERA)
        {
            // 권한이 허용되었으면 카메라를 연다.
            if (grantResults.all { it == PERMISSION_GRANTED})
                camera_view.openCamera()
        }
    }
}
