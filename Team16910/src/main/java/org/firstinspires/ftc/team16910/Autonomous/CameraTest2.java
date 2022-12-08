package org.firstinspires.ftc.team16910.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

@Autonomous(name= "CameraTest2")
public class CameraTest2 extends LinearOpMode
{
    private OpenCvInternalCamera cam;
    private CVPipeline openSleeve;

    public void runOpMode() throws InterruptedException
    {
        // Initialize Hardware
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        cam = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);

        openSleeve = new CVPipeline();

        cam.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);

        cam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                cam.setPipeline(openSleeve);
                cam.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
            }
            @Override
            public void onError(int errorCode)
            {
                //camera not opened
            }
        });
        waitForStart();
        if(!opModeIsActive()) {return;}

        int location = openSleeve.getDestination(); //scan

        if (location == 1) {
            telemetry.addData("Park1: ", "Magenta");
            telemetry.update();
        }
        if (location == 2)
        {
            telemetry.addData("Park2: ", "Yellow");
            telemetry.update();
        }
        if (location == 3) {
            telemetry.addData("Park3: ", "Cyan");
            telemetry.update();
        }
    }
}


// magenta 300
// yellow 60
//cyan 180
// H values /2

