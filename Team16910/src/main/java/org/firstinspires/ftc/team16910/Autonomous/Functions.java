package org.firstinspires.ftc.team16910.Autonomous;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.team16910.Hardware.SpaghettiHardware;

public class Functions
{
    final double halfClaw = 0.5;
    final double closeClaw = 1;
    private SpaghettiHardware hardware;

    Functions(SpaghettiHardware hardware)
    {
        this.hardware = hardware;
    }



    public void moveLift(int position)
    {
        hardware.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hardware.liftMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hardware.liftMotor3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hardware.liftMotor4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        hardware.liftMotor.setTargetPosition(hardware.liftMotor.getCurrentPosition() + position);
        hardware.liftMotor2.setTargetPosition(hardware.liftMotor.getCurrentPosition() + position);
        hardware.liftMotor3.setTargetPosition(hardware.liftMotor.getCurrentPosition() + position);
        hardware.liftMotor4.setTargetPosition(hardware.liftMotor.getCurrentPosition() + position);


        hardware.liftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        hardware.liftMotor2.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        hardware.liftMotor3.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        hardware.liftMotor4.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);


        hardware.liftMotor.setPower(1);
        hardware.liftMotor2.setPower(1);
        hardware.liftMotor3.setPower(1);
        hardware.liftMotor4.setPower(1);

    }
    public void openClaw()
    {
        hardware.leftClaw.setPosition(0);
        hardware.rightClaw.setPosition(0);
    }
    public void halfClaw()
    {
       hardware.leftClaw.setPosition(halfClaw);
       hardware.rightClaw.setPosition(halfClaw);
    }
    public void closeClaw()
    {
        hardware.leftClaw.setPosition(closeClaw);
        hardware.rightClaw.setPosition(closeClaw);
    }

    public void wait(int waitTime, Telemetry telemetry) {
        ElapsedTime time = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        time.reset();
        while (time.time() < waitTime) {
            telemetry.addData("Status", "Waiting");
            telemetry.addData("Wait Time", waitTime / 1000);
            telemetry.addData("Time Left", (waitTime - time.time()) / 1000);
            telemetry.update();
        }
    }

}
