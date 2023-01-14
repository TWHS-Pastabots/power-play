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
                cam.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
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
        // updated
        function.wait(500,telemetry);
        int location = openSleeve.getDestination(); //scan

        telemetry.addData("location: ", location);
        telemetry.update();

        drive.followTrajectorySequence(adjust);

        function.halfClaw();
        drive.followTrajectorySequence(adjust2);
        function.moveLift(1000);
        function.wait(500, telemetry);
        function.closeClaw();

      //  function.moveLift(-500);
      //  function.wait(500, telemetry);


        function.wait(800,telemetry);
        drive.followTrajectorySequence(toHigh);

        function.moveLift(-4600);
        function.wait(10000, telemetry);


        //function.moveLift(-4100);
        //function.wait(10000, telemetry);

        drive.followTrajectorySequence(moveUp);
        function.wait(2800, telemetry);
        function.openClaw();
        function.moveLift(2000);
        function.wait(1000, telemetry);
        drive.followTrajectorySequence(moveBack);
        function.wait(1000,telemetry);

        if (location ==1)
            drive.followTrajectorySequence(park1);
        function.wait(1000,telemetry);
        if (location == 3)
            drive.followTrajectorySequence(park3);
        function.wait(1000,telemetry);
        if (location == 2)
        {
            telemetry.addData("Position: ", "parked");
            telemetry.update();
            function.wait(1000, telemetry);
        }



    }
    private void moveCycle(Functions function,int height)
    {



    }
    private void buildTrajectories()
    {

        adjust = drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                .forward(2.8)
                .build();

        adjust2 = drive.trajectorySequenceBuilder(adjust.end())
                .back(2.3)
                .build();

        toHigh = drive.trajectorySequenceBuilder(adjust2.end())
                .forward(51)
                .turn(Math.toRadians(47.2))
                .build();

        moveUp = drive.trajectorySequenceBuilder(toHigh.end())
                .forward(2.8)
                .build();

        moveBack = drive.trajectorySequenceBuilder(moveUp.end())
                .back(3.7)
                .turn(Math.toRadians(-47.2))
                .build();

        park1 = drive.trajectorySequenceBuilder(moveBack.end())
                .turn(Math.toRadians(90))
                .forward(21)
                .build();
        park3 = drive.trajectorySequenceBuilder(moveBack.end())
                .turn(Math.toRadians(-90))
                .forward(23)
                .build();
    }
}

