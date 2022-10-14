package org.firstinspires.ftc.team16910.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team16910.Hardware.SpaghettiHardware;
import org.firstinspires.ftc.team16910.drive.SampleMecanumDrive;

@Autonomous(name= "RedAudience")
public class RedAudience extends LinearOpMode
{
    //blue on left side, red right
    private SampleMecanumDrive drive;

    private final Pose2d start = new Pose2d(-36,-64,Math.toRadians(90));
    private final Pose2d scan = new Pose2d(-36,-48,Math.toRadians(90));


    public void runOpMode()
    {
        // Initialize Hardware
        SpaghettiHardware hardware = new SpaghettiHardware();
        hardware.init(hardwareMap);


        // Initialize Mecanum Drive
        drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(new Pose2d());

        waitForStart();
    }
    private void buildTrajectories()
    {

    }
}
