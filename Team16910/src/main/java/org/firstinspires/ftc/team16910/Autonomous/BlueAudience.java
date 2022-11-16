package org.firstinspires.ftc.team16910.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team16910.Hardware.SpaghettiHardware;
import org.firstinspires.ftc.team16910.drive.SampleMecanumDrive;

@Autonomous(name= "BlueAudience")
public class BlueAudience extends LinearOpMode
{
    // Audience- blue on left side, red right
    private SampleMecanumDrive drive;
    private final Pose2d start = new Pose2d(-36,64,Math.toRadians(-90));
    private final Pose2d scan = new Pose2d(-36,48,Math.toRadians(-90));
    private final Pose2d highJuncLeft = new Pose2d(0,36,Math.toRadians(-45));
    private final Pose2d medJunc = new Pose2d(-24,36, Math.toRadians(-45));
    private final Pose2d midPoint = new Pose2d(-36,36,Math.toRadians(-90));
    private final Pose2d moveForward = new Pose2d(-24,32,Math.toRadians(-90));
    private final Pose2d highJuncRight = new Pose2d(-32,8,Math.toRadians(-45));
    private final Pose2d smallJuncLeft = new Pose2d(-32,56,Math.toRadians(-45));
    private final Pose2d smallJuncRight = new Pose2d(-40,32,Math.toRadians(-135));


    private final Pose2d park = new Pose2d();

    private Trajectory toScan, toLow, toMidPoint, toMed, toHigh, toCones, moveUp;


    public void runOpMode()
    {
        // Initialize Hardware
        SpaghettiHardware hardware = new SpaghettiHardware();
        hardware.init(hardwareMap);
        Functions function = new Functions(hardware);

        // Initialize Mecanum Drive
        drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(start);
        buildTrajectories();

        waitForStart();
        if(!opModeIsActive()) {return;}

        drive.followTrajectory(toMidPoint);

        function.wait(500,telemetry);

        drive.followTrajectory(toMed);

       // function.moveLift(function.liftPosition[2]);

        function.wait(500,telemetry);

        //drive.followTrajectory(moveUp);

        function.openClaw();

        function.wait(500,telemetry);
        
        function.closeClaw();

    }
    private void buildTrajectories()
    {

    toMidPoint = drive.trajectoryBuilder(start)
            .lineToLinearHeading(midPoint).build();

    toMed = drive.trajectoryBuilder(toMidPoint.end())
            .lineToLinearHeading(medJunc).build();

    moveUp = drive.trajectoryBuilder(toMed.end())
            .lineToLinearHeading(moveForward).build();
    }
}
