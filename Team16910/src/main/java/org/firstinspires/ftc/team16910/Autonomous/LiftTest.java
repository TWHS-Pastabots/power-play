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

@Autonomous(name= "LiftTest")
public class LiftTest extends LinearOpMode
{
    public void runOpMode() throws InterruptedException
    {
        // Initialize Hardware
        SpaghettiHardware hardware = new SpaghettiHardware();
        hardware.init(hardwareMap);
        Functions function = new Functions(hardware);

        waitForStart();
        if(!opModeIsActive()) {return;}

        function.wait(100, telemetry);

       // function.moveLift(-1300); //high from init
        // function.wait(3700, telemetry);

       // function.moveLift(100); //init to low
       // function.wait(630, telemetry);

         function.moveLift(1200); //high to cone
         function.wait(3550, telemetry);











    }
}

