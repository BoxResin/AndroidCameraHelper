[ ![Download](https://api.bintray.com/packages/boxresin/maven/boxresin.android.camera%3Acameraview/images/download.svg) ](https://bintray.com/boxresin/maven/boxresin.android.camera%3Acameraview/_latestVersion)

## 주의사항
본 라이브러리는 `androidx` 패키지를 사용하는 프로젝트에서만 이용 가능합니다.

## 설치하기
1. `app` 모듈의 `build.gradle` 을 다음처럼 편집합니다.

```gradle
android {
    ...
}

dependencies {
    ...
    // 추가
    implementation 'boxresin.android.camera:cameraview:<버전>'
    ...
}
```

`<버전>`은 `x.x.x` 형식으로 변경해주세요. (현재 버전 [ ![Download](https://api.bintray.com/packages/boxresin/maven/boxresin.android.camera%3Acameraview/images/download.svg) ](https://bintray.com/boxresin/maven/boxresin.android.camera%3Acameraview/_latestVersion))

2. `AndroidManifest.xml`에 카메라 권한을 추가합니다.
```xml
<manifest ...>
    ...
    <!-- 추가 -->
    <uses-permission android:name = "android.permission.CAMERA"/>
    ...
</manifest>
```

## 시작하기
1. 원하는 레이아웃 파일(ex. `activity_main.xml`)에 `<CameraView/>`를 추가합니다.

```xml
<androidx.constraintlayout.ConstraintLayout ...>
    ...
    <!-- 카메라 뷰 -->
    <boxresin.android.camera.CameraView
        android:id = "@+id/camera_view"
        android:layout_width = "match_parent"
        android:layout_height = "match_parent"/>
    ...
</androidx.constraintlayout.ConstraintLayout>

```

2. 액티비티 파일(ex. `MainActivity.kt`)에서 원하는 시점에 카메라를 열고 해제합니다.
```kotlin
class MainActivity : AppCompatActivity()
{
    ...

    override fun onResume()
    {
        // CameraView 에 카메라 미리보기가 나타난다.
        camera_view.openCamera()
    }

    override fun onPause()
    {
        // CameraView 에서 카메라 미리보기를 없앤다.
        camera_view.releaseCamera()
    }
    
    ...
}
```
※ 카메라가 열려있는 동안에는 다른 카메라 앱을 사용할 수 없습니다.
