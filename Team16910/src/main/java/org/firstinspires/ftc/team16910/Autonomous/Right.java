package org.firstinspires.ftc.team16910.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team16910.Hardware.SpaghettiHardware;
import org.firstinspires.ftc.team16910.drive.SampleMecanumDrive;
import org.firstinspires.ftc.team16910.trajectorysequence.TrajectorySequence;

@Autonomous(name= "Right")
public class Right extends LinearOpMode
{
    // Audience- blue on left side, red right
    private SampleMecanumDrive drive;

    private final Pose2d start = new Pose2d(-36,64,Math.toRadians(-90));

    private TrajectorySequence toScan, toLow, toMidPoint, toMidPointB, moveBackToJunc,
            toMed, toHigh, toHighB, toCones, moveUp, moveUpCone, moveBack, adjust, adjust2;


    public void runOpMode() throws InterruptedException {
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


       // function.moveLift(3800);
        //function.wait(4600, telemetry);


        //function.halfClaw();
        //function.moveLift(-3800);
        //function.wait(4600, telemetry);


        // cycle one


        drive.followTrajectorySequence(adjust);
        function.halfClaw();
        drive.followTrajectorySequence(adjust2);
        function.wait(1000,telemetry);
        function.closeClaw();
        function.wait(1000,telemetry);



        drive.followTrajectorySequence(toHigh);

        function.moveLift(3800);
        function.wait(4600, telemetry);

        drive.followTrajectorySequence(moveUp);

        function.wait(1000, telemetry);

        function.openClaw();

        function.moveLift(-2600);
        function.wait(3300, telemetry);

        function.halfClaw();


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
                .forward(3.2)
                .build();

        adjust2 = drive.trajectorySequenceBuilder(adjust.end())
                .back(2.5)
                .build();

        toHigh = drive.trajectorySequenceBuilder(adjust2.end())
                .forward(50)
                .turn(Math.toRadians(42)) //left 45
                .build();

        moveUp = drive.trajectorySequenceBuilder(toHigh.end())
                .forward(6.5)
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
