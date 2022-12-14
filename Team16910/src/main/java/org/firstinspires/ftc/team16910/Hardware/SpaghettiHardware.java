package org.firstinspires.ftc.team16910.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.internal.system.Assert;


public class SpaghettiHardware
{
    public DcMotorEx leftFront;
    public DcMotorEx rightFront;
    public DcMotorEx leftRear;
    public DcMotorEx rightRear;
    public DcMotorEx liftMotor;
    public DcMotorEx liftMotor2;
    public Servo leftClaw;
    public Servo rightClaw;
    public Servo wheelServoL;
    public Servo wheelServoR;

    public DcMotorEx[] motors;
    public Servo[] servos;

    public void init(HardwareMap hardwareMap)
    {
        Assert.assertNotNull(hardwareMap);
        initializeDriveMotors(hardwareMap);
        initializeClawServos(hardwareMap);
        initializeLiftMotors(hardwareMap);
    }

    public void initializeDriveMotors(HardwareMap hardwareMap)
    {
        //drive motors
        leftFront = hardwareMap.get(DcMotorEx.class, SpaghettiID.LEFT_FRONT_MOTOR);
        rightFront = hardwareMap.get(DcMotorEx.class, SpaghettiID.RIGHT_FRONT_MOTOR);
        leftRear = hardwareMap.get(DcMotorEx.class, SpaghettiID.LEFT_REAR_MOTOR);
        rightRear = hardwareMap.get(DcMotorEx.class, SpaghettiID.RIGHT_REAR_MOTOR);

        motors = new DcMotorEx[]{leftFront, leftRear, rightFront, rightRear};

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightRear.setDirection(DcMotorSimple.Direction.FORWARD);


        for (DcMotorEx motor : motors) {
            motor.setPower(0.0);
            motor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
            motor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        }
    }
    public void initializeLiftMotors(HardwareMap hardwareMap)
    {
        liftMotor = hardwareMap.get(DcMotorEx.class, SpaghettiID.LIFT_MOTOR);
        liftMotor2 = hardwareMap.get(DcMotorEx.class, SpaghettiID.LIFT_MOTOR2);

        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // motors move in opposite direction
        liftMotor.setDirection(DcMotorEx.Direction.FORWARD);
        liftMotor2.setDirection(DcMotorEx.Direction.REVERSE);

        liftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor2.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        liftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        liftMotor.setPower(0);
        liftMotor2.setPower(0);

    }
    public void initializeClawServos(HardwareMap hardwareMap)
    {


        leftClaw = hardwareMap.get(Servo.class, SpaghettiID.LEFT_CLAW);
        rightClaw = hardwareMap.get(Servo.class, SpaghettiID.RIGHT_CLAW);
        wheelServoL = hardwareMap.get(Servo.class, SpaghettiID.WHEEL_SERVOL);
        wheelServoR = hardwareMap.get(Servo.class, SpaghettiID.WHEEL_SERVOR);

        wheelServoL.setDirection(Servo.Direction.FORWARD);
        wheelServoL.setPosition(.5);
        wheelServoR.setDirection(Servo.Direction.REVERSE);
        wheelServoR.setPosition(.5);
        leftClaw.setDirection(Servo.Direction.REVERSE);
        leftClaw.setPosition(0);
        rightClaw.setDirection(Servo.Direction.FORWARD);
        rightClaw.setPosition(0);
    }

}
