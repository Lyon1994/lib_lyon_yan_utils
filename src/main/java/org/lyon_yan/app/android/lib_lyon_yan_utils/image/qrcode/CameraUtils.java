package org.lyon_yan.app.android.lib_lyon_yan_utils.image.qrcode;

import android.annotation.TargetApi;
import android.hardware.Camera;

/**
 * (c) Livotov Labs Ltd. 2012
 * Date: 03/11/2014
 */
public class CameraUtils {
    public synchronized static Camera.Parameters initParametersV1(Camera.Parameters parameters){
        parameters.getPreviewSize();


        return parameters;
    }
}
