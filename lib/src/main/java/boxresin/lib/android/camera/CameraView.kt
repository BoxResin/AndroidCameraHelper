package boxresin.lib.android.camera

import android.Manifest
import android.content.Context
import android.hardware.Camera
import android.util.AttributeSet
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.WindowManager


/**
 * # 카메라 뷰
 *
 * @author Minsuk Eom
 * @since v0.1.0
 */
class CameraView(context: Context, attrs: AttributeSet? = null) : SurfaceView(context, attrs),
        SurfaceHolder.Callback
{
    private var camera: Camera? = null


    init
    {
        // 카메라 미리보기를 띄울 표면 홀더에 콜백 설정
        holder.addCallback(this)
    }

    /**
     * 카메라를 열고, 카메라 뷰에 미리보기를 띄운다.
     * > ※ [CAMERA][Manifest.permission.CAMERA] 권한이 필요하다.
     */
    fun openCamera()
    {
        // 이미 카메라가 열려있는 상황을 대비해 카메라를 닫고 다시 연다.
        releaseCamera()
        camera = Camera.open()

        // 스마트폰의 화면 방향에 따라 카메라 각도를 조절한다.
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as? WindowManager
        when (windowManager?.defaultDisplay?.rotation)
        {
            // 카메라는 기본적으로 옆으로 누워있기 때문에 세로 모드일 때 방향을 90˚로 설정한다.
            Surface.ROTATION_0 -> camera?.setDisplayOrientation(90)
            Surface.ROTATION_90 -> camera?.setDisplayOrientation(0)
            Surface.ROTATION_180 -> camera?.setDisplayOrientation(270)
            Surface.ROTATION_270 -> camera?.setDisplayOrientation(180)
        }

        // 카메라 미리보기를 시작한다.
        camera?.setPreviewDisplay(holder)
        camera?.startPreview()
    }

    /**
     * 카메라를 해제한다. 카메라 뷰에 나타나던 미리보기가 사라진다.
     * > ※ 카메라가 열려있지 않은 상태에서 이 함수를 호출하면 아무 일도 일어나지 않는다.
     */
    fun releaseCamera()
    {
        // 카메라 미리보기를 정지한다.
        camera?.stopPreview()
        camera?.setPreviewDisplay(null)

        // 카메라를 해제한다.
        camera?.release()
        camera = null
    }

    /**
     * 카메라 미리보기를 띄울 표면이 생성되었을 때 호출된다.
     */
    override fun surfaceCreated(holder: SurfaceHolder)
    {
        // 카메라 미리보기를 띄울 표면 홀더를 설정한다.
        camera?.setPreviewDisplay(holder)
    }

    /**
     * 카메라 미리보기를 띄울 표면이 제거되었을 때 호출된다.
     */
    override fun surfaceDestroyed(holder: SurfaceHolder)
    {
        // 카메라 미리보기를 띄울 표면 홀더를 제거한다.
        camera?.setPreviewDisplay(null)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}
}
