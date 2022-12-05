package org.firstinspires.ftc.team16910.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team16910.Hardware.SpaghettiHardware;
import org.firstinspires.ftc.team16910.drive.SampleMecanumDrive;

@Autonomous(name= "Right")
public class Right extends LinearOpMode
{
    // Audience- blue on left side, red right
    private SampleMecanumDrive drive;

    private final String parkOneStr = "ParkOne";
    private final String parkTwoStr = "ParkTwo";
    private final String parkThreeStr = "ParkThree";

    private final Pose2d start = new Pose2d(-36,64,Math.toRadians(-90));
    private final Pose2d scan = new Pose2d(-36,48,Math.toRadians(-90));
    private final Pose2d highJuncLeft = new Pose2d(0,36,Math.toRadians(-90));
    private final Pose2d medJunc = new Pose2d(-24,40, Math.toRadians(-90));
    private final Pose2d midPoint = new Pose2d(-36,16,Math.toRadians(-90));
    private final Pose2d moveForward = new Pose2d(-24,10,Math.toRadians(-90));
    private final Pose2d hiJunR = new Pose2d(-24,16,Math.toRadians(-90));
    private final Pose2d smallJuncLeft = new Pose2d(-32,56,Math.toRadians(-90));
    private final Pose2d smallJuncRight = new Pose2d(-40,32,Math.toRadians(-90));
    private final Pose2d cones =  new Pose2d(-56,16,Math.toRadians(-90));
    private final Pose2d coneUp = new Pose2d(-62,16,Math.toRadians(90));


    private final Pose2d parkOne = new Pose2d();
    private final Pose2d parkTwo = new Pose2d();
    private final Pose2d parkThree = new Pose2d();

    int initLift;




    private Trajectory toScan, toLow, toMidPoint, toMidPointB, moveBackToJunc, toMed, toHigh, toHighB, toCones, moveUp, moveUpCone, moveBack;


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

        // test lift


        //function.moveLift();
        //wait(1000);
        //function.moveLift();





        // cycle one


        function.closeClaw();

        function.wait(500,telemetry);

        drive.followTrajectory(toHigh);

       // function.liftHigh();

        drive.followTrajectory(moveUp);

        function.wait(500,telemetry);

        function.openClaw();

        // cycle two


        //parking



    }

    private void moveCycle(Functions function,int height)
    {

        drive.followTrajectory(moveBack);

        // function.liftLow();

        drive.followTrajectory(toCones);

        //function.lift(height)   ///cone height //must be adjusted every cycle

        function.closeClaw();

        //function.lift //above cone height

        drive.followTrajectory(moveBackToJunc);

      //  function.lift //high junc height

        drive.followTrajectory(moveUpCone);

        function.openClaw();

    }

    private void buildTrajectories()
    {
/*
        toHigh = drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                .forward()
                .strafeLeft()
                .build();

        moveUp = drive.trajectorySequenceBuilder(toHigh.end())
                .forward()
                .build();

        moveBack = drive.trajectorySequenceBuilder(moveUp.end())
                .back()
                .build();

        toCones = drive.trajectorySequenceBuilder(moveBack.end())
                .turn(Math.toRadians(90))
                .forward()
                .strafeLeft() //slight adjustment to pick up cones
                .build();

        moveUpCone = drive.trajectorySequenceBuilder(toCones.end())
                .forward() //slight amount
                .build();

        moveBackToJunc = drive.trajectorySequenceBuilder(moveUpCone.end())
                .strafeRight() //slight amount to readjust
                .back() //till junction //lift will be lifted
                .turn(Math.toRadians(-90))
                .build();
                */


    }
}
