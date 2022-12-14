package org.firstinspires.ftc.team16911.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.internal.system.Assert;
import org.firstinspires.ftc.team16911.drive.SampleMecanumDrive;
import org.firstinspires.ftc.team16911.hardware.RigatoniHardware;

@Autonomous(name="BlueHome")
public class BlueHome extends LinearOpMode {
    private SampleMecanumDrive drive;
    private Utilities utilities;

    private final Pose2d blueHome = new Pose2d(-36, 60, 270);

    @Override
    public void runOpMode()
    {
        RigatoniHardware hardware = new RigatoniHardware(); //Horizontal Claw
        Assert.assertNotNull(hardwareMap);
        hardware.initializePrimaryMotors(hardwareMap);
        hardware.initializeClawServos(hardwareMap);
        hardware.initializeSupplementaryMotors(hardwareMap);

        utilities = new Utilities(hardware);
        drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(blueHome);
        buildTrajectories();




    }
    private void buildTrajectories()
    {

    }
}
