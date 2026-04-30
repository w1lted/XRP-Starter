package frc.robot;

import static edu.wpi.first.units.Units.Inches;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.xrp.XRPMotor;
import edu.wpi.first.wpilibj.xrp.XRPServo;


public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  
  private XRPMotor m_leftMotor = new XRPMotor(0);
  private XRPMotor m_rightMotor = new XRPMotor(1);
  private Encoder m_leftEncoder = new Encoder(4,5);
  private Encoder m_rightEncoder = new Encoder(6,7);
  private XRPServo m_servo = new XRPServo(4);
  private XboxController m_controller = new XboxController(0);
  private Ultrasonic m_ultrasonic = new Ultrasonic(2, 3);



  //constructor
  public Robot() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    
    m_rightMotor.setInverted(true);
  }

  public double getTicks(double inches){ 
    return (inches*78);
  }

  public double getAvgDistance(){  
    double left = m_leftEncoder.getDistance();
    double right = m_rightEncoder.getDistance();
    return (left + right) / 2.0;
  }

  public void drive(double speed){
    m_leftMotor.set(speed);
    m_rightMotor.set(speed);
  }

  public void turnLeft(double speed) {
    m_leftMotor.set(-speed);
    m_rightMotor.set(speed);
  }

  public void turnRight(double speed) {
    m_leftMotor.set(speed);
    m_rightMotor.set(-speed);
  }

  public void reset() { 
    m_leftEncoder.reset();
    m_rightEncoder.reset();
  }

  @Override
  public void robotPeriodic() {}


  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    reset();
    m_ultrasonic.setEnabled(true);
  }
  

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    // m_servo.setAngle(270);
    m_leftMotor.set((-m_controller.getLeftY()));
    m_rightMotor.set((-m_controller.getRightY()));
    
    System.out.println(m_ultrasonic.getRangeInches());

    /*if (m_ultrasonic.getRangeInches() < 1) {
      drive(-0.1);
      reset();
      if (getTicks(3) < getAvgDistance()) {  
        drive(0);
      }
    } */
  }
  } 
  

