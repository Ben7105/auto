package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp
public class TeleOop extends OpMode {

    public DcMotor  LeftRear   = null;
    public DcMotor  LeftFront  = null;
    public DcMotor  RightFront = null;
    public DcMotor  RightRear = null;


@Override
    public void init() {

     LeftRear    = hardwareMap.dcMotor.get ("Back_Left_Drive");
     LeftFront   = hardwareMap.dcMotor.get ("Front_Left_Drive");
     RightFront  = hardwareMap.dcMotor.get ("Front_Right_Drive");
     RightRear   = hardwareMap.dcMotor.get ("Back_Right_Drive");
    }

    @Override
    public void loop() {
    double Diameter = 1.88976;
    double Circumfrance = Diameter*3.14;
    double Drive  =-gamepad1.left_stick_y;
    double Strafe = gamepad1.left_stick_x;
    double Turn   = gamepad1.right_stick_x;
    double X =  (RightFront.getCurrentPosition()/537.6)/Circumfrance;
    double LY = (LeftRear.getCurrentPosition()/537.6)/Circumfrance;
    double RY = (RightRear.getCurrentPosition()/537.6)/Circumfrance;

        LeftFront.setPower(-Drive +Strafe -Turn);
        LeftRear.setPower(-Drive +Strafe +Turn);
        RightFront.setPower(+Drive +Strafe -Turn);
        RightRear.setPower(+Drive +Strafe +Turn);
        telemetry.addData("RY Odementry", RY);
        telemetry.addData("lY Odementry", LY);
        telemetry.addData("X Odementry", X);
    }

}
