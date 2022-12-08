package org.firstinspires.ftc.team16910.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team16910.Hardware.SpaghettiHardware;
import org.firstinspires.ftc.team16910.drive.SampleMecanumDrive;
import org.firstinspires.ftc.team16910.trajectorysequence.TrajectorySequence;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

@Autonomous(name= "Right")
public class Right extends LinearOpMode
{
    //yellow location 2
    //magenta location 1
    //cyan location 3

    private SampleMecanumDrive drive;
    private final Pose2d start = new Pose2d(-36,64,Math.toRadians(-90));
    private TrajectorySequence toScan, toLow, toMidPoint, toMidPointB, moveBackToJunc,
            toMed, toHigh, toHighB, toCones, moveUp, moveUpCone, moveBack, adjust, adjust2,
            park1,park3;

    private OpenCvInternalCamera cam;
    private CVPipeline openSleeve;

    public void runOpMode() throws InterruptedException
    {
        // Initialize Hardware
        SpaghettiHardware hardware = new SpaghettiHardware();
        hardware.init(hardwareMap);
        Functions function = new Functions(hardware);

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
                cam.startStreaming(360, 320, OpenCvCameraRotation.UPRIGHT);
            }
            @Override
            public void onError(int errorCode)
            {
                //camera not opened
            }
        });

        // Initialize Mecanum Drive
        drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(start);
        buildTrajectories();

        waitForStart();
        if(!opModeIsActive()) {return;}

        // cycle one
        drive.followTrajectorySequence(adjust);
        function.halfClaw();
        drive.followTrajectorySequence(adjust2);
        function.wait(1000,telemetry);
        function.closeClaw();
        function.wait(1000,telemetry);

        function.moveLift(800);
        function.wait(2400, telemetry);

        int location = openSleeve.getDestination(); //scan

        drive.followTrajectorySequence(toHigh);

        function.moveLift(950);
        function.wait(2600, telemetry);

        drive.followTrajectorySequence(moveUp);

        function.wait(1000, telemetry);

        function.openClaw();

        function.moveLift(-2200);
        function.wait(3200, telemetry);

        drive.followTrajectorySequence(moveBack);

        if (location ==1)
            drive.followTrajectorySequence(park1);
        if (location == 3)
            drive.followTrajectorySequence(park3);
    }
    private void moveCycle(Functions function,int height)
    {}
    private void buildTrajectories()
    {

        adjust = drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                .forward(3.2)
                .build();

        adjust2 = drive.trajectorySequenceBuilder(adjust.end())
                .back(3)
                .build();

        toHigh = drive.trajectorySequenceBuilder(adjust2.end())
                .forward(50)
                .turn(Math.toRadians(42))
                .build();

        moveUp = drive.trajectorySequenceBuilder(toHigh.end())
                .forward(4)
                .build();

        moveBack = drive.trajectorySequenceBuilder(moveUp.end())
                .back(4)
                .turn(Math.toRadians(-42))
                .build();

        park1 = drive.trajectorySequenceBuilder(moveBack.end())
                .turn(Math.toRadians(90))
                .forward(10)
                .build();
        park3 = drive.trajectorySequenceBuilder(moveBack.end())
                .turn(Math.toRadians(-90))
                .forward(10)
                .build();
    }
}

