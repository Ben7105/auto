package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="LinearOOOPMODE")
public class LinearOop extends LinearOpMode {

    public DcMotor  LeftRear   = null;
    public DcMotor  LeftFront  = null;
    public DcMotor  RightFront = null;
    public DcMotor  RightRear  = null;


    @Override
public void runOpMode(){
//--------------------Hardware--------------------------------------------------------------------//
        LeftRear    = hardwareMap.dcMotor.get ("Back_Left_Drive");
        LeftFront   = hardwareMap.dcMotor.get ("Front_Left_Drive");
        RightFront  = hardwareMap.dcMotor.get ("Front_Right_Drive");
        RightRear   = hardwareMap.dcMotor.get ("Back_Right_Drive");

        LeftRear  .setDirection(DcMotor.Direction.FORWARD);
        LeftFront .setDirection(DcMotor.Direction.FORWARD);
        RightFront.setDirection(DcMotor.Direction.REVERSE);
        RightRear .setDirection(DcMotor.Direction.REVERSE);



        LeftRear  .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        LeftFront .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        RightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        RightRear .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//------------------ActaulAuto--------------------------------------------------------------------//
    waitForStart();

    Drive(.25,15);

        /*LeftFront .setPower(.25);
        LeftRear  .setPower(.25);
        RightFront.setPower(.25);
        RightRear .setPower(.25);
        sleep(1000);
        stop();*/
    }
//----------------------Methods-------------------------------------------------------------------//

    public void Drive(double power, double Inches)
    {
        power = Range.clip(Math.abs(power),0,1.0);
        int ticks = ConvertInchesToRotations(Math.abs(Inches));
        ResetEncoders();

        LeftFront .setPower(power);
        LeftRear  .setPower(power);
        RightFront.setPower(power);
        RightRear .setPower(power);

        while (Math.abs(LeftRear.getCurrentPosition()) < ticks
                && opModeIsActive()) {}

        Stop();//Stop is a method that is just all the motors to setpower(0) just for convience

    }


    public void ResetEncoders()
    {

        LeftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        LeftRear  .setMode(DcMotor.RunMode.RUN_USING_ENCODER);//RightOdem
        LeftFront .setMode(DcMotor.RunMode.RUN_USING_ENCODER);//MiddleOdem
        RightRear .setMode(DcMotor.RunMode.RUN_USING_ENCODER);//LeftOdem

    }
    public int ConvertInchesToRotations(double inches)
    {

        double WHEEL_CIRCUMFRANCE = 5.93;
        double ENCODER_TICKS_PER_ROTATION=2048;
        double Rotations = inches/WHEEL_CIRCUMFRANCE;
        return (int)(ENCODER_TICKS_PER_ROTATION*Rotations);

    }
    public void Stop()
    {

        LeftFront .setPower(0);
        LeftRear  .setPower(0);
        RightFront.setPower(0);
        RightRear .setPower(0);

    }
}

