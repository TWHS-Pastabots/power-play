package org.firstinspires.ftc.team16910.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team16910.Hardware.SpaghettiHardware;
import org.firstinspires.ftc.team16910.drive.SampleMecanumDrive;


@Autonomous(name= "BlueAudience")
public class BlueAudience extends LinearOpMode
{
    private SampleMecanumDrive drive;


    public void runOpMode()
    {
        // Initialize Hardware
        SpaghettiHardware hardware = new SpaghettiHardware();
        hardware.init(hardwareMap);


        // Initialize Mecanum Drive
        drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(new Pose2d());
        buildTrajectories();
    }
    private void buildTrajectories()
    {

    }
}
