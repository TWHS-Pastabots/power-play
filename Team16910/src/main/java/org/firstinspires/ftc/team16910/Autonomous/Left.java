package org.firstinspires.ftc.team16910.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team16910.Hardware.SpaghettiHardware;
import org.firstinspires.ftc.team16910.drive.SampleMecanumDrive;
import org.firstinspires.ftc.team16910.trajectorysequence.TrajectorySequence;

@Autonomous(name= "Left")
public class Left extends LinearOpMode
{
    // Audience- blue on left side, red right
    private SampleMecanumDrive drive;

    private final Pose2d start = new Pose2d(36,64,Math.toRadians(-90));

    private TrajectorySequence toScan, toLow, toMidPoint, toMidPointB, moveBackToJunc,
            toMed, toHigh, toHighB, toCones, moveUp, moveUpCone, moveBack, adjust;


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
        //7.5 rotations  537.6 ticks per revolution


        function.moveLift(3800);
        function.wait(4600, telemetry);

        function.halfClaw();
        function.moveLift(-3800);
        function.wait(4600, telemetry);

        // cycle one


        drive.followTrajectorySequence(adjust);

        function.halfClaw();

        function.wait(500,telemetry);

        function.closeClaw();


        /*


        function.wait(500,telemetry);

        drive.followTrajectory(toHigh);

        function.moveLift(3800);
        function.wait(4600, telemetry);

        drive.followTrajectory(moveUp);

        function.wait(500,telemetry);

        function.halfClaw();

        function.wait(500,telemetry);

        function.moveLift(-3800);
        function.wait(4400, telemetry);



         */










        // cycle two


        //parking

    }

    private void moveCycle(Functions function,int height)
    {
 /*
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

  */

    }

    private void buildTrajectories()
    {

        adjust = drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                .back(1)
                .build();

        /*
        toHigh = drive.trajectorySequenceBuilder(adjust.end())
                .forward()
                .turn(Math.toRadians(-45)) //right 45
                .build();

        moveUp = drive.trajectorySequenceBuilder(toHigh.end())
                .forward()
                .build();

        /*

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
