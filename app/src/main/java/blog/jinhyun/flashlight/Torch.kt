package blog.jinhyun.flashlight

import android.content.Context
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager

/**
 * 손전등 기능을 구현한 클래스.
 *
 * @constructor 카메라 아이디 얻기.
 *
 * @param context `CameraManager`를 얻기 위해 필요함.
 */
class Torch(context: Context) {
    /**
     * 플래시가 있고, 기기의 뒤에 있는 카메라의 아이디
     */
    private var cameraId: String? = null

    /**
     * 플래시를 켜기 위해 필요한 `CameraManager` 객체
     */
    private val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager

    init {
        cameraId = getCameraId()
    }

    /**
     * 카메라의 `id`를 얻는 함수.
     * 카메라가 없다면 `null` 반환.
     * @return cameraId
     */
    private fun getCameraId(): String? {
        /**
         * 기기가 가지고 있는 모든 카메라 정보
         */
        val cameraIds = cameraManager.cameraIdList
        // 카메라 정보 목록을 순회
        for (id in cameraIds) {
            // 카메라 상세 정보
            val info = cameraManager.getCameraCharacteristics(id)
            // 플래시 가능 여부
            val flashAvailable = info.get(CameraCharacteristics.FLASH_INFO_AVAILABLE)
            // 카메라 렌즈 방향
            val lensFacing = info.get(CameraCharacteristics.LENS_FACING)
            // 플래시 사용이 가능하고, 카메라가 기기의 뒷면에 있는 경우
            if (flashAvailable != null && flashAvailable && lensFacing != null && lensFacing == CameraCharacteristics.LENS_FACING_BACK) {
                return id
            }
        }
        // 원하는 카메라가 없는 경우
        return null
    }

    /**
     * 플래시를 켜는 함수
     */
    fun flashOn() {
        cameraId?.let {
            cameraManager.setTorchMode(it, true)
        }
    }

    /**
     * 플래시를 끄는 함수
     */
    fun flashOff() {
        cameraId?.let {
            cameraManager.setTorchMode(it, false)
        }
    }
}